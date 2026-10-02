package cn.handyplus.particle.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleConstants;
import org.bukkit.configuration.file.FileConfiguration;

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

    public static FileConfiguration CATEGORY_CONFIG, EFFECT_CONFIG, MODE_CONFIG, COLOR_CONFIG, WING_COLOR_CONFIG;

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
        CATEGORY_CONFIG = HandyConfigUtil.load("gui/category.yml");
        EFFECT_CONFIG = HandyConfigUtil.load("gui/effect.yml");
        MODE_CONFIG = HandyConfigUtil.load("gui/mode.yml");
        COLOR_CONFIG = HandyConfigUtil.load("gui/color.yml");
        WING_COLOR_CONFIG = HandyConfigUtil.load("gui/wing-color.yml");

        // 加载刷新间隔
        Map<ParticleCategoryTypeEnum, Long> intervals = new EnumMap<>(ParticleCategoryTypeEnum.class);
        for (ParticleCategoryTypeEnum category : ParticleCategoryTypeEnum.values()) {
            long interval = BaseConstants.CONFIG.getLong("refreshInterval." + category.name().toLowerCase(Locale.ROOT), 2L);
            intervals.put(category, interval > 0L ? interval : 2L);
        }
        ParticleConstants.REFRESH_INTERVALS = Collections.unmodifiableMap(intervals);
    }

}
