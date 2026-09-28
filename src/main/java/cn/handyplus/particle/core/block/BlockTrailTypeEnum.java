package cn.handyplus.particle.core.block;

import cn.handyplus.lib.internal.XMaterial;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 旧版默认方块拖尾的内置材质组合。
 *
 * @author handy
 */
public enum BlockTrailTypeEnum {
    /**
     * 彩色羊毛。
     */
    BLOCK_WOOL(XMaterial.WHITE_WOOL, XMaterial.ORANGE_WOOL, XMaterial.MAGENTA_WOOL, XMaterial.LIGHT_BLUE_WOOL,
            XMaterial.YELLOW_WOOL, XMaterial.LIME_WOOL, XMaterial.PINK_WOOL, XMaterial.GRAY_WOOL,
            XMaterial.LIGHT_GRAY_WOOL, XMaterial.CYAN_WOOL, XMaterial.PURPLE_WOOL, XMaterial.BLUE_WOOL,
            XMaterial.BROWN_WOOL, XMaterial.GREEN_WOOL, XMaterial.RED_WOOL, XMaterial.BLACK_WOOL),
    /**
     * 彩色玻璃。
     */
    BLOCK_GLASS(XMaterial.WHITE_STAINED_GLASS, XMaterial.ORANGE_STAINED_GLASS,
            XMaterial.MAGENTA_STAINED_GLASS, XMaterial.LIGHT_BLUE_STAINED_GLASS,
            XMaterial.YELLOW_STAINED_GLASS, XMaterial.LIME_STAINED_GLASS,
            XMaterial.PINK_STAINED_GLASS, XMaterial.GRAY_STAINED_GLASS,
            XMaterial.LIGHT_GRAY_STAINED_GLASS, XMaterial.CYAN_STAINED_GLASS,
            XMaterial.PURPLE_STAINED_GLASS, XMaterial.BLUE_STAINED_GLASS,
            XMaterial.BROWN_STAINED_GLASS, XMaterial.GREEN_STAINED_GLASS,
            XMaterial.RED_STAINED_GLASS, XMaterial.BLACK_STAINED_GLASS),
    /**
     * 下界材质。
     */
    BLOCK_NETHER(XMaterial.NETHER_QUARTZ_ORE, XMaterial.NETHER_BRICKS, XMaterial.NETHERRACK,
            XMaterial.GLOWSTONE, XMaterial.SOUL_SAND, XMaterial.MAGMA_BLOCK,
            XMaterial.NETHER_GOLD_ORE, XMaterial.BLACKSTONE, XMaterial.BASALT,
            XMaterial.CRIMSON_NYLIUM, XMaterial.WARPED_NYLIUM, XMaterial.ANCIENT_DEBRIS),
    /**
     * 彩色陶瓦。
     */
    BLOCK_TERRACOTTA(XMaterial.WHITE_TERRACOTTA, XMaterial.ORANGE_TERRACOTTA,
            XMaterial.MAGENTA_TERRACOTTA, XMaterial.LIGHT_BLUE_TERRACOTTA,
            XMaterial.YELLOW_TERRACOTTA, XMaterial.LIME_TERRACOTTA,
            XMaterial.PINK_TERRACOTTA, XMaterial.GRAY_TERRACOTTA,
            XMaterial.LIGHT_GRAY_TERRACOTTA, XMaterial.CYAN_TERRACOTTA,
            XMaterial.PURPLE_TERRACOTTA, XMaterial.BLUE_TERRACOTTA,
            XMaterial.BROWN_TERRACOTTA, XMaterial.GREEN_TERRACOTTA,
            XMaterial.RED_TERRACOTTA, XMaterial.BLACK_TERRACOTTA),
    /**
     * 矿石材质。
     */
    BLOCK_ORE(XMaterial.LAPIS_ORE, XMaterial.EMERALD_ORE, XMaterial.REDSTONE_ORE,
            XMaterial.COAL_ORE, XMaterial.IRON_ORE, XMaterial.GOLD_ORE, XMaterial.DIAMOND_ORE,
            XMaterial.COPPER_ORE, XMaterial.DEEPSLATE_COAL_ORE, XMaterial.DEEPSLATE_IRON_ORE,
            XMaterial.DEEPSLATE_GOLD_ORE, XMaterial.DEEPSLATE_DIAMOND_ORE,
            XMaterial.DEEPSLATE_EMERALD_ORE, XMaterial.DEEPSLATE_REDSTONE_ORE,
            XMaterial.DEEPSLATE_LAPIS_ORE, XMaterial.DEEPSLATE_COPPER_ORE),
    /**
     * 红石机械。
     */
    BLOCK_REDSTONE(XMaterial.TNT, XMaterial.PISTON, XMaterial.NOTE_BLOCK, XMaterial.REDSTONE_BLOCK,
            XMaterial.REDSTONE_LAMP, XMaterial.DISPENSER, XMaterial.DROPPER, XMaterial.OBSERVER,
            XMaterial.DAYLIGHT_DETECTOR, XMaterial.TARGET),
    /**
     * 自然方块。
     */
    BLOCK_NATURE(XMaterial.PODZOL, XMaterial.GRASS_BLOCK, XMaterial.GRAVEL, XMaterial.SAND,
            XMaterial.DIRT, XMaterial.COARSE_DIRT, XMaterial.MYCELIUM, XMaterial.CLAY,
            XMaterial.SNOW_BLOCK, XMaterial.MOSS_BLOCK, XMaterial.ROOTED_DIRT, XMaterial.MUD),
    /**
     * 金属方块。
     */
    BLOCK_METAL(XMaterial.IRON_BLOCK, XMaterial.GOLD_BLOCK, XMaterial.DIAMOND_BLOCK,
            XMaterial.EMERALD_BLOCK, XMaterial.LAPIS_BLOCK, XMaterial.REDSTONE_BLOCK,
            XMaterial.COAL_BLOCK, XMaterial.COPPER_BLOCK, XMaterial.NETHERITE_BLOCK,
            XMaterial.RAW_IRON_BLOCK, XMaterial.RAW_GOLD_BLOCK, XMaterial.RAW_COPPER_BLOCK);

