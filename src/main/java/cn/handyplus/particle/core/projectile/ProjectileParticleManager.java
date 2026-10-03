package cn.handyplus.particle.core.projectile;

import cn.handyplus.particle.api.ParticleTypeEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抛射物效果开关记录。
 *
 * <p>抛射物效果没有常驻任务，落库仅表示玩家开启了效果，
 * 运行时只查内存记录，避免每个抛射物都访问数据库。
 *
 * @author handy
 */
public final class ProjectileParticleManager {

    private ProjectileParticleManager() {
    }

    /**
     * 玩家抛射物效果记录，键为玩家 UUID。
     */
    private static final Map<UUID, ParticleTypeEnum> ENABLED_MAP = new ConcurrentHashMap<>();

    /**
     * 记录玩家已开启的抛射物效果。
     *
     * @param playerId 玩家 UUID
     * @param type     效果类型
     */
    public static void enable(@NotNull UUID playerId, @NotNull ParticleTypeEnum type) {
        ENABLED_MAP.put(playerId, type);
    }

    /**
     * 移除玩家的抛射物效果记录。
     *
     * @param playerId 玩家 UUID
     */
    public static void disable(@NotNull UUID playerId) {
        ENABLED_MAP.remove(playerId);
    }

    /**
     * 读取玩家已开启的抛射物效果。
     *
     * @param player 玩家
     * @return 效果类型，未开启时返回 null
     */
    @Nullable
    public static ParticleTypeEnum get(@NotNull Player player) {
        return ENABLED_MAP.get(player.getUniqueId());
    }

    /**
     * 清空全部记录，插件卸载时调用。
     */
    public static void clear() {
        ENABLED_MAP.clear();
    }

}
