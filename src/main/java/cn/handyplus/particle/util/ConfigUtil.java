package cn.handyplus.particle.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * 配置
 *
 * @author handy
 */
public final class ConfigUtil {

    /**
     * 禁止实例化配置工具。
     */
    private ConfigUtil() {
    }

    /**
     * 重新加载配置和语言文件。
     */
    public static void reload() {
        HandyConfigUtil.loadConfig();
        HandyConfigUtil.loadLangConfig(false);
    }

    /**
     * 获取指定粒子类别的刷新间隔，非法配置回退到默认值。
     *
     * @param category 粒子类别
     * @return 刷新间隔，单位 tick
     */
    public static long getRefreshInterval(@NotNull ParticleCategoryTypeEnum category) {
        long interval = BaseConstants.CONFIG.getLong("refreshInterval." + category.name().toLowerCase(Locale.ROOT), 2L);
        return interval > 0L ? interval : 2L;
    }

}
