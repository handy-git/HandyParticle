package cn.handyplus.particle.api;

import cn.handyplus.lib.util.BaseUtil;
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
    WING_GRADIENT1_ANGEL(ParticleWingTypeEnum.ANGEL_GRADIENT1),
    /**
     * 天使渐变二翅膀。
     */
    WING_GRADIENT2_ANGEL(ParticleWingTypeEnum.ANGEL_GRADIENT2),
    /**
     * 天使渐变三翅膀。
     */
    WING_GRADIENT3_ANGEL(ParticleWingTypeEnum.ANGEL_GRADIENT3),
    /**
     * 天使蘑菇翅膀。
     */
    WING_MUSHROOM_ANGEL(ParticleWingTypeEnum.ANGEL_MUSHROOM),
    /**
     * 天使火焰翅膀。
     */
    WING_FIRE_ANGEL(ParticleWingTypeEnum.ANGEL_FIRE),
    /**
     * 天使彩色翅膀。
     */
    WING_COLORS_ANGEL(ParticleWingTypeEnum.ANGEL_COLORS),
    /**
     * 天使飞行翅膀。
     */
    WING_FLY_ANGEL(ParticleWingTypeEnum.ANGEL_FLY),
    /**
     * 蝴蝶渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_GRADIENT1),
    /**
     * 蝴蝶渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_GRADIENT2),
    /**
     * 蝴蝶渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_GRADIENT3),
    /**
     * 蝴蝶蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_MUSHROOM),
    /**
     * 蝴蝶火焰翅膀。
     */
    WING_FIRE_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_FIRE),
    /**
     * 蝴蝶彩色翅膀。
     */
    WING_COLORS_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_COLORS),
    /**
     * 蝴蝶飞行翅膀。
     */
    WING_FLY_BUTTERFLY(ParticleWingTypeEnum.BUTTERFLY_FLY),
    /**
     * 精灵渐变一翅膀。
     */
    WING_GRADIENT1_ELF(ParticleWingTypeEnum.ELF_GRADIENT1),
    /**
     * 精灵渐变二翅膀。
     */
    WING_GRADIENT2_ELF(ParticleWingTypeEnum.ELF_GRADIENT2),
    /**
     * 精灵渐变三翅膀。
     */
    WING_GRADIENT3_ELF(ParticleWingTypeEnum.ELF_GRADIENT3),
    /**
     * 精灵蘑菇翅膀。
     */
    WING_MUSHROOM_ELF(ParticleWingTypeEnum.ELF_MUSHROOM),
    /**
     * 精灵火焰翅膀。
     */
    WING_FIRE_ELF(ParticleWingTypeEnum.ELF_FIRE),
    /**
     * 精灵彩色翅膀。
     */
    WING_COLORS_ELF(ParticleWingTypeEnum.ELF_COLORS),
    /**
     * 精灵飞行翅膀。
     */
    WING_FLY_ELF(ParticleWingTypeEnum.ELF_FLY),
    /**
     * 长翼渐变一翅膀。
     */
    WING_GRADIENT1_LONG(ParticleWingTypeEnum.LONG_GRADIENT1),
    /**
     * 长翼渐变二翅膀。
     */
    WING_GRADIENT2_LONG(ParticleWingTypeEnum.LONG_GRADIENT2),
    /**
     * 长翼渐变三翅膀。
     */
    WING_GRADIENT3_LONG(ParticleWingTypeEnum.LONG_GRADIENT3),
    /**
     * 长翼蘑菇翅膀。
     */
    WING_MUSHROOM_LONG(ParticleWingTypeEnum.LONG_MUSHROOM),
    /**
     * 长翼火焰翅膀。
     */
    WING_FIRE_LONG(ParticleWingTypeEnum.LONG_FIRE),
    /**
     * 长翼彩色翅膀。
     */
    WING_COLORS_LONG(ParticleWingTypeEnum.LONG_COLORS),
    /**
     * 长翼飞行翅膀。
     */
    WING_FLY_LONG(ParticleWingTypeEnum.LONG_FLY),
    /**
     * 蝴蝶二渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT1),
    /**
     * 蝴蝶二渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT2),
    /**
     * 蝴蝶二渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_GRADIENT3),
    /**
     * 蝴蝶二蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_MUSHROOM),
    /**
     * 蝴蝶二火焰翅膀。
     */
    WING_FIRE_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_FIRE),
    /**
     * 蝴蝶二彩色翅膀。
     */
    WING_COLORS_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_COLORS),
    /**
     * 蝴蝶二飞行翅膀。
     */
    WING_FLY_BUTTERFLY2(ParticleWingTypeEnum.BUTTERFLY2_FLY),
    /**
     * 短翼渐变一翅膀。
     */
    WING_GRADIENT1_SHORT(ParticleWingTypeEnum.SHORT_GRADIENT1),
    /**
     * 短翼渐变二翅膀。
     */
    WING_GRADIENT2_SHORT(ParticleWingTypeEnum.SHORT_GRADIENT2),
    /**
     * 短翼渐变三翅膀。
     */
    WING_GRADIENT3_SHORT(ParticleWingTypeEnum.SHORT_GRADIENT3),
    /**
     * 短翼蘑菇翅膀。
     */
    WING_MUSHROOM_SHORT(ParticleWingTypeEnum.SHORT_MUSHROOM),
    /**
     * 短翼火焰翅膀。
     */
    WING_FIRE_SHORT(ParticleWingTypeEnum.SHORT_FIRE),
    /**
     * 短翼彩色翅膀。
     */
    WING_COLORS_SHORT(ParticleWingTypeEnum.SHORT_COLORS),
    /**
     * 短翼飞行翅膀。
     */
    WING_FLY_SHORT(ParticleWingTypeEnum.SHORT_FLY),
    /**
     * 恶魔渐变一翅膀。
     */
    WING_GRADIENT1_DEMON(ParticleWingTypeEnum.DEMON_GRADIENT1),
    /**
     * 恶魔渐变二翅膀。
     */
    WING_GRADIENT2_DEMON(ParticleWingTypeEnum.DEMON_GRADIENT2),
    /**
     * 恶魔渐变三翅膀。
     */
    WING_GRADIENT3_DEMON(ParticleWingTypeEnum.DEMON_GRADIENT3),
    /**
     * 恶魔蘑菇翅膀。
     */
    WING_MUSHROOM_DEMON(ParticleWingTypeEnum.DEMON_MUSHROOM),
    /**
     * 恶魔火焰翅膀。
     */
    WING_FIRE_DEMON(ParticleWingTypeEnum.DEMON_FIRE),
    /**
     * 恶魔彩色翅膀。
     */
    WING_COLORS_DEMON(ParticleWingTypeEnum.DEMON_COLORS),
    /**
     * 恶魔飞行翅膀。
     */
    WING_FLY_DEMON(ParticleWingTypeEnum.DEMON_FLY),
    /**
     * 暗夜渐变一翅膀。
     */
    WING_GRADIENT1_NIGHT(ParticleWingTypeEnum.NIGHT_GRADIENT1),
    /**
     * 暗夜渐变二翅膀。
     */
    WING_GRADIENT2_NIGHT(ParticleWingTypeEnum.NIGHT_GRADIENT2),
    /**
     * 暗夜渐变三翅膀。
     */
    WING_GRADIENT3_NIGHT(ParticleWingTypeEnum.NIGHT_GRADIENT3),
    /**
     * 暗夜蘑菇翅膀。
     */
    WING_MUSHROOM_NIGHT(ParticleWingTypeEnum.NIGHT_MUSHROOM),
    /**
     * 暗夜火焰翅膀。
     */
    WING_FIRE_NIGHT(ParticleWingTypeEnum.NIGHT_FIRE),
    /**
     * 暗夜彩色翅膀。
     */
    WING_COLORS_NIGHT(ParticleWingTypeEnum.NIGHT_COLORS),
    /**
     * 暗夜飞行翅膀。
     */
    WING_FLY_NIGHT(ParticleWingTypeEnum.NIGHT_FLY),
    /**
     * 圆形轨迹。
     */
    TRAIL_CIRCLE(ParticleTrailPatternEnum.CIRCLE),
    /**
     * 螺旋臂轨迹。
     */
    TRAIL_HELIX(ParticleTrailPatternEnum.HELIX),
    /**
     * 升降螺旋轨迹。
     */
    TRAIL_SPIRAL(ParticleTrailPatternEnum.SPIRAL),
    /**
     * 波浪轨迹。
     */
    TRAIL_WAVES(ParticleTrailPatternEnum.WAVES),
    /**
     * 脉冲轨迹。
     */
    TRAIL_PULSE(ParticleTrailPatternEnum.PULSE),
    /**
     * 护盾轨迹。
     */
    TRAIL_SHIELD(ParticleTrailPatternEnum.SHIELD);

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

    /**
     * 从当前语言文件读取类型描述。
     *
     * @return 类型描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("particleTypeDesc." + name());
    }

    /**
     * 判断当前类型是否为需要指定粒子的轨迹。
     *
     * @return 是轨迹时返回 true
     */
    public boolean isTrail() {
        return trailPattern != null;
    }

    /**
     * 按枚举名称或旧名称忽略大小写获取粒子类型，兼容补全描述。
     *
     * @param type 粒子类型名称
     * @return 对应的粒子类型
     * @throws RuntimeException 类型不存在时抛出
     */
    @NotNull
    public static ParticleTypeEnum getEnumThrow(@NotNull String type) {
        int descStart = type.indexOf('(');
        String name = descStart < 0 ? type : type.substring(0, descStart);
        for (ParticleTypeEnum particleType : values()) {
            if (particleType.name().equalsIgnoreCase(name)) {
                return particleType;
            }
            if (particleType.wingType != null && particleType.wingType.name().equalsIgnoreCase(name)) {
                return particleType;
            }
            if (particleType.trailPattern != null && particleType.trailPattern.name().equalsIgnoreCase(name)) {
                return particleType;
            }
        }
        throw new RuntimeException(BaseUtil.getLangMsg("invalidParticleType") + ": " + type);
    }

}
