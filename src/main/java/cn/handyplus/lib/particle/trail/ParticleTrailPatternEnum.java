package cn.handyplus.lib.particle.trail;

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
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, o.getY(), 0.0D);
            point.setPitch(0.0F);
            point.setYaw((float) bounceAngle(tick, o.getSpeed()));
            emitter.emit(player, point.add(point.getDirection()), tick);
        }
    },
    /**
     * 多条旋臂围绕玩家展开的螺旋轨迹。
     */
    HELIX {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, 0.2D, 0.0D);
            int active = (int) (Math.floorMod(tick, o.getParticles()) + 1);
            for (int strand = 1; strand <= o.getStrands(); strand++) {
                for (int particle = 1; particle <= o.getParticles(); particle++) {
                    if (particle != active) {
                        continue;
                    }
                    double ratio = (double) particle / o.getParticles();
                    double angle = o.getSpeed() * ratio * 2.0D * Math.PI / o.getStrands()
                            + 2.0D * Math.PI * strand / o.getStrands() + o.getRotation();
                    emitter.emit(player, point.clone().add(
                            Math.cos(angle) * ratio * o.getRadius(), 0.0D,
                            Math.sin(angle) * ratio * o.getRadius()), tick);
                }
            }
        }
    },
    /**
     * 绕玩家往返升降的螺旋轨迹。
     */
    SPIRAL {
        /** {@inheritDoc} */
        @Override
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            double height = bounceHeight(tick, o.getVerticalSpeed(), o.getMaxHeight());
            double angle = bounceAngle(tick, o.getSpeed());
            if (o.isRotate90()) {
                angle += (tick / 90L) * 90.0D;
            }
            Location point = origin.clone().add(0.0D, o.getY() + height, 0.0D);
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
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            int step = (int) (tick % Math.max(1L, Math.round(o.getLength() / o.getGrow())));
            Location point = origin.clone();
            for (int helix = 0; helix < o.getWaveStrands(); helix++) {
                double angle = step * o.getRadial() + Math.PI * helix;
                emitVector(player, point, rotateY(new Vector(Math.cos(angle) * o.getRadius(), step * o.getGrow(),
                        Math.sin(angle) * o.getRadius()), -origin.getYaw()), tick, emitter);
            }
            if (step % o.getBaseInterval() == 0) {
                double angle = step * o.getRadial();
                for (int i = -o.getBaseParticles(); i <= o.getBaseParticles(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    emitVector(player, point, rotateY(new Vector(Math.cos(angle) * o.getRadius() * i / o.getBaseParticles(),
                            step * o.getGrow(), Math.sin(angle) * o.getRadius() * i / o.getBaseParticles()), -origin.getYaw()), tick, emitter);
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
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            int phase = (int) (tick % 50L);
            if (phase == 0 || phase % 10 != 0) {
                return;
            }
            Location center = origin.clone().add(0.0D, o.getY() + 2.2D, 0.0D);
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
        public void render(Player player, Location origin, long tick, ParticleTrailOptions o, ParticleEmitter emitter) {
            Location point = origin.clone().add(0.0D, o.getY(), 0.0D);
            point.setPitch(0.0F);
            double rotation = bounceAngle(tick, 1.0D);
            for (double angle : new double[]{45.0D, -45.0D, 135.0D, -135.0D}) {
                Location target = point.clone();
                target.setYaw((float) (angle + rotation));
                emitter.emit(player, target.add(target.getDirection().multiply(1.4D)), tick);
            }
        }
    };

    /**
     * 在相对原点的位置发射粒子。
     */
    private static void emitVector(Player player, Location origin, Vector vector, long tick, ParticleEmitter emitter) {
        emitter.emit(player, origin.clone().add(vector), tick);
    }

    /**
     * 计算往返旋转角度。
     */
    private static double bounceAngle(long tick, double speed) {
        double phase = Math.floorMod((long) Math.floor(tick * speed), 358L);
        return phase <= 179.0D ? phase : phase - 358.0D;
    }

    /**
     * 计算往返升降高度。
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
