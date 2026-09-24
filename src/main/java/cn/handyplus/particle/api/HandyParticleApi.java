package cn.handyplus.particle.api;

import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.trail.ParticleEmitter;
import cn.handyplus.particle.core.trail.ParticleTrailOptions;
import cn.handyplus.particle.core.trail.ParticleTrailPattern;
import cn.handyplus.particle.core.wing.ParticleWingTypeEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
     * 启用指定组合的粒子翅膀。
     *
     * @param player   玩家
     * @param wingType 翅膀组合
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleWingTypeEnum wingType) {
        return HandyParticleCore.enable(player, wingType);
    }

    /**
     * 使用默认参数和普通粒子启用玩家轨迹。
     *
     * @param player       玩家
     * @param pattern      运动算法
     * @param particleName XParticle 粒子名称
     * @return 玩家在线且已发起调度请求时返回 true
     * @throws IllegalArgumentException 粒子不存在、不受当前服务端支持或需要额外数据
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTrailPattern pattern, @NotNull String particleName) {
        return HandyParticleCore.enable(player, pattern, particleName);
    }

    /**
     * 使用默认参数启用玩家粒子轨迹。
     *
     * @param player  玩家
     * @param pattern 运动算法
     * @param emitter 粒子发射器
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTrailPattern pattern, @NotNull ParticleEmitter emitter) {
        return HandyParticleCore.enable(player, pattern, emitter);
    }

    /**
     * 启用玩家粒子轨迹。
     *
     * @param player  玩家
     * @param pattern 运动算法
     * @param options 轨迹参数
     * @param emitter 粒子发射器
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTrailPattern pattern, @NotNull ParticleTrailOptions options, @NotNull ParticleEmitter emitter) {
        return HandyParticleCore.enable(player, pattern, options, emitter);
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
