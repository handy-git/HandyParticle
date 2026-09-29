package cn.handyplus.particle.core.trail;

import cn.handyplus.particle.constants.ParticleConstants;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 普通粒子拖尾运动模式算法。
 *
 * @author handy
 */
final class TrailModeAlgorithm {

    private TrailModeAlgorithm() {
    }

    /**
     * 绘制当前模式的一帧轨迹。
     *
     * @param mode 运动模式
     * @param task 当前实体的拖尾任务
     */
    static void render(@NotNull TrailModeTypeEnum mode, @NotNull TrailTask task) {
        switch (mode) {
            case CIRCLE:
                circle(task);
                break;
            case MAGICIAN:
                magician(task);
                break;
            case PULSE:
                pulse(task);
                break;
            case HELIX:
                helix(task);
                break;
            case DNA:
                dna(task);
                break;
            case SPIRAL:
                spiral(task);
                break;
            case SHOOTER:
                shooter(task);
                break;
            case SLINKY:
                slinky(task);
                break;
            case FOOT_CIRCLE:
                footCircle(task);
                break;
            case COLOR_SPIN:
                colorSpin(task);
                break;
            case FAIRY:
                fairy(task);
                break;
            default:
                task.spawnAt(task.entity.getLocation().clone().add(0.0D, task.type.getY(), 0.0D), 4, 1.0D);
        }
    }

    /**
     * 左键空气时启动点击型轨迹。
     *
     * @param mode 运动模式
     * @param task 当前实体的拖尾任务
     */
    static void trigger(@NotNull TrailModeTypeEnum mode, @NotNull TrailTask task) {
        boolean shooterMode = mode == TrailModeTypeEnum.SHOOTER;
        boolean slinkyMode = mode == TrailModeTypeEnum.SLINKY;
        boolean clickMode = shooterMode || slinkyMode;
        if (!clickMode) {
            return;
        }
        // 弹簧轨迹未结束前忽略重复点击，避免同时维护多条轨迹。
        boolean slinkyRunning = slinkyMode && task.shotOrigin != null;
        if (slinkyRunning) {
            return;
        }
        double height = shooterMode ? 1.0D : 0.0D;
        task.shotOrigin = task.entity.getLocation().clone().add(0.0D, height, 0.0D);
        task.shotFrame = 0;
        task.fall = 0.0D;
    }

    /**
     * 绘制围绕实体的一点旋转轨迹。
     *
     * @param task 当前拖尾任务
     */
    private static void circle(TrailTask task) {
        Location location = task.entity.getLocation().clone().add(0.0D, ParticleConstants.PLAYER_HEAD_Y_OFFSET, 0.0D);
        location.setPitch(0.0F);
        location.setYaw(task.frame * 30.0F);
        task.spawnAt(location.add(location.getDirection()), 3, 0.0D);
    }

    /**
     * 绘制四点旋转的魔法环。
     *
     * @param task 当前拖尾任务
     */
    private static void magician(TrailTask task) {
        Location origin = task.entity.getLocation().clone().add(0.0D, 0.5D, 0.0D);
        origin.setPitch(0.0F);
        for (int angle = 45; angle < 360; angle += 90) {
            Location point = origin.clone();
            point.setYaw(angle + task.frame);
            task.spawnAt(point.add(point.getDirection().multiply(1.4D)), 2, 0.0D);
        }
    }

    /**
     * 按五个阶段绘制扩散环。
     *
     * @param task 当前拖尾任务
     */
    private static void pulse(TrailTask task) {
        int phase = task.frame % 51;
        boolean notFirstFrame = phase != 0;
        boolean intervalFrame = phase % 10 == 0;
        boolean pulseFrame = notFirstFrame && intervalFrame;
        if (!pulseFrame) {
            return;
        }
        Location origin = task.entity.getLocation().clone().add(0.0D, ParticleConstants.PLAYER_HEAD_Y_OFFSET, 0.0D);
        for (int angle = -180; angle <= 180; angle += 40) {
            Location point = origin.clone();
            point.setPitch(0.0F);
            point.setYaw(angle);
            task.spawnAt(point.add(point.getDirection().multiply(phase * 0.02D)), 3, 0.0D);
        }
    }

    /**
     * 绘制螺旋轨迹当前帧。
     *
     * @param task 当前拖尾任务
     */
    private static void helix(TrailTask task) {
        Location origin = task.entity.getLocation().clone().add(0.0D, 1.0D, 0.0D);
        double angle = task.frame * 0.35D;
        task.spawnAt(origin.add(Math.cos(angle), Math.sin(angle * 0.5D), Math.sin(angle)), 2, 0.0D);
    }

    /**
     * 绘制双螺旋轨迹当前帧。
     *
     * @param task 当前拖尾任务
     */
    private static void dna(TrailTask task) {
        Location origin = task.entity.getLocation().clone().add(0.0D, 1.0D, 0.0D);
        double angle = task.frame * 0.3D;
        for (int i = 0; i < 2; i++) {
            double offset = 0.0D;
            if (i != 0) {
                offset = Math.PI;
            }
            Location point = origin.clone().add(Math.cos(angle + offset), Math.sin(angle * 0.4D), Math.sin(angle + offset));
            task.spawnAt(point, 1, 0.0D);
        }
    }

