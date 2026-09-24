package cn.handyplus.particle.api;

import cn.handyplus.particle.core.trail.ParticleTrailPatternEnum;
import cn.handyplus.particle.core.wing.ParticleWingTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 可启用的翅膀组合与轨迹算法。
 *
 * @author handy
 */
public enum ParticleTypeEnum {
    /**
     * 天使渐变一翅膀。
     */
    ANGEL_GRADIENT1(ParticleWingTypeEnum.ANGEL_GRADIENT1),
    /**
     * 天使渐变二翅膀。
     */
    ANGEL_GRADIENT2(ParticleWingTypeEnum.ANGEL_GRADIENT2),
    /**
     * 天使渐变三翅膀。
     */
    ANGEL_GRADIENT3(ParticleWingTypeEnum.ANGEL_GRADIENT3),
    /**
     * 天使蘑菇翅膀。
     */
    ANGEL_MUSHROOM(ParticleWingTypeEnum.ANGEL_MUSHROOM),
    /**
     * 天使火焰翅膀。
     */
    ANGEL_FIRE(ParticleWingTypeEnum.ANGEL_FIRE),
    /**
     * 天使彩色翅膀。
     */
    ANGEL_COLORS(ParticleWingTypeEnum.ANGEL_COLORS),
    /**
     * 天使飞行翅膀。
     */
    ANGEL_FLY(ParticleWingTypeEnum.ANGEL_FLY),
    /**
     * 蝴蝶渐变一翅膀。
     */
    BUTTERFLY_GRADIENT1(ParticleWingTypeEnum.BUTTERFLY_GRADIENT1),
    /**
     * 蝴蝶渐变二翅膀。
     */
    BUTTERFLY_GRADIENT2(ParticleWingTypeEnum.BUTTERFLY_GRADIENT2),
    /**
     * 蝴蝶渐变三翅膀。
     */
    BUTTERFLY_GRADIENT3(ParticleWingTypeEnum.BUTTERFLY_GRADIENT3),
    /**
     * 蝴蝶蘑菇翅膀。
     */
    BUTTERFLY_MUSHROOM(ParticleWingTypeEnum.BUTTERFLY_MUSHROOM),
    /**
     * 蝴蝶火焰翅膀。
     */
    BUTTERFLY_FIRE(ParticleWingTypeEnum.BUTTERFLY_FIRE),
    /**
     * 蝴蝶彩色翅膀。
     */
    BUTTERFLY_COLORS(ParticleWingTypeEnum.BUTTERFLY_COLORS),
    /**
     * 蝴蝶飞行翅膀。
     */
    BUTTERFLY_FLY(ParticleWingTypeEnum.BUTTERFLY_FLY),
    /**
     * 精灵渐变一翅膀。
     */
    ELF_GRADIENT1(ParticleWingTypeEnum.ELF_GRADIENT1),
    /**
     * 精灵渐变二翅膀。
     */
    ELF_GRADIENT2(ParticleWingTypeEnum.ELF_GRADIENT2),
    /**
     * 精灵渐变三翅膀。
     */
    ELF_GRADIENT3(ParticleWingTypeEnum.ELF_GRADIENT3),
    /**
     * 精灵蘑菇翅膀。
     */
    ELF_MUSHROOM(ParticleWingTypeEnum.ELF_MUSHROOM),
    /**
     * 精灵火焰翅膀。
     */
    ELF_FIRE(ParticleWingTypeEnum.ELF_FIRE),
    /**
     * 精灵彩色翅膀。
     */
    ELF_COLORS(ParticleWingTypeEnum.ELF_COLORS),
    /**
     * 精灵飞行翅膀。
     */
    ELF_FLY(ParticleWingTypeEnum.ELF_FLY),
    /**
     * 长翼渐变一翅膀。
     */
    LONG_GRADIENT1(ParticleWingTypeEnum.LONG_GRADIENT1),
    /**
     * 长翼渐变二翅膀。
     */
    LONG_GRADIENT2(ParticleWingTypeEnum.LONG_GRADIENT2),
    /**
     * 长翼渐变三翅膀。
     */
    LONG_GRADIENT3(ParticleWingTypeEnum.LONG_GRADIENT3),
    /**
     * 长翼蘑菇翅膀。
     */
    LONG_MUSHROOM(ParticleWingTypeEnum.LONG_MUSHROOM),
    /**
     * 长翼火焰翅膀。
     */
    LONG_FIRE(ParticleWingTypeEnum.LONG_FIRE),
    /**
     * 长翼彩色翅膀。
     */
    LONG_COLORS(ParticleWingTypeEnum.LONG_COLORS),
    /**
     * 长翼飞行翅膀。
     */
    LONG_FLY(ParticleWingTypeEnum.LONG_FLY),
    /**
     * 蝴蝶二渐变一翅膀。
     */
    BUTTERFLY2_GRADIENT1(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT1),
    /**
     * 蝴蝶二渐变二翅膀。
     */
    BUTTERFLY2_GRADIENT2(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT2),
    /**
     * 蝴蝶二渐变三翅膀。
     */
    BUTTERFLY2_GRADIENT3(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT3),
    /**
     * 蝴蝶二蘑菇翅膀。
     */
    BUTTERFLY2_MUSHROOM(ParticleWingTypeEnum.BUTTERFLY2_MUSHROOM),
    /**
     * 蝴蝶二火焰翅膀。
     */
    BUTTERFLY2_FIRE(ParticleWingTypeEnum.BUTTERFLY2_FIRE),
    /**
     * 蝴蝶二彩色翅膀。
     */
    BUTTERFLY2_COLORS(ParticleWingTypeEnum.BUTTERFLY2_COLORS),
    /**
     * 蝴蝶二飞行翅膀。
     */
    BUTTERFLY2_FLY(ParticleWingTypeEnum.BUTTERFLY2_FLY),
    /**
     * 短翼渐变一翅膀。
     */
    SHORT_GRADIENT1(ParticleWingTypeEnum.SHORT_GRADIENT1),
    /**
     * 短翼渐变二翅膀。
     */
    SHORT_GRADIENT2(ParticleWingTypeEnum.SHORT_GRADIENT2),
    /**
     * 短翼渐变三翅膀。
     */
    SHORT_GRADIENT3(ParticleWingTypeEnum.SHORT_GRADIENT3),
    /**
     * 短翼蘑菇翅膀。
     */
    SHORT_MUSHROOM(ParticleWingTypeEnum.SHORT_MUSHROOM),
    /**
     * 短翼火焰翅膀。
     */
    SHORT_FIRE(ParticleWingTypeEnum.SHORT_FIRE),
    /**
     * 短翼彩色翅膀。
     */
    SHORT_COLORS(ParticleWingTypeEnum.SHORT_COLORS),
    /**
     * 短翼飞行翅膀。
     */
    SHORT_FLY(ParticleWingTypeEnum.SHORT_FLY),
    /**
     * 恶魔渐变一翅膀。
     */
    DEMON_GRADIENT1(ParticleWingTypeEnum.DEMON_GRADIENT1),
    /**
     * 恶魔渐变二翅膀。
     */
    DEMON_GRADIENT2(ParticleWingTypeEnum.DEMON_GRADIENT2),
    /**
     * 恶魔渐变三翅膀。
     */
    DEMON_GRADIENT3(ParticleWingTypeEnum.DEMON_GRADIENT3),
    /**
     * 恶魔蘑菇翅膀。
     */
    DEMON_MUSHROOM(ParticleWingTypeEnum.DEMON_MUSHROOM),
    /**
     * 恶魔火焰翅膀。
     */
    DEMON_FIRE(ParticleWingTypeEnum.DEMON_FIRE),
    /**
     * 恶魔彩色翅膀。
     */
    DEMON_COLORS(ParticleWingTypeEnum.DEMON_COLORS),
    /**
     * 恶魔飞行翅膀。
     */
    DEMON_FLY(ParticleWingTypeEnum.DEMON_FLY),
    /**
     * 暗夜渐变一翅膀。
     */
    NIGHT_GRADIENT1(ParticleWingTypeEnum.NIGHT_GRADIENT1),
    /**
     * 暗夜渐变二翅膀。
     */
    NIGHT_GRADIENT2(ParticleWingTypeEnum.NIGHT_GRADIENT2),
    /**
     * 暗夜渐变三翅膀。
     */
    NIGHT_GRADIENT3(ParticleWingTypeEnum.NIGHT_GRADIENT3),
    /**
     * 暗夜蘑菇翅膀。
     */
    NIGHT_MUSHROOM(ParticleWingTypeEnum.NIGHT_MUSHROOM),
    /**
     * 暗夜火焰翅膀。
     */
    NIGHT_FIRE(ParticleWingTypeEnum.NIGHT_FIRE),
    /**
     * 暗夜彩色翅膀。
     */
    NIGHT_COLORS(ParticleWingTypeEnum.NIGHT_COLORS),
    /**
     * 暗夜飞行翅膀。
     */
    NIGHT_FLY(ParticleWingTypeEnum.NIGHT_FLY),
    /**
     * 圆形轨迹。
     */
    CIRCLE(ParticleTrailPatternEnum.CIRCLE),
    /**
     * 螺旋臂轨迹。
     */
    HELIX(ParticleTrailPatternEnum.HELIX),
    /**
     * 升降螺旋轨迹。
     */
    SPIRAL(ParticleTrailPatternEnum.SPIRAL),
    /**
     * 波浪轨迹。
     */
    WAVES(ParticleTrailPatternEnum.WAVES),
    /**
     * 脉冲轨迹。
     */
    PULSE(ParticleTrailPatternEnum.PULSE),
    /**
     * 护盾轨迹。
     */
    SHIELD(ParticleTrailPatternEnum.SHIELD);

    /**
     * 固定翅膀组合，轨迹类型时为空。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final ParticleWingTypeEnum wingType;
    /**
     * 轨迹算法，翅膀类型时为空。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final ParticleTrailPatternEnum trailPattern;

    /**
     * 创建翅膀类型。
     *
     * @param wingType 翅膀组合
     */
    ParticleTypeEnum(@NotNull ParticleWingTypeEnum wingType) {
        this.wingType = wingType;
        this.trailPattern = null;
    }

    /**
     * 创建轨迹类型。
     *
     * @param trailPattern 轨迹算法
     */
    ParticleTypeEnum(@NotNull ParticleTrailPatternEnum trailPattern) {
        this.wingType = null;
        this.trailPattern = trailPattern;
    }

}
