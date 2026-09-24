package cn.handyplus.particle.core.wing;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 粒子翅膀内部渲染工具。
 *
 * @author handy
 */
final class ParticleWingUtil {
    /**
     * 彩色翅膀点使用的粒子。
     */
    private static final Particle COLOR_PARTICLE = getParticle("REDSTONE");
    /**
     * 火焰翅膀点使用的粒子。
     */
    private static final Particle FLAME_PARTICLE = getParticle("FLAME");
    /**
     * 女巫法术翅膀点使用的粒子。
     */
    private static final Particle WITCH_PARTICLE = getParticle("WITCH");
    /**
     * 熔岩滴落翅膀点使用的粒子。
     */
    private static final Particle DRIP_LAVA_PARTICLE = getParticle("DRIPPING_LAVA");
    /**
     * 魔法暴击翅膀点使用的粒子。
     */
    private static final Particle CRIT_PARTICLE = getParticle("CRIT_MAGIC");

    /**
     * 禁止实例化渲染工具。
     */
    private ParticleWingUtil() {
    }

    /**
     * 渲染指定玩家的翅膀。
     *
     * @param player 玩家
     * @param wingType 翅膀组合
     */
    static void render(@NotNull Player player, @NotNull ParticleWingTypeEnum wingType) {
        Location origin = player.getLocation().clone();
        origin.setPitch(0.0F);
        origin.add(origin.getDirection().multiply(-0.3D));
        drawWing(player, origin, wingType.getPoints(), -120.0F);
        drawWing(player, origin, wingType.getPoints(), 120.0F);
    }

    /**
     * 按点阵绘制单侧翅膀。
     *
     * @param player 玩家
     * @param origin 翅膀原点
     * @param points 翅膀点阵
     * @param angle 翅膀旋转角度
     */
    private static void drawWing(@NotNull Player player, @NotNull Location origin, @NotNull WingPoint[] points, float angle) {
        for (WingPoint point : points) {
            Location location = origin.clone();
            location.setYaw(location.getYaw() - angle);
            location.add(0.0D, 0.2D * (14 - point.getRow()), 0.0D);
            location.add(location.getDirection().multiply(point.getColumn() * 0.2D));
            spawn(player, location, point);
        }
    }

    /**
     * 根据点阵类型发射粒子。
     *
     * @param player 玩家
     * @param location 发射位置
     * @param point 点阵数据
     */
    private static void spawn(@NotNull Player player, @NotNull Location location, @NotNull WingPoint point) {
        switch (point.getType()) {
            // 图案 RGB 彩色粒子
            case 1:
            case 2:
            case 3:
                int rgb = point.getColor();
                Color color = Color.fromRGB((rgb >> 16) & 255, (rgb >> 8) & 255, rgb & 255);
                player.spawnParticle(COLOR_PARTICLE, location, 1, 0.0D, 0.0D, 0.0D, 0.0D, new Particle.DustOptions(color, 1.0F));
                return;
            // 火焰粒子
            case 4:
                spawnSimple(player, FLAME_PARTICLE, location);
                return;
            // 女巫法术粒子
            case 5:
                spawnSimple(player, WITCH_PARTICLE, location);
                return;
            // 熔岩滴落粒子
            case 6:
                spawnSimple(player, DRIP_LAVA_PARTICLE, location);
                return;
            // 魔法暴击粒子
            case 7:
                spawnSimple(player, CRIT_PARTICLE, location);
                return;
            default:
                return;
        }
    }

    /**
     * 发射一个无需额外数据的粒子。
     *
     * @param player 玩家
     * @param particle 粒子类型
     * @param location 发射位置
     */
    private static void spawnSimple(@NotNull Player player, @NotNull Particle particle, @NotNull Location location) {
        player.spawnParticle(particle, location, 1);
    }

    /**
     * 获取跨版本粒子。
     *
     * @param name 旧版本粒子名称
     * @return 当前版本对应的粒子
     */
    private static Particle getParticle(@NotNull String name) {
        // 以下名称变更版本与 XSeries 13.7.1 的 XParticle 定义保持一致。
        boolean useNewName = BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_20_5.getVersionId();
        switch (name) {
            // 彩色红石粉尘粒子
            case "REDSTONE":
                return Particle.valueOf(useNewName ? "DUST" : "REDSTONE");
            // 女巫法术粒子
            case "WITCH":
                return Particle.valueOf(useNewName ? "WITCH" : "SPELL_WITCH");
            // 熔岩滴落粒子
            case "DRIPPING_LAVA":
                return Particle.valueOf(useNewName ? "DRIPPING_LAVA" : "DRIP_LAVA");
            // 魔法暴击粒子
            case "CRIT_MAGIC":
                return Particle.valueOf(useNewName ? "ENCHANTED_HIT" : "CRIT_MAGIC");
            // 火焰粒子，所有支持版本名称一致
            case "FLAME":
                return Particle.valueOf("FLAME");
            default:
                throw new IllegalArgumentException("Unsupported wing particle: " + name);
        }
    }

}
