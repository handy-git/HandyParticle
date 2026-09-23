package cn.handyplus.lib.particle;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.lib.util.BaseUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 粒子API
 *
 * @author handy
 */
public final class ParticleWingApi {
    private static final Map<UUID, HandyRunnable> TASK_MAP = new HashMap<>();
    private static boolean initialized;

    private ParticleWingApi() {
    }

    /**
     * 初始化粒子翅膀模块。
     *
     * @param plugin 插件
     */
    public static synchronized void init(@NotNull Plugin plugin) {
        if (initialized) {
            return;
        }
        Bukkit.getPluginManager().registerEvents(new ParticleWingListener(), plugin);
        initialized = true;
    }

    /**
     * 启用指定组合的粒子翅膀。
     *
     * @param playerId 玩家 UUID
     * @param wingType 翅膀组合
     * @return 玩家在线且成功创建任务时返回 true
     */
    public static boolean enable(@NotNull UUID playerId, @NotNull ParticleWingType wingType) {
        if (!BaseUtil.getOnlinePlayer(playerId).isPresent()) {
            return false;
        }
        disable(playerId);
        ParticleWingTask task = new ParticleWingTask(playerId, wingType);
        TASK_MAP.put(playerId, task);
        HandySchedulerUtil.runTaskTimer(task, 1L, 2L);
        return true;
    }

    /**
     * 关闭指定玩家的粒子翅膀。
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
     * 关闭全部粒子翅膀。
     */
    public static void disableAll() {
        for (UUID playerId : TASK_MAP.keySet()) {
            disable(playerId);
        }
    }

    /**
     * 关闭并重置粒子翅膀模块。
     */
    public static synchronized void shutdown() {
        disableAll();
        initialized = false;
    }

}
