package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 粒子效果统一 API。
 *
 * @author handy
 */
public final class HandyParticleApi {

    /**
     * 禁止实例化粒子 API。
     */
    private HandyParticleApi() {
    }

    /**
     * 启用粒子
     *
     * @param entity 实体
     * @param type   粒子类型
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Entity entity, @NotNull ParticleTypeEnum type) {
        return enable(entity, type, TrailModeTypeEnum.DEFAULT);
    }

    /**
     * 启用粒子
     *
     * @param entity 实体
     * @param type   粒子类型
     * @param mode   普通拖尾运动模式，为空时使用默认模式
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Entity entity, @NotNull ParticleTypeEnum type, @Nullable TrailModeTypeEnum mode) {
        Objects.requireNonNull(type, "type");
        // 翅膀
        if (type.getWingType() != null) {
            return HandyParticleCore.setWings(entity, type.getWingType());
        }
        // 方块粒子
        if (type.getBlockTrail() != null) {
            return HandyParticleCore.setBlock(entity, type.getBlockTrail());
        }
        // 物品雨粒子
        if (type.getItemRain() != null) {
            return HandyParticleCore.setItemRain(entity, type.getItemRain());
        }
        // 活动纸屑
        if (type.getEventConfetti() != null) {
            return HandyParticleCore.setConfetti(entity, type.getEventConfetti());
        }
        // 活动旋转色环
        if (type.getEventColorSpin() != null) {
            return HandyParticleCore.setColorSpin(entity, type.getEventColorSpin());
        }
        // 活动脚下圆圈
        if (type.getEventCircle() != null) {
            return HandyParticleCore.setCircle(entity, type.getEventCircle());
        }
        // 活动粒子精灵
        if (type.getEventFairy() != null) {
            return HandyParticleCore.setFairy(entity, type.getEventFairy());
        }
        // 普通粒子
        TrailTypeEnum builtinTrail = Objects.requireNonNull(type.getBuiltinTrail(), "builtinTrail");
        TrailModeTypeEnum trailMode = mode == null ? TrailModeTypeEnum.DEFAULT : mode;
        return HandyParticleCore.setTrail(entity, builtinTrail, trailMode);
    }

    /**
     * 关闭指定实体的粒子效果。
     *
     * @param entity 实体
     */
    public static void disable(@NotNull Entity entity) {
        HandyParticleCore.disable(entity);
    }

    /**
     * 关闭指定实体某一类别的粒子效果。
     *
     * @param entity   实体
     * @param category 粒子类别
     */
    public static void disable(@NotNull Entity entity, @NotNull ParticleCategoryTypeEnum category) {
        HandyParticleCore.disable(entity, category);
    }

    /**
     * 关闭全部粒子效果。
     */
    public static void disableAll() {
        HandyParticleCore.disableAll();
    }

}
