package cn.handyplus.particle.core.wing;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 翅膀颜色图案。
 *
 * @author handy
 */
public enum WingColorEnum {
    /**
     * 渐变一。
     */
    GRADIENT1,
    /**
     * 渐变二。
     */
    GRADIENT2,
    /**
     * 渐变三。
     */
    GRADIENT3,
    /**
     * 蘑菇图案。
     */
    MUSHROOM,
    /**
     * 火焰图案。
     */
    FIRE,
    /**
     * 彩色图案。
     */
    COLORS,
    /**
     * 飞行图案。
     */
    FLY;

    /**
     * 获取所有颜色图案名称。
     *
     * @return 颜色图案名称，中文环境下附带描述
     */
    @NotNull
    public native static List<String> getAll();

    /**
     * 解析颜色图案名称，未匹配到时返回 null。
     *
     * @param value 颜色图案名称，可为空
     * @return 颜色图案，未匹配到时返回 null
     */
    @Nullable
    public native static WingColorEnum get(@Nullable String value);

    /**
     * 解析颜色图案名称。
     *
     * @param value 颜色图案名称
     * @return 颜色图案
     */
    @NotNull
    public native static WingColorEnum getEnumThrow(@NotNull String value);

}
