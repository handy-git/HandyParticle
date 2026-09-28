package cn.handyplus.particle.api;

import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.wing.WingTypeEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
     * 启用翅膀或内置拖尾。
     *
     * @param player 玩家
     * @param type   翅膀组合或内置拖尾
     * @return 玩家在线且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Player player, @NotNull ParticleTypeEnum type) {
        Objects.requireNonNull(type, "type");
        WingTypeEnum wingType = type.getWingType();
        if (wingType != null) {
            return HandyParticleCore.setWings(player, wingType);
        }
        return HandyParticleCore.setTrail(player, Objects.requireNonNull(type.getBuiltinTrail(), "builtinTrail"));
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
