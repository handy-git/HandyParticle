package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.HandyLoginEvent;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleType;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import cn.handyplus.particle.util.ParticleRestoreUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
     * <p>无法解析的类别或效果视为历史脏数据，直接清理该记录并输出控制台提示。
     *
     * @param event 登录事件
     */
    @EventHandler
    public void onEvent(HandyLoginEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            for (PlayerParticleEntity saved : PlayerParticleService.findByUuid(playerId)) {
                ParticleType particleType = ParticleRestoreUtil.parse("玩家 " + playerId, saved.getCategory(),
                        saved.getType(), saved.getColor(), saved.getMode());
                if (particleType == null) {
                    // 不清理的话每次登录都会重复尝试同一条无效记录
                    PlayerParticleService.delete(playerId, saved.getCategory());
                    continue;
                }
                HandyParticleApi.enable(player, particleType);
            }
        });
    }

}
