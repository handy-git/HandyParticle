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
    ANGEL_GRADIENT1(WingModelData.ANGEL, WingPaletteData.GRADIENT1),
    /**
     * Angel 模型 + Gradient2 图案。
     */
    ANGEL_GRADIENT2(WingModelData.ANGEL, WingPaletteData.GRADIENT2),
    /**
     * Angel 模型 + Gradient3 图案。
     */
    ANGEL_GRADIENT3(WingModelData.ANGEL, WingPaletteData.GRADIENT3),
    /**
     * Angel 模型 + Mushroom 图案。
     */
    ANGEL_MUSHROOM(WingModelData.ANGEL, WingPaletteData.MUSHROOM),
    /**
     * Angel 模型 + Fire 图案。
     */
    ANGEL_FIRE(WingModelData.ANGEL, WingPaletteData.FIRE),
    /**
     * Angel 模型 + Colors 图案。
     */
    ANGEL_COLORS(WingModelData.ANGEL, WingPaletteData.COLORS),
    /**
     * Angel 模型 + Fly 图案。
     */
    ANGEL_FLY(WingModelData.ANGEL, WingPaletteData.FLY),
    /**
     * Butterfly 模型 + Gradient1 图案。
     */
    BUTTERFLY_GRADIENT1(WingModelData.BUTTERFLY, WingPaletteData.GRADIENT1),
    /**
     * Butterfly 模型 + Gradient2 图案。
     */
    BUTTERFLY_GRADIENT2(WingModelData.BUTTERFLY, WingPaletteData.GRADIENT2),
    /**
     * Butterfly 模型 + Gradient3 图案。
     */
    BUTTERFLY_GRADIENT3(WingModelData.BUTTERFLY, WingPaletteData.GRADIENT3),
    /**
     * Butterfly 模型 + Mushroom 图案。
     */
    BUTTERFLY_MUSHROOM(WingModelData.BUTTERFLY, WingPaletteData.MUSHROOM),
    /**
     * Butterfly 模型 + Fire 图案。
     */
    BUTTERFLY_FIRE(WingModelData.BUTTERFLY, WingPaletteData.FIRE),
    /**
     * Butterfly 模型 + Colors 图案。
     */
    BUTTERFLY_COLORS(WingModelData.BUTTERFLY, WingPaletteData.COLORS),
    /**
     * Butterfly 模型 + Fly 图案。
     */
    BUTTERFLY_FLY(WingModelData.BUTTERFLY, WingPaletteData.FLY),
    /**
     * Elf 模型 + Gradient1 图案。
     */
    ELF_GRADIENT1(WingModelData.ELF, WingPaletteData.GRADIENT1),
    /**
     * Elf 模型 + Gradient2 图案。
     */
    ELF_GRADIENT2(WingModelData.ELF, WingPaletteData.GRADIENT2),
    /**
     * Elf 模型 + Gradient3 图案。
     */
    ELF_GRADIENT3(WingModelData.ELF, WingPaletteData.GRADIENT3),
    /**
     * Elf 模型 + Mushroom 图案。
     */
    ELF_MUSHROOM(WingModelData.ELF, WingPaletteData.MUSHROOM),
    /**
     * Elf 模型 + Fire 图案。
     */
    ELF_FIRE(WingModelData.ELF, WingPaletteData.FIRE),
    /**
     * Elf 模型 + Colors 图案。
     */
    ELF_COLORS(WingModelData.ELF, WingPaletteData.COLORS),
    /**
     * Elf 模型 + Fly 图案。
     */
    ELF_FLY(WingModelData.ELF, WingPaletteData.FLY),
    /**
     * Long 模型 + Gradient1 图案。
     */
    LONG_GRADIENT1(WingModelData.LONG, WingPaletteData.GRADIENT1),
    /**
     * Long 模型 + Gradient2 图案。
     */
    LONG_GRADIENT2(WingModelData.LONG, WingPaletteData.GRADIENT2),
    /**
     * Long 模型 + Gradient3 图案。
     */
    LONG_GRADIENT3(WingModelData.LONG, WingPaletteData.GRADIENT3),
    /**
     * Long 模型 + Mushroom 图案。
     */
    LONG_MUSHROOM(WingModelData.LONG, WingPaletteData.MUSHROOM),
    /**
     * Long 模型 + Fire 图案。
     */
    LONG_FIRE(WingModelData.LONG, WingPaletteData.FIRE),
    /**
     * Long 模型 + Colors 图案。
     */
    LONG_COLORS(WingModelData.LONG, WingPaletteData.COLORS),
    /**
     * Long 模型 + Fly 图案。
     */
    LONG_FLY(WingModelData.LONG, WingPaletteData.FLY),
    /**
     * Butterfly2 模型 + Gradient1 图案。
     */
    BUTTERFLY2_GRADIENT1(WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT1),
    /**
     * Butterfly2 模型 + Gradient2 图案。
     */
    BUTTERFLY2_GRADIENT2(WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT2),
    /**
     * Butterfly2 模型 + Gradient3 图案。
     */
    BUTTERFLY2_GRADIENT3(WingModelData.BUTTERFLY2, WingPaletteData.GRADIENT3),
    /**
     * Butterfly2 模型 + Mushroom 图案。
     */
    BUTTERFLY2_MUSHROOM(WingModelData.BUTTERFLY2, WingPaletteData.MUSHROOM),
    /**
     * Butterfly2 模型 + Fire 图案。
     */
    BUTTERFLY2_FIRE(WingModelData.BUTTERFLY2, WingPaletteData.FIRE),
    /**
     * Butterfly2 模型 + Colors 图案。
     */
    BUTTERFLY2_COLORS(WingModelData.BUTTERFLY2, WingPaletteData.COLORS),
    /**
     * Butterfly2 模型 + Fly 图案。
     */
    BUTTERFLY2_FLY(WingModelData.BUTTERFLY2, WingPaletteData.FLY),
    /**
     * Short 模型 + Gradient1 图案。
     */
    SHORT_GRADIENT1(WingModelData.SHORT, WingPaletteData.GRADIENT1),
    /**
     * Short 模型 + Gradient2 图案。
     */
    SHORT_GRADIENT2(WingModelData.SHORT, WingPaletteData.GRADIENT2),
    /**
     * Short 模型 + Gradient3 图案。
     */
    SHORT_GRADIENT3(WingModelData.SHORT, WingPaletteData.GRADIENT3),
    /**
     * Short 模型 + Mushroom 图案。
     */
    SHORT_MUSHROOM(WingModelData.SHORT, WingPaletteData.MUSHROOM),
    /**
     * Short 模型 + Fire 图案。
     */
    SHORT_FIRE(WingModelData.SHORT, WingPaletteData.FIRE),
    /**
     * Short 模型 + Colors 图案。
     */
    SHORT_COLORS(WingModelData.SHORT, WingPaletteData.COLORS),
    /**
     * Short 模型 + Fly 图案。
     */
    SHORT_FLY(WingModelData.SHORT, WingPaletteData.FLY),
    /**
     * Demon 模型 + Gradient1 图案。
     */
    DEMON_GRADIENT1(WingModelData.DEMON, WingPaletteData.GRADIENT1),
    /**
     * Demon 模型 + Gradient2 图案。
     */
    DEMON_GRADIENT2(WingModelData.DEMON, WingPaletteData.GRADIENT2),
    /**
     * Demon 模型 + Gradient3 图案。
     */
    DEMON_GRADIENT3(WingModelData.DEMON, WingPaletteData.GRADIENT3),
    /**
     * Demon 模型 + Mushroom 图案。
     */
    DEMON_MUSHROOM(WingModelData.DEMON, WingPaletteData.MUSHROOM),
    /**
     * Demon 模型 + Fire 图案。
     */
    DEMON_FIRE(WingModelData.DEMON, WingPaletteData.FIRE),
    /**
     * Demon 模型 + Colors 图案。
     */
    DEMON_COLORS(WingModelData.DEMON, WingPaletteData.COLORS),
    /**
     * Demon 模型 + Fly 图案。
     */
    DEMON_FLY(WingModelData.DEMON, WingPaletteData.FLY),
    /**
     * Night 模型 + Gradient1 图案。
     */
    NIGHT_GRADIENT1(WingModelData.NIGHT, WingPaletteData.GRADIENT1),
    /**
     * Night 模型 + Gradient2 图案。
     */
    NIGHT_GRADIENT2(WingModelData.NIGHT, WingPaletteData.GRADIENT2),
    /**
     * Night 模型 + Gradient3 图案。
     */
    NIGHT_GRADIENT3(WingModelData.NIGHT, WingPaletteData.GRADIENT3),
    /**
     * Night 模型 + Mushroom 图案。
     */
    NIGHT_MUSHROOM(WingModelData.NIGHT, WingPaletteData.MUSHROOM),
    /**
     * Night 模型 + Fire 图案。
     */
    NIGHT_FIRE(WingModelData.NIGHT, WingPaletteData.FIRE),
    /**
     * Night 模型 + Colors 图案。
     */
    NIGHT_COLORS(WingModelData.NIGHT, WingPaletteData.COLORS),
    /**
     * Night 模型 + Fly 图案。
     */
    NIGHT_FLY(WingModelData.NIGHT, WingPaletteData.FLY);

    /**
     * 预先由 model 与 pattern 合成的固定粒子点。
     */
    @Getter(AccessLevel.PACKAGE)
    private final WingPoint[] points;

    /**
     * 将模型点位和图案颜色组合成渲染点阵。
     *
     * @param shape   模型点位及粒子类型
     * @param palette 图案颜色
     */
    WingTypeEnum(int[][] shape, int[][] palette) {
        this.points = new WingPoint[shape.length];
        for (int i = 0; i < shape.length; i++) {
            int[] point = shape[i];
            this.points[i] = new WingPoint(point[0], point[1], point[2], palette[point[0]][point[1]]);
        }
    }

}
