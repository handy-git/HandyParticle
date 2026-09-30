package cn.handyplus.particle.core.wing;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 粒子翅膀渲染工具。
 *
 * @author handy
 */
final class WingUtil {
    /**
     * 火焰翅膀点显示对象。
     */
    private static final ParticleDisplay FLAME_DISPLAY = ParticleDisplay.of(XParticle.FLAME).withCount(1);
    /**
     * 女巫法术翅膀点显示对象。
     */
    private static final ParticleDisplay WITCH_DISPLAY = ParticleDisplay.of(XParticle.WITCH).withCount(1);
    /**
     * 熔岩滴落翅膀点显示对象。
     */
    private static final ParticleDisplay DRIP_LAVA_DISPLAY = ParticleDisplay.of(XParticle.DRIPPING_LAVA).withCount(1);
    /**
     * 魔法暴击翅膀点显示对象。
     */
    private static final ParticleDisplay CRIT_DISPLAY = ParticleDisplay.of(XParticle.ENCHANTED_HIT).withCount(1);

    /**
     * 禁止实例化渲染工具。
     */
    private WingUtil() {
    }

    /**
     * 渲染指定实体的翅膀。
     *
     * @param entity 实体
     * @param points 翅膀点阵
     */
    static void render(@NotNull Entity entity, @NotNull WingPoint[] points) {
        Location origin = entity.getLocation();
        origin.setPitch(0.0F);
        origin.add(origin.getDirection().multiply(-0.3D));
        drawWing(origin, points, -120.0F);
        drawWing(origin, points, 120.0F);
    }

    /**
     * 组合外形和颜色图案的点阵。
     *
     * @param model 翅膀外形
     * @param color 颜色图案
     * @return 翅膀点阵
     */
    @NotNull
    static WingPoint[] createPoints(@NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        int[][] shape = model.getShape();
        int[][] palette = color.getPalette();
        WingPoint[] points = new WingPoint[shape.length];
        for (int index = 0; index < shape.length; index++) {
            int[] point = shape[index];
            points[index] = new WingPoint(point[0], point[1], displayFor(point[2], palette[point[0]][point[1]]));
        }
        return points;
    }

    /**
     * 按点阵绘制单侧翅膀。
     *
     * @param origin 翅膀原点
     * @param points 翅膀点阵
     * @param angle  翅膀旋转角度
     */
    private static void drawWing(@NotNull Location origin, @NotNull WingPoint[] points, float angle) {
        Location rotated = origin.clone();
        rotated.setYaw(rotated.getYaw() - angle);
        Vector direction = rotated.getDirection();
        double stepX = direction.getX() * 0.2D;
        double stepZ = direction.getZ() * 0.2D;
        Location location = rotated.clone();
        for (WingPoint point : points) {
            location.setX(rotated.getX() + stepX * point.getColumn());
            location.setY(rotated.getY() + 0.2D * (14 - point.getRow()));
            location.setZ(rotated.getZ() + stepZ * point.getColumn());
            ParticleDisplay display = point.getDisplay();
            if (display != null) {
                display.spawn(location);
            }
        }
    }

    /**
     * 根据点阵类型创建粒子显示对象。
     *
     * @param type 点阵类型
     * @param rgb  图案颜色
     * @return 粒子显示对象，未知类型时为空
     */
    @Nullable
    private static ParticleDisplay displayFor(int type, int rgb) {
        switch (type) {
            // 图案 RGB 彩色粒子
            case 1:
            case 2:
            case 3:
                return ParticleDisplay.of(XParticle.DUST).withColor(new Color(rgb), 1.0F);
            // 火焰粒子
            case 4:
                return FLAME_DISPLAY;
            // 女巫法术粒子
            case 5:
                return WITCH_DISPLAY;
            // 熔岩滴落粒子
            case 6:
                return DRIP_LAVA_DISPLAY;
            // 魔法暴击粒子
            case 7:
                return CRIT_DISPLAY;
            default:
                return null;
        }
    }

}
