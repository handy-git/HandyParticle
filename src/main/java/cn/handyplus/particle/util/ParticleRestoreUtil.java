package cn.handyplus.particle.util;

import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.CustomTrailParticle;
import cn.handyplus.particle.api.ParticleType;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 数据库粒子记录解析工具。
 *
 * @author handy
 */
public final class ParticleRestoreUtil {

    /**
     * 工具类禁止实例化。
     */
    private ParticleRestoreUtil() {
    }

    /**
     * 解析数据库保存的粒子记录。
     *
     * @param source   记录来源，用于控制台提示
     * @param category 粒子类别名称
     * @param type     效果类型名称
     * @param color    颜色文本，可为空
     * @param mode     运动模式名称，可为空
     * @return 粒子效果，记录无效时返回 null
     */
    @Nullable
    public static ParticleType parse(@NotNull String source, @Nullable String category, @Nullable String type, @Nullable String color, @Nullable String mode) {
        try {
            return doParse(source, category, type, color, mode);
        } catch (RuntimeException exception) {
            // 兜底：任何未预料到的解析异常同样按无效记录处理，避免中断整批恢复
            warn(source, "解析异常: " + exception.getMessage());
            return null;
        }
    }

    /**
     * 解析数据库保存的粒子记录。
     *
     * @param source   记录来源
     * @param category 粒子类别名称
     * @param type     效果类型名称
     * @param color    颜色文本，可为空
     * @param mode     运动模式名称，可为空
     * @return 粒子效果，记录无效时返回 null
     */
    @Nullable
    private static ParticleType doParse(@NotNull String source, @Nullable String category, @Nullable String type, @Nullable String color, @Nullable String mode) {
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnum(category);
        if (categoryType == null) {
            warn(source, "未知的粒子类别: " + category);
            return null;
        }
        if (categoryType == ParticleCategoryTypeEnum.WING) {
            return parseWing(source, type, color);
        }
        ParticleTypeEnum particleType = ParticleTypeEnum.getEnum(type);
        if (particleType == null) {
            warn(source, "未知的效果类型: " + type);
            return null;
        }
        // 类别与效果必须一致，否则清除命令无法按类别定位该记录
        if (particleType.getCategory() != categoryType) {
            warn(source, "记录类别与效果不匹配: " + category + " / " + particleType.getCategory().name());
            return null;
        }
        if (categoryType != ParticleCategoryTypeEnum.TRAIL) {
            return particleType;
        }
        return parseTrail(source, particleType, color, mode);
    }

    /**
     * 解析翅膀记录。
     *
     * @param source 记录来源
     * @param type   翅膀外形名称
     * @param color  翅膀颜色图案名称
     * @return 翅膀预置，记录无效时返回 null
     */
    @Nullable
    private static ParticleType parseWing(@NotNull String source, @Nullable String type, @Nullable String color) {
        WingModelEnum model = WingModelEnum.get(type);
        if (model == null) {
            warn(source, "未知的翅膀外形: " + type);
            return null;
        }
        WingColorEnum wingColor = WingColorEnum.get(color);
        if (wingColor == null) {
            warn(source, "未知的翅膀颜色图案: " + color);
            return null;
        }
        return ParticleTypeEnum.wing(model, wingColor);
    }

    /**
     * 解析拖尾记录，未知的运动模式回退为默认模式。
     *
     * @param source       记录来源
     * @param particleType 拖尾预置
     * @param color        颜色文本，可为空
     * @param mode         运动模式名称，可为空
     * @return 拖尾效果，记录无效时返回 null
     */
    @Nullable
    private static ParticleType parseTrail(@NotNull String source, @NotNull ParticleTypeEnum particleType,
                                           @Nullable String color, @Nullable String mode) {
        Color trailColor = null;
        if (color != null) {
            // 只有支持自定义颜色的拖尾才允许保存颜色，其余情况都按脏数据处理
            if (!particleType.supportsColor()) {
                warn(source, "该效果不支持自定义颜色: " + color);
                return null;
            }
            trailColor = ParticleColorUtil.get(color);
            if (trailColor == null) {
                warn(source, "颜色格式非法: " + color);
                return null;
            }
        }
        TrailModeTypeEnum trailMode = mode == null ? TrailModeTypeEnum.DEFAULT : TrailModeTypeEnum.get(mode);
        if (trailMode == null) {
            trailMode = TrailModeTypeEnum.DEFAULT;
        }
        return new CustomTrailParticle(particleType, trailMode, trailColor);
    }

    /**
     * 输出无效记录的控制台提示。
     *
     * @param source 记录来源
     * @param reason 记录无效的原因
     */
    private static void warn(@NotNull String source, @NotNull String reason) {
        MessageUtil.sendConsoleMessage("&e已移除无效的粒子记录 [" + source + "] &7原因: " + reason);
    }

}
