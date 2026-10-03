package cn.handyplus.particle.api;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.confetti.ConfettiTypeEnum;
import cn.handyplus.particle.core.elytra.ElytraTrailTypeEnum;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.core.projectile.ProjectileTrailTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Locale;

/**
 * 插件内置的粒子效果预设。
 *
 * @author handy
 */
@Getter
public enum ParticleTypeEnum implements ParticleType {
    /**
     * 天使渐变一翅膀。
     */
    WING_GRADIENT1_ANGEL(WingModelEnum.ANGEL, WingColorEnum.GRADIENT1),
    /**
     * 天使渐变二翅膀。
     */
    WING_GRADIENT2_ANGEL(WingModelEnum.ANGEL, WingColorEnum.GRADIENT2),
    /**
     * 天使渐变三翅膀。
     */
    WING_GRADIENT3_ANGEL(WingModelEnum.ANGEL, WingColorEnum.GRADIENT3),
    /**
     * 天使蘑菇翅膀。
     */
    WING_MUSHROOM_ANGEL(WingModelEnum.ANGEL, WingColorEnum.MUSHROOM),
    /**
     * 天使火焰翅膀。
     */
    WING_FIRE_ANGEL(WingModelEnum.ANGEL, WingColorEnum.FIRE),
    /**
     * 天使彩色翅膀。
     */
    WING_COLORS_ANGEL(WingModelEnum.ANGEL, WingColorEnum.COLORS),
    /**
     * 天使飞行翅膀。
     */
    WING_FLY_ANGEL(WingModelEnum.ANGEL, WingColorEnum.FLY),

