package cn.handyplus.particle.api;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.block.BlockTrailTypeEnum;
import cn.handyplus.particle.core.confetti.ConfettiTypeEnum;
import cn.handyplus.particle.core.rain.ItemRainTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.trail.TrailTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 粒子效果配置。
 *
 * <p>该类使用不可变配置对象代替枚举，使调用方可以预设或动态组合效果参数。</p>
 *
 * @author handy
 */
@Getter
public final class ParticleType {

    /**
     * 爱心拖尾。
     */
    public static final ParticleType TRAIL_HEART = trail("TRAIL_HEART", TrailTypeEnum.TRAIL_HEART);
    /**
     * 愤怒村民拖尾。
     */
    public static final ParticleType TRAIL_ANGRY = trail("TRAIL_ANGRY", TrailTypeEnum.TRAIL_ANGRY);
    /**
     * 魔法暴击拖尾。
     */
    public static final ParticleType TRAIL_MAGIC = trail("TRAIL_MAGIC", TrailTypeEnum.TRAIL_MAGIC);
    /**
     * 红石拖尾。
     */
    public static final ParticleType TRAIL_FUN = trail("TRAIL_FUN", TrailTypeEnum.TRAIL_FUN);
    /**
     * 云拖尾。
     */
    public static final ParticleType TRAIL_CLOUD = trail("TRAIL_CLOUD", TrailTypeEnum.TRAIL_CLOUD);
    /**
     * 女巫拖尾。
     */
    public static final ParticleType TRAIL_WITCH = trail("TRAIL_WITCH", TrailTypeEnum.TRAIL_WITCH);
    /**
     * 末影拖尾。
     */
    public static final ParticleType TRAIL_ENDER = trail("TRAIL_ENDER", TrailTypeEnum.TRAIL_ENDER);
    /**
     * 绿色拖尾。
     */
    public static final ParticleType TRAIL_GREEN = trail("TRAIL_GREEN", TrailTypeEnum.TRAIL_GREEN);
    /**
     * 火花拖尾。
     */
    public static final ParticleType TRAIL_SPARK = trail("TRAIL_SPARK", TrailTypeEnum.TRAIL_SPARK);
    /**
     * 火焰拖尾。
     */
    public static final ParticleType TRAIL_FLAME = trail("TRAIL_FLAME", TrailTypeEnum.TRAIL_FLAME);
    /**
     * 白色魔法拖尾。
     */
    public static final ParticleType TRAIL_WHITE = trail("TRAIL_WHITE", TrailTypeEnum.TRAIL_WHITE);
    /**
     * 音符拖尾。
     */
    public static final ParticleType TRAIL_NOTE = trail("TRAIL_NOTE", TrailTypeEnum.TRAIL_NOTE);
    /**
     * 雪花拖尾。
     */
    public static final ParticleType TRAIL_SNOW = trail("TRAIL_SNOW", TrailTypeEnum.TRAIL_SNOW);
    /**
     * 水滴拖尾。
     */
    public static final ParticleType TRAIL_WATER = trail("TRAIL_WATER", TrailTypeEnum.TRAIL_WATER);
    /**
     * 熔岩滴落拖尾。
     */
    public static final ParticleType TRAIL_LAVA = trail("TRAIL_LAVA", TrailTypeEnum.TRAIL_LAVA);
    /**
     * 暴击拖尾。
     */
    public static final ParticleType TRAIL_CRIT = trail("TRAIL_CRIT", TrailTypeEnum.TRAIL_CRIT);
    /**
     * 烟雾拖尾。
     */
    public static final ParticleType TRAIL_SMOKE = trail("TRAIL_SMOKE", TrailTypeEnum.TRAIL_SMOKE);
    /**
     * 药水拖尾。
     */
    public static final ParticleType TRAIL_SPELL = trail("TRAIL_SPELL", TrailTypeEnum.TRAIL_SPELL);
    /**
     * 附魔拖尾。
     */
    public static final ParticleType TRAIL_ENCHANT = trail("TRAIL_ENCHANT", TrailTypeEnum.TRAIL_ENCHANT);
    /**
     * 水花拖尾。
     */
    public static final ParticleType TRAIL_SPLASH = trail("TRAIL_SPLASH", TrailTypeEnum.TRAIL_SPLASH);
    /**
     * 史莱姆拖尾。
     */
    public static final ParticleType TRAIL_SLIME = trail("TRAIL_SLIME", TrailTypeEnum.TRAIL_SLIME);
    /**
     * 雪球拖尾。
     */
    public static final ParticleType TRAIL_SNOWBALL = trail("TRAIL_SNOWBALL", TrailTypeEnum.TRAIL_SNOWBALL);
    /**
     * 虚空拖尾。
     */
    public static final ParticleType TRAIL_VOID = trail("TRAIL_VOID", TrailTypeEnum.TRAIL_VOID);
    /**
     * 熔岩爆裂拖尾。
     */
    public static final ParticleType TRAIL_LAVA_POP = trail("TRAIL_LAVA_POP", TrailTypeEnum.TRAIL_LAVA_POP);
    /**
     * 龙息拖尾。
     */
    public static final ParticleType TRAIL_BREATH = trail("TRAIL_BREATH", TrailTypeEnum.TRAIL_BREATH);
    /**
     * 伤害拖尾。
     */
    public static final ParticleType TRAIL_DAMAGE = trail("TRAIL_DAMAGE", TrailTypeEnum.TRAIL_DAMAGE);
    /**
     * 末地烛拖尾。
     */
    public static final ParticleType TRAIL_END_ROD = trail("TRAIL_END_ROD", TrailTypeEnum.TRAIL_END_ROD);
    /**
     * 不死图腾拖尾。
     */
    public static final ParticleType TRAIL_TOTEM = trail("TRAIL_TOTEM", TrailTypeEnum.TRAIL_TOTEM);
    /**
     * 灵魂拖尾。
     */
    public static final ParticleType TRAIL_SOUL = trail("TRAIL_SOUL", TrailTypeEnum.TRAIL_SOUL);
    /**
     * 灵魂火拖尾。
     */
    public static final ParticleType TRAIL_SOUL_FIRE = trail("TRAIL_SOUL_FIRE", TrailTypeEnum.TRAIL_SOUL_FIRE);
    /**
     * 金色火花拖尾。
     */
    public static final ParticleType TRAIL_GOLD_SPARKS = trail("TRAIL_GOLD_SPARKS", TrailTypeEnum.TRAIL_GOLD_SPARKS);
    /**
     * 发光鱿鱼拖尾。
     */
    public static final ParticleType TRAIL_GLOW_SQUID = trail("TRAIL_GLOW_SQUID", TrailTypeEnum.TRAIL_GLOW_SQUID);
    /**
     * 粉色纸屑拖尾。
     */
    public static final ParticleType TRAIL_PINK_CONFETTI = trail("TRAIL_PINK_CONFETTI", TrailTypeEnum.TRAIL_PINK_CONFETTI);
    /**
     * 支持自定义颜色的 Dust 拖尾。
     */
    public static final ParticleType TRAIL_COLOR = trail("TRAIL_COLOR", TrailTypeEnum.TRAIL_COLOR);
    /**
     * 彩色羊毛方块拖尾。
     */
    public static final ParticleType BLOCK_WOOL = block("BLOCK_WOOL", BlockTrailTypeEnum.BLOCK_WOOL);
    /**
     * 彩色玻璃方块拖尾。
     */
    public static final ParticleType BLOCK_GLASS = block("BLOCK_GLASS", BlockTrailTypeEnum.BLOCK_GLASS);
    /**
     * 下界材质方块拖尾。
     */
    public static final ParticleType BLOCK_NETHER = block("BLOCK_NETHER", BlockTrailTypeEnum.BLOCK_NETHER);
    /**
     * 彩色陶瓦方块拖尾。
     */
    public static final ParticleType BLOCK_TERRACOTTA = block("BLOCK_TERRACOTTA", BlockTrailTypeEnum.BLOCK_TERRACOTTA);
    /**
     * 矿石方块拖尾。
     */
    public static final ParticleType BLOCK_ORE = block("BLOCK_ORE", BlockTrailTypeEnum.BLOCK_ORE);
    /**
     * 红石机械方块拖尾。
     */
    public static final ParticleType BLOCK_REDSTONE = block("BLOCK_REDSTONE", BlockTrailTypeEnum.BLOCK_REDSTONE);
    /**
     * 自然方块拖尾。
     */
    public static final ParticleType BLOCK_NATURE = block("BLOCK_NATURE", BlockTrailTypeEnum.BLOCK_NATURE);
    /**
     * 金属方块拖尾。
     */
    public static final ParticleType BLOCK_METAL = block("BLOCK_METAL", BlockTrailTypeEnum.BLOCK_METAL);
    /**
     * 常用物品雨。
     */
    public static final ParticleType RAIN_COMMON = rain("RAIN_COMMON", ItemRainTypeEnum.RAIN_COMMON);
    /**
     * 金色物品雨。
     */
    public static final ParticleType RAIN_GOLD = rain("RAIN_GOLD", ItemRainTypeEnum.RAIN_GOLD);
    /**
     * 红石物品雨。
     */
    public static final ParticleType RAIN_REDSTONE = rain("RAIN_REDSTONE", ItemRainTypeEnum.RAIN_REDSTONE);
    /**
     * 稀有物品雨。
     */
    public static final ParticleType RAIN_RARE = rain("RAIN_RARE", ItemRainTypeEnum.RAIN_RARE);
    /**
     * 彩色羊毛物品雨。
     */
    public static final ParticleType RAIN_WOOL = rain("RAIN_WOOL", ItemRainTypeEnum.RAIN_WOOL);
    /**
     * 红黄绿纸屑。
     */
    public static final ParticleType CONFETTI_RED_YELLOW_GREEN = confetti("CONFETTI_RED_YELLOW_GREEN", ConfettiTypeEnum.RED_YELLOW_GREEN);
    /**
     * 白黑紫纸屑。
     */
    public static final ParticleType CONFETTI_WHITE_BLACK_PURPLE = confetti("CONFETTI_WHITE_BLACK_PURPLE", ConfettiTypeEnum.WHITE_BLACK_PURPLE);
    /**
     * 白红蓝纸屑。
     */
    public static final ParticleType CONFETTI_WHITE_RED_BLUE = confetti("CONFETTI_WHITE_RED_BLUE", ConfettiTypeEnum.WHITE_RED_BLUE);
    /**
     * 绿蓝黑纸屑。
     */
    public static final ParticleType CONFETTI_GREEN_BLUE_BLACK = confetti("CONFETTI_GREEN_BLUE_BLACK", ConfettiTypeEnum.GREEN_BLUE_BLACK);
    /**
     * 红黄白纸屑。
     */
    public static final ParticleType CONFETTI_RED_YELLOW_WHITE = confetti("CONFETTI_RED_YELLOW_WHITE", ConfettiTypeEnum.RED_YELLOW_WHITE);
    /**
     * 黑黄紫纸屑。
     */
    public static final ParticleType CONFETTI_BLACK_YELLOW_PURPLE = confetti("CONFETTI_BLACK_YELLOW_PURPLE", ConfettiTypeEnum.BLACK_YELLOW_PURPLE);
    /**
     * 红绿黑纸屑。
     */
    public static final ParticleType CONFETTI_RED_GREEN_BLACK = confetti("CONFETTI_RED_GREEN_BLACK", ConfettiTypeEnum.RED_GREEN_BLACK);

