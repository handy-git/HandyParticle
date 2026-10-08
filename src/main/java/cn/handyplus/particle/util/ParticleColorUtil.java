package cn.handyplus.particle.util;

import cn.handyplus.lib.core.PatternUtil;
import cn.handyplus.lib.util.BaseUtil;
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
     * 解析十六进制 RGB 颜色，格式非法时返回 null。
     *
     * @param value 颜色文本，格式为 #RGB 或 #RRGGBB，可为空
     * @return 解析后的颜色，为空或格式非法时返回 null
     */
    @Nullable
    public static Color get(@Nullable String value) {
        if (value == null) {
            return null;
        }
        String colorText = value;
        if (colorText.startsWith("&#") || colorText.startsWith("§#")) {
            colorText = colorText.substring(1);
        }
        if (!PatternUtil.RPG_PATTERN.matcher("&" + colorText).matches()) {
            return null;
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
     * 解析十六进制 RGB 颜色。
     *
     * @param value 颜色文本，格式为 #RGB 或 #RRGGBB，可为空
     * @return 解析后的颜色，为空时返回 null
     */
    @Nullable
    public static Color parse(@Nullable String value) {
        if (value == null) {
            return null;
        }
        Color color = get(value);
        if (color == null) {
            throw new IllegalArgumentException(BaseUtil.getLangMsg("invalidColor"));
        }
        return color;
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
