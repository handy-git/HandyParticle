package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * 三色纸屑活动效果任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class EventConfettiTask extends HandyRunnable {
    private final Player player;
    private final EventConfettiTypeEnum type;

    /**
     * 在玩家所在位置生成三组物品碎片粒子。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        Location location = player.getLocation();
        for (ItemStack item : type.getItems()) {
            EventParticleUtil.spawnItem(location, item);
        }
    }
}
