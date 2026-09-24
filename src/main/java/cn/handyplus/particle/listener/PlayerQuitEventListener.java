package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 粒子效果生命周期监听器。
 *
 * @author handy
 */
@HandyListener
public final class PlayerQuitEventListener implements Listener {

    /**
     * 处理玩家退出。
     *
     * @param event 退出事件
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        HandyParticleCore.disable(event.getPlayer());
    }

}
