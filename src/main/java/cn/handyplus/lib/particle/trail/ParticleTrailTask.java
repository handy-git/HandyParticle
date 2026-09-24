package cn.handyplus.lib.particle.trail;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.lib.particle.HandyParticleApi;
import cn.handyplus.lib.util.BaseUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * 玩家粒子轨迹定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ParticleTrailTask extends HandyRunnable {
    /**
     * 任务所属玩家的 UUID。
     */
    private final UUID playerId;
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
        Player player = BaseUtil.getOnlinePlayer(playerId).orElse(null);
        if (player == null) {
            HandyParticleApi.disable(playerId);
            return;
        }
        pattern.render(player, player.getLocation().clone(), tick++, options, emitter);
    }

}
