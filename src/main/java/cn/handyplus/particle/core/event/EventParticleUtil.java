package cn.handyplus.particle.core.event;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * 活动效果粒子发送工具。
 *
 * @author handy
 */
final class EventParticleUtil {

    /**
     * 禁止实例化粒子工具。
     */
    private EventParticleUtil() {
    }

    /**
     * 生成带物品外观的纸屑粒子。
     *
     * @param location 粒子位置
     * @param item     纸屑物品外观
     */
    static void spawnItem(@NotNull Location location, @NotNull ItemStack item) {
        ParticleDisplay.of(XParticle.ITEM)
                .withItem(item)
                .withCount(3)
                .withExtra(0.1D)
                .spawn(location);
    }

    /**
     * 生成指定颜色的 Dust 粒子。
     *
     * @param location 粒子位置
     * @param color    粒子颜色
     * @param count    粒子数量
     */
    static void spawnColor(@NotNull Location location, @NotNull Color color, int count) {
        ParticleDisplay.of(XParticle.DUST)
                .withColor(color, 1.0F)
                .withCount(count)
                .spawn(location);
    }
}
