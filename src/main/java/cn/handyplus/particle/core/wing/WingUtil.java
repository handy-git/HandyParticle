package cn.handyplus.particle.core.wing;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * 粒子翅膀渲染工具。
 *
 * @author handy
 */
final class WingUtil {
    /**
     * 彩色翅膀点使用的粒子。
     */
    private static final XParticle COLOR_PARTICLE = XParticle.DUST;
    /**
     * 火焰翅膀点使用的粒子。
     */
    private static final XParticle FLAME_PARTICLE = XParticle.FLAME;
    /**
     * 女巫法术翅膀点使用的粒子。
     */
    private static final XParticle WITCH_PARTICLE = XParticle.WITCH;
    /**
     * 熔岩滴落翅膀点使用的粒子。
     */
    private static final XParticle DRIP_LAVA_PARTICLE = XParticle.DRIPPING_LAVA;
    /**
     * 魔法暴击翅膀点使用的粒子。
     */
    private static final XParticle CRIT_PARTICLE = XParticle.ENCHANTED_HIT;

    /**
     * 禁止实例化渲染工具。
     */
    private WingUtil() {
    }

    /**
     * 渲染指定实体的翅膀。
     *
     * @param entity   实体
     * @param wingType 翅膀组合
     */
    static void render(@NotNull Entity entity, @NotNull WingTypeEnum wingType) {
        Location origin = entity.getLocation().clone();
        origin.setPitch(0.0F);
        origin.add(origin.getDirection().multiply(-0.3D));
        drawWing(origin, wingType.getPoints(), -120.0F);
        drawWing(origin, wingType.getPoints(), 120.0F);
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
        for (WingPoint point : points) {
            Location location = rotated.clone().add(stepX * point.getColumn(), 0.2D * (14 - point.getRow()), stepZ * point.getColumn());
            spawn(location, point);
        }
    }

    /**
     * 根据点阵类型发射粒子。
     *
     * @param location 发射位置
     * @param point    点阵数据
     */
    private static void spawn(@NotNull Location location, @NotNull WingPoint point) {
        switch (point.getType()) {
            // 图案 RGB 彩色粒子
            case 1:
            case 2:
            case 3:
                int rgb = point.getColor();
                ParticleDisplay.of(COLOR_PARTICLE)
                        .withColor(new Color(rgb), 1.0F)
                        .spawn(location);
                return;
            // 火焰粒子
            case 4:
                spawnSimple(FLAME_PARTICLE, location);
                return;
            // 女巫法术粒子
            case 5:
                spawnSimple(WITCH_PARTICLE, location);
                return;
            // 熔岩滴落粒子
            case 6:
                spawnSimple(DRIP_LAVA_PARTICLE, location);
                return;
            // 魔法暴击粒子
            case 7:
                spawnSimple(CRIT_PARTICLE, location);
                return;
            default:
                return;
        }
    }

    /**
     * 发射一个无需额外数据的粒子。
     *
     * @param particle 粒子类型
     * @param location 发射位置
     */
    private static void spawnSimple(@NotNull XParticle particle, @NotNull Location location) {
        ParticleDisplay.of(particle).withCount(1).spawn(location);
    }

}
