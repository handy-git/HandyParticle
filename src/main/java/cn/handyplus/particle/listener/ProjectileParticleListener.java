package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.EntitySchedulerUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.core.projectile.ProjectileTrail;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家发射抛射物时附加一次性拖尾。
 *
 * <p>只有发射者本人开启了抛射物效果时才会附加，任务不进入粒子任务表，
 * 随抛射物落地自动结束。
 *
 * @author handy
 */
@HandyListener
public final class ProjectileParticleListener implements Listener {

    /**
     * 拖尾任务刷新间隔，单位 tick。
     */
    private static final long TRAIL_PERIOD = 1L;
    /**
     * 拖尾任务首次延迟，单位 tick。
     */
    private static final long TRAIL_DELAY = 1L;

    /**
     * 处理抛射物发射，为开启效果玩家的抛射物附加拖尾。
     *
     * @param event 发射事件
     */
    @EventHandler
    public void onLaunch(@NotNull ProjectileLaunchEvent event) {
        ProjectileSource source = event.getEntity().getShooter();
        // 只处理玩家自己发射的抛射物
        if (!(source instanceof Player)) {
            return;
        }
        Player player = (Player) source;
        ParticleTypeEnum type = ProjectileParticleManager.get(player);
        if (type == null) {
            return;
        }
        // 只对玩家勾选的抛射物类型生效
        if (!ProjectileParticleManager.matches(player, event.getEntity())) {
            return;
        }
        ProjectileTrail trail = new ProjectileTrail(event.getEntity(), type);
        EntitySchedulerUtil.runTaskTimer(event.getEntity(), trail, TRAIL_DELAY, TRAIL_PERIOD, trail::stop);
    }

}
