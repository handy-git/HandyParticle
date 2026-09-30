package cn.handyplus.particle.constants;

import cn.handyplus.lib.constants.BaseConstants;
import org.jetbrains.annotations.NotNull;

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
     * 实体头部附近效果使用的纵向偏移。
     */
    public static final double PLAYER_HEAD_Y_OFFSET = 2.2D;

    /**
     * 实体脚下圆圈的半径。
     */
    public static final double PLAYER_CIRCLE_RADIUS = 0.75D;

    /**
     * 各类粒子的刷新间隔。
     */
    public static volatile Map<ParticleCategoryTypeEnum, Long> REFRESH_INTERVALS = Collections.emptyMap();


    /**
     * 判断当前是否使用中文语言文件。
     *
     * @return 使用中文时返回 true
     */
    public static boolean isChinese() {
        if (BaseConstants.CONFIG == null) {
            return true;
        }
        return "zh_CN".equalsIgnoreCase(BaseConstants.CONFIG.getString("language", "zh_CN"));
    }

    /**
     * 去掉补全附带的括号描述。
     *
     * @param value 参数值
     * @return 只保留名称部分
     */
    @NotNull
    public static String trimDescription(@NotNull String value) {
        int index = value.indexOf('(');
        return (index < 0 ? value : value.substring(0, index)).trim();
    }

}
