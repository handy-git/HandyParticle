package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.lib.internal.HandyLoginEvent;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;
import java.util.UUID;

/**
 * 玩家登录时恢复保存的粒子效果。
 *
 * @author handy
 */
@HandyListener
public final class HandyLoginEventListener implements Listener {

    /**
     * 恢复玩家保存的效果。
     *
     * @param event 登录事件
     */
    @EventHandler
    public void onJoin(HandyLoginEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            Optional<PlayerParticleEntity> saved = PlayerParticleService.findByUuid(playerId);
            if (!saved.isPresent()) {
                return;
            }
            PlayerParticleEntity entity = saved.get();
            EntitySchedulerUtil.runSafeOnEntityScheduler(player,
                    () -> HandyParticleApi.enable(player, ParticleTypeEnum.valueOf(entity.getParticleType())));
        });
    }

}
