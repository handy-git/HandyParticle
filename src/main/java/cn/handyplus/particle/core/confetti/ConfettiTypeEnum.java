package cn.handyplus.particle.core.confetti;

import cn.handyplus.lib.internal.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 内置纸屑物品组合。
 *
 * @author handy
 */
public enum ConfettiTypeEnum {
    /**
     * 红、黄、绿染料纸屑。
     */
    RED_YELLOW_GREEN(XMaterial.RED_DYE, XMaterial.YELLOW_DYE, XMaterial.GREEN_DYE),
    /**
     * 白、黑、紫染料纸屑。
     */
    WHITE_BLACK_PURPLE(XMaterial.WHITE_DYE, XMaterial.BLACK_DYE, XMaterial.PURPLE_DYE),
    /**
     * 白、红、蓝染料纸屑。
     */
    WHITE_RED_BLUE(XMaterial.WHITE_DYE, XMaterial.RED_DYE, XMaterial.BLUE_DYE),
    /**
     * 绿、蓝、黑染料纸屑。
     */
    GREEN_BLUE_BLACK(XMaterial.GREEN_DYE, XMaterial.BLUE_DYE, XMaterial.BLACK_DYE),
    /**
     * 红、黄、白染料纸屑。
     */
    RED_YELLOW_WHITE(XMaterial.RED_DYE, XMaterial.YELLOW_DYE, XMaterial.WHITE_DYE),
    /**
     * 黑、黄、紫染料纸屑。
     */
    BLACK_YELLOW_PURPLE(XMaterial.BLACK_DYE, XMaterial.YELLOW_DYE, XMaterial.PURPLE_DYE),
    /**
     * 红、绿、黑染料纸屑。
     */
    RED_GREEN_BLACK(XMaterial.RED_DYE, XMaterial.GREEN_DYE, XMaterial.BLACK_DYE);

    /**
     * 当前服务端支持的纸屑材质。
     */
    private final XMaterial[] materials;

    /**
     * 创建物品组合，并过滤当前版本不支持的物品。
     *
     * @param materials 物品材质
     */
    ConfettiTypeEnum(@NotNull XMaterial... materials) {
        List<XMaterial> supported = new ArrayList<>();
        for (XMaterial material : materials) {
            if (material.get() != null) {
                supported.add(material);
            }
        }
        this.materials = supported.toArray(new XMaterial[0]);
    }

    /**
     * 获取当前版本可发送的纸屑物品。
     *
     * @return 物品副本数组
     */
    @NotNull
    public ItemStack[] items() {
        ItemStack[] items = new ItemStack[materials.length];
        for (int index = 0; index < materials.length; index++) {
            items[index] = Objects.requireNonNull(materials[index].parseItem());
        }
        return items;
    }
}
