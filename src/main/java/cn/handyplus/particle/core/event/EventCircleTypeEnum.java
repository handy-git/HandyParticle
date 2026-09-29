package cn.handyplus.particle.core.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 实体脚下圆圈的固定颜色。
 *
 * @author handy
 */
@Getter
@RequiredArgsConstructor
public enum EventCircleTypeEnum {
    /**
     * 白色脚下圆圈。
     */
    CIRCLE_WHITE(EventParticleColorEnum.WHITE),
    /**
     * 红色脚下圆圈。
     */
    CIRCLE_RED(EventParticleColorEnum.RED),
    /**
     * 黄色脚下圆圈。
     */
    CIRCLE_YELLOW(EventParticleColorEnum.YELLOW),
    /**
     * 绿色脚下圆圈。
     */
    CIRCLE_GREEN(EventParticleColorEnum.GREEN),
    /**
     * 蓝色脚下圆圈。
     */
    CIRCLE_BLUE(EventParticleColorEnum.BLUE),
    /**
     * 紫色脚下圆圈。
     */
    CIRCLE_PURPLE(EventParticleColorEnum.PURPLE),
    /**
     * 黑色脚下圆圈。
     */
    CIRCLE_BLACK(EventParticleColorEnum.BLACK);

    private final EventParticleColorEnum color;
}
