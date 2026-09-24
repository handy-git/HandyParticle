package cn.handyplus.particle.api;

import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.wing.ParticleWingTypeEnum;
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
     * 启用翅膀；轨迹类型因缺少粒子名称会抛出异常。
     *
     * @param player 玩家
     * @param type   翅膀组合或轨迹算法
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTypeEnum type) {
        return enable(player, type, null);
    }

    /**
     * 启用翅膀或指定粒子的轨迹；翅膀类型忽略粒子名称。
     *
     * @param player       玩家
     * @param type         翅膀组合或轨迹算法
     * @param particleName XParticle 粒子名称；翅膀类型可为空
     * @return 玩家在线且已发起调度请求时返回 true
     * @throws IllegalArgumentException 粒子不存在、不受当前服务端支持或需要额外数据
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTypeEnum type, @Nullable String particleName) {
        Objects.requireNonNull(type, "type");
        ParticleWingTypeEnum wingType = type.getWingType();
        if (wingType != null) {
            return HandyParticleCore.setWings(player, wingType);
        }
        if (particleName == null) {
            throw new IllegalArgumentException("Particle name is required for trails");
        }
        return HandyParticleCore.setTrail(player, Objects.requireNonNull(type.getTrailPattern(), "trailPattern"), particleName);
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
