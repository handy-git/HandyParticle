package cn.handyplus.particle.util;

import cn.handyplus.lib.core.PatternUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 粒子颜色解析工具。
 *
 * @author handy
 */
public final class ParticleColorUtil {

    private ParticleColorUtil() {
    }

    /**
     * 解析十六进制 RGB 颜色。
     *
     * @param value 颜色文本，格式为 #RGB 或 #RRGGBB，可为空
     * @return 解析后的颜色
     */
    @Nullable
    public static Color parse(@Nullable String value) {
        if (value == null) {
            return null;
        }
        String colorText = value;
        if (colorText.startsWith("&#") || colorText.startsWith("§#")) {
            colorText = colorText.substring(1);
        }
        if (!PatternUtil.isMatch(PatternUtil.RPG_PATTERN.pattern(), "&" + colorText)) {
            throw new IllegalArgumentException("颜色必须使用 #RGB 或 #RRGGBB 格式");
        }
        String hex = colorText.substring(1);
        if (hex.length() == 3) {
            hex = String.valueOf(hex.charAt(0)) + hex.charAt(0) +
                    hex.charAt(1) + hex.charAt(1) +
                    hex.charAt(2) + hex.charAt(2);
        }
        return new Color(Integer.parseInt(hex, 16));
    }

    /**
     * 将颜色转换为十六进制文本。
     *
     * @param color 颜色
     * @return #RRGGBB 格式的颜色文本
     */
    @NotNull
    public static String format(@NotNull Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
