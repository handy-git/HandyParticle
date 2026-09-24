package cn.handyplus.particle.core.trail;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

/**
 * 玩家粒子轨迹定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ParticleTrailTask extends HandyRunnable {
    /**
     * 任务所属玩家。
     */
    private final Player player;
    /**
     * 当前使用的运动算法。
     */
    private final ParticleTrailPattern pattern;
    /**
     * 运动算法参数。
     */
    private final ParticleTrailOptions options;
    /**
     * 调用方提供的粒子发射器。
     */
    private final ParticleEmitter emitter;
    /**
     * 已执行的轨迹刷新次数。
     */
    private long tick;

    /**
     * 刷新一次轨迹。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        pattern.render(player, player.getLocation().clone(), tick++, options, emitter);
    }

}
