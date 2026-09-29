package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleConstants;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 在玩家脚下生成圆圈粒子的任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class EventCircleTask extends HandyRunnable {
    private final Player player;
    private final EventCircleTypeEnum type;

    /**
     * 在玩家脚下生成一圈彩色粒子。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        Location location = player.getLocation();
        double angle = 0.0D;
        for (int i = 0; i < 25; i++) {
            double x = ParticleConstants.PLAYER_CIRCLE_RADIUS * Math.sin(angle);
            double z = ParticleConstants.PLAYER_CIRCLE_RADIUS * Math.cos(angle);
            EventParticleUtil.spawnColor(location.clone().add(x, 0.0D, z), type.getColor().getColor(), 1);
            angle += 0.251D;
        }
    }
}
