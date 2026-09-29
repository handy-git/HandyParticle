package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleConstants;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 在实体附近游动并跟随的粒子精灵任务。
 *
 * @author handy
 */
public final class EventFairyTask extends HandyRunnable {
    private final Entity entity;
    private final EventFairyTypeEnum type;
    private Location current;
    private double targetX = Double.NaN;
    private double targetZ = Double.NaN;

    /**
     * 创建粒子精灵并将其放在实体头部附近。
     *
     * @param entity 实体
     * @param type   精灵颜色
     */
    public EventFairyTask(@NotNull Entity entity, @NotNull EventFairyTypeEnum type) {
        this.entity = entity;
        this.type = type;
        this.resetCurrent();
    }

    /**
     * 推进精灵位置并生成彩色粒子团。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        Location entityLocation = entity.getLocation();
        if (current.getWorld() != entityLocation.getWorld()) {
            this.resetCurrent();
            entityLocation = entity.getLocation();
        }
        if (current.distanceSquared(entityLocation) > 1600.0D) {
            this.resetCurrent();
            entityLocation = entity.getLocation();
        }

        // 精灵离开实体四格后选择实体头部附近的新目标并折返。
        if (current.distanceSquared(entityLocation) > 16.0D) {
            this.moveToward(entityLocation);
        } else {
            targetX = Double.NaN;
            targetZ = Double.NaN;
        }
        current.add(current.getDirection().multiply(0.3D));
        EventParticleUtil.spawnColor(current, type.getColor().getColor(), 3);
    }

    /**
     * 让精灵朝实体头部附近的随机目标移动。
     *
     * @param entityLocation 实体当前位置
     */
    private void moveToward(@NotNull Location entityLocation) {
        if (Double.isNaN(targetX)) {
            targetX = randomOffset(20);
            targetZ = randomOffset(20);
        }
        Location target = entityLocation.clone().add(targetX, ParticleConstants.PLAYER_HEAD_Y_OFFSET, targetZ);
        Vector direction = target.toVector().subtract(current.toVector());
        current.setDirection(direction);
    }

    /**
     * 将精灵重置到实体头部附近，保留实体当前朝向作为初始移动方向。
     */
    private void resetCurrent() {
        current = entity.getLocation().clone().add(randomOffset(10), 2.5D, randomOffset(10));
        targetX = Double.NaN;
        targetZ = Double.NaN;
    }

    /**
     * 生成以 0.1 格为步长的随机坐标偏移。
     *
     * @param range 最大十分位偏移
     * @return 随机偏移
     */
    private double randomOffset(int range) {
        return ThreadLocalRandom.current().nextInt(-range, range + 1) * 0.1D;
    }
}