    /**
     * 绘制升降螺旋轨迹当前帧。
     *
     * @param task 当前拖尾任务
     */
    private static void spiral(TrailTask task) {
        double angle = task.frame * 0.25D;
        double radius = 0.2D + (task.frame % 20) * 0.04D;
        double height = 0.5D + (task.frame % 20) * 0.08D;
        Location location = task.entity.getLocation().clone().add(Math.cos(angle) * radius, height, Math.sin(angle) * radius);
        task.spawnAt(location, 2, 0.0D);
    }

    /**
     * 推进一次点击发射轨迹。
     *
     * @param task 当前拖尾任务
     */
    private static void shooter(TrailTask task) {
        if (task.shotOrigin == null) {
            return;
        }
        if (task.shotFrame >= 25) {
            task.shotOrigin = null;
            return;
        }
        task.shotFrame++;
        Location location = task.shotOrigin.clone().add(task.shotOrigin.getDirection().multiply(task.shotFrame * 0.4D));
        task.spawnAt(location, 4, 0.2D);
    }

    /**
     * 推进一次点击弹簧轨迹。
     *
     * @param task 当前拖尾任务
     */
    private static void slinky(TrailTask task) {
        if (task.shotOrigin == null) {
            return;
        }
        if (task.shotFrame >= 48) {
            task.shotOrigin = null;
            return;
        }
        double x = task.shotFrame * 0.2D;
        double y = 1.4D * Math.sin(task.shotFrame * 0.35D) - task.fall;
        Location direction = task.shotOrigin.clone();
        direction.setPitch(0.0F);
        Location location = task.shotOrigin.clone()
                .add(direction.getDirection().multiply(x))
                .add(0.0D, y, 0.0D);
        task.spawnAt(location, 1, 0.0D);

        boolean fallFrame = task.shotFrame == 14 || task.shotFrame == 33;
        boolean belowFallLimit = task.fall < 5.0D;
        boolean canFall = fallFrame && belowFallLimit;
        if (canFall) {
            boolean emptyBelow = location.clone().subtract(0.0D, 1.0D, 0.0D).getBlock().isEmpty();
            if (emptyBelow) {
                // 只有脚下为空时才继续累积下坠距离，模拟落地停顿。
                task.fall += 0.1D;
                return;
            }
        }
        task.shotFrame++;
    }

    /**
     * 在实体脚下绘制完整圆圈。
     *
     * @param task 当前拖尾任务
     */
    private static void footCircle(TrailTask task) {
        Location origin = task.entity.getLocation();
        double angle = 0.0D;
        for (int i = 0; i < 25; i++) {
            double x = ParticleConstants.PLAYER_CIRCLE_RADIUS * Math.sin(angle);
            double z = ParticleConstants.PLAYER_CIRCLE_RADIUS * Math.cos(angle);
            task.spawnAt(origin.clone().add(x, 0.0D, z), 1, 0.0D);
            angle += 0.251D;
        }
    }

    /**
     * 在实体头部周围旋转一个粒子点。
     *
     * @param task 当前拖尾任务
     */
    private static void colorSpin(TrailTask task) {
        Location location = task.entity.getLocation().clone().add(0.0D, ParticleConstants.PLAYER_HEAD_Y_OFFSET, 0.0D);
        location.setPitch(0.0F);
        location.setYaw((task.frame + 1) * 30.0F);
        task.spawnAt(location.add(location.getDirection()), 1, 0.0D);
    }

    /**
     * 推进实体附近游动的粒子点。
     *
     * @param task 当前拖尾任务
     */
    private static void fairy(TrailTask task) {
        Location entityLocation = task.entity.getLocation();
        if (task.fairyCurrent == null || task.fairyCurrent.getWorld() != entityLocation.getWorld() || task.fairyCurrent.distanceSquared(entityLocation) > 1600.0D) {
            task.fairyCurrent = entityLocation.clone().add(randomOffset(10), 2.5D, randomOffset(10));
            task.fairyTargetX = Double.NaN;
            task.fairyTargetZ = Double.NaN;
        }
        if (task.fairyCurrent.distanceSquared(entityLocation) > 16.0D) {
            if (Double.isNaN(task.fairyTargetX)) {
                task.fairyTargetX = randomOffset(20);
                task.fairyTargetZ = randomOffset(20);
            }
            Location target = entityLocation.clone().add(task.fairyTargetX, ParticleConstants.PLAYER_HEAD_Y_OFFSET, task.fairyTargetZ);
            Vector direction = target.toVector().subtract(task.fairyCurrent.toVector());
            task.fairyCurrent.setDirection(direction);
        } else {
            task.fairyTargetX = Double.NaN;
            task.fairyTargetZ = Double.NaN;
        }
        task.fairyCurrent.add(task.fairyCurrent.getDirection().multiply(0.3D));
        task.spawnAt(task.fairyCurrent, 3, 0.0D);
    }

    /**
     * 生成以 0.1 格为步长的随机坐标偏移。
     *
     * @param range 最大十分位偏移
     * @return 随机偏移
     */
    private static double randomOffset(int range) {
        return ThreadLocalRandom.current().nextInt(-range, range + 1) * 0.1D;
    }

}
