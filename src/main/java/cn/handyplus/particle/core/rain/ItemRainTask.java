package cn.handyplus.particle.core.rain;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 客户端物品粒子雨任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ItemRainTask extends HandyRunnable {
    private static final int DROP_TTL = 5;
    private static final double DROP_STEP = 0.25D;
    private static final double RAIN_RADIUS = 0.8D;

    private final Entity entity;
    private final ItemRainTypeEnum type;
    private final Map<Location, Integer> drops = new HashMap<>();
    private final ItemStack[] recentItems = new ItemStack[DROP_TTL];
    private long tick;

    /**
     * 从实体头顶上方生成雨滴，逐帧下落至头顶后停止发送；不创建世界实体。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
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
            Location location = entry.getKey().clone().subtract(0.0D, age * DROP_STEP, 0.0D);
            entry.setValue(age);
            send(location, recentItems[(int) ((tick - age) % DROP_TTL)]);
        }
        Location head = entity.getLocation().add(0.0D, 1.6D, 0.0D);
        if (entity instanceof LivingEntity) {
            head = ((LivingEntity) entity).getEyeLocation();
        }
        ItemStack item = type.item(tick);
        recentItems[(int) (tick % DROP_TTL)] = item;
        for (int index = 0; index < 2; index++) {
            double x = ThreadLocalRandom.current().nextDouble(-RAIN_RADIUS, RAIN_RADIUS);
            double z = ThreadLocalRandom.current().nextDouble(-RAIN_RADIUS, RAIN_RADIUS);
            Location spawn = head.clone().add(x, 1.0D, z);
            drops.put(spawn, 0);
            this.send(spawn, item);
        }
        tick++;
    }

    /**
     * 在雨滴位置向世界发送单个客户端物品粒子。
     *
     * @param location 粒子位置
     * @param item     物品外观
     */
    private void send(@NotNull Location location, @NotNull ItemStack item) {
        ParticleDisplay.of(XParticle.ITEM)
                .withItem(item)
                .withCount(1)
                .spawn(location);
    }

}
