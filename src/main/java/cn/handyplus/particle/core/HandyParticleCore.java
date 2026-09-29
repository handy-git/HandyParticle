package cn.handyplus.particle.core;

import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.block.BlockTrailTask;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.event.EventColorSpinTask;
import cn.handyplus.particle.core.event.EventColorSpinTypeEnum;
import cn.handyplus.particle.core.event.EventConfettiTask;
import cn.handyplus.particle.core.event.EventConfettiTypeEnum;
import cn.handyplus.particle.core.event.EventFairyTask;
import cn.handyplus.particle.core.event.EventFairyTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTask;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTask;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingTask;
import cn.handyplus.particle.core.wing.WingTypeEnum;
import cn.handyplus.particle.util.ConfigUtil;
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
        start(player, new WingTask(player, wingType), ParticleCategoryTypeEnum.WING);
        return true;
    }

    /**
     * 启用指定运动模式的内置粒子拖尾。
     *
     * @param player 玩家
     * @param type   内置拖尾类型
     * @param mode   运动模式
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setTrail(@NotNull Player player, @NotNull TrailTypeEnum type, @NotNull TrailModeTypeEnum mode) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new TrailTask(player, type, mode), ParticleCategoryTypeEnum.TRAIL);
        return true;
    }

    /**
     * 触发玩家当前普通粒子拖尾的点击型运动。
     *
     * @param player 玩家
     */
    public static synchronized void triggerTrail(@NotNull Player player) {
        HandyRunnable task = TASK_MAP.get(player.getUniqueId());
        if (task instanceof TrailTask) {
            ((TrailTask) task).trigger();
        }
    }

    /**
     * 启用内置方块拖尾。
     *
     * @param player 玩家
     * @param type   方块拖尾类型
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setBlock(@NotNull Player player, @NotNull BlockTrailTypeEnum type) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new BlockTrailTask(player, type), ParticleCategoryTypeEnum.BLOCK);
        return true;
    }

    /**
     * 启用内置物品雨。
     *
     * @param player 玩家
     * @param type   物品雨类型
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setItemRain(@NotNull Player player, @NotNull ItemRainTypeEnum type) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new ItemRainTask(player, type), ParticleCategoryTypeEnum.RAIN);
        return true;
    }

    /**
     * 启用内置三色纸屑效果。
     *
     * @param player 玩家
     * @param type   纸屑染料组合
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setConfetti(@NotNull Player player, @NotNull EventConfettiTypeEnum type) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new EventConfettiTask(player, type), ParticleCategoryTypeEnum.EVENT);
        return true;
    }

    /**
     * 启用内置旋转色环效果。
     *
     * @param player 玩家
     * @param type   色环颜色
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setColorSpin(@NotNull Player player, @NotNull EventColorSpinTypeEnum type) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new EventColorSpinTask(player, type), ParticleCategoryTypeEnum.EVENT);
        return true;
    }

    /**
     * 启用内置粒子精灵效果。
     *
     * @param player 玩家
     * @param type   精灵颜色
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static synchronized boolean setFairy(@NotNull Player player, @NotNull EventFairyTypeEnum type) {
        if (!player.isOnline()) {
            return false;
        }
        start(player, new EventFairyTask(player, type), ParticleCategoryTypeEnum.EVENT);
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
     * @param category 粒子类别
     */
    private static void start(@NotNull Player player, @NotNull HandyRunnable task, @NotNull ParticleCategoryTypeEnum category) {
        UUID playerId = player.getUniqueId();
        disable(playerId);
        EntitySchedulerUtil.runTaskTimer(player, task, 1L, ConfigUtil.getRefreshInterval(category));
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
        if (task instanceof BlockTrailTask) {
            ((BlockTrailTask) task).cleanup();
        }
        try {
            task.cancel();
        } catch (IllegalStateException ignored) {
            // 任务可能在调度器完成绑定前被替换。
        }
    }

}
