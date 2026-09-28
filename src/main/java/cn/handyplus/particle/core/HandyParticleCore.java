package cn.handyplus.particle.core;

import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.trail.TrailTask;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingTask;
import cn.handyplus.particle.core.wing.WingTypeEnum;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 玩家粒子效果统一
 *
 * @author handy
 */
public class HandyParticleCore {

    /**
     * 玩家当前启用的粒子任务。
     */
    private static final Map<UUID, HandyRunnable> TASK_MAP = new HashMap<>();

    /**
     * 禁止实例化
     */
    private HandyParticleCore() {
    }

    /**
     * 启用指定组合的粒子翅膀。
     *
     * @param player   玩家
     * @param wingType 翅膀组合
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setWings(@NotNull Player player, @NotNull WingTypeEnum wingType) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new WingTask(player, wingType));
        return true;
    }

    /**
     * 启用固定参数的内置粒子拖尾。
     *
     * @param player 玩家
     * @param type   内置拖尾类型
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setTrail(@NotNull Player player, @NotNull TrailTypeEnum type) {
        Particle particle = type.resolveParticle();
        if (!player.isOnline()) {
            return false;
        }
        start(player, new TrailTask(player, type, particle));
        return true;
    }

    /**
     * 关闭指定玩家的粒子效果。
     *
     * @param player 玩家
     */
    public static synchronized void disable(@NotNull Player player) {
        disable(player.getUniqueId());
    }

    /**
     * 关闭全部粒子效果。
     */
    public static synchronized void disableAll() {
        for (UUID playerId : new ArrayList<>(TASK_MAP.keySet())) {
            disable(playerId);
        }
    }

    /**
     * 替换并启动玩家的粒子任务。
     *
     * @param player 玩家
     * @param task   新任务
     */
    private static void start(@NotNull Player player, @NotNull HandyRunnable task) {
        UUID playerId = player.getUniqueId();
        disable(playerId);
        EntitySchedulerUtil.runTaskTimer(player, task, 1L, 2L);
        TASK_MAP.put(playerId, task);
    }

    /**
     * 按玩家 UUID 清理内部任务。
     *
     * @param playerId 玩家 UUID
     */
    private static void disable(@NotNull UUID playerId) {
        HandyRunnable task = TASK_MAP.remove(playerId);
        if (task == null) {
            return;
        }
        try {
            task.cancel();
        } catch (IllegalStateException ignored) {
            // 任务可能在调度器完成绑定前被替换。
        }
    }

}
