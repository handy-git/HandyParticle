package cn.handyplus.particle.core.trail;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 粒子轨迹的实际发射器，由调用方决定粒子类型和参数。
 *
 * @author handy
 */
@FunctionalInterface
public interface ParticleEmitter {

    /**
     * 在指定位置生成粒子。
     *
     * @param player   玩家
     * @param location 生成位置
     * @param tick     当前轨迹 Tick
     */
    void emit(Player player, Location location, long tick);
}
