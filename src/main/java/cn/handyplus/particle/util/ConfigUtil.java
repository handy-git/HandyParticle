package cn.handyplus.particle.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleConstants;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

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

        // 加载刷新间隔
        Map<ParticleCategoryTypeEnum, Long> intervals = new EnumMap<>(ParticleCategoryTypeEnum.class);
        for (ParticleCategoryTypeEnum category : ParticleCategoryTypeEnum.values()) {
            long interval = BaseConstants.CONFIG.getLong("refreshInterval." + category.name().toLowerCase(Locale.ROOT), 2L);
            intervals.put(category, interval > 0L ? interval : 2L);
        }
        ParticleConstants.REFRESH_INTERVALS = Collections.unmodifiableMap(intervals);
    }

}