    private static final Map<String, ParticleType> BUILTIN_TYPES;

    static {
        Map<String, ParticleType> types = new LinkedHashMap<>();
        for (ParticleType type : getBuiltinTypes()) {
            types.put(type.name, type);
        }
        BUILTIN_TYPES = Collections.unmodifiableMap(types);
    }

    /**
     * 配置名称，用于命令显示和持久化。
     */
    @Getter
    private final String name;
    /**
     * 效果所属类别。
     */
    private final ParticleCategoryTypeEnum category;
    /**
     * 拖尾类型，非拖尾配置为空。
     */
    private final TrailTypeEnum builtinTrail;
    /**
     * 方块拖尾类型，非方块配置为空。
     */
    private final BlockTrailTypeEnum blockTrail;
    /**
     * 物品雨类型，非物品雨配置为空。
     */
    private final ItemRainTypeEnum itemRain;
    /**
     * 纸屑类型，非纸屑配置为空。
     */
    private final ConfettiTypeEnum confetti;
    /**
     * 拖尾运动模式，非拖尾配置为空。
     */
    private final TrailModeTypeEnum mode;
    /**
     * Dust 拖尾颜色，其他类型为空。
     */
    private final Color color;
    /**
     * 翅膀模型，非翅膀配置为空。
     */
    private final WingModelEnum wingModel;
    /**
     * 翅膀颜色图案，非翅膀配置为空。
     */
    private final WingColorEnum wingColor;

