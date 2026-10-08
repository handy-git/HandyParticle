package cn.handyplus.particle.core.wing;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 翅膀外形。
 *
 * @author handy
 */
public enum WingModelEnum {
    /**
     * 天使翅膀。
     */
    ANGEL,
    /**
     * 蝴蝶翅膀。
     */
    BUTTERFLY,
    /**
     * 精灵翅膀。
     */
    ELF,
    /**
     * 长翼翅膀。
     */
    LONG,
    /**
     * 蝴蝶二翅膀。
     */
    BUTTERFLY2,
    /**
     * 短翼翅膀。
     */
    SHORT,
    /**
     * 恶魔翅膀。
     */
    DEMON,
    /**
     * 暗夜翅膀。
     */
    NIGHT;

    /**
     * 获取模型描述。
     *
     * @return 模型描述
     */
    @NotNull
    public native String getDesc();

    /**
     * 获取所有模型名称。
     *
     * @return 模型名称，中文环境下附带描述
     */
    @NotNull
    public native static List<String> getAll();

    /**
     * 解析模型名称，未匹配到时返回 null。
     *
     * @param value 模型名称，可为空
     * @return 模型，未匹配到时返回 null
     */
    @Nullable
    public native static WingModelEnum get(@Nullable String value);

    /**
     * 解析模型名称。
     *
     * @param value 模型名称
     * @return 模型
     */
    @NotNull
    public native static WingModelEnum getEnumThrow(@NotNull String value);

}
