package cn.handyplus.lib.particle;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 固定粒子翅膀组合。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum ParticleWingType {
    /**
     * Angel 模型的 7 种图案组合。
     */
    ANGEL_GRADIENT1("Angel", "Gradient1", WingData.ANGEL_GRADIENT1),
    /**
     * Angel 模型 + Gradient2 图案。
     */
    ANGEL_GRADIENT2("Angel", "Gradient2", WingData.ANGEL_GRADIENT2),
    /**
     * Angel 模型 + Gradient3 图案。
     */
    ANGEL_GRADIENT3("Angel", "Gradient3", WingData.ANGEL_GRADIENT3),
    /**
     * Angel 模型 + Mushroom 图案。
     */
    ANGEL_MUSHROOM("Angel", "Mushroom", WingData.ANGEL_MUSHROOM),
    /**
     * Angel 模型 + Fire 图案。
     */
    ANGEL_FIRE("Angel", "Fire", WingData.ANGEL_FIRE),
    /**
     * Angel 模型 + Colors 图案。
     */
    ANGEL_COLORS("Angel", "Colors", WingData.ANGEL_COLORS),
    /**
     * Angel 模型 + Fly 图案。
     */
    ANGEL_FLY("Angel", "Fly", WingData.ANGEL_FLY),
    /**
     * Butterfly 模型的 7 种图案组合。
     */
    BUTTERFLY_GRADIENT1("Butterfly", "Gradient1", WingData.BUTTERFLY_GRADIENT1),
    /**
     * Butterfly 模型 + Gradient2 图案。
     */
    BUTTERFLY_GRADIENT2("Butterfly", "Gradient2", WingData.BUTTERFLY_GRADIENT2),
    /**
     * Butterfly 模型 + Gradient3 图案。
     */
    BUTTERFLY_GRADIENT3("Butterfly", "Gradient3", WingData.BUTTERFLY_GRADIENT3),
    /**
     * Butterfly 模型 + Mushroom 图案。
     */
    BUTTERFLY_MUSHROOM("Butterfly", "Mushroom", WingData.BUTTERFLY_MUSHROOM),
    /**
     * Butterfly 模型 + Fire 图案。
     */
    BUTTERFLY_FIRE("Butterfly", "Fire", WingData.BUTTERFLY_FIRE),
    /**
     * Butterfly 模型 + Colors 图案。
     */
    BUTTERFLY_COLORS("Butterfly", "Colors", WingData.BUTTERFLY_COLORS),
    /**
     * Butterfly 模型 + Fly 图案。
     */
    BUTTERFLY_FLY("Butterfly", "Fly", WingData.BUTTERFLY_FLY),
    /**
     * Elf 模型的 7 种图案组合。
     */
    ELF_GRADIENT1("Elf", "Gradient1", WingData.ELF_GRADIENT1),
    /**
     * Elf 模型 + Gradient2 图案。
     */
    ELF_GRADIENT2("Elf", "Gradient2", WingData.ELF_GRADIENT2),
    /**
     * Elf 模型 + Gradient3 图案。
     */
    ELF_GRADIENT3("Elf", "Gradient3", WingData.ELF_GRADIENT3),
    /**
     * Elf 模型 + Mushroom 图案。
     */
    ELF_MUSHROOM("Elf", "Mushroom", WingData.ELF_MUSHROOM),
    /**
     * Elf 模型 + Fire 图案。
     */
    ELF_FIRE("Elf", "Fire", WingData.ELF_FIRE),
    /**
     * Elf 模型 + Colors 图案。
     */
    ELF_COLORS("Elf", "Colors", WingData.ELF_COLORS),
    /**
     * Elf 模型 + Fly 图案。
     */
    ELF_FLY("Elf", "Fly", WingData.ELF_FLY),
    /**
     * Long 模型的 7 种图案组合。
     */
    LONG_GRADIENT1("Long", "Gradient1", WingData.LONG_GRADIENT1),
    LONG_GRADIENT2("Long", "Gradient2", WingData.LONG_GRADIENT2),
    LONG_GRADIENT3("Long", "Gradient3", WingData.LONG_GRADIENT3),
    LONG_MUSHROOM("Long", "Mushroom", WingData.LONG_MUSHROOM),
    LONG_FIRE("Long", "Fire", WingData.LONG_FIRE),
    LONG_COLORS("Long", "Colors", WingData.LONG_COLORS),
    LONG_FLY("Long", "Fly", WingData.LONG_FLY),
    /**
     * Butterfly2 模型的 7 种图案组合。
     */
    BUTTERFLY2_GRADIENT1("Butterfly2", "Gradient1", WingData.BUTTERFLY2_GRADIENT1),
    BUTTERFLY2_GRADIENT2("Butterfly2", "Gradient2", WingData.BUTTERFLY2_GRADIENT2),
    BUTTERFLY2_GRADIENT3("Butterfly2", "Gradient3", WingData.BUTTERFLY2_GRADIENT3),
    BUTTERFLY2_MUSHROOM("Butterfly2", "Mushroom", WingData.BUTTERFLY2_MUSHROOM),
    BUTTERFLY2_FIRE("Butterfly2", "Fire", WingData.BUTTERFLY2_FIRE),
    BUTTERFLY2_COLORS("Butterfly2", "Colors", WingData.BUTTERFLY2_COLORS),
    BUTTERFLY2_FLY("Butterfly2", "Fly", WingData.BUTTERFLY2_FLY),
    /**
     * Short 模型的 7 种图案组合。
     */
    SHORT_GRADIENT1("Short", "Gradient1", WingData.SHORT_GRADIENT1),
    SHORT_GRADIENT2("Short", "Gradient2", WingData.SHORT_GRADIENT2),
    SHORT_GRADIENT3("Short", "Gradient3", WingData.SHORT_GRADIENT3),
    SHORT_MUSHROOM("Short", "Mushroom", WingData.SHORT_MUSHROOM),
    SHORT_FIRE("Short", "Fire", WingData.SHORT_FIRE),
    SHORT_COLORS("Short", "Colors", WingData.SHORT_COLORS),
    SHORT_FLY("Short", "Fly", WingData.SHORT_FLY),
    /**
     * Demon 模型的 7 种图案组合。
     */
    DEMON_GRADIENT1("Demon", "Gradient1", WingData.DEMON_GRADIENT1),
    DEMON_GRADIENT2("Demon", "Gradient2", WingData.DEMON_GRADIENT2),
    DEMON_GRADIENT3("Demon", "Gradient3", WingData.DEMON_GRADIENT3),
    DEMON_MUSHROOM("Demon", "Mushroom", WingData.DEMON_MUSHROOM),
    DEMON_FIRE("Demon", "Fire", WingData.DEMON_FIRE),
    DEMON_COLORS("Demon", "Colors", WingData.DEMON_COLORS),
    DEMON_FLY("Demon", "Fly", WingData.DEMON_FLY),
    /**
     * Night 模型的 7 种图案组合。
     */
    NIGHT_GRADIENT1("Night", "Gradient1", WingData.NIGHT_GRADIENT1),
    NIGHT_GRADIENT2("Night", "Gradient2", WingData.NIGHT_GRADIENT2),
    NIGHT_GRADIENT3("Night", "Gradient3", WingData.NIGHT_GRADIENT3),
    NIGHT_MUSHROOM("Night", "Mushroom", WingData.NIGHT_MUSHROOM),
    NIGHT_FIRE("Night", "Fire", WingData.NIGHT_FIRE),
    NIGHT_COLORS("Night", "Colors", WingData.NIGHT_COLORS),
    NIGHT_FLY("Night", "Fly", WingData.NIGHT_FLY);

    /**
     * 翅膀模型名称。
     */
    private final String modelName;
    /**
     * 翅膀图案名称。
     */
    private final String patternName;
    /**
     * 预先由 model 与 pattern 合成的固定粒子点。
     */
    private final WingPoint[] points;

}
