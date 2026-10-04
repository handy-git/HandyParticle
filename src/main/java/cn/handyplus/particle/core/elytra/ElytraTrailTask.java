package cn.handyplus.particle.core.elytra;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import cn.handyplus.particle.util.ParticleVisibilityUtil;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 鞘翅滑翔拖尾任务。
 *
 * <p>仅在玩家穿戴鞘翅并处于滑翔状态时在身后生成物品粒子。
 *
 * @author handy
 */
public final class ElytraTrailTask extends HandyRunnable {
    /**
     * 每侧拖尾的粒子数量。
     */
    private static final int TRAIL_POINTS = 6;

    /**
     * 任务所属实体。
     */
    private final Entity entity;
    /**
     * 当前材质组合对应的可复用粒子显示对象。
     */
    private final ParticleDisplay[] displays;

    /**
     * 创建鞘翅拖尾任务。
     *
     * @param entity 实体
     * @param type   材质组合
     */
    public ElytraTrailTask(@NotNull Entity entity, @NotNull ElytraTrailTypeEnum type) {
        this.entity = entity;
        this.displays = this.createDisplays(type.items());
    }

    /**
     * 创建自定义鞘翅拖尾任务。
     *
     * @param entity 实体
     * @param items  自定义物品
     */
    public ElytraTrailTask(@NotNull Entity entity, @NotNull ItemStack[] items) {
        this.entity = entity;
        this.displays = this.createDisplays(items);
    }

    /**
     * 创建鞘翅残影显示对象。
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
     * 滑翔时在实体身后生成鞘翅残影。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        if (!this.canGlide()) {
            return;
        }
        Location origin = entity.getLocation();
        Vector direction = origin.getDirection();
        double yaw = Math.toRadians(origin.getYaw());
        double sideX = Math.cos(yaw);
        double sideZ = Math.sin(yaw);
        // 在身后左右两侧展开残影，避免多个粒子重叠在同一位置。
        for (int index = 0; index < TRAIL_POINTS; index++) {
            double distance = 0.6D + index * 0.35D;
            for (int side = -1; side <= 1; side += 2) {
                double width = side * (0.6D + ThreadLocalRandom.current().nextDouble(0.2D));
                Location location = origin.clone().add(
                        -direction.getX() * distance + sideX * width,
                        0.9D - direction.getY() * distance + ThreadLocalRandom.current().nextDouble(0.3D),
                        -direction.getZ() * distance + sideZ * width);
                ParticleVisibilityUtil.spawn(displays[ThreadLocalRandom.current().nextInt(displays.length)], location, entity);
            }
        }
    }

    /**
     * 判断实体当前是否处于鞘翅滑翔状态。
     *
     * @return 正在滑翔时返回 true
     */
    private boolean canGlide() {
        if (!(entity instanceof Player)) {
            return false;
        }
        Player player = (Player) entity;
        if (!player.isGliding()) {
            return false;
        }
        ItemStack chestplate = player.getInventory().getChestplate();
        return chestplate != null && chestplate.getType() == Material.ELYTRA;
    }

}