    /**
     * 蝴蝶渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.GRADIENT1),
    /**
     * 蝴蝶渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.GRADIENT2),
    /**
     * 蝴蝶渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.GRADIENT3),
    /**
     * 蝴蝶蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.MUSHROOM),
    /**
     * 蝴蝶火焰翅膀。
     */
    WING_FIRE_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.FIRE),
    /**
     * 蝴蝶彩色翅膀。
     */
    WING_COLORS_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.COLORS),
    /**
     * 蝴蝶飞行翅膀。
     */
    WING_FLY_BUTTERFLY(WingModelEnum.BUTTERFLY, WingColorEnum.FLY),

    /**
     * 精灵渐变一翅膀。
     */
    WING_GRADIENT1_ELF(WingModelEnum.ELF, WingColorEnum.GRADIENT1),
    /**
     * 精灵渐变二翅膀。
     */
    WING_GRADIENT2_ELF(WingModelEnum.ELF, WingColorEnum.GRADIENT2),
    /**
     * 精灵渐变三翅膀。
     */
    WING_GRADIENT3_ELF(WingModelEnum.ELF, WingColorEnum.GRADIENT3),
    /**
     * 精灵蘑菇翅膀。
     */
    WING_MUSHROOM_ELF(WingModelEnum.ELF, WingColorEnum.MUSHROOM),
    /**
     * 精灵火焰翅膀。
     */
    WING_FIRE_ELF(WingModelEnum.ELF, WingColorEnum.FIRE),
    /**
     * 精灵彩色翅膀。
     */
    WING_COLORS_ELF(WingModelEnum.ELF, WingColorEnum.COLORS),
    /**
     * 精灵飞行翅膀。
     */
    WING_FLY_ELF(WingModelEnum.ELF, WingColorEnum.FLY),

    /**
     * 长翼渐变一翅膀。
     */
    WING_GRADIENT1_LONG(WingModelEnum.LONG, WingColorEnum.GRADIENT1),
    /**
     * 长翼渐变二翅膀。
     */
    WING_GRADIENT2_LONG(WingModelEnum.LONG, WingColorEnum.GRADIENT2),
    /**
     * 长翼渐变三翅膀。
     */
    WING_GRADIENT3_LONG(WingModelEnum.LONG, WingColorEnum.GRADIENT3),
    /**
     * 长翼蘑菇翅膀。
     */
    WING_MUSHROOM_LONG(WingModelEnum.LONG, WingColorEnum.MUSHROOM),
    /**
     * 长翼火焰翅膀。
     */
    WING_FIRE_LONG(WingModelEnum.LONG, WingColorEnum.FIRE),
    /**
     * 长翼彩色翅膀。
     */
    WING_COLORS_LONG(WingModelEnum.LONG, WingColorEnum.COLORS),
    /**
     * 长翼飞行翅膀。
     */
    WING_FLY_LONG(WingModelEnum.LONG, WingColorEnum.FLY),

    /**
     * 蝴蝶二渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.GRADIENT1),
    /**
     * 蝴蝶二渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.GRADIENT2),
    /**
     * 蝴蝶二渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.GRADIENT3),
    /**
     * 蝴蝶二蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.MUSHROOM),
    /**
     * 蝴蝶二火焰翅膀。
     */
    WING_FIRE_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.FIRE),
    /**
     * 蝴蝶二彩色翅膀。
     */
    WING_COLORS_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.COLORS),
    /**
     * 蝴蝶二飞行翅膀。
     */
    WING_FLY_BUTTERFLY2(WingModelEnum.BUTTERFLY2, WingColorEnum.FLY),

    /**
     * 短翼渐变一翅膀。
     */
    WING_GRADIENT1_SHORT(WingModelEnum.SHORT, WingColorEnum.GRADIENT1),
    /**
     * 短翼渐变二翅膀。
     */
    WING_GRADIENT2_SHORT(WingModelEnum.SHORT, WingColorEnum.GRADIENT2),
    /**
     * 短翼渐变三翅膀。
     */
    WING_GRADIENT3_SHORT(WingModelEnum.SHORT, WingColorEnum.GRADIENT3),
    /**
     * 短翼蘑菇翅膀。
     */
    WING_MUSHROOM_SHORT(WingModelEnum.SHORT, WingColorEnum.MUSHROOM),
    /**
     * 短翼火焰翅膀。
     */
    WING_FIRE_SHORT(WingModelEnum.SHORT, WingColorEnum.FIRE),
    /**
     * 短翼彩色翅膀。
     */
    WING_COLORS_SHORT(WingModelEnum.SHORT, WingColorEnum.COLORS),
    /**
     * 短翼飞行翅膀。
     */
    WING_FLY_SHORT(WingModelEnum.SHORT, WingColorEnum.FLY),

    /**
     * 恶魔渐变一翅膀。
     */
    WING_GRADIENT1_DEMON(WingModelEnum.DEMON, WingColorEnum.GRADIENT1),
    /**
     * 恶魔渐变二翅膀。
     */
    WING_GRADIENT2_DEMON(WingModelEnum.DEMON, WingColorEnum.GRADIENT2),
    /**
     * 恶魔渐变三翅膀。
     */
    WING_GRADIENT3_DEMON(WingModelEnum.DEMON, WingColorEnum.GRADIENT3),
    /**
     * 恶魔蘑菇翅膀。
     */
    WING_MUSHROOM_DEMON(WingModelEnum.DEMON, WingColorEnum.MUSHROOM),
    /**
     * 恶魔火焰翅膀。
     */
    WING_FIRE_DEMON(WingModelEnum.DEMON, WingColorEnum.FIRE),
    /**
     * 恶魔彩色翅膀。
     */
    WING_COLORS_DEMON(WingModelEnum.DEMON, WingColorEnum.COLORS),
    /**
     * 恶魔飞行翅膀。
     */
    WING_FLY_DEMON(WingModelEnum.DEMON, WingColorEnum.FLY),

    /**
     * 暗夜渐变一翅膀。
     */
    WING_GRADIENT1_NIGHT(WingModelEnum.NIGHT, WingColorEnum.GRADIENT1),
    /**
     * 暗夜渐变二翅膀。
     */
    WING_GRADIENT2_NIGHT(WingModelEnum.NIGHT, WingColorEnum.GRADIENT2),
    /**
     * 暗夜渐变三翅膀。
     */
    WING_GRADIENT3_NIGHT(WingModelEnum.NIGHT, WingColorEnum.GRADIENT3),
    /**
     * 暗夜蘑菇翅膀。
     */
    WING_MUSHROOM_NIGHT(WingModelEnum.NIGHT, WingColorEnum.MUSHROOM),
    /**
     * 暗夜火焰翅膀。
     */
    WING_FIRE_NIGHT(WingModelEnum.NIGHT, WingColorEnum.FIRE),
    /**
     * 暗夜彩色翅膀。
     */
    WING_COLORS_NIGHT(WingModelEnum.NIGHT, WingColorEnum.COLORS),
    /**
     * 暗夜飞行翅膀。
     */
    WING_FLY_NIGHT(WingModelEnum.NIGHT, WingColorEnum.FLY),

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
     * 云朵拖尾。
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
     * 粉色纸屑拖尾。
     */
    TRAIL_PINK_CONFETTI(TrailTypeEnum.TRAIL_PINK_CONFETTI),
    /**
     * 可指定颜色的 Dust 拖尾。
     */
    TRAIL_COLOR(TrailTypeEnum.TRAIL_COLOR),

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
    RAIN_WOOL(ItemRainTypeEnum.RAIN_WOOL),

    /**
     * 红黄绿纸屑。
     */
    CONFETTI_RED_YELLOW_GREEN(ConfettiTypeEnum.RED_YELLOW_GREEN),
    /**
     * 白黑紫纸屑。
     */
    CONFETTI_WHITE_BLACK_PURPLE(ConfettiTypeEnum.WHITE_BLACK_PURPLE),
    /**
     * 白红蓝纸屑。
     */
    CONFETTI_WHITE_RED_BLUE(ConfettiTypeEnum.WHITE_RED_BLUE),
    /**
     * 绿蓝黑纸屑。
     */
    CONFETTI_GREEN_BLUE_BLACK(ConfettiTypeEnum.GREEN_BLUE_BLACK),
    /**
     * 红黄白纸屑。
     */
    CONFETTI_RED_YELLOW_WHITE(ConfettiTypeEnum.RED_YELLOW_WHITE),
    /**
     * 黑黄紫纸屑。
     */
    CONFETTI_BLACK_YELLOW_PURPLE(ConfettiTypeEnum.BLACK_YELLOW_PURPLE),
    /**
     * 红绿黑纸屑。
     */
    CONFETTI_RED_GREEN_BLACK(ConfettiTypeEnum.RED_GREEN_BLACK),

    /**
     * 贵金属鞘翅拖尾。
     */
    ELYTRA_METAL(ElytraTrailTypeEnum.ELYTRA_METAL),
    /**
     * 海晶鞘翅拖尾。
     */
    ELYTRA_PRISMARINE(ElytraTrailTypeEnum.ELYTRA_PRISMARINE),
    /**
     * 珍宝鞘翅拖尾。
     */
    ELYTRA_DRAGON(ElytraTrailTypeEnum.ELYTRA_DRAGON),

    /**
     * 魔法抛射物拖尾。
     */
    PROJECTILE_MAGIC(ProjectileTrailTypeEnum.MAGIC),
    /**
     * 绿色火花抛射物拖尾。
     */
    PROJECTILE_GREEN_SPARKLE(ProjectileTrailTypeEnum.GREEN_SPARKLE),
    /**
     * 彩色尘土抛射物拖尾。
     */
    PROJECTILE_COLOR_DUST(ProjectileTrailTypeEnum.COLOR_DUST),
    /**
     * 暴击抛射物拖尾。
     */
    PROJECTILE_CRIT(ProjectileTrailTypeEnum.CRIT),
    /**
     * 火花抛射物拖尾。
     */
    PROJECTILE_SPARKS(ProjectileTrailTypeEnum.SPARKS),
    /**
     * 火焰抛射物拖尾。
     */
    PROJECTILE_FLAME(ProjectileTrailTypeEnum.FLAME),
    /**
     * 飘落字符抛射物拖尾。
     */
    PROJECTILE_GLYPH(ProjectileTrailTypeEnum.GLYPH),
    /**
     * 爱心抛射物拖尾。
     */
    PROJECTILE_HEART(ProjectileTrailTypeEnum.HEART),
    /**
     * 瞬间法术抛射物拖尾。
     */
    PROJECTILE_SPELL(ProjectileTrailTypeEnum.SPELL),
    /**
     * 烟雾抛射物拖尾。
     */
    PROJECTILE_SMOKE(ProjectileTrailTypeEnum.SMOKE),
    /**
     * 浓烟抛射物拖尾。
     */
    PROJECTILE_LARGE_SMOKE(ProjectileTrailTypeEnum.LARGE_SMOKE),
    /**
     * 熔岩迸溅抛射物拖尾。
     */
    PROJECTILE_LAVA_POP(ProjectileTrailTypeEnum.LAVA_POP),
    /**
     * 熔岩滴落抛射物拖尾。
     */
    PROJECTILE_LAVA_DRIP(ProjectileTrailTypeEnum.LAVA_DRIP),
    /**
     * 魔法暴击抛射物拖尾。
     */
    PROJECTILE_MAGIC_CRIT(ProjectileTrailTypeEnum.MAGIC_CRIT),
    /**
     * 音符抛射物拖尾。
     */
    PROJECTILE_NOTE(ProjectileTrailTypeEnum.NOTE),
    /**
     * 附魔抛射物拖尾。
     */
    PROJECTILE_ENCHANT(ProjectileTrailTypeEnum.ENCHANT),
    /**
     * 传送门抛射物拖尾。
     */
    PROJECTILE_PORTAL(ProjectileTrailTypeEnum.PORTAL),
    /**
     * 药水旋涡抛射物拖尾。
     */
    PROJECTILE_POTION_SWIRL(ProjectileTrailTypeEnum.POTION_SWIRL),
    /**
     * 史莱姆抛射物拖尾。
     */
    PROJECTILE_SLIME(ProjectileTrailTypeEnum.SLIME),
    /**
     * 雪花抛射物拖尾。
     */
    PROJECTILE_SNOW(ProjectileTrailTypeEnum.SNOW),
    /**
     * 雪球抛射物拖尾。
     */
    PROJECTILE_SNOWBALL(ProjectileTrailTypeEnum.SNOWBALL),
    /**
     * 水花抛射物拖尾。
     */
    PROJECTILE_SPLASH(ProjectileTrailTypeEnum.SPLASH),
    /**
     * 愤怒村民抛射物拖尾。
     */
    PROJECTILE_ANGRY_VILLAGER(ProjectileTrailTypeEnum.ANGRY_VILLAGER),
    /**
     * 虚空雾抛射物拖尾。
     */
    PROJECTILE_VOID_FOG(ProjectileTrailTypeEnum.VOID_FOG),
    /**
     * 水滴抛射物拖尾。
     */
    PROJECTILE_WATER_DRIP(ProjectileTrailTypeEnum.WATER_DRIP),
    /**
     * 末地烛抛射物拖尾。
     */
    PROJECTILE_END_ROD(ProjectileTrailTypeEnum.END_ROD),
    /**
     * 不死图腾抛射物拖尾。
     */
    PROJECTILE_TOTEM(ProjectileTrailTypeEnum.TOTEM),
    /**
     * 灵魂火抛射物拖尾。
     */
    PROJECTILE_SOUL_FIRE(ProjectileTrailTypeEnum.SOUL_FIRE),
    /**
     * 金色火花抛射物拖尾。
     */
    PROJECTILE_GOLD_SPARKS(ProjectileTrailTypeEnum.GOLD_SPARKS),
    /**
     * 龙息抛射物拖尾。
     */
    PROJECTILE_DRAGON_BREATH(ProjectileTrailTypeEnum.DRAGON_BREATH),
    /**
     * 菌丝抛射物拖尾。
     */
    PROJECTILE_MYCELIUM(ProjectileTrailTypeEnum.MYCELIUM),
    /**
     * 爆炸抛射物拖尾。
     */
    PROJECTILE_EXPLOSION(ProjectileTrailTypeEnum.EXPLOSION),
    /**
     * 爆炸发射抛射物拖尾。
     */
    PROJECTILE_EXPLOSION_EMITTER(ProjectileTrailTypeEnum.EXPLOSION_EMITTER);

    private final ParticleCategoryTypeEnum category;
    private final TrailTypeEnum builtinTrail;
    private final BlockTrailTypeEnum blockTrail;
    private final ItemRainTypeEnum itemRain;
    private final ConfettiTypeEnum confetti;
    private final ElytraTrailTypeEnum elytraTrail;
    private final ProjectileTrailTypeEnum projectileTrail;
    private final WingModelEnum wingModel;
    private final WingColorEnum wingColor;

    /**
     * 创建翅膀预置。
     */
    ParticleTypeEnum(WingModelEnum model, WingColorEnum color) {
        this.category = ParticleCategoryTypeEnum.WING;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = null;
        this.elytraTrail = null;
        this.projectileTrail = null;
        this.wingModel = model;
        this.wingColor = color;
    }

    /**
     * 创建普通拖尾预置。
     */
    ParticleTypeEnum(TrailTypeEnum trail) {
        this.category = ParticleCategoryTypeEnum.TRAIL;
        this.builtinTrail = trail;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = null;
        this.elytraTrail = null;
        this.projectileTrail = null;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 创建方块拖尾预置。
     */
    ParticleTypeEnum(BlockTrailTypeEnum block) {
        this.category = ParticleCategoryTypeEnum.BLOCK;
        this.builtinTrail = null;
        this.blockTrail = block;
        this.itemRain = null;
        this.confetti = null;
        this.elytraTrail = null;
        this.projectileTrail = null;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 创建物品雨预置。
     */
    ParticleTypeEnum(ItemRainTypeEnum rain) {
        this.category = ParticleCategoryTypeEnum.RAIN;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = rain;
        this.confetti = null;
        this.elytraTrail = null;
        this.projectileTrail = null;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 创建纸屑预置。
     */
    ParticleTypeEnum(ConfettiTypeEnum confetti) {
        this.category = ParticleCategoryTypeEnum.CONFETTI;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = confetti;
        this.elytraTrail = null;
        this.projectileTrail = null;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 创建鞘翅拖尾预置。
     */
    ParticleTypeEnum(ElytraTrailTypeEnum elytra) {
        this.category = ParticleCategoryTypeEnum.ELYTRA;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = null;
        this.elytraTrail = elytra;
        this.projectileTrail = null;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 创建抛射物拖尾预置。
     */
    ParticleTypeEnum(ProjectileTrailTypeEnum projectile) {
        this.category = ParticleCategoryTypeEnum.PROJECTILE;
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = null;
        this.elytraTrail = null;
        this.projectileTrail = projectile;
        this.wingModel = null;
        this.wingColor = null;
    }

    /**
     * 按已保存的名称查找预置，忽略命令补全中的本地化描述。
     *
     * @param value 预置名称，可为空
     * @return 对应预置，未找到时返回 null
     */
    @Nullable
    public static ParticleTypeEnum getEnum(@Nullable String value) {
        if (value == null) {
            return null;
        }
        String name = value.split("\\(", 2)[0].trim().toUpperCase(Locale.ROOT);
        for (ParticleTypeEnum type : values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 按已保存的名称查找预置，忽略命令补全中的本地化描述。
     *
     * @param value 预置名称
     * @return 对应预置
     */
    @NotNull
    public static ParticleTypeEnum getEnumThrow(@NotNull String value) {
        ParticleTypeEnum type = getEnum(value);
        if (type == null) {
            throw new IllegalArgumentException(BaseUtil.getLangMsg("invalidParticleType"));
        }
        return type;
    }

    /**
     * 根据已保存的模型和图案查找翅膀预置。
     *
     * @param model 翅膀模型
     * @param color 翅膀图案
     * @return 翅膀预置
     */
    @NotNull
    public static ParticleTypeEnum wing(@NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        for (ParticleTypeEnum type : values()) {
            if (type.wingModel == model && type.wingColor == color) {
                return type;
            }
        }
        throw new IllegalArgumentException(BaseUtil.getLangMsg("invalidWingPreset"));
    }

    /**
     * 获取当前预置所属的粒子类别。
     *
     * @return 粒子类别
     */
    @Override
    @NotNull
    public ParticleCategoryTypeEnum getCategory() {
        return this.category;
    }

    /**
     * 获取预置的本地化描述。
     *
     * @return 预置描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("particleTypeDesc." + this.name());
    }

    /**
     * 判断当前拖尾是否支持自定义颜色。
     *
     * @return 支持颜色时返回 true
     */
    public boolean supportsColor() {
        return this.builtinTrail != null && this.builtinTrail.supportsColor();
    }

    /**
     * 启用当前预设。
     *
     * @param entity 目标实体
     */
    @Override
    public void enable(@NotNull Entity entity) {
        this.enable(entity, TrailModeTypeEnum.DEFAULT, null);
    }

    /**
     * 启用当前预置，可覆盖拖尾运动模式与 Dust 颜色。
     *
     * @param entity 目标实体
     * @param mode   拖尾运动模式
     * @param color  Dust 颜色
     */
    void enable(@NotNull Entity entity, @NotNull TrailModeTypeEnum mode, @Nullable Color color) {
        if (this.projectileTrail != null) {
            // 抛射物效果没有常驻任务，仅登记开关，发射时由监听器附加一次性拖尾
            ProjectileParticleManager.enable(entity.getUniqueId(), this);
            return;
        }
        if (this.wingModel != null) {
            HandyParticleCore.setWings(entity, this.wingModel, this.wingColor);
            return;
        }
        if (this.blockTrail != null) {
            HandyParticleCore.setBlock(entity, this.blockTrail);
            return;
        }
        if (this.itemRain != null) {
            HandyParticleCore.setItemRain(entity, this.itemRain);
            return;
        }
        if (this.confetti != null) {
            HandyParticleCore.setConfetti(entity, this.confetti);
            return;
        }
        if (this.elytraTrail != null) {
            HandyParticleCore.setElytra(entity, this.elytraTrail);
            return;
        }
        HandyParticleCore.setTrail(entity, this.builtinTrail, mode, color);
    }
}
