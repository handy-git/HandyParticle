package cn.handyplus.particle.core.wing;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 固定翅膀像素点。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
final class WingPoint {
    /**
     * 图片行坐标。
     */
    private final int row;
    /**
     * 图片列坐标。
     */
    private final int column;
    /**
     * 粒子类型，1-3 为彩色粒子，4-7 为特殊粒子。
     */
    private final int type;
    /**
     * pattern 对应的 RGB 颜色值。
     */
    private final int color;
}
