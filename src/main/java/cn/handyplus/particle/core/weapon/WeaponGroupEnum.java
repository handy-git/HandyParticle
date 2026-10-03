package cn.handyplus.particle.core.weapon;

import cn.handyplus.lib.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 可造成伤害的武器分组。
 *
 * <p>按材质名前缀分组而非逐个枚举，这样新增材质等级时自动兼容。
 * 例如 {@code DIAMOND_SWORD} 命中 {@link #SWORD} 分组。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum WeaponGroupEnum {
    /**
     * 剑，包括木石铁金钻下界合金。
     */
    SWORD("_SWORD"),
    /**
     * 斧，包括木石铁金钻下界合金。
     */
    AXE("_AXE"),
    /**
     * 镐，包括木石铁金钻下界合金。
     */
    PICKAXE("_PICKAXE"),
    /**
     * 锹，包括木石铁金钻下界合金。
     */
    SHOVEL("_SPADE"),
    /**
     * 锄，包括木石铁金钻下界合金。
     */
    HOE("_HOE"),
    /**
     * 三叉戟。
     */
    TRIDENT("_TRIDENT"),
    /**
     * 锤。
     */
    MACE("_MACE"),
    /**
     * 矛，包括木石铜铁金钻下界合金。
     */
    SPEAR("_SPEAR"),
    /**
     * 空手。
     */
    HAND("");

    /**
     * 全部取消时存储的标记值。
     */
    public static final String NONE_MARKER = "NONE";

    /**
     * 材质名后缀或完整匹配值。
     */
    private final String suffix;

    /**
     * 获取本地化描述。
     *
     * @return 武器分组描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("weaponGroupDesc." + name());
    }

    /**
     * 判断物品是否属于当前分组。
     *
     * @param material 物品材质
     * @return 属于当前分组时返回 true
     */
    public boolean matches(@Nullable Material material) {
        // 空手持物品按空手处理
        if (material == null || material == Material.AIR) {
            return this == HAND;
        }
        if (this == HAND) {
            return false;
        }
        return material.name().endsWith(this.suffix);
    }

    /**
     * 查找手持物品对应的分组。
     *
     * @param itemStack 手持物品，可为空
     * @return 对应分组，无法识别时返回 null
     */
    @Nullable
    public static WeaponGroupEnum match(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return HAND;
        }
        for (WeaponGroupEnum group : values()) {
            if (group != HAND && group.matches(itemStack.getType())) {
                return group;
            }
        }
        return null;
    }

    /**
     * 拆分逗号分隔的分组名称并统一转大写。
     *
     * @param value 逗号分隔的分组名称
     * @return 拆分后的名称列表
     */
    @NotNull
    public static List<String> split(@NotNull String value) {
        List<String> names = new ArrayList<>();
        for (String name : value.split(",")) {
            String key = name.trim().toUpperCase(Locale.ROOT);
            if (!key.isEmpty()) {
                names.add(key);
            }
        }
        return names;
    }

    /**
     * 解析逗号分隔的分组名称。
     *
     * <p>空值表示未设置按全部处理，{@code NONE} 表示全部取消。
     *
     * @param value 逗号分隔的分组名称
     * @return 分组列表，未设置时返回全部，全部取消时返回空列表
     */
    @NotNull
    public static List<WeaponGroupEnum> parse(@Nullable String value) {
        if (value == null || value.trim().isEmpty()) {
            return Arrays.asList(values());
        }
        if (NONE_MARKER.equalsIgnoreCase(value.trim())) {
            return new ArrayList<>();
        }
        List<WeaponGroupEnum> result = new ArrayList<>();
        for (String key : split(value)) {
            for (WeaponGroupEnum group : values()) {
                if (group.name().equals(key)) {
                    result.add(group);
                    break;
                }
            }
        }
        return result.isEmpty() ? Arrays.asList(values()) : result;
    }

    /**
     * 将分组列表序列化为逗号分隔文本。
     *
     * @param groups 分组列表
     * @return 存储文本，全不选返回 NONE，全选返回空串
     */
    @NotNull
    public static String serialize(@NotNull List<WeaponGroupEnum> groups) {
        if (groups.isEmpty()) {
            return NONE_MARKER;
        }
        if (groups.size() >= values().length) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (WeaponGroupEnum group : groups) {
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(group.name());
        }
        return builder.toString();
    }

}
