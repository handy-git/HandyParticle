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
    /**
     * 物品雨粒子的存活帧数。
     */
    private static final int DROP_TTL = 5;
    /**
     * 物品雨每帧的下落距离。
     */
    private static final double DROP_STEP = 0.25D;
    /**
     * 物品雨相对实体头部的水平范围。
     */
    private static final double RAIN_RADIUS = 0.8D;

    /**
     * 任务所属实体。
     */
    private final Entity entity;
    /**
     * 当前物品组合对应的可复用粒子显示对象。
     */
    private final ParticleDisplay[] displays;
    /**
     * 当前正在下落的物品位置及年龄。
     */
    private final Map<Location, Integer> drops = new HashMap<>();
    /**
     * 当前物品雨刷新帧数。
     */
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
        this.displays = this.createDisplays(items);
    }

    /**
     * 创建自定义物品雨任务。
     *
     * @param entity 实体
     * @param items  自定义物品
     */
    public ItemRainTask(@NotNull Entity entity, @NotNull ItemStack[] items) {
        this.entity = entity;
        this.displays = this.createDisplays(items);
    }

    /**
     * 创建物品雨显示对象。
     *
     * @param items 物品列表
     * @return 粒子显示对象
     */
    private ParticleDisplay[] createDisplays(@NotNull ItemStack[] items) {
        ParticleDisplay[] displays = new ParticleDisplay[items.length];
        for (int index = 0; index < items.length; index++) {
            displays[index] = ParticleDisplay.of(XParticle.ITEM).withItem(items[index]).withCount(1);
        }
        return displays;
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
