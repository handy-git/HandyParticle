package cn.handyplus.lib.particle;

import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.lib.particle.listener.PlayerQuitEventListener;
import cn.handyplus.lib.particle.trail.ParticleEmitter;
import cn.handyplus.lib.particle.trail.ParticleTrailOptions;
import cn.handyplus.lib.particle.trail.ParticleTrailPattern;
import cn.handyplus.lib.particle.trail.ParticleTrailTask;
import cn.handyplus.lib.particle.wing.ParticleWingTask;
import cn.handyplus.lib.particle.wing.ParticleWingTypeEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 玩家粒子效果统一 API。
 *
 * @author handy
 */
public final class HandyParticleApi {
    /**
     * 玩家当前启用的粒子任务。
     */
    private static final Map<UUID, HandyRunnable> TASK_MAP = new HashMap<>();
    /**
     * 玩家退出事件监听器。
     */
    private static PlayerQuitEventListener listener;
    /**
     * 模块是否已初始化。
     */
    private static boolean initialized;

    /**
     * 禁止实例化粒子 API。
     */
    private HandyParticleApi() {
    }

    /**
     * 初始化粒子效果模块。
     *
     * @param plugin 插件
     */
    public static synchronized void init(@NotNull Plugin plugin) {
        if (initialized) {
            return;
        }
        listener = new PlayerQuitEventListener();
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        initialized = true;
    }

    /**
     * 启用指定组合的粒子翅膀。
     *
     * @param player   玩家
     * @param wingType 翅膀组合
     * @return 模块已初始化、玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean enable(@NotNull Player player, @NotNull ParticleWingTypeEnum wingType) {
        if (!initialized || !player.isOnline()) {
            return false;
        }
        start(player, new ParticleWingTask(player, wingType));
        return true;
    }

    /**
     * 使用默认参数启用玩家粒子轨迹。
     *
     * @param player  玩家
     * @param pattern 运动算法
     * @param emitter 粒子发射器
     * @return 模块已初始化、玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTrailPattern pattern, @NotNull ParticleEmitter emitter) {
        return enable(player, pattern, ParticleTrailOptions.defaults(), emitter);
    }

    /**
     * 启用玩家粒子轨迹。
     *
     * @param player  玩家
     * @param pattern 运动算法
     * @param options 轨迹参数
     * @param emitter 粒子发射器
     * @return 模块已初始化、玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean enable(@NotNull Player player, @NotNull ParticleTrailPattern pattern, @NotNull ParticleTrailOptions options, @NotNull ParticleEmitter emitter) {
        if (!initialized || !player.isOnline()) {
            return false;
        }
        start(player, new ParticleTrailTask(player, pattern, options, emitter));
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

    /**
     * 关闭全部粒子效果。
     */
    public static synchronized void disableAll() {
        for (UUID playerId : new ArrayList<>(TASK_MAP.keySet())) {
            disable(playerId);
        }
    }

    /**
     * 关闭并重置粒子效果模块。
     */
    public static synchronized void shutdown() {
        disableAll();
        if (listener != null) {
            HandlerList.unregisterAll(listener);
            listener = null;
        }
        initialized = false;
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

}
