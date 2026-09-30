package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

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
     * 启用指定效果。
     *
     * @param entity 目标实体
     * @param type   粒子效果
     * @return 实体有效且已发起调度请求时返回 true
     */
    public static boolean enable(@NotNull Entity entity, @NotNull ParticleType type) {
        return type.enable(entity);
    }

    /**
     * 关闭指定实体的全部粒子效果。
     *
     * @param entity 目标实体
     */
    public static void disable(@NotNull Entity entity) {
        HandyParticleCore.disable(entity);
    }

    /**
     * 关闭指定实体某一类别的粒子效果。
     *
     * @param entity   目标实体
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
