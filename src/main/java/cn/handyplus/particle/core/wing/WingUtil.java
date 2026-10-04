package cn.handyplus.particle.core.wing;

import cn.handyplus.particle.util.ParticleVisibilityUtil;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;

/**
 * 粒子翅膀渲染工具。
 *
 * @author handy
 */
final class WingUtil {
    /**
     * 翅膀点阵的横向与纵向间距。
     */
    private static final double GRID_STEP = 0.2D;

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
        drawWing(entity, origin, points, -120.0F);
        drawWing(entity, origin, points, 120.0F);
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
     * @param entity 实体
     * @param origin 翅膀原点
     * @param points 翅膀点阵
     * @param angle  翅膀旋转角度
     */
    private static void drawWing(@NotNull Entity entity, @NotNull Location origin, @NotNull WingPoint[] points, float angle) {
        Location rotated = origin.clone();
        rotated.setYaw(rotated.getYaw() - angle);
        Vector direction = rotated.getDirection();
        double stepX = direction.getX() * GRID_STEP;
        double stepZ = direction.getZ() * GRID_STEP;
        Location location = rotated.clone();
        for (WingPoint point : points) {
            location.setX(rotated.getX() + stepX * point.getColumn());
            location.setY(rotated.getY() + GRID_STEP * (14 - point.getRow()));
            location.setZ(rotated.getZ() + stepZ * point.getColumn());
            ParticleDisplay display = point.getDisplay();
            if (display != null) {
                ParticleVisibilityUtil.spawn(display, location, entity);
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
            default:
                return null;
        }
    }

}
