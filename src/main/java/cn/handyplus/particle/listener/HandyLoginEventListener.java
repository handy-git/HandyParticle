package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.HandyLoginEvent;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import cn.handyplus.particle.util.ParticleColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
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
    public void onEvent(HandyLoginEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            List<PlayerParticleEntity> saved = PlayerParticleService.findByUuid(playerId);
            for (PlayerParticleEntity entity : saved) {
                if (ParticleCategoryTypeEnum.WING.name().equalsIgnoreCase(entity.getCategory())) {
                    HandyParticleApi.enable(player, WingModelEnum.getEnumThrow(entity.getType()), WingColorEnum.getEnumThrow(entity.getColor()));
                    continue;
                }
                ParticleTypeEnum particleTypeEnum = ParticleTypeEnum.getEnumThrow(entity.getType());
                TrailModeTypeEnum trailModeTypeEnum = entity.getMode() == null ? null : TrailModeTypeEnum.get(entity.getMode());
                HandyParticleApi.enable(player, particleTypeEnum, trailModeTypeEnum, ParticleColorUtil.parse(entity.getColor()));
            }
        });
    }

}
