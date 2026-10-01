package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.RegionSchedulerUtil;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * 区块加载时恢复区块内的实体粒子效果。
 *
 * @author handy
 */
@HandyListener
public final class EntityParticleRestoreListener implements Listener {

    /**
     * 区块加载完成后按区块所在区域恢复实体粒子。
     *
     * @param event 区块加载事件
     */
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (EntityParticleRestoreUtil.isDone()) {
            return;
        }
        Chunk chunk = event.getChunk();
        World world = chunk.getWorld();
        // 实体在区块加载后才会进入世界，延迟一 tick 再按区域扫描
        Location location = new Location(world, chunk.getX() << 4, 0, chunk.getZ() << 4);
        RegionSchedulerUtil.runTaskLater(location, () -> EntityParticleRestoreUtil.restore(chunk), 1L);
    }

}
