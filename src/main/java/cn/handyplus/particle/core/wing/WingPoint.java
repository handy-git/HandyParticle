package cn.handyplus.particle.core.wing;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * 翅膀像素点。
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
     * 预构建的粒子显示对象，未知类型时为空。
     */
    @Nullable
    private final ParticleDisplay display;
}
