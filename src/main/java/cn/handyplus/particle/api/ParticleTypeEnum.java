package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 插件内置的粒子效果预设。
 *
 * @author handy
 */
public enum ParticleTypeEnum implements ParticleType {
    /**
     * 天使渐变一翅膀。
     */
    WING_GRADIENT1_ANGEL,
    /**
     * 天使渐变二翅膀。
     */
    WING_GRADIENT2_ANGEL,
    /**
     * 天使渐变三翅膀。
     */
    WING_GRADIENT3_ANGEL,
    /**
     * 天使蘑菇翅膀。
     */
    WING_MUSHROOM_ANGEL,
    /**
     * 天使火焰翅膀。
     */
    WING_FIRE_ANGEL,
    /**
     * 天使彩色翅膀。
     */
    WING_COLORS_ANGEL,
    /**
     * 天使飞行翅膀。
     */
    WING_FLY_ANGEL,

    /**
     * 蝴蝶渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY,
    /**
     * 蝴蝶渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY,
    /**
     * 蝴蝶渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY,
    /**
     * 蝴蝶蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY,
    /**
     * 蝴蝶火焰翅膀。
     */
    WING_FIRE_BUTTERFLY,
    /**
     * 蝴蝶彩色翅膀。
     */
    WING_COLORS_BUTTERFLY,
    /**
     * 蝴蝶飞行翅膀。
     */
    WING_FLY_BUTTERFLY,

    /**
     * 精灵渐变一翅膀。
     */
    WING_GRADIENT1_ELF,
    /**
     * 精灵渐变二翅膀。
     */
    WING_GRADIENT2_ELF,
    /**
     * 精灵渐变三翅膀。
     */
    WING_GRADIENT3_ELF,
    /**
     * 精灵蘑菇翅膀。
     */
    WING_MUSHROOM_ELF,
    /**
     * 精灵火焰翅膀。
     */
    WING_FIRE_ELF,
    /**
     * 精灵彩色翅膀。
     */
    WING_COLORS_ELF,
    /**
     * 精灵飞行翅膀。
     */
    WING_FLY_ELF,

    /**
     * 长翼渐变一翅膀。
     */
    WING_GRADIENT1_LONG,
    /**
     * 长翼渐变二翅膀。
     */
    WING_GRADIENT2_LONG,
    /**
     * 长翼渐变三翅膀。
     */
    WING_GRADIENT3_LONG,
    /**
     * 长翼蘑菇翅膀。
     */
    WING_MUSHROOM_LONG,
    /**
     * 长翼火焰翅膀。
     */
    WING_FIRE_LONG,
    /**
     * 长翼彩色翅膀。
     */
    WING_COLORS_LONG,
    /**
     * 长翼飞行翅膀。
     */
    WING_FLY_LONG,

    /**
     * 蝴蝶二渐变一翅膀。
     */
    WING_GRADIENT1_BUTTERFLY2,
    /**
     * 蝴蝶二渐变二翅膀。
     */
    WING_GRADIENT2_BUTTERFLY2,
    /**
     * 蝴蝶二渐变三翅膀。
     */
    WING_GRADIENT3_BUTTERFLY2,
    /**
     * 蝴蝶二蘑菇翅膀。
     */
    WING_MUSHROOM_BUTTERFLY2,
    /**
     * 蝴蝶二火焰翅膀。
     */
    WING_FIRE_BUTTERFLY2,
    /**
     * 蝴蝶二彩色翅膀。
     */
    WING_COLORS_BUTTERFLY2,
    /**
     * 蝴蝶二飞行翅膀。
     */
    WING_FLY_BUTTERFLY2,

    /**
     * 短翼渐变一翅膀。
     */
    WING_GRADIENT1_SHORT,
    /**
     * 短翼渐变二翅膀。
     */
    WING_GRADIENT2_SHORT,
    /**
     * 短翼渐变三翅膀。
     */
    WING_GRADIENT3_SHORT,
    /**
     * 短翼蘑菇翅膀。
     */
    WING_MUSHROOM_SHORT,
    /**
     * 短翼火焰翅膀。
     */
    WING_FIRE_SHORT,
    /**
     * 短翼彩色翅膀。
     */
    WING_COLORS_SHORT,
    /**
     * 短翼飞行翅膀。
     */
    WING_FLY_SHORT,

