package cn.handyplus.particle.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 跟随实体游动的固定精灵颜色。
 *
 * @author handy
 */
@Getter
@RequiredArgsConstructor
public enum EventFairyTypeEnum {
    /**
     * 绿色粒子精灵。
     */
    FAIRY_GREEN(EventParticleColorEnum.GREEN),
    /**
     * 蓝色粒子精灵。
     */
    FAIRY_BLUE(EventParticleColorEnum.BLUE),
    /**
     * 紫色粒子精灵。
     */
    FAIRY_PURPLE(EventParticleColorEnum.PURPLE),
    /**
     * 红色粒子精灵。
     */
    FAIRY_RED(EventParticleColorEnum.RED),
    /**
     * 黑色粒子精灵。
     */
    FAIRY_BLACK(EventParticleColorEnum.BLACK),
    /**
     * 白色粒子精灵。
     */
    FAIRY_WHITE(EventParticleColorEnum.WHITE),
    /**
     * 黄色粒子精灵。
     */
    FAIRY_YELLOW(EventParticleColorEnum.YELLOW);

    private final EventParticleColorEnum color;
}
