package cn.handyplus.particle.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 粒子命令操作类型。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum ParticleCommandActionEnum {
    /**
     * 设置粒子效果。
     */
    SET("set"),
    /**
     * 清除粒子效果。
     */
    CLEAR("clear");

    private final String value;

    /**
     * 按命令参数解析操作类型。
     *
     * @param value 参数值
     * @return 操作类型，无法解析时返回 null
     */
    public static ParticleCommandActionEnum get(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.toLowerCase(Locale.ROOT);
        for (ParticleCommandActionEnum action : values()) {
            if (action.value.equals(normalized)) {
                return action;
            }
        }
        return null;
    }

    /**
     * 获取执行者操作
     *
     * @return 参数值列表
     */
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (ParticleCommandActionEnum action : values()) {
            result.add(action.value);
        }
        return result;
    }

}