    /**
     * 恶魔渐变一翅膀。
     */
    WING_GRADIENT1_DEMON,
    /**
     * 恶魔渐变二翅膀。
     */
    WING_GRADIENT2_DEMON,
    /**
     * 恶魔渐变三翅膀。
     */
    WING_GRADIENT3_DEMON,
    /**
     * 恶魔蘑菇翅膀。
     */
    WING_MUSHROOM_DEMON,
    /**
     * 恶魔火焰翅膀。
     */
    WING_FIRE_DEMON,
    /**
     * 恶魔彩色翅膀。
     */
    WING_COLORS_DEMON,
    /**
     * 恶魔飞行翅膀。
     */
    WING_FLY_DEMON,

    /**
     * 暗夜渐变一翅膀。
     */
    WING_GRADIENT1_NIGHT,
    /**
     * 暗夜渐变二翅膀。
     */
    WING_GRADIENT2_NIGHT,
    /**
     * 暗夜渐变三翅膀。
     */
    WING_GRADIENT3_NIGHT,
    /**
     * 暗夜蘑菇翅膀。
     */
    WING_MUSHROOM_NIGHT,
    /**
     * 暗夜火焰翅膀。
     */
    WING_FIRE_NIGHT,
    /**
     * 暗夜彩色翅膀。
     */
    WING_COLORS_NIGHT,
    /**
     * 暗夜飞行翅膀。
     */
    WING_FLY_NIGHT,

    /**
     * 爱心拖尾。
     */
    TRAIL_HEART,
    /**
     * 愤怒村民拖尾。
     */
    TRAIL_ANGRY,
    /**
     * 魔法暴击拖尾。
     */
    TRAIL_MAGIC,
    /**
     * 红石拖尾。
     */
    TRAIL_FUN,
    /**
     * 云朵拖尾。
     */
    TRAIL_CLOUD,
    /**
     * 女巫拖尾。
     */
    TRAIL_WITCH,
    /**
     * 末影拖尾。
     */
    TRAIL_ENDER,
    /**
     * 绿色拖尾。
     */
    TRAIL_GREEN,
    /**
     * 火花拖尾。
     */
    TRAIL_SPARK,
    /**
     * 火焰拖尾。
     */
    TRAIL_FLAME,
    /**
     * 白色魔法拖尾。
     */
    TRAIL_WHITE,
    /**
     * 音符拖尾。
     */
    TRAIL_NOTE,
    /**
     * 雪花拖尾。
     */
    TRAIL_SNOW,
    /**
     * 水滴拖尾。
     */
    TRAIL_WATER,
    /**
     * 熔岩滴落拖尾。
     */
    TRAIL_LAVA,
    /**
     * 暴击拖尾。
     */
    TRAIL_CRIT,
    /**
     * 烟雾拖尾。
     */
    TRAIL_SMOKE,
    /**
     * 药水拖尾。
     */
    TRAIL_SPELL,
    /**
     * 附魔拖尾。
     */
    TRAIL_ENCHANT,
    /**
     * 水花拖尾。
     */
    TRAIL_SPLASH,
    /**
     * 史莱姆拖尾。
     */
    TRAIL_SLIME,
    /**
     * 雪球拖尾。
     */
    TRAIL_SNOWBALL,
    /**
     * 虚空拖尾。
     */
    TRAIL_VOID,
    /**
     * 熔岩爆裂拖尾。
     */
    TRAIL_LAVA_POP,
    /**
     * 龙息拖尾。
     */
    TRAIL_BREATH,
    /**
     * 伤害拖尾。
     */
    TRAIL_DAMAGE,
    /**
     * 末地烛拖尾。
     */
    TRAIL_END_ROD,
    /**
     * 不死图腾拖尾。
     */
    TRAIL_TOTEM,
    /**
     * 灵魂拖尾。
     */
    TRAIL_SOUL,
    /**
     * 灵魂火拖尾。
     */
    TRAIL_SOUL_FIRE,
    /**
     * 金色火花拖尾。
     */
    TRAIL_GOLD_SPARKS,
    /**
     * 发光鱿鱼拖尾。
     */
    TRAIL_GLOW_SQUID,
    /**
     * 粉色纸屑拖尾。
     */
    TRAIL_PINK_CONFETTI,
    /**
     * 可指定颜色的 Dust 拖尾。
     */
    TRAIL_COLOR,

    /**
     * 彩色羊毛方块拖尾。
     */
    BLOCK_WOOL,
    /**
     * 彩色玻璃方块拖尾。
     */
    BLOCK_GLASS,
    /**
     * 下界材质方块拖尾。
     */
    BLOCK_NETHER,
    /**
     * 彩色陶瓦方块拖尾。
     */
    BLOCK_TERRACOTTA,
    /**
     * 矿石方块拖尾。
     */
    BLOCK_ORE,
    /**
     * 红石机械方块拖尾。
     */
    BLOCK_REDSTONE,
    /**
     * 自然方块拖尾。
     */
    BLOCK_NATURE,
    /**
     * 金属方块拖尾。
     */
    BLOCK_METAL,

