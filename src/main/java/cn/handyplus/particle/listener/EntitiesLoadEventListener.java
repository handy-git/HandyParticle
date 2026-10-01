package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.internal.RegionSchedulerUtil;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.EntitiesLoadEvent;

/**
 * 实体加载完成后恢复保存的粒子效果。
 *
 * @author handy
 */
@HandyListener(version = VersionCheckEnum.V_1_18)
public final class EntitiesLoadEventListener implements Listener {

    /**
     * 等实体进入世界后，在所属区域恢复粒子。
     *
     * @param event 实体加载事件
     */
    @EventHandler
    public void onEntitiesLoad(EntitiesLoadEvent event) {
        if (EntityParticleRestoreUtil.isDone()) {
            return;
        }
        Chunk chunk = event.getChunk();
        Location location = new Location(chunk.getWorld(), chunk.getX() << 4, 0, chunk.getZ() << 4);
        // 事件提供实体列表，避免依赖区块是否已加载或主动加载区块。
        RegionSchedulerUtil.runTaskLater(location, () -> {
            for (Entity entity : event.getEntities()) {
                EntityParticleRestoreUtil.restore(entity);
            }
        }, 1L);
    }
}