    /**
     * 创建可自定义模式和颜色的拖尾配置。
     */
    @NotNull
    public static ParticleType trail(@NotNull TrailTypeEnum trail, @Nullable TrailModeTypeEnum mode, @Nullable Color color) {
        return new ParticleType(trail.name(), ParticleCategoryTypeEnum.TRAIL, trail, null, null, null, mode == null ? TrailModeTypeEnum.DEFAULT : mode, color, null, null);
    }

    /**
     * 创建翅膀配置。
     */
    @NotNull
    public static ParticleType wing(@NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        return new ParticleType("WING_" + model.name() + "_" + color.name(), ParticleCategoryTypeEnum.WING, null, null, null, null, null, null, model, color);
    }

    /**
     * 返回所有粒子配置。
     */
    @NotNull
    public static List<ParticleType> values() {
        return Collections.unmodifiableList(getBuiltinTypes());
    }

    /**
     * 按名称查找粒子配置。
     */
    @NotNull
    public static ParticleType getEnumThrow(@NotNull String value) {
        String typeName = value.split("\\(", 2)[0].trim();
        ParticleType type = BUILTIN_TYPES.get(typeName.toUpperCase(Locale.ROOT));
        if (type == null) {
            throw new RuntimeException("not found particleType: " + value);
        }
        return type;
    }