    /**
     * 常用物品雨。
     */
    RAIN_COMMON,
    /**
     * 金色物品雨。
     */
    RAIN_GOLD,
    /**
     * 红石物品雨。
     */
    RAIN_REDSTONE,
    /**
     * 稀有物品雨。
     */
    RAIN_RARE,
    /**
     * 彩色羊毛物品雨。
     */
    RAIN_WOOL,

    /**
     * 红黄绿纸屑。
     */
    CONFETTI_RED_YELLOW_GREEN,
    /**
     * 白黑紫纸屑。
     */
    CONFETTI_WHITE_BLACK_PURPLE,
    /**
     * 白红蓝纸屑。
     */
    CONFETTI_WHITE_RED_BLUE,
    /**
     * 绿蓝黑纸屑。
     */
    CONFETTI_GREEN_BLUE_BLACK,
    /**
     * 红黄白纸屑。
     */
    CONFETTI_RED_YELLOW_WHITE,
    /**
     * 黑黄紫纸屑。
     */
    CONFETTI_BLACK_YELLOW_PURPLE,
    /**
     * 红绿黑纸屑。
     */
    CONFETTI_RED_GREEN_BLACK,

    /**
     * 贵金属鞘翅拖尾。
     */
    ELYTRA_METAL,
    /**
     * 海晶鞘翅拖尾。
     */
    ELYTRA_PRISMARINE,
    /**
     * 珍宝鞘翅拖尾。
     */
    ELYTRA_DRAGON,

    /**
     * 魔法抛射物拖尾。
     */
    PROJECTILE_MAGIC,
    /**
     * 绿色火花抛射物拖尾。
     */
    PROJECTILE_GREEN_SPARKLE,
    /**
     * 彩色尘土抛射物拖尾。
     */
    PROJECTILE_COLOR_DUST,
    /**
     * 暴击抛射物拖尾。
     */
    PROJECTILE_CRIT,
    /**
     * 火花抛射物拖尾。
     */
    PROJECTILE_SPARKS,
    /**
     * 火焰抛射物拖尾。
     */
    PROJECTILE_FLAME,
    /**
     * 飘落字符抛射物拖尾。
     */
    PROJECTILE_GLYPH,
    /**
     * 爱心抛射物拖尾。
     */
    PROJECTILE_HEART,
    /**
     * 瞬间法术抛射物拖尾。
     */
    PROJECTILE_SPELL,
    /**
     * 烟雾抛射物拖尾。
     */
    PROJECTILE_SMOKE,
    /**
     * 浓烟抛射物拖尾。
     */
    PROJECTILE_LARGE_SMOKE,
    /**
     * 熔岩迸溅抛射物拖尾。
     */
    PROJECTILE_LAVA_POP,
    /**
     * 熔岩滴落抛射物拖尾。
     */
    PROJECTILE_LAVA_DRIP,
    /**
     * 魔法暴击抛射物拖尾。
     */
    PROJECTILE_MAGIC_CRIT,
    /**
     * 音符抛射物拖尾。
     */
    PROJECTILE_NOTE,
    /**
     * 附魔抛射物拖尾。
     */
    PROJECTILE_ENCHANT,
    /**
     * 传送门抛射物拖尾。
     */
    PROJECTILE_PORTAL,
    /**
     * 药水旋涡抛射物拖尾。
     */
    PROJECTILE_POTION_SWIRL,
    /**
     * 史莱姆抛射物拖尾。
     */
    PROJECTILE_SLIME,
    /**
     * 雪花抛射物拖尾。
     */
    PROJECTILE_SNOW,
    /**
     * 雪球抛射物拖尾。
     */
    PROJECTILE_SNOWBALL,
    /**
     * 水花抛射物拖尾。
     */
    PROJECTILE_SPLASH,
    /**
     * 愤怒村民抛射物拖尾。
     */
    PROJECTILE_ANGRY_VILLAGER,
    /**
     * 虚空雾抛射物拖尾。
     */
    PROJECTILE_VOID_FOG,
    /**
     * 水滴抛射物拖尾。
     */
    PROJECTILE_WATER_DRIP,
    /**
     * 末地烛抛射物拖尾。
     */
    PROJECTILE_END_ROD,
    /**
     * 不死图腾抛射物拖尾。
     */
    PROJECTILE_TOTEM,
    /**
     * 灵魂火抛射物拖尾。
     */
    PROJECTILE_SOUL_FIRE,
    /**
     * 金色火花抛射物拖尾。
     */
    PROJECTILE_GOLD_SPARKS,
    /**
     * 龙息抛射物拖尾。
     */
    PROJECTILE_DRAGON_BREATH,
    /**
     * 菌丝抛射物拖尾。
     */
    PROJECTILE_MYCELIUM,
    /**
     * 爆炸抛射物拖尾。
     */
    PROJECTILE_EXPLOSION,
    /**
     * 爆炸发射抛射物拖尾。
     */
    PROJECTILE_EXPLOSION_EMITTER,

