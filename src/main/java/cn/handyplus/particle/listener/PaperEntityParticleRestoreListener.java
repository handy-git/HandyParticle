package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 按 Paper 实体进入和离开世界的实际生命周期恢复粒子。
 *
 * @author handy
 */
@HandyListener
public final class PaperEntityParticleRestoreListener implements Listener {

    /**
     * 实体已被标记为有效后恢复粒子，无需猜测加载延迟。
     *
     * @param event 实体进入世界事件
     */
    @EventHandler
    public void onEntityAdd(EntityAddToWorldEvent event) {
        EntityParticleRestoreUtil.restore(event.getEntity());
    }

    /**
     * 实体离开世界时停止旧实例的任务。
     *
     * @param event 实体离开世界事件
     */
    @EventHandler
    public void onEntityRemove(EntityRemoveFromWorldEvent event) {
        EntityParticleRestoreUtil.unload(event.getEntity());
    }
}
