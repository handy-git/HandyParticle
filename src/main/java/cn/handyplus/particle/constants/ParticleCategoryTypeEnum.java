package cn.handyplus.particle.constants;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 设置命令支持的粒子效果类别。
 *
 * @author handy
 */
public enum ParticleCategoryTypeEnum {
    /**
     * 普通粒子拖尾。
     */
    TRAIL,
    /**
     * 翅膀效果。
     */
    WING,
    /**
     * 方块效果。
     */
    BLOCK,
    /**
     * 物品雨效果。
     */
    RAIN,
    /**
     * 纸屑效果。
     */
    CONFETTI,
    /**
     * 鞘翅拖尾效果，仅在玩家滑翔时生成。
     */
    ELYTRA,
    /**
     * 抛射物效果，仅对自己发射的抛射物生效。
     */
    PROJECTILE,
    /**
     * 武器攻击效果，仅近战伤害触发。
     */
    WEAPON;

    /**
     * 获取当前类别的本地化描述。
     *
     * @return 类别描述
     */
    @NotNull
    public native String getDesc();

    /**
     * 按名称查找粒子类别。
     *
     * @param value 类别名称，可为空
     * @return 粒子类别，未找到时返回 null
     */
    @Nullable
    public native static ParticleCategoryTypeEnum getEnum(@Nullable String value);

    /**
     * 按名称查找粒子类别。
     *
     * @param value 类别名称
     * @return 粒子类别
     */
    @NotNull
    public native static ParticleCategoryTypeEnum getEnumThrow(@NotNull String value);

    /**
     * 列出命令支持的效果类别。
     *
     * @return 可补全的效果类别名称，中文环境下附带描述
     */
    @NotNull
    public native static List<String> getAll();

    /**
     * 列出指定类别下的效果名称。
     *
     * @param category 效果类别
     * @return 类别对应的效果名称
     */
    @NotNull
    public native static List<String> get(@NotNull ParticleCategoryTypeEnum category);

}
