package cn.handyplus.lib.particle.trail;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 粒子运动算法。
 *
 * @author handy
 */
public interface ParticleTrailPattern {

    /**
     * 根据当前 Tick 计算轨迹位置。
     *
     * @param player  玩家
     * @param origin  轨迹原点
     * @param tick    当前轨迹 Tick
     * @param options 轨迹参数
     * @param emitter 粒子发射器
     */
    void render(Player player, Location origin, long tick, ParticleTrailOptions options, ParticleEmitter emitter);
}
