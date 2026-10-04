package cn.handyplus.particle.util;

import cn.handyplus.lib.constants.BaseConstants;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * 粒子可见范围控制。
 *
 * @author handy
 */
public final class ParticleVisibilityUtil {

    private static final long TPS_CACHE_TIME = 1000L;
    private static volatile long lastCheckTime;
    private static volatile boolean onlyOwner;
    private static Method getTpsMethod;

    private ParticleVisibilityUtil() {
    }

    /**
     * 向实体位置发送粒子，并按低 TPS 配置限制接收者。
     *
     * @param display 粒子显示对象
     * @param location 生成位置
     * @param entity 效果所属实体
     */
    public static void spawn(@NotNull ParticleDisplay display, @NotNull Location location, @NotNull Entity entity) {
        if (entity instanceof Player && thisOnlyVisible()) {
            display.onlyVisibleTo((Player) entity);
            try {
                display.spawn(location);
            } finally {
                display.onlyVisibleTo();
            }
            return;
        }
        display.spawn(location);
    }

    /**
     * 判断当前是否启用低 TPS 仅自己可见。
     *
     * @return 启用且 TPS 低于阈值时返回 true
     */
    private static boolean thisOnlyVisible() {
        long now = System.currentTimeMillis();
        if (now - lastCheckTime < TPS_CACHE_TIME) {
            return onlyOwner;
        }
        synchronized (ParticleVisibilityUtil.class) {
            if (now - lastCheckTime < TPS_CACHE_TIME) {
                return onlyOwner;
            }
            boolean enabled = BaseConstants.CONFIG != null
                    && BaseConstants.CONFIG.getBoolean("particleVisibility.lowTps.enabled", true);
            double threshold = BaseConstants.CONFIG == null
                    ? 18.0D : BaseConstants.CONFIG.getDouble("particleVisibility.lowTps.threshold", 18.0D);
            onlyOwner = enabled && readTps() < threshold;
            lastCheckTime = now;
            return onlyOwner;
        }
    }

    /**
     * 读取服务端一分钟平均 TPS。
     *
     * @return TPS，无法读取时返回 20
     */
    private static double readTps() {
        try {
            if (getTpsMethod == null) {
                getTpsMethod = Bukkit.getServer().getClass().getMethod("getTPS");
            }
            Object result = getTpsMethod.invoke(Bukkit.getServer());
            if (result instanceof double[] && ((double[]) result).length > 0) {
                return ((double[]) result)[0];
            }
        } catch (ReflectiveOperationException ignored) {
            // 非 Paper 服务端没有 TPS API，保持全局可见。
        }
        return 20.0D;
    }
}
