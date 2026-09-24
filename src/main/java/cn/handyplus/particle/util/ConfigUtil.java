package cn.handyplus.particle.util;

import cn.handyplus.lib.util.HandyConfigUtil;

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

}
