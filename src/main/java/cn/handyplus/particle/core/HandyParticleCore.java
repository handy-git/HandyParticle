package cn.handyplus.particle.core;

import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleConstants;
import cn.handyplus.particle.core.block.BlockTrailTask;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.event.EventCircleTask;
import cn.handyplus.particle.core.event.EventCircleTypeEnum;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 实体粒子效果统一。
 *
 * @author handy
 */
public class HandyParticleCore {

    /**
     * 实体当前启用的粒子任务。
     */
    private static final Map<UUID, Map<ParticleCategoryTypeEnum, HandyRunnable>> TASK_MAP = new HashMap<>();

    /**
     * 禁止实例化
     */
    private HandyParticleCore() {
    }

    /**
     * 启用指定实体的粒子翅膀。
     *
     * @param entity   实体
     * @param wingType 翅膀组合
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setWings(@NotNull Entity entity, @NotNull WingTypeEnum wingType) {
        return start(entity, new WingTask(entity, wingType), ParticleCategoryTypeEnum.WING);
    }

    /**
     * 启用指定实体的内置粒子拖尾。
     *
     * @param entity 实体
     * @param type   内置拖尾类型
     * @param mode   运动模式
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setTrail(@NotNull Entity entity, @NotNull TrailTypeEnum type, @NotNull TrailModeTypeEnum mode) {
        return start(entity, new TrailTask(entity, type, mode), ParticleCategoryTypeEnum.TRAIL);
    }

    /**
     * 触发玩家当前普通粒子拖尾的点击型运动。
     *
     * @param player 玩家
     */
    public static synchronized void triggerTrail(@NotNull Player player) {
        Map<ParticleCategoryTypeEnum, HandyRunnable> tasks = TASK_MAP.get(player.getUniqueId());
        if (tasks == null) {
            return;
        }
        HandyRunnable task = tasks.get(ParticleCategoryTypeEnum.TRAIL);
        if (task instanceof TrailTask) {
            ((TrailTask) task).trigger();
        }
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
     * 启用指定实体的三色纸屑效果。
     *
     * @param entity 实体
     * @param type   纸屑染料组合
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setConfetti(@NotNull Entity entity, @NotNull EventConfettiTypeEnum type) {
        return start(entity, new EventConfettiTask(entity, type), ParticleCategoryTypeEnum.EVENT);
    }

    /**
     * 启用指定实体的旋转色环效果。
     *
     * @param entity 实体
     * @param type   色环颜色
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setColorSpin(@NotNull Entity entity, @NotNull EventColorSpinTypeEnum type) {
        return start(entity, new EventColorSpinTask(entity, type), ParticleCategoryTypeEnum.EVENT);
    }

    /**
     * 启用指定实体的脚下圆圈效果。
     *
     * @param entity 实体
     * @param type   圆圈颜色
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setCircle(@NotNull Entity entity, @NotNull EventCircleTypeEnum type) {
        return start(entity, new EventCircleTask(entity, type), ParticleCategoryTypeEnum.EVENT);
    }

    /**
     * 启用指定实体的粒子精灵效果。
     *
     * @param entity 实体
     * @param type   精灵颜色
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static synchronized boolean setFairy(@NotNull Entity entity, @NotNull EventFairyTypeEnum type) {
        return start(entity, new EventFairyTask(entity, type), ParticleCategoryTypeEnum.EVENT);
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
    private static boolean start(@NotNull Entity entity, @NotNull HandyRunnable task,
                                 @NotNull ParticleCategoryTypeEnum category) {
        if (!entity.isValid() || (entity instanceof Player && !((Player) entity).isOnline())) {
            return false;
        }
        UUID entityId = entity.getUniqueId();
        // 同一实体同一类别只保留一个任务，重新设置时先停止旧任务，避免重复刷新粒子。
        Map<ParticleCategoryTypeEnum, HandyRunnable> tasks = TASK_MAP.computeIfAbsent(entityId, ignored -> new HashMap<>());
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