    /**
     * 末影信号武器攻击粒子。
     */
    WEAPON_ENDER_SIGNAL,
    /**
     * 刷怪箱火焰武器攻击粒子。
     */
    WEAPON_SPAWNER_FLAME,
    /**
     * 雪铲武器攻击粒子。
     */
    WEAPON_SNOW_SHOVEL,
    /**
     * 受伤武器攻击粒子。
     */
    WEAPON_HURT,
    /**
     * 浓烟武器攻击粒子。
     */
    WEAPON_SMOKE,
    /**
     * 烈焰人火球武器攻击粒子。
     */
    WEAPON_FLAME,
    /**
     * 爱心武器攻击粒子。
     */
    WEAPON_HEART,
    /**
     * 音符武器攻击粒子。
     */
    WEAPON_NOTE,
    /**
     * 瞬间法术武器攻击粒子。
     */
    WEAPON_SPELL,
    /**
     * 传送门武器攻击粒子。
     */
    WEAPON_PORTAL,
    /**
     * 暴击武器攻击粒子。
     */
    WEAPON_CRIT,
    /**
     * 气泡武器攻击粒子。
     */
    WEAPON_BUBBLE,
    /**
     * 火焰武器攻击粒子。
     */
    WEAPON_FIRE,
    /**
     * 扫火武器攻击粒子。
     */
    WEAPON_SWEEP,
    /**
     * 伤害指示武器攻击粒子。
     */
    WEAPON_DAMAGE_INDICATOR,
    /**
     * 雪球武器攻击粒子。
     */
    WEAPON_SNOWBALL,
    /**
     * 黏液块武器攻击粒子。
     */
    WEAPON_SLIME,
    /**
     * 水花武器攻击粒子。
     */
    WEAPON_SPLASH,
    /**
     * 村民开心武器攻击粒子。
     */
    WEAPON_VILLAGER_HAPPY,
    /**
     * 巫妖魔法武器攻击粒子。
     */
    WEAPON_WITCH,
    /**
     * 云龙武器攻击粒子。
     */
    WEAPON_CLOUD,
    /**
     * 火花武器攻击粒子。
     */
    WEAPON_FIREWORK,
    /**
     * 大型爆炸武器攻击粒子。
     */
    WEAPON_LARGE_EXPLOSION,
    /**
     * 熔岩迸溅武器攻击粒子。
     */
    WEAPON_LAVA_POP,
    /**
     * 灵光武器攻击粒子。
     */
    WEAPON_GLOW;

    /**
     * 按已保存的名称查找预置，忽略命令补全中的本地化描述。
     *
     * @param value 预置名称，可为空
     * @return 对应预置，未找到时返回 null
     */
    @Nullable
    public native static ParticleTypeEnum getEnum(@Nullable String value);

    /**
     * 按已保存的名称查找预置，忽略命令补全中的本地化描述。
     *
     * @param value 预置名称
     * @return 对应预置
     */
    @NotNull
    public native static ParticleTypeEnum getEnumThrow(@NotNull String value);

    /**
     * 根据已保存的模型和图案查找翅膀预置。
     *
     * @param model 翅膀模型
     * @param color 翅膀图案
     * @return 翅膀预置
     */
    @NotNull
    public native static ParticleTypeEnum wing(@NotNull WingModelEnum model, @NotNull WingColorEnum color);

    /**
     * 获取当前预置所属的粒子类别。
     *
     * @return 粒子类别
     */
    @Override
    @NotNull
    public native ParticleCategoryTypeEnum getCategory();

    /**
     * 获取预置的本地化描述。
     *
     * @return 预置描述
     */
    @NotNull
    public native String getDesc();

    /**
     * 判断当前拖尾是否支持自定义颜色。
     *
     * @return 支持颜色时返回 true
     */
    public native boolean supportsColor();

    /**
     * 启用当前预设。
     *
     * @param entity 目标实体
     */
    @Override
    public native void enable(@NotNull Entity entity);
}
