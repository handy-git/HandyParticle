package cn.handyplus.lib.particle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 粒子翅膀生命周期监听器。
 *
 * @author handy
 */
final class ParticleWingListener implements Listener {

    /**
     * 处理玩家退出。
     *
     * @param event 退出事件
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ParticleWingApi.disable(event.getPlayer().getUniqueId());
    }

}
