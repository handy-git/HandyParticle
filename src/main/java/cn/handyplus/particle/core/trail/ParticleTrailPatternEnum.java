package cn.handyplus.particle.core.trail;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * 常用粒子运动算法。
 *
 * @author handy
 */
public enum ParticleTrailPatternEnum implements ParticleTrailPattern {
    /**
     * 围绕玩家水平旋转的单点轨迹。
     */
    CIRCLE {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, options.getY(), 0.0D);
            point.setPitch(0.0F);
            point.setYaw((float) bounceAngle(tick, options.getSpeed()));
            emitter.emit(player, point.add(point.getDirection()), tick);
        }
    },
    /**
     * 多条旋臂围绕玩家展开的螺旋轨迹。
     */
    HELIX {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, 0.2D, 0.0D);
            int particleCount = options.getParticles();
            int active = (int) Math.floorMod(tick, particleCount) + 1;
            double ratio = (double) active / particleCount;
            for (int strand = 1; strand <= options.getStrands(); strand++) {
                double angle = options.getSpeed() * ratio * 2.0D * Math.PI / options.getStrands() + 2.0D * Math.PI * strand / options.getStrands() + options.getRotation();
                emitter.emit(player, point.clone().add(Math.cos(angle) * ratio * options.getRadius(), 0.0D, Math.sin(angle) * ratio * options.getRadius()), tick);
            }
        }
    },
    /**
     * 绕玩家往返升降的螺旋轨迹。
     */
    SPIRAL {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            double height = bounceHeight(tick, options.getVerticalSpeed(), options.getMaxHeight());
            double angle = bounceAngle(tick, options.getSpeed());
            if (options.isRotate90()) {
                angle += ((double) tick / 90L) * 90.0D;
            }
            Location point = origin.clone().add(0.0D, options.getY() + height, 0.0D);
            point.setPitch(0.0F);
            point.setYaw((float) (angle % 360.0D));
            emitter.emit(player, point.add(point.getDirection()), tick);
        }
    },
    /**
     * 纵向推进并周期性展开横向粒子的波浪轨迹。
     */
    WAVES {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            int step = (int) (tick % Math.max(1L, Math.round(options.getLength() / options.getGrow())));
            Location point = origin.clone();
            for (int strand = 0; strand < options.getWaveStrands(); strand++) {
                double angle = step * options.getRadial() + Math.PI * strand;
                emitVector(player, point, rotateY(new Vector(Math.cos(angle) * options.getRadius(), step * options.getGrow(), Math.sin(angle) * options.getRadius()), -origin.getYaw()), tick, emitter);
            }
            if (step % options.getBaseInterval() == 0) {
                double angle = step * options.getRadial();
                for (int offset = -options.getBaseParticles(); offset <= options.getBaseParticles(); offset++) {
                    if (offset == 0) {
                        continue;
                    }
                    emitVector(player, point, rotateY(new Vector(Math.cos(angle) * options.getRadius() * offset / options.getBaseParticles(), step * options.getGrow(), Math.sin(angle) * options.getRadius() * offset / options.getBaseParticles()), -origin.getYaw()), tick, emitter);
                }
            }
        }
    },
    /**
     * 按固定间隔向外扩散的脉冲轨迹。
     */
    PULSE {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            int phase = (int) (tick % 50L);
            if (phase == 0 || phase % 10 != 0) {
                return;
            }
            Location center = origin.clone().add(0.0D, options.getY() + 2.2D, 0.0D);
            double distance = phase / 10.0D * 0.2D;
            for (int angle = -180; angle <= 180; angle += 40) {
                Location point = center.clone();
                point.setPitch(0.0F);
                point.setYaw(angle);
                emitter.emit(player, point.add(point.getDirection().multiply(distance)), tick);
            }
        }
    },
    /**
     * 围绕玩家旋转的四点护盾轨迹。
     */
    SHIELD {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, options.getY(), 0.0D);
            point.setPitch(0.0F);
            double rotation = bounceAngle(tick, 1.0D);
            for (double angle : SHIELD_ANGLES) {
                Location target = point.clone();
                target.setYaw((float) (angle + rotation));
                emitter.emit(player, target.add(target.getDirection().multiply(1.4D)), tick);
            }
        }
    };

    /**
     * 护盾四个粒子的初始角度。
     */
    private static final double[] SHIELD_ANGLES = {45.0D, -45.0D, 135.0D, -135.0D};

    /**
     * 在相对原点的位置发射粒子。
     *
     * @param player 玩家
     * @param origin 原点
     * @param vector 相对位移
     * @param tick 当前轨迹 Tick
     * @param emitter 粒子发射器
     */
    private static void emitVector(Player player, Location origin, Vector vector, long tick, ParticleEmitter emitter) {
        emitter.emit(player, origin.clone().add(vector), tick);
    }

    /**
     * 计算往返旋转角度。
     *
     * @param tick 当前轨迹 Tick
     * @param speed 每次刷新的角度增量
     * @return 往返旋转角度
     */
    private static double bounceAngle(long tick, double speed) {
        double phase = Math.floorMod((long) Math.floor(tick * speed), 358L);
        return phase <= 179.0D ? phase : phase - 358.0D;
    }

    /**
     * 计算往返升降高度。
     *
     * @param tick 当前轨迹 Tick
     * @param speed 每次刷新的高度增量
     * @param maxHeight 最大高度
     * @return 当前高度
     */
    private static double bounceHeight(long tick, double speed, double maxHeight) {
        if (speed <= 0.0D || maxHeight <= 0.0D) {
            return 0.0D;
        }
        double phase = (tick * speed) % (maxHeight * 2.0D);
        return phase <= maxHeight ? phase : maxHeight * 2.0D - phase;
    }

    /**
     * 围绕 Y 轴旋转位移向量。
     *
     * @param vector 原位移向量
     * @param degrees 旋转角度
     * @return 旋转后的位移向量
     */
    private static Vector rotateY(Vector vector, double degrees) {
        double radians = Math.toRadians(degrees);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double x = vector.getX();
        double z = vector.getZ();
        return vector.setX(x * cos + z * sin).setZ(x * -sin + z * cos);
    }
}
