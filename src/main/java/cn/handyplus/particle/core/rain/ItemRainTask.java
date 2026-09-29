package cn.handyplus.particle.core.rain;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
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
public final class ItemRainTask extends HandyRunnable {
    private static final int DROP_TTL = 5;
    private static final double DROP_STEP = 0.25D;
    private static final double RAIN_RADIUS = 0.8D;

    private final Entity entity;
    private final ParticleDisplay[] displays;
    private final Map<Location, Integer> drops = new HashMap<>();
    private long tick;

    /**
     * 为当前实体创建独立的物品雨显示对象。
     *
     * @param entity 实体
     * @param type   物品组合
     */
    public ItemRainTask(@NotNull Entity entity, @NotNull ItemRainTypeEnum type) {
        this.entity = entity;
        ItemStack[] items = type.items();
        this.displays = new ParticleDisplay[items.length];
        for (int index = 0; index < items.length; index++) {
            displays[index] = ParticleDisplay.of(XParticle.ITEM).withItem(items[index]).withCount(1);
        }
    }

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
            displays[(int) ((tick - age) % displays.length)].spawn(location);
        }
        Location head = entity.getLocation().add(0.0D, 1.6D, 0.0D);
        if (entity instanceof LivingEntity) {
            head = ((LivingEntity) entity).getEyeLocation();
        }
        ParticleDisplay display = displays[(int) (tick % displays.length)];
        for (int index = 0; index < 2; index++) {
            double x = ThreadLocalRandom.current().nextDouble(-RAIN_RADIUS, RAIN_RADIUS);
            double z = ThreadLocalRandom.current().nextDouble(-RAIN_RADIUS, RAIN_RADIUS);
            Location spawn = head.clone().add(x, 1.0D, z);
            drops.put(spawn, 0);
            display.spawn(spawn);
        }
        tick++;
    }

}
