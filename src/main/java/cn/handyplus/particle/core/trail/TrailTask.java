package cn.handyplus.particle.core.trail;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

/**
 * 内置粒子拖尾定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class TrailTask extends HandyRunnable {
    /**
     * 任务所属玩家。
     */
    private final Player player;
    /**
     * 当前启用的内置拖尾。
     */
    private final TrailTypeEnum type;
    /**
     * 启用时解析并校验过的粒子。
     */
    private final Particle particle;

    /**
     * 刷新一次拖尾，玩家离线时取消当前任务。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        type.spawn(player, particle);
    }
}
