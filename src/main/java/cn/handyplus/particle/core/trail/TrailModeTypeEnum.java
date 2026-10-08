package cn.handyplus.particle.core.trail;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 粒子拖尾的内置运动模式。
 *
 * @author handy
 */
public enum TrailModeTypeEnum {
    /**
     * 直接在实体当前位置生成。
     */
    DEFAULT,
    /**
     * 围绕实体前方摆动。
     */
    CIRCLE,
    /**
     * 在实体周围生成魔法环。
     */
    MAGICIAN,
    /**
     * 周期性改变扩散半径。
     */
    PULSE,
    /**
     * 沿螺旋轨迹生成。
     */
    HELIX,
    /**
     * 生成双螺旋轨迹。
     */
    DNA,
    /**
     * 沿半径变化的螺旋轨迹生成。
     */
    SPIRAL,
    /**
     * 在实体脚下绘制完整圆圈。
     */
    FOOT_CIRCLE,
    /**
     * 在实体头部周围旋转。
     */
    COLOR_SPIN,
    /**
     * 在实体附近游动并跟随。
     */
    FAIRY;

    /**
     * 获取当前运动模式的本地化描述。
     *
     * @return 运动模式描述
     */
    @NotNull
    public native String getDesc();

    /**
     * 列出粒子拖尾可用的运动模式。
     *
     * @return 运动模式名称，中文环境下附带描述
     */
    @NotNull
    public native static List<String> getAll();

    /**
     * 按名称查找运动模式，忽略命令补全中的本地化描述。
     *
     * @param value 运动模式名称，可为空
     * @return 对应运动模式，未找到时返回 null
     */
    @Nullable
    public native static TrailModeTypeEnum get(@Nullable String value);

}
