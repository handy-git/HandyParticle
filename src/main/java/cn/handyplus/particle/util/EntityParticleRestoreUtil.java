package cn.handyplus.particle.util;

import cn.handyplus.lib.internal.RegionSchedulerUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleType;
import cn.handyplus.particle.entity.EntityParticleEntity;
import cn.handyplus.particle.service.EntityParticleService;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 实体粒子效果的延迟恢复。
 *
 * <p>实体所在区块未加载时 Bukkit 取不到实体，因此先把记录缓存在内存，
 * 等实体加载后再按实体 UUID 恢复，并补充扫描启动时已加载的区块。
 *
 * @author handy
 */
public final class EntityParticleRestoreUtil {

    /**
     * 工具类禁止实例化。
     */
    private EntityParticleRestoreUtil() {
    }

    /**
     * 待恢复的实体粒子效果，key 为实体 UUID。
     */
    private static final Map<UUID, List<ParticleType>> PENDING = new HashMap<>();

    /**
     * 读取并校验数据库中的实体粒子记录，供实体加载时恢复。
     */
    public static synchronized void load() {
        PENDING.clear();
        for (EntityParticleEntity saved : EntityParticleService.findAll()) {
            UUID entityUuid = saved.getEntityUuid();
            ParticleType particleType = ParticleRestoreUtil.parse("实体 " + entityUuid, saved.getCategory(), saved.getType(), saved.getColor(), saved.getMode());
            if (particleType == null) {
                // 不清理的话每次启动都会重复尝试同一条无效记录
                EntityParticleService.delete(entityUuid, saved.getCategory());
                continue;
            }
            PENDING.computeIfAbsent(entityUuid, ignored -> new ArrayList<>()).add(particleType);
        }
    }

    /**
     * 判断是否还有待恢复的实体粒子记录。
     *
     * @return 全部恢复完成时返回 true
     */
    public static synchronized boolean isDone() {
        return PENDING.isEmpty();
    }

    /**
     * 补充恢复插件启用前已经加载的区块，不主动加载其他区块。
     */
    public static void restoreLoadedChunks() {
        if (isDone()) {
            return;
        }
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                Location location = new Location(world, chunk.getX() << 4, 0, chunk.getZ() << 4);
                RegionSchedulerUtil.runTaskLater(location, () -> restore(chunk), 1L);
            }
        }
    }

    /**
     * 恢复区块内尚未恢复的实体粒子效果，须在实体所属线程调用。
     *
     * @param chunk 已加载的区块
     */
    public static synchronized void restore(@NotNull Chunk chunk) {
        if (PENDING.isEmpty() || !chunk.isLoaded()) {
            return;
        }
        for (Entity entity : chunk.getEntities()) {
            restore(entity);
        }
    }

    /**
     * 恢复已经进入世界的实体粒子，须在实体所属线程调用。
     *
     * @param entity 已加载的实体
     */
    public static synchronized void restore(@NotNull Entity entity) {
        // 核心会拒绝无效实体，此时必须保留记录等待真正加载完成。
        if (!entity.isValid()) {
            return;
        }
        UUID entityUuid = entity.getUniqueId();
        List<ParticleType> types = PENDING.get(entityUuid);
        if (types == null) {
            return;
        }
        for (ParticleType type : types) {
            HandyParticleApi.enable(entity, type);
        }
        PENDING.remove(entityUuid);
    }

}
