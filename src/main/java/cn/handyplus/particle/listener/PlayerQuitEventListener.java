package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.core.weapon.WeaponParticleManager;
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
        // 抛射物与武器效果不在任务表中，需单独清理避免记录泄漏
        ProjectileParticleManager.disable(event.getPlayer().getUniqueId());
        WeaponParticleManager.disable(event.getPlayer().getUniqueId());
    }

}
