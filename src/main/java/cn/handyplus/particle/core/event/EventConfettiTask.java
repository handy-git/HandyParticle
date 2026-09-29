package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 * 三色纸屑活动效果任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class EventConfettiTask extends HandyRunnable {
    private final Entity entity;
    private final EventConfettiTypeEnum type;

    /**
     * 在玩家所在位置生成三组物品碎片粒子。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        Location location = entity.getLocation();
        for (ItemStack item : type.getItems()) {
            EventParticleUtil.spawnItem(location, item);
        }
    }
}
