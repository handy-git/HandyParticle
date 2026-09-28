package cn.handyplus.particle.api;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 可直接启用的翅膀组合与内置粒子效果。
 *
 * @author handy
 */
public enum ParticleTypeEnum {
    /**
     * 天使渐变一翅膀。
     */
    WING_GRADIENT1_ANGEL(WingTypeEnum.ANGEL_GRADIENT1),
    /**
     * 天使渐变二翅膀。
     */
    WING_GRADIENT2_ANGEL(WingTypeEnum.ANGEL_GRADIENT2),
    /**
     * 天使渐变三翅膀。
     */
    WING_GRADIENT3_ANGEL(WingTypeEnum.ANGEL_GRADIENT3),
    /**
     * 天使蘑菇翅膀。
     */
    WING_MUSHROOM_ANGEL(WingTypeEnum.ANGEL_MUSHROOM),
    /**
     * 天使火焰翅膀。
     */
    WING_FIRE_ANGEL(WingTypeEnum.ANGEL_FIRE),
    /**
     * 天使彩色翅膀。
     */
    WING_COLORS_ANGEL(WingTypeEnum.ANGEL_COLORS),
    /**
     * 天使飞行翅膀。
     */
    WING_FLY_ANGEL(WingTypeEnum.ANGEL_FLY),
    /**
     * 蝴蝶渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY(WingTypeEnum.BUTTERFLY_GRADIENT1),
    /**
     * 蝴蝶渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY(WingTypeEnum.BUTTERFLY_GRADIENT2),
    /**
     * 蝴蝶渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY(WingTypeEnum.BUTTERFLY_GRADIENT3),
    /**
     * 蝴蝶蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY(WingTypeEnum.BUTTERFLY_MUSHROOM),
    /**
     * 蝴蝶火焰翅膀。
     */
    WING_FIRE_BUTTERFLY(WingTypeEnum.BUTTERFLY_FIRE),
    /**
     * 蝴蝶彩色翅膀。
     */
    WING_COLORS_BUTTERFLY(WingTypeEnum.BUTTERFLY_COLORS),
    /**
     * 蝴蝶飞行翅膀。
     */
    WING_FLY_BUTTERFLY(WingTypeEnum.BUTTERFLY_FLY),
    /**
     * 精灵渐变一翅膀。
     */
    WING_GRADIENT1_ELF(WingTypeEnum.ELF_GRADIENT1),
    /**
     * 精灵渐变二翅膀。
     */
    WING_GRADIENT2_ELF(WingTypeEnum.ELF_GRADIENT2),
    /**
     * 精灵渐变三翅膀。
     */
    WING_GRADIENT3_ELF(WingTypeEnum.ELF_GRADIENT3),
    /**
     * 精灵蘑菇翅膀。
     */
    WING_MUSHROOM_ELF(WingTypeEnum.ELF_MUSHROOM),
    /**
     * 精灵火焰翅膀。
     */
    WING_FIRE_ELF(WingTypeEnum.ELF_FIRE),
    /**
     * 精灵彩色翅膀。
     */
    WING_COLORS_ELF(WingTypeEnum.ELF_COLORS),
    /**
     * 精灵飞行翅膀。
     */
    WING_FLY_ELF(WingTypeEnum.ELF_FLY),
    /**
     * 长翼渐变一翅膀。
     */
    WING_GRADIENT1_LONG(WingTypeEnum.LONG_GRADIENT1),
    /**
     * 长翼渐变二翅膀。
     */
    WING_GRADIENT2_LONG(WingTypeEnum.LONG_GRADIENT2),
    /**
     * 长翼渐变三翅膀。
     */
    WING_GRADIENT3_LONG(WingTypeEnum.LONG_GRADIENT3),
    /**
     * 长翼蘑菇翅膀。
     */
    WING_MUSHROOM_LONG(WingTypeEnum.LONG_MUSHROOM),
    /**
     * 长翼火焰翅膀。
     */
    WING_FIRE_LONG(WingTypeEnum.LONG_FIRE),
    /**
     * 长翼彩色翅膀。
     */
    WING_COLORS_LONG(WingTypeEnum.LONG_COLORS),
    /**
     * 长翼飞行翅膀。
     */
    WING_FLY_LONG(WingTypeEnum.LONG_FLY),
    /**
     * 蝴蝶二渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_GRADIENT1),
    /**
     * 蝴蝶二渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_GRADIENT2),
    /**
     * 蝴蝶二渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_GRADIENT3),
    /**
     * 蝴蝶二蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_MUSHROOM),
    /**
     * 蝴蝶二火焰翅膀。
     */
    WING_FIRE_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_FIRE),
    /**
     * 蝴蝶二彩色翅膀。
     */
    WING_COLORS_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_COLORS),
    /**
     * 蝴蝶二飞行翅膀。
     */
    WING_FLY_BUTTERFLY2(WingTypeEnum.BUTTERFLY2_FLY),
    /**
     * 短翼渐变一翅膀。
     */
    WING_GRADIENT1_SHORT(WingTypeEnum.SHORT_GRADIENT1),
    /**
     * 短翼渐变二翅膀。
     */
    WING_GRADIENT2_SHORT(WingTypeEnum.SHORT_GRADIENT2),
    /**
     * 短翼渐变三翅膀。
     */
    WING_GRADIENT3_SHORT(WingTypeEnum.SHORT_GRADIENT3),
    /**
     * 短翼蘑菇翅膀。
     */
    WING_MUSHROOM_SHORT(WingTypeEnum.SHORT_MUSHROOM),
    /**
     * 短翼火焰翅膀。
     */
    WING_FIRE_SHORT(WingTypeEnum.SHORT_FIRE),
    /**
     * 短翼彩色翅膀。
     */
    WING_COLORS_SHORT(WingTypeEnum.SHORT_COLORS),
    /**
     * 短翼飞行翅膀。
     */
    WING_FLY_SHORT(WingTypeEnum.SHORT_FLY),
    /**
     * 恶魔渐变一翅膀。
     */
    WING_GRADIENT1_DEMON(WingTypeEnum.DEMON_GRADIENT1),
    /**
     * 恶魔渐变二翅膀。
     */
    WING_GRADIENT2_DEMON(WingTypeEnum.DEMON_GRADIENT2),
    /**
     * 恶魔渐变三翅膀。
     */
    WING_GRADIENT3_DEMON(WingTypeEnum.DEMON_GRADIENT3),
    /**
     * 恶魔蘑菇翅膀。
     */
    WING_MUSHROOM_DEMON(WingTypeEnum.DEMON_MUSHROOM),
    /**
     * 恶魔火焰翅膀。
     */
    WING_FIRE_DEMON(WingTypeEnum.DEMON_FIRE),
    /**
     * 恶魔彩色翅膀。
     */
    WING_COLORS_DEMON(WingTypeEnum.DEMON_COLORS),
    /**
     * 恶魔飞行翅膀。
     */
    WING_FLY_DEMON(WingTypeEnum.DEMON_FLY),
    /**
     * 暗夜渐变一翅膀。
     */
    WING_GRADIENT1_NIGHT(WingTypeEnum.NIGHT_GRADIENT1),
    /**
     * 暗夜渐变二翅膀。
     */
    WING_GRADIENT2_NIGHT(WingTypeEnum.NIGHT_GRADIENT2),
    /**
     * 暗夜渐变三翅膀。
     */
    WING_GRADIENT3_NIGHT(WingTypeEnum.NIGHT_GRADIENT3),
    /**
     * 暗夜蘑菇翅膀。
     */
    WING_MUSHROOM_NIGHT(WingTypeEnum.NIGHT_MUSHROOM),
    /**
     * 暗夜火焰翅膀。
     */
    WING_FIRE_NIGHT(WingTypeEnum.NIGHT_FIRE),
    /**
     * 暗夜彩色翅膀。
     */
    WING_COLORS_NIGHT(WingTypeEnum.NIGHT_COLORS),
    /**
     * 暗夜飞行翅膀。
     */
    WING_FLY_NIGHT(WingTypeEnum.NIGHT_FLY),
    /**
     * 爱心拖尾。
     */
    TRAIL_HEART(TrailTypeEnum.TRAIL_HEART),
    /**
     * 愤怒村民拖尾。
     */
    TRAIL_ANGRY(TrailTypeEnum.TRAIL_ANGRY),
    /**
     * 魔法暴击拖尾。
     */
    TRAIL_MAGIC(TrailTypeEnum.TRAIL_MAGIC),
    /**
     * 红石拖尾。
     */
    TRAIL_FUN(TrailTypeEnum.TRAIL_FUN),
    /**
     * 云拖尾。
     */
    TRAIL_CLOUD(TrailTypeEnum.TRAIL_CLOUD),
    /**
     * 女巫拖尾。
     */
    TRAIL_WITCH(TrailTypeEnum.TRAIL_WITCH),
    /**
     * 末影拖尾。
     */
    TRAIL_ENDER(TrailTypeEnum.TRAIL_ENDER),
    /**
     * 绿色拖尾。
     */
    TRAIL_GREEN(TrailTypeEnum.TRAIL_GREEN),
    /**
     * 火花拖尾。
     */
    TRAIL_SPARK(TrailTypeEnum.TRAIL_SPARK),
    /**
     * 火焰拖尾。
     */
    TRAIL_FLAME(TrailTypeEnum.TRAIL_FLAME),
    /**
     * 白色魔法拖尾。
     */
    TRAIL_WHITE(TrailTypeEnum.TRAIL_WHITE),
    /**
     * 音符拖尾。
     */
    TRAIL_NOTE(TrailTypeEnum.TRAIL_NOTE),
    /**
     * 雪花拖尾。
     */
    TRAIL_SNOW(TrailTypeEnum.TRAIL_SNOW),
    /**
     * 水滴拖尾。
     */
    TRAIL_WATER(TrailTypeEnum.TRAIL_WATER),
    /**
     * 熔岩滴落拖尾。
     */
    TRAIL_LAVA(TrailTypeEnum.TRAIL_LAVA),
    /**
     * 暴击拖尾。
     */
    TRAIL_CRIT(TrailTypeEnum.TRAIL_CRIT),
    /**
     * 烟雾拖尾。
     */
    TRAIL_SMOKE(TrailTypeEnum.TRAIL_SMOKE),
    /**
     * 药水拖尾。
     */
    TRAIL_SPELL(TrailTypeEnum.TRAIL_SPELL),
    /**
     * 附魔拖尾。
     */
    TRAIL_ENCHANT(TrailTypeEnum.TRAIL_ENCHANT),
    /**
     * 水花拖尾。
     */
    TRAIL_SPLASH(TrailTypeEnum.TRAIL_SPLASH),
    /**
     * 史莱姆拖尾。
     */
    TRAIL_SLIME(TrailTypeEnum.TRAIL_SLIME),
    /**
     * 雪球拖尾。
     */
    TRAIL_SNOWBALL(TrailTypeEnum.TRAIL_SNOWBALL),
    /**
     * 虚空拖尾。
     */
    TRAIL_VOID(TrailTypeEnum.TRAIL_VOID),
    /**
     * 熔岩爆裂拖尾。
     */
    TRAIL_LAVA_POP(TrailTypeEnum.TRAIL_LAVA_POP),
    /**
     * 龙息拖尾。
     */
    TRAIL_BREATH(TrailTypeEnum.TRAIL_BREATH),
    /**
     * 伤害拖尾。
     */
    TRAIL_DAMAGE(TrailTypeEnum.TRAIL_DAMAGE),
    /**
     * 末地烛拖尾。
     */
    TRAIL_END_ROD(TrailTypeEnum.TRAIL_END_ROD),
    /**
     * 不死图腾拖尾。
     */
    TRAIL_TOTEM(TrailTypeEnum.TRAIL_TOTEM),
    /**
     * 灵魂拖尾。
     */
    TRAIL_SOUL(TrailTypeEnum.TRAIL_SOUL),
    /**
     * 灵魂火拖尾。
     */
    TRAIL_SOUL_FIRE(TrailTypeEnum.TRAIL_SOUL_FIRE),
    /**
     * 金色火花拖尾。
     */
    TRAIL_GOLD_SPARKS(TrailTypeEnum.TRAIL_GOLD_SPARKS),
    /**
     * 发光鱿鱼拖尾。
     */
    TRAIL_GLOW_SQUID(TrailTypeEnum.TRAIL_GLOW_SQUID),
    /**
     * 粉色粒子拖尾。
     */
    TRAIL_PINK_CONFETTI(TrailTypeEnum.TRAIL_PINK_CONFETTI),
    /**
     * 彩色羊毛方块拖尾。
     */
    BLOCK_WOOL(BlockTrailTypeEnum.BLOCK_WOOL),
    /**
     * 彩色玻璃方块拖尾。
     */
    BLOCK_GLASS(BlockTrailTypeEnum.BLOCK_GLASS),
    /**
     * 下界材质方块拖尾。
     */
    BLOCK_NETHER(BlockTrailTypeEnum.BLOCK_NETHER),
    /**
     * 彩色陶瓦方块拖尾。
     */
    BLOCK_TERRACOTTA(BlockTrailTypeEnum.BLOCK_TERRACOTTA),
    /**
     * 矿石方块拖尾。
     */
    BLOCK_ORE(BlockTrailTypeEnum.BLOCK_ORE),
    /**
     * 红石机械方块拖尾。
     */
    BLOCK_REDSTONE(BlockTrailTypeEnum.BLOCK_REDSTONE),
    /**
     * 自然方块拖尾。
     */
    BLOCK_NATURE(BlockTrailTypeEnum.BLOCK_NATURE),
    /**
     * 金属方块拖尾。
     */
    BLOCK_METAL(BlockTrailTypeEnum.BLOCK_METAL),
    /**
     * 常用物品雨。
     */
    RAIN_COMMON(ItemRainTypeEnum.RAIN_COMMON),
    /**
     * 金色物品雨。
     */
    RAIN_GOLD(ItemRainTypeEnum.RAIN_GOLD),
    /**
     * 红石物品雨。
     */
    RAIN_REDSTONE(ItemRainTypeEnum.RAIN_REDSTONE),
    /**
     * 稀有物品雨。
     */
    RAIN_RARE(ItemRainTypeEnum.RAIN_RARE),
    /**
     * 彩色羊毛物品雨。
     */
    RAIN_WOOL(ItemRainTypeEnum.RAIN_WOOL);

    /**
     * 固定翅膀组合，粒子类型时为空。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final WingTypeEnum wingType;
    /**
     * 固定参数的内置粒子类型，翅膀类型时为空。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final TrailTypeEnum builtinTrail;
    /**
     * 固定参数的内置方块拖尾类型。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final BlockTrailTypeEnum blockTrail;
    /**
     * 固定参数的内置物品雨类型。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final ItemRainTypeEnum itemRain;
    /**
     * 效果所属类别。
     */
    @Getter
    private final ParticleCategoryTypeEnum category;

    /**
     * 创建翅膀类型。
     *
     * @param wingType 翅膀组合
     */
    ParticleTypeEnum(@NotNull WingTypeEnum wingType) {
        this.wingType = wingType;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.category = ParticleCategoryTypeEnum.WING;
    }

    /**
     * 创建内置拖尾类型。
     *
     * @param builtinTrail 内置拖尾
     */
    ParticleTypeEnum(@NotNull TrailTypeEnum builtinTrail) {
        this.wingType = null;
        this.builtinTrail = builtinTrail;
        this.blockTrail = null;
        this.itemRain = null;
        this.category = ParticleCategoryTypeEnum.TRAIL;
    }

    /**
     * 创建方块拖尾类型。
     *
     * @param blockTrail 方块拖尾
     */
    ParticleTypeEnum(@NotNull BlockTrailTypeEnum blockTrail) {
        this.wingType = null;
        this.builtinTrail = null;
        this.blockTrail = blockTrail;
        this.itemRain = null;
        this.category = ParticleCategoryTypeEnum.BLOCK;
    }

    /**
     * 创建物品雨类型。
     *
     * @param itemRain 物品雨组合
     */
    ParticleTypeEnum(@NotNull ItemRainTypeEnum itemRain) {
        this.wingType = null;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = itemRain;
        this.category = ParticleCategoryTypeEnum.RAIN;
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

}
