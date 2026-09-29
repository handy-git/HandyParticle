package cn.handyplus.particle.core.event;

import lombok.Getter;

import java.awt.*;

/**
 * 活动效果使用的固定粒子颜色。
 *
 * @author handy
 */
@Getter
public enum EventParticleColorEnum {
    /**
     * 白色，RGB 为 255、255、255。
     */
    WHITE(new Color(255, 255, 255)),
    /**
     * 红色，RGB 为 255、10、10。
     */
    RED(new Color(255, 10, 10)),
    /**
     * 黄色，RGB 为 255、255、10。
     */
    YELLOW(new Color(255, 255, 10)),
    /**
     * 绿色，RGB 为 10、255、10。
     */
    GREEN(new Color(10, 255, 10)),
    /**
     * 蓝色，RGB 为 10、10、255。
     */
    BLUE(new Color(10, 10, 255)),
    /**
     * 紫色，RGB 为 255、10、255。
     */
    PURPLE(new Color(255, 10, 255)),
    /**
     * 黑色，RGB 为 10、10、10。
     */
    BLACK(new Color(10, 10, 10));

    private final Color color;

    /**
     * 创建固定粒子颜色。
     *
     * @param color 粒子颜色
     */
    EventParticleColorEnum(Color color) {
        this.color = color;
    }
}
