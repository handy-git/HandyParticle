package cn.handyplus.particle.core.trail;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.constants.ParticleConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 粒子拖尾的内置运动模式。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum TrailModeTypeEnum {
    /**
     * 直接在实体当前位置生成。
     */
    DEFAULT(4, 1.0D),
    /**
     * 围绕实体前方摆动。
     */
    CIRCLE(3, 0.0D),
    /**
     * 在实体周围生成魔法环。
     */
    MAGICIAN(2, 0.0D),
    /**
     * 周期性改变扩散半径。
     */
    PULSE(3, 0.0D),
    /**
     * 沿螺旋轨迹生成。
     */
    HELIX(2, 0.0D),
    /**
     * 生成双螺旋轨迹。
     */
    DNA(1, 0.0D),
    /**
     * 沿半径变化的螺旋轨迹生成。
     */
    SPIRAL(2, 0.0D),
    /**
     * 在实体脚下绘制完整圆圈。
     */
    FOOT_CIRCLE(1, 0.0D),
    /**
     * 在实体头部周围旋转。
     */
    COLOR_SPIN(1, 0.0D),
    /**
     * 在实体附近游动并跟随。
     */
    FAIRY(3, 0.0D);

    /**
     * 每次刷新发送的粒子数量。
     */
    private final int count;
    /**
     * 粒子扩散范围。
     */
    private final double offset;

    /**
     * 获取当前运动模式的本地化描述。
     *
     * @return 运动模式描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("trailModeDesc." + name());
    }

    /**
     * 列出粒子拖尾可用的运动模式。
     *
     * @return 运动模式名称，中文环境下附带描述
     */
    @NotNull
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        boolean chinese = ParticleConstants.isChinese();
        for (TrailModeTypeEnum mode : values()) {
            result.add(chinese ? mode.name() + "(" + mode.getDesc() + ")" : mode.name());
        }
        return result;
    }

    /**
     * 按名称查找运动模式，忽略命令补全中的本地化描述。
     *
     * @param value 运动模式名称，可为空
     * @return 对应运动模式，未找到时返回 null
     */
    @Nullable
    public static TrailModeTypeEnum get(@Nullable String value) {
        if (value == null) {
            return null;
        }
        String name = ParticleConstants.trimDescription(value);
        for (TrailModeTypeEnum mode : values()) {
            if (mode.name().equalsIgnoreCase(name)) {
                return mode;
            }
        }
        return null;
    }
}