    /**
     * 获取类型描述。
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("particleTypeDesc." + name);
    }

    /**
     * 判断当前拖尾是否支持自定义颜色。
     */
    public boolean supportsColor() {
        return builtinTrail != null && builtinTrail.supportsColor();
    }

    /**
     * 创建固定模式的拖尾配置。
     */
    private static ParticleType trail(@NotNull String name, @NotNull TrailTypeEnum trail) {
        return new ParticleType(name, ParticleCategoryTypeEnum.TRAIL, trail, null, null, null, TrailModeTypeEnum.DEFAULT, null, null, null);
    }

    /**
     * 创建方块拖尾配置。
     */
    private static ParticleType block(@NotNull String name, @NotNull BlockTrailTypeEnum block) {
        return new ParticleType(name, ParticleCategoryTypeEnum.BLOCK, null, block, null, null, null, null, null, null);
    }

    /**
     * 创建物品雨配置。
     */
    private static ParticleType rain(@NotNull String name, @NotNull ItemRainTypeEnum rain) {
        return new ParticleType(name, ParticleCategoryTypeEnum.RAIN, null, null, rain, null, null, null, null, null);
    }

    /**
     * 创建纸屑配置。
     */
    private static ParticleType confetti(@NotNull String name, @NotNull ConfettiTypeEnum confetti) {
        return new ParticleType(name, ParticleCategoryTypeEnum.CONFETTI, null, null, null, confetti, null, null, null, null);
    }

    /**
     * 收集所有可通过名称解析的配置。
     */
    @NotNull
    private static List<ParticleType> getBuiltinTypes() {
        return Arrays.asList(TRAIL_HEART, TRAIL_ANGRY, TRAIL_MAGIC, TRAIL_FUN, TRAIL_CLOUD, TRAIL_WITCH,
                TRAIL_ENDER, TRAIL_GREEN, TRAIL_SPARK, TRAIL_FLAME, TRAIL_WHITE, TRAIL_NOTE, TRAIL_SNOW,
                TRAIL_WATER, TRAIL_LAVA, TRAIL_CRIT, TRAIL_SMOKE, TRAIL_SPELL, TRAIL_ENCHANT, TRAIL_SPLASH,
                TRAIL_SLIME, TRAIL_SNOWBALL, TRAIL_VOID, TRAIL_LAVA_POP, TRAIL_BREATH, TRAIL_DAMAGE,
                TRAIL_END_ROD, TRAIL_TOTEM, TRAIL_SOUL, TRAIL_SOUL_FIRE, TRAIL_GOLD_SPARKS, TRAIL_GLOW_SQUID,
                TRAIL_PINK_CONFETTI, TRAIL_COLOR, BLOCK_WOOL, BLOCK_GLASS, BLOCK_NETHER, BLOCK_TERRACOTTA,
                BLOCK_ORE, BLOCK_REDSTONE, BLOCK_NATURE, BLOCK_METAL, RAIN_COMMON, RAIN_GOLD, RAIN_REDSTONE,
                RAIN_RARE, RAIN_WOOL, CONFETTI_RED_YELLOW_GREEN, CONFETTI_WHITE_BLACK_PURPLE,
                CONFETTI_WHITE_RED_BLUE, CONFETTI_GREEN_BLUE_BLACK, CONFETTI_RED_YELLOW_WHITE,
                CONFETTI_BLACK_YELLOW_PURPLE, CONFETTI_RED_GREEN_BLACK);
    }

    private ParticleType(@NotNull String name, @NotNull ParticleCategoryTypeEnum category,
                         TrailTypeEnum builtinTrail, BlockTrailTypeEnum blockTrail,
                         ItemRainTypeEnum itemRain, ConfettiTypeEnum confetti,
                         TrailModeTypeEnum mode, Color color,
                         WingModelEnum wingModel, WingColorEnum wingColor) {
        this.name = name;
        this.category = category;
        this.builtinTrail = builtinTrail;
        this.blockTrail = blockTrail;
        this.itemRain = itemRain;
        this.confetti = confetti;
        this.mode = mode;
        this.color = color;
        this.wingModel = wingModel;
        this.wingColor = wingColor;
    }

}
