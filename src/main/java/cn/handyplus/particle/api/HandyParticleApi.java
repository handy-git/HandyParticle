package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

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
     * 启用指定配置的粒子效果。
     *
     * @param entity 实体
     * @param type   粒子配置
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Entity entity, @NotNull ParticleType type) {
        Objects.requireNonNull(type, "type");
        if (type.getWingModel() != null) {
            return HandyParticleCore.setWings(entity, type.getWingModel(), Objects.requireNonNull(type.getWingColor(), "wingColor"));
        }
        // 方块粒子
        if (type.getBlockTrail() != null) {
            return HandyParticleCore.setBlock(entity, type.getBlockTrail());
        }
        // 物品雨粒子
        if (type.getItemRain() != null) {
            return HandyParticleCore.setItemRain(entity, type.getItemRain());
        }
        // 纸屑
        if (type.getConfetti() != null) {
            return HandyParticleCore.setConfetti(entity, type.getConfetti());
        }
        // 拖尾粒子
        return HandyParticleCore.setTrail(entity, Objects.requireNonNull(type.getBuiltinTrail(), "builtinTrail"), Objects.requireNonNull(type.getMode(), "mode"), type.getColor());
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
