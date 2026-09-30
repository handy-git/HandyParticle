package cn.handyplus.particle.core;

import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleConstants;
import cn.handyplus.particle.core.block.BlockTrailTask;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.confetti.ConfettiTask;
import cn.handyplus.particle.core.confetti.ConfettiTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTask;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTask;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.core.wing.WingTask;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 实体粒子效果统一。
 *
 * @author handy
 */
public final class HandyParticleCore {

    /**
     * 工具类禁止实例化。
     */
    private HandyParticleCore() {
    }

    /**
     * 实体当前启用的粒子任务。
     */
    private static final Map<UUID, Map<ParticleCategoryTypeEnum, HandyRunnable>> TASK_MAP = new HashMap<>();

    /**
     * 启用指定外形和颜色图案的翅膀。
     *
     * @param entity 实体
     * @param model  翅膀外形
     * @param color  颜色图案
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setWings(@NotNull Entity entity, @NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        return start(entity, new WingTask(entity, model, color), ParticleCategoryTypeEnum.WING);
    }

    /**
     * 启用指定实体的内置粒子拖尾。
     *
     * @param entity 实体
     * @param type   内置拖尾类型
     * @param mode   运动模式
     * @param color  自定义 Dust 颜色，其他类型忽略
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setTrail(@NotNull Entity entity, @NotNull TrailTypeEnum type, @NotNull TrailModeTypeEnum mode, Color color) {
        return start(entity, new TrailTask(entity, type, mode, color), ParticleCategoryTypeEnum.TRAIL);
    }

    /**
     * 启用指定实体的方块拖尾。
     *
     * @param entity 实体
     * @param type   方块拖尾类型
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setBlock(@NotNull Entity entity, @NotNull BlockTrailTypeEnum type) {
        return start(entity, new BlockTrailTask(entity, type), ParticleCategoryTypeEnum.BLOCK);
    }

    /**
     * 启用指定实体的物品雨。
     *
     * @param entity 实体
     * @param type   物品雨类型
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setItemRain(@NotNull Entity entity, @NotNull ItemRainTypeEnum type) {
        return start(entity, new ItemRainTask(entity, type), ParticleCategoryTypeEnum.RAIN);
    }

    /**
     * 启用指定实体的纸屑效果。
     *
     * @param entity 实体
     * @param type   纸屑物品组合
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setConfetti(@NotNull Entity entity, @NotNull ConfettiTypeEnum type) {
        return start(entity, new ConfettiTask(entity, type), ParticleCategoryTypeEnum.CONFETTI);
    }

    /**
     * 关闭指定实体的粒子效果。
     *
     * @param entity 实体
     */
    public static synchronized void disable(@NotNull Entity entity) {
        disable(entity.getUniqueId());
    }

    /**
     * 关闭指定实体某一类别的粒子效果。
     *
     * @param entity   实体
     * @param category 粒子类别
     */
    public static synchronized void disable(@NotNull Entity entity, @NotNull ParticleCategoryTypeEnum category) {
        Map<ParticleCategoryTypeEnum, HandyRunnable> tasks = TASK_MAP.get(entity.getUniqueId());
        if (tasks == null) {
            return;
        }
        stop(tasks.remove(category));
        if (tasks.isEmpty()) {
            TASK_MAP.remove(entity.getUniqueId());
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
     * 启动实体的粒子任务，同类别效果会替换旧任务。
     *
     * @param entity   实体
     * @param task     新任务
     * @param category 粒子类别
     */
    private static boolean start(@NotNull Entity entity, @NotNull HandyRunnable task, @NotNull ParticleCategoryTypeEnum category) {
        if (!entity.isValid()) {
            return false;
        }
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (!player.isOnline()) {
                return false;
            }
        }
        UUID entityId = entity.getUniqueId();
        // 同一实体同一类别只保留一个任务，重新设置时先停止旧任务，避免重复刷新粒子。
        Map<ParticleCategoryTypeEnum, HandyRunnable> tasks = TASK_MAP.computeIfAbsent(entityId, ignored -> new EnumMap<>(ParticleCategoryTypeEnum.class));
        stop(tasks.put(category, task));
        Long period = ParticleConstants.REFRESH_INTERVALS.getOrDefault(category, 2L);
        // 使用实体调度器绑定任务生命周期，实体失效后由调度器自动终止执行。
        EntitySchedulerUtil.runTaskTimer(entity, task, 1L, period);
        return true;
    }

    /**
     * 按实体 UUID 清理内部任务。
     *
     * @param playerId 待清理任务所属实体的 UUID
     */
    private static void disable(@NotNull UUID playerId) {
        Map<ParticleCategoryTypeEnum, HandyRunnable> tasks = TASK_MAP.remove(playerId);
        if (tasks == null) {
            return;
        }
        for (HandyRunnable task : tasks.values()) {
            stop(task);
        }
    }

    /**
     * 停止单个粒子任务并清理其临时数据。
     *
     * @param task 待停止任务
     */
    private static void stop(HandyRunnable task) {
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
