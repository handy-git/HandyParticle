package cn.handyplus.particle.util;

import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.lib.internal.RegionSchedulerUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleType;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.entity.EntityParticleEntity;
import cn.handyplus.particle.service.EntityParticleService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 实体粒子效果的延迟恢复。
 *
 * <p>实体所在区块未加载时 Bukkit 取不到实体，因此先把记录缓存在内存，
 * 等实体进入世界后按 UUID 恢复，设置保留到管理员清除。
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
     * 已保存的分类粒子效果，key 为实体 UUID。
     */
    private static final Map<UUID, Map<ParticleCategoryTypeEnum, ParticleType>> SAVED = new HashMap<>();

    /**
     * 读取并校验数据库中的实体粒子记录，供实体加载时恢复。
     */
    public static synchronized void load() {
        SAVED.clear();
        for (EntityParticleEntity saved : EntityParticleService.findAll()) {
            if (!cache(saved)) {
                // 不清理的话每次启动都会重复尝试同一条无效记录
                EntityParticleService.delete(saved.getEntityUuid(), saved.getCategory());
            }
        }
        // 只执行一次，补齐监听注册前已经进入世界的实体。
        HandySchedulerUtil.runTask(EntityParticleRestoreUtil::restoreLoaded);
    }

    /**
     * 一次性按保存的 UUID 恢复已加载实体，不扫描或加载区块。
     */
    private static synchronized void restoreLoaded() {
        for (UUID entityUuid : SAVED.keySet()) {
            Entity entity = Bukkit.getEntity(entityUuid);
            if (entity == null) {
                continue;
            }
            if (HandySchedulerUtil.isFolia()) {
                RegionSchedulerUtil.runTask(entity.getLocation(), () -> restore(entity));
            } else {
                restore(entity);
            }
        }
    }

    /**
     * 恢复有效实体的设置，须在实体所属线程调用。
     *
     * @param entity 已进入世界的实体
     */
    public static synchronized void restore(@NotNull Entity entity) {
        Map<ParticleCategoryTypeEnum, ParticleType> types = SAVED.get(entity.getUniqueId());
        if (types == null || !entity.isValid()) {
            return;
        }
        for (ParticleType type : types.values()) {
            HandyParticleApi.enable(entity, type);
        }
    }

    /**
     * 停止离开世界的已配置实体任务，保留设置供再次加载时恢复。
     *
     * @param entity 离开世界的实体
     */
    public static synchronized void unload(@NotNull Entity entity) {
        if (SAVED.containsKey(entity.getUniqueId())) {
            HandyParticleApi.disable(entity);
        }
    }

    /**
     * 同步成功写入数据库的实体设置。
     *
     * @param saved 已保存的设置
     * @return 设置有效且已写入缓存时返回 true
     */
    public static synchronized boolean cache(@NotNull EntityParticleEntity saved) {
        UUID entityUuid = saved.getEntityUuid();
        ParticleType type = ParticleRestoreUtil.parse("实体 " + entityUuid, saved.getCategory(), saved.getType(), saved.getColor(), saved.getMode());
        if (type == null) {
            return false;
        }
        SAVED.computeIfAbsent(entityUuid, ignored -> new EnumMap<>(ParticleCategoryTypeEnum.class)).put(type.getCategory(), type);
        return true;
    }

    /**
     * 同步成功删除的实体设置，避免重新加载时恢复旧效果。
     *
     * @param entityUuid 实体 UUID
     * @param category 删除的类别，为空时删除全部设置
     */
    public static synchronized void remove(@NotNull UUID entityUuid, @Nullable String category) {
        if (category == null) {
            SAVED.remove(entityUuid);
            return;
        }
        Map<ParticleCategoryTypeEnum, ParticleType> types = SAVED.get(entityUuid);
        if (types != null) {
            types.remove(ParticleCategoryTypeEnum.getEnum(category));
            if (types.isEmpty()) {
                SAVED.remove(entityUuid);
            }
        }
    }

}
