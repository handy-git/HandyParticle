package cn.handyplus.particle.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 绕实体头部旋转的固定颜色效果。
 *
 * @author handy
 */
@Getter
@RequiredArgsConstructor
public enum EventColorSpinTypeEnum {
    /**
     * 白色旋转色环。
     */
    COLOR_SPIN_WHITE(EventParticleColorEnum.WHITE),
    /**
     * 红色旋转色环。
     */
    COLOR_SPIN_RED(EventParticleColorEnum.RED),
    /**
     * 黄色旋转色环。
     */
    COLOR_SPIN_YELLOW(EventParticleColorEnum.YELLOW),
    /**
     * 绿色旋转色环。
     */
    COLOR_SPIN_GREEN(EventParticleColorEnum.GREEN),
    /**
     * 蓝色旋转色环。
     */
    COLOR_SPIN_BLUE(EventParticleColorEnum.BLUE),
    /**
     * 紫色旋转色环。
     */
    COLOR_SPIN_PURPLE(EventParticleColorEnum.PURPLE),
    /**
     * 黑色旋转色环。
     */
    COLOR_SPIN_BLACK(EventParticleColorEnum.BLACK);

    private final EventParticleColorEnum color;
}
