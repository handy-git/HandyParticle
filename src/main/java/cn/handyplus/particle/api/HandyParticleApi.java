package cn.handyplus.particle.api;

import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 玩家粒子效果统一 API。
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
     * @param player 玩家
     * @param type   粒子类型
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTypeEnum type) {
        return enable(player, type, TrailModeTypeEnum.DEFAULT);
    }

    /**
     * 启用粒子
     *
     * @param player 玩家
     * @param type   粒子类型
     * @param mode   普通拖尾运动模式，为空时使用默认模式
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTypeEnum type, @Nullable TrailModeTypeEnum mode) {
        Objects.requireNonNull(type, "type");
        // 翅膀
        if (type.getWingType() != null) {
            return HandyParticleCore.setWings(player, type.getWingType());
        }
        // 方块粒子
        if (type.getBlockTrail() != null) {
            return HandyParticleCore.setBlock(player, type.getBlockTrail());
        }
        // 物品雨粒子
        if (type.getItemRain() != null) {
            return HandyParticleCore.setItemRain(player, type.getItemRain());
        }
        // 活动纸屑
        if (type.getEventConfetti() != null) {
            return HandyParticleCore.setConfetti(player, type.getEventConfetti());
        }
        // 活动旋转色环
        if (type.getEventColorSpin() != null) {
            return HandyParticleCore.setColorSpin(player, type.getEventColorSpin());
        }
        // 活动粒子精灵
        if (type.getEventFairy() != null) {
            return HandyParticleCore.setFairy(player, type.getEventFairy());
        }
        // 普通粒子
        TrailTypeEnum builtinTrail = Objects.requireNonNull(type.getBuiltinTrail(), "builtinTrail");
        TrailModeTypeEnum trailMode = mode == null ? TrailModeTypeEnum.DEFAULT : mode;
        return HandyParticleCore.setTrail(player, builtinTrail, trailMode);
    }

    /**
     * 关闭指定玩家的粒子效果。
     *
     * @param player 玩家
     */
    public static void disable(@NotNull Player player) {
        HandyParticleCore.disable(player);
    }

    /**
     * 关闭全部粒子效果。
     */
    public static void disableAll() {
        HandyParticleCore.disableAll();
    }

}
