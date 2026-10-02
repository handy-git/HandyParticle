package cn.handyplus.particle.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 粒子选择gui类型。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum ParticleGuiTypeEnum {
    /**
     * 一级菜单，选择粒子类别。
     */
    CATEGORY("particleCategory"),
    /**
     * 二级菜单，选择粒子效果或翅膀外形。
     */
    EFFECT("particleEffect"),
    /**
     * 三级菜单，选择拖尾运动模式。
     */
    MODE("particleMode"),
    /**
     * 四级菜单，选择拖尾颜色。
     */
    COLOR("particleColor"),
    /**
     * 三级菜单，选择翅膀图案。
     */
    WING_COLOR("particleWingColor");

    private final String type;

}
