package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 使用预置粒子类型和指定运动模式的自定义拖尾。
 *
 * @author handy
 */
public final class CustomTrailParticle implements ParticleType {
    /**
     * 拖尾预置。
     */
    private final ParticleTypeEnum type;
    /**
     * 运动模式。
     */
    private final TrailModeTypeEnum mode;
    /**
     * Dust 粒子颜色，可为空。
     */
    private final Color color;

    /**
     * 创建指定运动模式和颜色的拖尾配置。
     *
     * @param type  拖尾预置
     * @param mode  运动模式
     * @param color Dust 颜色，非 Dust 拖尾必须为空
     */
    public CustomTrailParticle(@NotNull ParticleTypeEnum type, @NotNull TrailModeTypeEnum mode, @Nullable Color color) {
        if (type.getCategory() != ParticleCategoryTypeEnum.TRAIL) {
            throw new IllegalArgumentException("type must be a trail preset");
        }
        if (color != null && !type.supportsColor()) {
            throw new IllegalArgumentException("type does not support custom color");
        }
        this.type = type;
        this.mode = mode;
        this.color = color;
    }

    /**
     * 创建指定运动模式的拖尾配置。
     *
     * @param type 拖尾预置
     * @param mode 运动模式
     */
    public CustomTrailParticle(@NotNull ParticleTypeEnum type, @NotNull TrailModeTypeEnum mode) {
        this(type, mode, null);
    }

    /**
     * 创建使用默认运动模式的 Dust 颜色拖尾。
     *
     * @param type  Dust 拖尾预置
     * @param color Dust 颜色
     */
    public CustomTrailParticle(@NotNull ParticleTypeEnum type, @NotNull Color color) {
        this(type, TrailModeTypeEnum.DEFAULT, color);
    }

    /**
     * 获取当前效果所属的粒子类别。
     *
     * @return 粒子类别
     */
    @Override
    @NotNull
    public ParticleCategoryTypeEnum getCategory() {
        return this.type.getCategory();
    }

    /**
     * 启用自定义拖尾。
     *
     * @param entity 目标实体
     */
    @Override
    public native void enable(@NotNull Entity entity);

}
