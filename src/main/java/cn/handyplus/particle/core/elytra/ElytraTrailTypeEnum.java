package cn.handyplus.particle.core.elytra;

import cn.handyplus.lib.internal.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 内置鞘翅拖尾材质组合。
 *
 * @author handy
 */
public enum ElytraTrailTypeEnum {
    /**
     * 贵金属鞘翅拖尾。
     * 钻石、金锭、铁锭、铜锭、下界合金锭。
     */
    ELYTRA_METAL(XMaterial.DIAMOND, XMaterial.GOLD_INGOT, XMaterial.IRON_INGOT, XMaterial.COPPER_INGOT, XMaterial.NETHERITE_INGOT),
    /**
     * 海晶鞘翅拖尾。
     * 海晶砂、海晶晶体、海洋之心。
     */
    ELYTRA_PRISMARINE(XMaterial.PRISMARINE, XMaterial.PRISMARINE_CRYSTALS, XMaterial.HEART_OF_THE_SEA),
    /**
     * 珍宝鞘翅拖尾。
     * 龙蛋。
     */
    ELYTRA_DRAGON(XMaterial.DRAGON_EGG);

    /**
     * 当前服务端支持的鞘翅材质。
     */
    private final XMaterial[] materials;

    /**
     * 创建鞘翅材质组合。
     *
     * @param materials 物品材质
     */
    ElytraTrailTypeEnum(@NotNull XMaterial... materials) {
        List<XMaterial> supported = new ArrayList<>();
        for (XMaterial material : materials) {
            if (material.get() != null) {
                supported.add(material);
            }
        }
        this.materials = supported.toArray(new XMaterial[0]);
    }

    /**
     * 获取当前版本支持的鞘翅拖尾组合。
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
