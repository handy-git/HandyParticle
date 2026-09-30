package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * 对外暴露的粒子效果。
 *
 * @author handy
 */
public interface ParticleType {

    /**
     * 获取当前效果所属的粒子类别。
     *
     * @return 粒子类别
     */
    @NotNull
    ParticleCategoryTypeEnum getCategory();

    /**
     * 启用当前粒子效果。
     *
     * @param entity 目标实体
     * @return 实体有效且已发起调度请求时返回 true
     */
    boolean enable(@NotNull Entity entity);

    /**
     * 关闭目标实体在当前效果所属类别下的粒子效果。
     *
     * <p>关闭的是类别而非具体效果，若该类别期间已被其他效果替换，同样会被关闭。
     *
     * @param entity 目标实体
     */
    default void disable(@NotNull Entity entity) {
        HandyParticleCore.disable(entity, this.getCategory());
    }

}
