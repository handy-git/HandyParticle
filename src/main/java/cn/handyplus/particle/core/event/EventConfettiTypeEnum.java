package cn.handyplus.particle.core.event;

import cn.handyplus.lib.internal.XMaterial;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 纸屑活动效果的固定染料组合。
 *
 * @author handy
 */
@Getter
public enum EventConfettiTypeEnum {
    /**
     * 红色染料、黄色染料、绿色染料。
     */
    CONFETTI_RED_YELLOW_GREEN(XMaterial.RED_DYE, XMaterial.YELLOW_DYE, XMaterial.GREEN_DYE),
    /**
     * 白色染料、黑色染料、紫色染料。
     */
    CONFETTI_WHITE_BLACK_PURPLE(XMaterial.WHITE_DYE, XMaterial.BLACK_DYE, XMaterial.PURPLE_DYE),
    /**
     * 白色染料、红色染料、蓝色染料。
     */
    CONFETTI_WHITE_RED_BLUE(XMaterial.WHITE_DYE, XMaterial.RED_DYE, XMaterial.BLUE_DYE),
    /**
     * 绿色染料、蓝色染料、黑色染料。
     */
    CONFETTI_GREEN_BLUE_BLACK(XMaterial.GREEN_DYE, XMaterial.BLUE_DYE, XMaterial.BLACK_DYE),
    /**
     * 红色染料、黄色染料、白色染料。
     */
    CONFETTI_RED_YELLOW_WHITE(XMaterial.RED_DYE, XMaterial.YELLOW_DYE, XMaterial.WHITE_DYE),
    /**
     * 黑色染料、黄色染料、紫色染料。
     */
    CONFETTI_BLACK_YELLOW_PURPLE(XMaterial.BLACK_DYE, XMaterial.YELLOW_DYE, XMaterial.PURPLE_DYE),
    /**
     * 红色染料、绿色染料、黑色染料。
     */
    CONFETTI_RED_GREEN_BLACK(XMaterial.RED_DYE, XMaterial.GREEN_DYE, XMaterial.BLACK_DYE);

    private final ItemStack[] items;

    /**
     * 创建纸屑染料组合。
     *
     * @param materials 染料材质
     */
    EventConfettiTypeEnum(@NotNull XMaterial... materials) {
        this.items = new ItemStack[materials.length];
        for (int index = 0; index < materials.length; index++) {
            this.items[index] = Objects.requireNonNull(materials[index].parseItem());
        }
    }
}
