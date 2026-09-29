package cn.handyplus.particle.core.trail;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 内置粒子拖尾定时任务。
 *
 * @author handy
 */
public final class TrailTask extends HandyRunnable {
    /**
     * 任务所属玩家。
     */
    final Player player;
    /**
     * 当前启用的内置拖尾。
     */
    final TrailTypeEnum type;
    /**
     * 当前运动模式。
     */
    private final TrailModeTypeEnum mode;
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
     * 创建指定运动模式的拖尾任务。
     *
     * @param player   玩家
     * @param type     拖尾类型
     * @param mode     运动模式
     */
    public TrailTask(@NotNull Player player, @NotNull TrailTypeEnum type, @NotNull TrailModeTypeEnum mode) {
        this.player = player;
        this.type = type;
        this.mode = mode;
    }

    /**
     * 刷新一次拖尾，玩家离线时取消当前任务。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
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

}
