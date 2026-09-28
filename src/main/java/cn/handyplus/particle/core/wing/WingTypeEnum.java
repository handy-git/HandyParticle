package cn.handyplus.particle.core.wing;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * 粒子翅膀组合。
 *
 * @author handy
 */
public enum WingTypeEnum {
    /**
     * Angel 模型 + Gradient1 图案。
     */
    ANGEL_GRADIENT1("Angel", "Gradient1", WingModelData.ANGEL, WingPaletteData.GRADIENT1),
    /**
     * Angel 模型 + Gradient2 图案。
     */
    ANGEL_GRADIENT2("Angel", "Gradient2", WingModelData.ANGEL, WingPaletteData.GRADIENT2),
    /**
     * Angel 模型 + Gradient3 图案。
     */
    ANGEL_GRADIENT3("Angel", "Gradient3", WingModelData.ANGEL, WingPaletteData.GRADIENT3),
    /**
     * Angel 模型 + Mushroom 图案。
     */
    ANGEL_MUSHROOM("Angel", "Mushroom", WingModelData.ANGEL, WingPaletteData.MUSHROOM),
    /**
     * Angel 模型 + Fire 图案。
     */
    ANGEL_FIRE("Angel", "Fire", WingModelData.ANGEL, WingPaletteData.FIRE),
    /**
     * Angel 模型 + Colors 图案。
     */
    ANGEL_COLORS("Angel", "Colors", WingModelData.ANGEL, WingPaletteData.COLORS),
    /**
     * Angel 模型 + Fly 图案。
     */
    ANGEL_FLY("Angel", "Fly", WingModelData.ANGEL, WingPaletteData.FLY),
    /**
     * Butterfly 模型 + Gradient1 图案。
     */
    BUTTERFLY_GRADIENT1("Butterfly", "Gradient1", WingModelData.BUTTERFLY, WingPaletteData.GRADIENT1),
    /**
     * Butterfly 模型 + Gradient2 图案。
     */
    BUTTERFLY_GRADIENT2("Butterfly", "Gradient2", WingModelData.BUTTERFLY, WingPaletteData.GRADIENT2),
    /**
     * Butterfly 模型 + Gradient3 图案。
     */
    BUTTERFLY_GRADIENT3("Butterfly", "Gradient3", WingModelData.BUTTERFLY, WingPaletteData.GRADIENT3),
    /**
     * Butterfly 模型 + Mushroom 图案。
     */
    BUTTERFLY_MUSHROOM("Butterfly", "Mushroom", WingModelData.BUTTERFLY, WingPaletteData.MUSHROOM),
    /**
     * Butterfly 模型 + Fire 图案。
     */
    BUTTERFLY_FIRE("Butterfly", "Fire", WingModelData.BUTTERFLY, WingPaletteData.FIRE),
    /**
     * Butterfly 模型 + Colors 图案。
     */
    BUTTERFLY_COLORS("Butterfly", "Colors", WingModelData.BUTTERFLY, WingPaletteData.COLORS),
    /**
     * Butterfly 模型 + Fly 图案。
     */
    BUTTERFLY_FLY("Butterfly", "Fly", WingModelData.BUTTERFLY, WingPaletteData.FLY),
    /**
     * Elf 模型 + Gradient1 图案。
     */
    ELF_GRADIENT1("Elf", "Gradient1", WingModelData.ELF, WingPaletteData.GRADIENT1),
    /**
     * Elf 模型 + Gradient2 图案。
     */
    ELF_GRADIENT2("Elf", "Gradient2", WingModelData.ELF, WingPaletteData.GRADIENT2),
    /**
     * Elf 模型 + Gradient3 图案。
     */
    ELF_GRADIENT3("Elf", "Gradient3", WingModelData.ELF, WingPaletteData.GRADIENT3),
    /**
     * Elf 模型 + Mushroom 图案。
     */
    ELF_MUSHROOM("Elf", "Mushroom", WingModelData.ELF, WingPaletteData.MUSHROOM),
    /**
     * Elf 模型 + Fire 图案。
     */
    ELF_FIRE("Elf", "Fire", WingModelData.ELF, WingPaletteData.FIRE),
    /**
     * Elf 模型 + Colors 图案。
     */
    ELF_COLORS("Elf", "Colors", WingModelData.ELF, WingPaletteData.COLORS),
    /**
     * Elf 模型 + Fly 图案。
     */
    ELF_FLY("Elf", "Fly", WingModelData.ELF, WingPaletteData.FLY),
    /**
     * Long 模型 + Gradient1 图案。
     */
    LONG_GRADIENT1("Long", "Gradient1", WingModelData.LONG, WingPaletteData.GRADIENT1),
    /**
     * Long 模型 + Gradient2 图案。
     */
    LONG_GRADIENT2("Long", "Gradient2", WingModelData.LONG, WingPaletteData.GRADIENT2),
    /**
     * Long 模型 + Gradient3 图案。
     */
    LONG_GRADIENT3("Long", "Gradient3", WingModelData.LONG, WingPaletteData.GRADIENT3),
    /**
     * Long 模型 + Mushroom 图案。
     */
    LONG_MUSHROOM("Long", "Mushroom", WingModelData.LONG, WingPaletteData.MUSHROOM),
    /**
     * Long 模型 + Fire 图案。
     */
    LONG_FIRE("Long", "Fire", WingModelData.LONG, WingPaletteData.FIRE),
    /**
     * Long 模型 + Colors 图案。
     */
    LONG_COLORS("Long", "Colors", WingModelData.LONG, WingPaletteData.COLORS),
    /**
     * Long 模型 + Fly 图案。
     */
    LONG_FLY("Long", "Fly", WingModelData.LONG, WingPaletteData.FLY),
    /**
     * Butterfly2 模型 + Gradient1 图案。
     */
    BUTTERFLY2_GRADIENT1("Butterfly2", "Gradient1", WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT1),
    /**
     * Butterfly2 模型 + Gradient2 图案。
     */
    BUTTERFLY2_GRADIENT2("Butterfly2", "Gradient2", WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT2),
    /**
     * Butterfly2 模型 + Gradient3 图案。
     */
    BUTTERFLY2_GRADIENT3("Butterfly2", "Gradient3", WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT3),
    /**
     * Butterfly2 模型 + Mushroom 图案。
     */
    BUTTERFLY2_MUSHROOM("Butterfly2", "Mushroom", WingModelData.BUTTERFLY2, WingPaletteData.MUSHROOM),
    /**
     * Butterfly2 模型 + Fire 图案。
     */
    BUTTERFLY2_FIRE("Butterfly2", "Fire", WingModelData.BUTTERFLY2, WingPaletteData.FIRE),
    /**
     * Butterfly2 模型 + Colors 图案。
     */
    BUTTERFLY2_COLORS("Butterfly2", "Colors", WingModelData.BUTTERFLY2, WingPaletteData.COLORS),
    /**
     * Butterfly2 模型 + Fly 图案。
     */
    BUTTERFLY2_FLY("Butterfly2", "Fly", WingModelData.BUTTERFLY2, WingPaletteData.FLY),
    /**
     * Short 模型 + Gradient1 图案。
     */
    SHORT_GRADIENT1("Short", "Gradient1", WingModelData.SHORT, WingPaletteData.GRADIENT1),
    /**
     * Short 模型 + Gradient2 图案。
     */
    SHORT_GRADIENT2("Short", "Gradient2", WingModelData.SHORT, WingPaletteData.GRADIENT2),
    /**
     * Short 模型 + Gradient3 图案。
     */
    SHORT_GRADIENT3("Short", "Gradient3", WingModelData.SHORT, WingPaletteData.GRADIENT3),
    /**
     * Short 模型 + Mushroom 图案。
     */
    SHORT_MUSHROOM("Short", "Mushroom", WingModelData.SHORT, WingPaletteData.MUSHROOM),
    /**
     * Short 模型 + Fire 图案。
     */
    SHORT_FIRE("Short", "Fire", WingModelData.SHORT, WingPaletteData.FIRE),
    /**
     * Short 模型 + Colors 图案。
     */
    SHORT_COLORS("Short", "Colors", WingModelData.SHORT, WingPaletteData.COLORS),
    /**
     * Short 模型 + Fly 图案。
     */
    SHORT_FLY("Short", "Fly", WingModelData.SHORT, WingPaletteData.FLY),
    /**
     * Demon 模型 + Gradient1 图案。
     */
    DEMON_GRADIENT1("Demon", "Gradient1", WingModelData.DEMON, WingPaletteData.GRADIENT1),
    /**
     * Demon 模型 + Gradient2 图案。
     */
    DEMON_GRADIENT2("Demon", "Gradient2", WingModelData.DEMON, WingPaletteData.GRADIENT2),
    /**
     * Demon 模型 + Gradient3 图案。
     */
    DEMON_GRADIENT3("Demon", "Gradient3", WingModelData.DEMON, WingPaletteData.GRADIENT3),
    /**
     * Demon 模型 + Mushroom 图案。
     */
    DEMON_MUSHROOM("Demon", "Mushroom", WingModelData.DEMON, WingPaletteData.MUSHROOM),
    /**
     * Demon 模型 + Fire 图案。
     */
    DEMON_FIRE("Demon", "Fire", WingModelData.DEMON, WingPaletteData.FIRE),
    /**
     * Demon 模型 + Colors 图案。
     */
    DEMON_COLORS("Demon", "Colors", WingModelData.DEMON, WingPaletteData.COLORS),
    /**
     * Demon 模型 + Fly 图案。
     */
    DEMON_FLY("Demon", "Fly", WingModelData.DEMON, WingPaletteData.FLY),
    /**
     * Night 模型 + Gradient1 图案。
     */
    NIGHT_GRADIENT1("Night", "Gradient1", WingModelData.NIGHT, WingPaletteData.GRADIENT1),
    /**
     * Night 模型 + Gradient2 图案。
     */
    NIGHT_GRADIENT2("Night", "Gradient2", WingModelData.NIGHT, WingPaletteData.GRADIENT2),
    /**
     * Night 模型 + Gradient3 图案。
     */
    NIGHT_GRADIENT3("Night", "Gradient3", WingModelData.NIGHT, WingPaletteData.GRADIENT3),
    /**
     * Night 模型 + Mushroom 图案。
     */
    NIGHT_MUSHROOM("Night", "Mushroom", WingModelData.NIGHT, WingPaletteData.MUSHROOM),
    /**
     * Night 模型 + Fire 图案。
     */
    NIGHT_FIRE("Night", "Fire", WingModelData.NIGHT, WingPaletteData.FIRE),
    /**
     * Night 模型 + Colors 图案。
     */
    NIGHT_COLORS("Night", "Colors", WingModelData.NIGHT, WingPaletteData.COLORS),
    /**
     * Night 模型 + Fly 图案。
     */
    NIGHT_FLY("Night", "Fly", WingModelData.NIGHT, WingPaletteData.FLY);

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

    /**
     * 将模型点位和图案颜色组合成渲染点阵。
     *
     * @param modelName   模型名称
     * @param patternName 图案名称
     * @param shape       模型点位及粒子类型
     * @param palette     图案颜色
     */
    WingTypeEnum(String modelName, String patternName, int[][] shape, int[][] palette) {
        this.modelName = modelName;
        this.patternName = patternName;
        this.points = new WingPoint[shape.length];
        for (int i = 0; i < shape.length; i++) {
            int[] point = shape[i];
            this.points[i] = new WingPoint(point[0], point[1], point[2], palette[point[0]][point[1]]);
        }
    }

}
