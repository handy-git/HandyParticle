package cn.handyplus.particle.core.confetti;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 围绕实体发送物品纸屑粒子。
 *
 * @author handy
 */
public final class ConfettiTask extends HandyRunnable {
    private final Entity entity;
    private final ItemStack[] items;

    /**
     * 创建纸屑任务。
     *
     * @param entity 实体
     * @param type   物品组合
     */
    public ConfettiTask(@NotNull Entity entity, @NotNull ConfettiTypeEnum type) {
        this.entity = entity;
        this.items = type.items();
    }

    /**
     * 在实体位置发送每种物品的纸屑粒子。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        Location location = entity.getLocation();
        for (ItemStack item : items) {
            ParticleDisplay.of(XParticle.ITEM).withItem(item).withCount(3).withExtra(0.1D).spawn(location);
        }
    }
}
