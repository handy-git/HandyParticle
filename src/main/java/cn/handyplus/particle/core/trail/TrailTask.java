package cn.handyplus.particle.core.trail;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 内置粒子拖尾定时任务。
 *
 * @author handy
 */
public final class TrailTask extends HandyRunnable {
    /**
     * 任务所属实体。
     */
    final Entity entity;
    /**
     * 当前启用的内置拖尾。
     */
    final TrailTypeEnum type;
    /**
     * 当前运动模式。
     */
    private final TrailModeTypeEnum mode;
    /**
     * 自定义 Dust 颜色。
     */
    final Color color;
    /**
     * 当前任务复用的粒子显示对象。
     */
    private final ParticleDisplay display;
    /**
     * 当前定时刷新帧数。
     */
    int frame;
    /**
     * 点击型轨迹的起点，空值表示未触发。
     */
    Location shotOrigin;
    /**
     * 点击型轨迹已绘制的帧数。
     */
    int shotFrame;
    /**
     * 弹簧轨迹的下坠距离。
     */
    double fall;
    /**
     * 精灵当前的位置。
     */
    Location fairyCurrent;
    double fairyTargetX = Double.NaN;
    double fairyTargetZ = Double.NaN;

    /**
     * 创建指定运动模式的拖尾任务。
     *
     * @param entity 实体
     * @param type   拖尾类型
     * @param mode   运动模式
     * @param color  自定义 Dust 颜色，为空时使用白色
     */
    public TrailTask(@NotNull Entity entity, @NotNull TrailTypeEnum type, @NotNull TrailModeTypeEnum mode,
                     @Nullable Color color) {
        this.entity = entity;
        this.type = type;
        this.mode = mode;
        this.color = color == null ? Color.WHITE : color;
        this.display = type.createDisplay(this.color);
    }

    /**
     * 刷新一次拖尾，实体失效时取消当前任务。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        // 每次调度只推进一帧，点击型模式则在触发后由同一状态继续推进。
        TrailModeAlgorithm.render(mode, this);
        frame++;
    }

    /**
     * 左键空气时开始一次点击型运动。
     */
    public void trigger() {
        TrailModeAlgorithm.trigger(mode, this);
    }

    /**
     * 在指定位置发送当前拖尾粒子。
     *
     * @param location 位置
     * @param amount   数量
     * @param offset   扩散范围
     */
    void spawnAt(@NotNull Location location, int amount, double offset) {
        type.spawnAt(display, location, amount, offset);
    }

}
