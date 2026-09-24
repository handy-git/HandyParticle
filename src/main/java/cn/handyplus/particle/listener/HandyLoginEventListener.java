package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.HandyLoginEvent;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.trail.ParticleTrailPatternEnum;
import cn.handyplus.particle.core.wing.ParticleWingTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

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
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            Player player = event.getPlayer();
            Optional<PlayerParticleEntity> saved = PlayerParticleService.findByUuid(player.getUniqueId());
            if (!saved.isPresent()) {
                return;
            }
            PlayerParticleEntity entity = saved.get();
            ParticleTypeEnum type = ParticleTypeEnum.getEnumThrow(entity.getParticleType());
            if (type.isTrail()) {
                ParticleTrailPatternEnum pattern = ParticleTrailPatternEnum.valueOf(type.name());
                HandyParticleCore.setTrail(player, pattern, entity.getParticleName());
                return;
            }
            ParticleWingTypeEnum wingType = ParticleWingTypeEnum.valueOf(type.name());
            HandyParticleCore.setWings(player, wingType);
        });
    }

}
