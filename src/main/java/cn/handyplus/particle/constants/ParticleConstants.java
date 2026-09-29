package cn.handyplus.particle.constants;

import java.util.Collections;
import java.util.Map;

/**
 * 常量
 *
 * @author handy
 */
public final class ParticleConstants {

    private ParticleConstants() {
    }

    /**
     * 玩家头部附近效果使用的纵向偏移。
     */
    public static final double PLAYER_HEAD_Y_OFFSET = 2.2D;

    /**
     * 各类粒子的刷新间隔。
     */
    public static volatile Map<ParticleCategoryTypeEnum, Long> REFRESH_INTERVALS = Collections.emptyMap();

}
