package cn.handyplus.particle.core.wing;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.BaseUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 翅膀颜色图案。
 *
 * @author handy
 */
@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor
public enum WingColorEnum {
    /**
     * 渐变一。
     */
    GRADIENT1(WingPaletteData.GRADIENT1),
    /**
     * 渐变二。
     */
    GRADIENT2(WingPaletteData.GRADIENT2),
    /**
     * 渐变三。
     */
    GRADIENT3(WingPaletteData.GRADIENT3),
    /**
     * 蘑菇图案。
     */
    MUSHROOM(WingPaletteData.MUSHROOM),
    /**
     * 火焰图案。
     */
    FIRE(WingPaletteData.FIRE),
    /**
     * 彩色图案。
     */
    COLORS(WingPaletteData.COLORS),
    /**
     * 飞行图案。
     */
    FLY(WingPaletteData.FLY);

    /**
     * 模型对应的 RGB 颜色点阵。
     */
    private final int[][] palette;

    /**
     * 获取颜色描述。
     *
     * @return 颜色描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("wingColorDesc." + name());
    }

    /**
     * 获取所有颜色图案名称。
     *
     * @return 颜色图案名称
     */
    @NotNull
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        boolean chinese = "zh_CN".equalsIgnoreCase(BaseConstants.CONFIG.getString("language", "zh_CN"));
        for (WingColorEnum color : values()) {
            result.add(chinese ? color.name() + "(" + color.getDesc() + ")" : color.name());
        }
        return result;
    }

    /**
     * 解析颜色图案名称。
     *
     * @param value 颜色图案名称
     * @return 颜色图案
     */
    public static WingColorEnum getEnumThrow(@NotNull String value) {
        String name = value.split("\\(", 2)[0].trim();
        for (WingColorEnum color : values()) {
            if (color.name().equalsIgnoreCase(name)) {
                return color;
            }
        }
        throw new IllegalArgumentException("not found wing color: " + value);
    }
}
