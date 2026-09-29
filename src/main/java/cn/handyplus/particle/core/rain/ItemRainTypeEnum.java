package cn.handyplus.particle.core.rain;

import cn.handyplus.lib.internal.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 内置物品雨组合。
 *
 * @author handy
 */
public enum ItemRainTypeEnum {
    /**
     * 常用物品雨
     * 附魔台、末影箱、橡木按钮、苹果、钻石、煤炭、铁锭、书、指南针、时钟、地图、火把、灯笼、羽毛、线。
     */
    RAIN_COMMON(XMaterial.ENCHANTING_TABLE, XMaterial.ENDER_CHEST, XMaterial.OAK_BUTTON,
            XMaterial.APPLE, XMaterial.DIAMOND, XMaterial.COAL, XMaterial.IRON_INGOT,
            XMaterial.BOOK, XMaterial.COMPASS, XMaterial.CLOCK, XMaterial.MAP,
            XMaterial.TORCH, XMaterial.LANTERN, XMaterial.FEATHER, XMaterial.STRING),
    /**
     * 金色物品雨
     * 金锭、金苹果、金粒、金块、附魔金苹果、粗金、金矿、金胡萝卜。
     */
    RAIN_GOLD(XMaterial.GOLD_INGOT, XMaterial.GOLDEN_APPLE, XMaterial.GOLD_NUGGET,
            XMaterial.GOLD_BLOCK, XMaterial.ENCHANTED_GOLDEN_APPLE, XMaterial.RAW_GOLD,
            XMaterial.GOLD_ORE, XMaterial.GOLDEN_CARROT),
    /**
     * 红石物品雨
     * 红石、纸、鸡蛋、蛋糕、曲奇、瓜片、末影之眼、中继器、比较器、红石火把、发射器、投掷器、侦测器、标靶。
     */
    RAIN_REDSTONE(XMaterial.REDSTONE, XMaterial.PAPER, XMaterial.EGG, XMaterial.CAKE,
            XMaterial.COOKIE, XMaterial.MELON_SLICE, XMaterial.ENDER_EYE, XMaterial.REPEATER,
            XMaterial.COMPARATOR, XMaterial.REDSTONE_TORCH, XMaterial.DISPENSER,
            XMaterial.DROPPER, XMaterial.OBSERVER, XMaterial.TARGET),
    /**
     * 稀有物品雨
     * 恶魂之泪、弓、绿宝石、下界之星、铁砧、附魔金苹果、末影珍珠、烈焰棒、烈焰粉、海晶碎片、海晶砂粒、紫水晶碎片、鹦鹉螺壳、海洋之心、潜影壳、不死图腾、三叉戟、弩。
     */
    RAIN_RARE(XMaterial.GHAST_TEAR, XMaterial.BOW, XMaterial.EMERALD,
            XMaterial.NETHER_STAR, XMaterial.ANVIL, XMaterial.ENCHANTED_GOLDEN_APPLE,
            XMaterial.ENDER_PEARL, XMaterial.BLAZE_ROD, XMaterial.BLAZE_POWDER,
            XMaterial.PRISMARINE_SHARD, XMaterial.PRISMARINE_CRYSTALS, XMaterial.AMETHYST_SHARD,
            XMaterial.NAUTILUS_SHELL, XMaterial.HEART_OF_THE_SEA, XMaterial.SHULKER_SHELL,
            XMaterial.TOTEM_OF_UNDYING, XMaterial.TRIDENT, XMaterial.CROSSBOW),
    /**
     * 彩色羊毛物品雨
     * 白、橙、品红、淡蓝、黄、黄绿、粉、灰、淡灰、青、紫、蓝、棕、绿、红、黑。
     */
    RAIN_WOOL(XMaterial.WHITE_WOOL, XMaterial.ORANGE_WOOL, XMaterial.MAGENTA_WOOL,
            XMaterial.LIGHT_BLUE_WOOL, XMaterial.YELLOW_WOOL, XMaterial.LIME_WOOL,
            XMaterial.PINK_WOOL, XMaterial.GRAY_WOOL, XMaterial.LIGHT_GRAY_WOOL,
            XMaterial.CYAN_WOOL, XMaterial.PURPLE_WOOL, XMaterial.BLUE_WOOL,
            XMaterial.BROWN_WOOL, XMaterial.GREEN_WOOL, XMaterial.RED_WOOL, XMaterial.BLACK_WOOL);

    private final XMaterial[] materials;

    /**
     * 创建物品雨材质组合。
     *
     * @param materials 物品材质
     */
    ItemRainTypeEnum(@NotNull XMaterial... materials) {
        List<XMaterial> supported = new ArrayList<>();
        for (XMaterial material : materials) {
            if (material.get() != null) {
                supported.add(material);
            }
        }
        this.materials = supported.toArray(new XMaterial[0]);
    }

    /**
     * 获取当前版本支持的物品雨组合。
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
