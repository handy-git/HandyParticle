package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.constants.ParticleConstants;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 玩家头部旋转色环任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class EventColorSpinTask extends HandyRunnable {
    private final Player player;
    private final EventColorSpinTypeEnum type;
    private float angle;

    /**
     * 推进旋转角度并在头部周围生成一个彩色粒子。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        // 每两 tick 转动 30 度，单点连续刷新后形成环状残影。
        angle += 30.0F;
        if (angle >= 180.0F) {
            angle = -180.0F;
        }
        Location location = player.getLocation().clone().add(0.0D, ParticleConstants.PLAYER_HEAD_Y_OFFSET, 0.0D);
        location.setPitch(0.0F);
        location.setYaw(angle);
        location.add(location.getDirection());
        EventParticleUtil.spawnColor(location, type.getColor().getColor(), 1);
    }
}
