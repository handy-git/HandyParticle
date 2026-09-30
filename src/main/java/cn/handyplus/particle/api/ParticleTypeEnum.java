package cn.handyplus.particle.api;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.confetti.ConfettiTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 可直接启用的内置粒子效果。
 *
 * @author handy
 */
public enum ParticleTypeEnum {
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
     * 自定义颜色的 Dust 拖尾。
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
     * 红、黄、绿三色纸屑。
     */
    CONFETTI_RED_YELLOW_GREEN(ConfettiTypeEnum.RED_YELLOW_GREEN),
    /**
     * 白、黑、紫三色纸屑。
     */
    CONFETTI_WHITE_BLACK_PURPLE(ConfettiTypeEnum.WHITE_BLACK_PURPLE),
    /**
     * 白、红、蓝三色纸屑。
     */
    CONFETTI_WHITE_RED_BLUE(ConfettiTypeEnum.WHITE_RED_BLUE),
    /**
     * 绿、蓝、黑三色纸屑。
     */
    CONFETTI_GREEN_BLUE_BLACK(ConfettiTypeEnum.GREEN_BLUE_BLACK),
    /**
     * 红、黄、白三色纸屑。
     */
    CONFETTI_RED_YELLOW_WHITE(ConfettiTypeEnum.RED_YELLOW_WHITE),
    /**
     * 黑、黄、紫三色纸屑。
     */
    CONFETTI_BLACK_YELLOW_PURPLE(ConfettiTypeEnum.BLACK_YELLOW_PURPLE),
    /**
     * 红、绿、黑三色纸屑。
     */
    CONFETTI_RED_GREEN_BLACK(ConfettiTypeEnum.RED_GREEN_BLACK);

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
     * 固定物品组合的纸屑效果。
     */
    @Getter(AccessLevel.PACKAGE)
    @Nullable
    private final ConfettiTypeEnum confetti;
    /**
     * 效果所属类别。
     */
    @Getter
    private final ParticleCategoryTypeEnum category;

    /**
     * 创建内置拖尾类型。
     *
     * @param builtinTrail 内置拖尾
     */
    ParticleTypeEnum(@NotNull TrailTypeEnum builtinTrail) {
        this.builtinTrail = builtinTrail;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = null;
        this.category = ParticleCategoryTypeEnum.TRAIL;
    }

    /**
     * 创建方块拖尾类型。
     *
     * @param blockTrail 方块拖尾
     */
    ParticleTypeEnum(@NotNull BlockTrailTypeEnum blockTrail) {
        this.builtinTrail = null;
        this.blockTrail = blockTrail;
        this.itemRain = null;
        this.confetti = null;
        this.category = ParticleCategoryTypeEnum.BLOCK;
    }

    /**
     * 创建物品雨类型。
     *
     * @param itemRain 物品雨组合
     */
    ParticleTypeEnum(@NotNull ItemRainTypeEnum itemRain) {
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = itemRain;
        this.confetti = null;
        this.category = ParticleCategoryTypeEnum.RAIN;
    }

    /**
     * 创建纸屑效果类型。
     *
     * @param confetti 纸屑物品组合
     */
    ParticleTypeEnum(@NotNull ConfettiTypeEnum confetti) {
        this.builtinTrail = null;
        this.blockTrail = null;
        this.itemRain = null;
        this.confetti = confetti;
        this.category = ParticleCategoryTypeEnum.CONFETTI;
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
     * 判断效果是否支持自定义颜色。
     *
     * @return 是否支持自定义颜色
     */
    public boolean supportsColor() {
        return builtinTrail != null && builtinTrail.supportsColor();
    }

    /**
     * 按名称查找粒子类型。
     *
     * @param value 类型名称
     * @return 粒子类型，未找到时返回 null
     */
    @NotNull
    public static ParticleTypeEnum getEnumThrow(@NotNull String value) {
        String typeName = value.split("\\(", 2)[0].trim();
        for (ParticleTypeEnum particleType : values()) {
            if (particleType.name().equalsIgnoreCase(typeName)) {
                return particleType;
            }
        }
        throw new RuntimeException("not found particleType: " + value);
    }

}
