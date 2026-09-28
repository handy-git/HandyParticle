package cn.handyplus.particle.core.rain;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 客户端物品粒子雨任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ItemRainTask extends HandyRunnable {
    private static final int DROP_TTL = 5;
    private static final double VIEW_DISTANCE_SQUARED = 625.0D;

    private final Player player;
    private final ItemRainTypeEnum type;
    private final Map<Location, Integer> drops = new HashMap<>();
    private long tick;

    /**
     * 刷新物品雨。所有物品只作为客户端粒子发送，不创建世界实体。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleCore.disable(player);
            return;
        }
        Iterator<Map.Entry<Location, Integer>> iterator = drops.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Location, Integer> entry = iterator.next();
            int age = entry.getValue() + 1;
            if (age >= DROP_TTL) {
                iterator.remove();
                continue;
            }
            Location location = entry.getKey().clone().subtract(0.0D, 0.5D, 0.0D);
            entry.setValue(age);
            send(location, type.item(tick - age));
        }
        Location spawn = player.getLocation().clone().add(0.0D, 3.0D, 0.0D);
        drops.put(spawn, 0);
        send(spawn, type.item(tick));
        tick++;
    }

    /**
     * 向附近玩家发送单个客户端物品粒子。
     *
     * @param location 粒子位置
     * @param item     物品外观
     */
    private void send(@NotNull Location location, @NotNull ItemStack item) {
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (viewer.getWorld() != location.getWorld() || viewer.getLocation().distanceSquared(location) > VIEW_DISTANCE_SQUARED) {
                continue;
            }
            ParticleDisplay.of(XParticle.ITEM)
                    .withItem(item)
                    .withCount(1)
                    .onlyVisibleTo(viewer)
                    .spawn(location);
        }
    }
}
