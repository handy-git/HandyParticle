package cn.handyplus.lib.particle;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.lib.particle.listener.PlayerQuitEventListener;
import cn.handyplus.lib.particle.trail.ParticleEmitter;
import cn.handyplus.lib.particle.trail.ParticleTrailOptions;
import cn.handyplus.lib.particle.trail.ParticleTrailPattern;
import cn.handyplus.lib.particle.trail.ParticleTrailTask;
import cn.handyplus.lib.particle.wing.ParticleWingTask;
import cn.handyplus.lib.particle.wing.ParticleWingTypeEnum;
import cn.handyplus.lib.util.BaseUtil;
import org.bukkit.Bukkit;
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
     * @param playerId 玩家 UUID
     * @param wingType 翅膀组合
     * @return 玩家在线且成功创建任务时返回 true
     */
    public static boolean enable(@NotNull UUID playerId, @NotNull ParticleWingTypeEnum wingType) {
        if (!BaseUtil.getOnlinePlayer(playerId).isPresent()) {
            return false;
        }
        start(playerId, new ParticleWingTask(playerId, wingType));
        return true;
    }

    /**
     * 使用默认参数启用玩家粒子轨迹。
     *
     * @param playerId 玩家 UUID
     * @param pattern  运动算法
     * @param emitter  粒子发射器
     * @return 玩家在线且任务创建成功时返回 true
     */
    public static boolean enable(@NotNull UUID playerId, @NotNull ParticleTrailPattern pattern, @NotNull ParticleEmitter emitter) {
        return enable(playerId, pattern, ParticleTrailOptions.defaults(), emitter);
    }

    /**
     * 启用玩家粒子轨迹。
     *
     * @param playerId 玩家 UUID
     * @param pattern  运动算法
     * @param options  轨迹参数
     * @param emitter  粒子发射器
     * @return 玩家在线且任务创建成功时返回 true
     */
    public static boolean enable(@NotNull UUID playerId, @NotNull ParticleTrailPattern pattern, @NotNull ParticleTrailOptions options, @NotNull ParticleEmitter emitter) {
        if (!BaseUtil.getOnlinePlayer(playerId).isPresent()) {
            return false;
        }
        start(playerId, new ParticleTrailTask(playerId, pattern, options, emitter));
        return true;
    }

    /**
     * 关闭指定玩家的粒子效果。
     *
     * @param playerId 玩家 UUID
     */
    public static void disable(@NotNull UUID playerId) {
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
    public static void disableAll() {
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
     * @param playerId 玩家 UUID
     * @param task     新任务
     */
    private static void start(@NotNull UUID playerId, @NotNull HandyRunnable task) {
        disable(playerId);
        TASK_MAP.put(playerId, task);
        HandySchedulerUtil.runTaskTimer(task, 1L, 2L);
    }

}