    private static final List<XMaterial> GROUND = Arrays.asList(
            XMaterial.GRASS_BLOCK, XMaterial.STONE, XMaterial.COBBLESTONE, XMaterial.DIRT, XMaterial.SAND,
            XMaterial.GRAVEL, XMaterial.COAL_BLOCK, XMaterial.COAL_ORE, XMaterial.IRON_ORE, XMaterial.GOLD_BLOCK,
            XMaterial.DIAMOND_BLOCK, XMaterial.EMERALD_ORE, XMaterial.EMERALD_BLOCK, XMaterial.OAK_PLANKS,
            XMaterial.SPRUCE_PLANKS, XMaterial.BIRCH_PLANKS, XMaterial.JUNGLE_PLANKS, XMaterial.ACACIA_PLANKS,
            XMaterial.DARK_OAK_PLANKS, XMaterial.STONE_BRICKS, XMaterial.MOSSY_STONE_BRICKS, XMaterial.BRICKS,
            XMaterial.NETHERRACK, XMaterial.GLOWSTONE, XMaterial.OBSIDIAN, XMaterial.SNOW_BLOCK, XMaterial.SPONGE,
            XMaterial.BEDROCK, XMaterial.QUARTZ_BLOCK, XMaterial.HAY_BLOCK, XMaterial.CLAY, XMaterial.WHITE_TERRACOTTA,
            XMaterial.WHITE_WOOL, XMaterial.DEEPSLATE, XMaterial.COBBLED_DEEPSLATE, XMaterial.TUFF,
            XMaterial.DIORITE, XMaterial.GRANITE, XMaterial.ANDESITE, XMaterial.DIRT_PATH, XMaterial.MYCELIUM,
            XMaterial.PODZOL, XMaterial.SOUL_SAND, XMaterial.SOUL_SOIL, XMaterial.END_STONE,
            XMaterial.SANDSTONE, XMaterial.RED_SANDSTONE, XMaterial.BLACKSTONE, XMaterial.BASALT);
    private final XMaterial[] materials;

    /**
     * 创建方块材质组合。
     *
     * @param materials 方块材质
     */
    BlockTrailTypeEnum(@NotNull XMaterial... materials) {
        // 仅轮换当前服务端存在的方块，避免新版本材质在旧服务端解析为空。
        List<XMaterial> supported = new ArrayList<>();
        for (XMaterial material : materials) {
            if (material.get() != null) {
                supported.add(material);
            }
        }
        this.materials = supported.toArray(new XMaterial[0]);
    }

    /**
     * 获取当前刷新要显示的材质。
     *
     * @param tick 刷新次数
     * @return 方块材质
     */
    @NotNull
    public XMaterial material(long tick) {
        return materials[(int) (tick % materials.length)];
    }

    /**
     * 判断玩家脚下方块是否允许显示拖尾。
     *
     * @param block 玩家脚下方块
     * @return 是否允许
     */
    public boolean isGround(@NotNull Block block) {
        return GROUND.contains(XMaterial.matchXMaterial(block.getType()));
    }

    /**
     * 创建临时方块数据。
     *
     * @param tick 刷新次数
     * @return 方块数据
     */
    @NotNull
    public BlockData blockData(long tick) {
        Material material = this.material(tick).get();
        return Objects.requireNonNull(material).createBlockData();
    }

    /**
     * 获取旧版方块材质。
     *
     * @param tick 刷新次数
     * @return 旧版材质
     */
    @NotNull
    public Material legacyMaterial(long tick) {
        return Objects.requireNonNull(this.material(tick).get());
    }

    /**
     * 获取旧版方块数据值。
     *
     * @param tick 刷新次数
     * @return 数据值
     */
    public byte legacyData(long tick) {
        return this.material(tick).getData();
    }

}
