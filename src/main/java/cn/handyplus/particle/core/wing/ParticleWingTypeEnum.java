package cn.handyplus.particle.core.wing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 固定粒子翅膀组合。
 *
 * @author handy
 */
@AllArgsConstructor
public enum ParticleWingTypeEnum {
    /**
     * Angel 模型 + Gradient1 图案。
     */
    ANGEL_GRADIENT1("Angel", "Gradient1", AngelWingData.GRADIENT1),
    /**
     * Angel 模型 + Gradient2 图案。
     */
    ANGEL_GRADIENT2("Angel", "Gradient2", AngelWingData.GRADIENT2),
    /**
     * Angel 模型 + Gradient3 图案。
     */
    ANGEL_GRADIENT3("Angel", "Gradient3", AngelWingData.GRADIENT3),
    /**
     * Angel 模型 + Mushroom 图案。
     */
    ANGEL_MUSHROOM("Angel", "Mushroom", AngelWingData.MUSHROOM),
    /**
     * Angel 模型 + Fire 图案。
     */
    ANGEL_FIRE("Angel", "Fire", AngelWingData.FIRE),
    /**
     * Angel 模型 + Colors 图案。
     */
    ANGEL_COLORS("Angel", "Colors", AngelWingData.COLORS),
    /**
     * Angel 模型 + Fly 图案。
     */
    ANGEL_FLY("Angel", "Fly", AngelWingData.FLY),
    /**
     * Butterfly 模型 + Gradient1 图案。
     */
    BUTTERFLY_GRADIENT1("Butterfly", "Gradient1", ButterflyWingData.GRADIENT1),
    /**
     * Butterfly 模型 + Gradient2 图案。
     */
    BUTTERFLY_GRADIENT2("Butterfly", "Gradient2", ButterflyWingData.GRADIENT2),
    /**
     * Butterfly 模型 + Gradient3 图案。
     */
    BUTTERFLY_GRADIENT3("Butterfly", "Gradient3", ButterflyWingData.GRADIENT3),
    /**
     * Butterfly 模型 + Mushroom 图案。
     */
    BUTTERFLY_MUSHROOM("Butterfly", "Mushroom", ButterflyWingData.MUSHROOM),
    /**
     * Butterfly 模型 + Fire 图案。
     */
    BUTTERFLY_FIRE("Butterfly", "Fire", ButterflyWingData.FIRE),
    /**
     * Butterfly 模型 + Colors 图案。
     */
    BUTTERFLY_COLORS("Butterfly", "Colors", ButterflyWingData.COLORS),
    /**
     * Butterfly 模型 + Fly 图案。
     */
    BUTTERFLY_FLY("Butterfly", "Fly", ButterflyWingData.FLY),
    /**
     * Elf 模型 + Gradient1 图案。
     */
    ELF_GRADIENT1("Elf", "Gradient1", ElfWingData.GRADIENT1),
    /**
     * Elf 模型 + Gradient2 图案。
     */
    ELF_GRADIENT2("Elf", "Gradient2", ElfWingData.GRADIENT2),
    /**
     * Elf 模型 + Gradient3 图案。
     */
    ELF_GRADIENT3("Elf", "Gradient3", ElfWingData.GRADIENT3),
    /**
     * Elf 模型 + Mushroom 图案。
     */
    ELF_MUSHROOM("Elf", "Mushroom", ElfWingData.MUSHROOM),
    /**
     * Elf 模型 + Fire 图案。
     */
    ELF_FIRE("Elf", "Fire", ElfWingData.FIRE),
    /**
     * Elf 模型 + Colors 图案。
     */
    ELF_COLORS("Elf", "Colors", ElfWingData.COLORS),
    /**
     * Elf 模型 + Fly 图案。
     */
    ELF_FLY("Elf", "Fly", ElfWingData.FLY),
    /**
     * Long 模型 + Gradient1 图案。
     */
    LONG_GRADIENT1("Long", "Gradient1", LongWingData.GRADIENT1),
    /**
     * Long 模型 + Gradient2 图案。
     */
    LONG_GRADIENT2("Long", "Gradient2", LongWingData.GRADIENT2),
    /**
     * Long 模型 + Gradient3 图案。
     */
    LONG_GRADIENT3("Long", "Gradient3", LongWingData.GRADIENT3),
    /**
     * Long 模型 + Mushroom 图案。
     */
    LONG_MUSHROOM("Long", "Mushroom", LongWingData.MUSHROOM),
    /**
     * Long 模型 + Fire 图案。
     */
    LONG_FIRE("Long", "Fire", LongWingData.FIRE),
    /**
     * Long 模型 + Colors 图案。
     */
    LONG_COLORS("Long", "Colors", LongWingData.COLORS),
    /**
     * Long 模型 + Fly 图案。
     */
    LONG_FLY("Long", "Fly", LongWingData.FLY),
    /**
     * Butterfly2 模型 + Gradient1 图案。
     */
    BUTTERFLY2_GRADIENT1("Butterfly2", "Gradient1", Butterfly2WingData.GRADIENT1),
    /**
     * Butterfly2 模型 + Gradient2 图案。
     */
    BUTTERFLY2_GRADIENT2("Butterfly2", "Gradient2", Butterfly2WingData.GRADIENT2),
    /**
     * Butterfly2 模型 + Gradient3 图案。
     */
    BUTTERFLY2_GRADIENT3("Butterfly2", "Gradient3", Butterfly2WingData.GRADIENT3),
    /**
     * Butterfly2 模型 + Mushroom 图案。
     */
    BUTTERFLY2_MUSHROOM("Butterfly2", "Mushroom", Butterfly2WingData.MUSHROOM),
    /**
     * Butterfly2 模型 + Fire 图案。
     */
    BUTTERFLY2_FIRE("Butterfly2", "Fire", Butterfly2WingData.FIRE),
    /**
     * Butterfly2 模型 + Colors 图案。
     */
    BUTTERFLY2_COLORS("Butterfly2", "Colors", Butterfly2WingData.COLORS),
    /**
     * Butterfly2 模型 + Fly 图案。
     */
    BUTTERFLY2_FLY("Butterfly2", "Fly", Butterfly2WingData.FLY),
    /**
     * Short 模型 + Gradient1 图案。
     */
    SHORT_GRADIENT1("Short", "Gradient1", ShortWingData.GRADIENT1),
    /**
     * Short 模型 + Gradient2 图案。
     */
    SHORT_GRADIENT2("Short", "Gradient2", ShortWingData.GRADIENT2),
    /**
     * Short 模型 + Gradient3 图案。
     */
    SHORT_GRADIENT3("Short", "Gradient3", ShortWingData.GRADIENT3),
    /**
     * Short 模型 + Mushroom 图案。
     */
    SHORT_MUSHROOM("Short", "Mushroom", ShortWingData.MUSHROOM),
    /**
     * Short 模型 + Fire 图案。
     */
    SHORT_FIRE("Short", "Fire", ShortWingData.FIRE),
    /**
     * Short 模型 + Colors 图案。
     */
    SHORT_COLORS("Short", "Colors", ShortWingData.COLORS),
    /**
     * Short 模型 + Fly 图案。
     */
    SHORT_FLY("Short", "Fly", ShortWingData.FLY),
    /**
     * Demon 模型 + Gradient1 图案。
     */
    DEMON_GRADIENT1("Demon", "Gradient1", DemonWingData.GRADIENT1),
    /**
     * Demon 模型 + Gradient2 图案。
     */
    DEMON_GRADIENT2("Demon", "Gradient2", DemonWingData.GRADIENT2),
    /**
     * Demon 模型 + Gradient3 图案。
     */
    DEMON_GRADIENT3("Demon", "Gradient3", DemonWingData.GRADIENT3),
    /**
     * Demon 模型 + Mushroom 图案。
     */
    DEMON_MUSHROOM("Demon", "Mushroom", DemonWingData.MUSHROOM),
    /**
     * Demon 模型 + Fire 图案。
     */
    DEMON_FIRE("Demon", "Fire", DemonWingData.FIRE),
    /**
     * Demon 模型 + Colors 图案。
     */
    DEMON_COLORS("Demon", "Colors", DemonWingData.COLORS),
    /**
     * Demon 模型 + Fly 图案。
     */
    DEMON_FLY("Demon", "Fly", DemonWingData.FLY),
    /**
     * Night 模型 + Gradient1 图案。
     */
    NIGHT_GRADIENT1("Night", "Gradient1", NightWingData.GRADIENT1),
    /**
     * Night 模型 + Gradient2 图案。
     */
    NIGHT_GRADIENT2("Night", "Gradient2", NightWingData.GRADIENT2),
    /**
     * Night 模型 + Gradient3 图案。
     */
    NIGHT_GRADIENT3("Night", "Gradient3", NightWingData.GRADIENT3),
    /**
     * Night 模型 + Mushroom 图案。
     */
    NIGHT_MUSHROOM("Night", "Mushroom", NightWingData.MUSHROOM),
    /**
     * Night 模型 + Fire 图案。
     */
    NIGHT_FIRE("Night", "Fire", NightWingData.FIRE),
    /**
     * Night 模型 + Colors 图案。
     */
    NIGHT_COLORS("Night", "Colors", NightWingData.COLORS),
    /**
     * Night 模型 + Fly 图案。
     */
    NIGHT_FLY("Night", "Fly", NightWingData.FLY);

    /**
     * 翅膀模型名称。
     */
    @Getter
    private final String modelName;
    /**
     * 翅膀图案名称。
     */
    @Getter
    private final String patternName;
    /**
     * 预先由 model 与 pattern 合成的固定粒子点。
     */
    @Getter(AccessLevel.PACKAGE)
    private final WingPoint[] points;

}
