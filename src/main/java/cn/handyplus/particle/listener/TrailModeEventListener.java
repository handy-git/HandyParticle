package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 处理点击触发的普通粒子运动模式。
 *
 * @author handy
 */
@HandyListener
public final class TrailModeEventListener implements Listener {

    /**
     * 左键空气时触发 Shooter 或 Slinky。
     *
     * @param event 玩家交互事件
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            HandyParticleCore.triggerTrail(event.getPlayer());
        }
    }
}
