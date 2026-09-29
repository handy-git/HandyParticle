package cn.handyplus.particle.core.block;

import cn.handyplus.lib.internal.XMaterial;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 默认方块拖尾的内置材质组合。
 *
 * @author handy
 */
public enum BlockTrailTypeEnum {
    /**
     * 彩色羊毛
     * 白、橙、品红、淡蓝、黄、黄绿、粉、灰、淡灰、青、紫、蓝、棕、绿、红、黑。
     */
    BLOCK_WOOL(XMaterial.WHITE_WOOL, XMaterial.ORANGE_WOOL, XMaterial.MAGENTA_WOOL, XMaterial.LIGHT_BLUE_WOOL,
            XMaterial.YELLOW_WOOL, XMaterial.LIME_WOOL, XMaterial.PINK_WOOL, XMaterial.GRAY_WOOL,
            XMaterial.LIGHT_GRAY_WOOL, XMaterial.CYAN_WOOL, XMaterial.PURPLE_WOOL, XMaterial.BLUE_WOOL,
            XMaterial.BROWN_WOOL, XMaterial.GREEN_WOOL, XMaterial.RED_WOOL, XMaterial.BLACK_WOOL),
    /**
     * 彩色玻璃
     * 白、橙、品红、淡蓝、黄、黄绿、粉、灰、淡灰、青、紫、蓝、棕、绿、红、黑。
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
     * 下界材质
     * 下界石英矿、下界砖、下界岩、萤石、灵魂沙、岩浆块、下界金矿、黑石、玄武岩、绯红菌岩、诡异菌岩、远古残骸。
     */
    BLOCK_NETHER(XMaterial.NETHER_QUARTZ_ORE, XMaterial.NETHER_BRICKS, XMaterial.NETHERRACK,
            XMaterial.GLOWSTONE, XMaterial.SOUL_SAND, XMaterial.MAGMA_BLOCK,
            XMaterial.NETHER_GOLD_ORE, XMaterial.BLACKSTONE, XMaterial.BASALT,
            XMaterial.CRIMSON_NYLIUM, XMaterial.WARPED_NYLIUM, XMaterial.ANCIENT_DEBRIS),
    /**
     * 彩色陶瓦
     * 白、橙、品红、淡蓝、黄、黄绿、粉、灰、淡灰、青、紫、蓝、棕、绿、红、黑。
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
     * 矿石材质
     * 煤、铁、金、钻石、绿宝石、红石、青金石、铜矿，以及对应的深层矿石。
     */
    BLOCK_ORE(XMaterial.LAPIS_ORE, XMaterial.EMERALD_ORE, XMaterial.REDSTONE_ORE,
            XMaterial.COAL_ORE, XMaterial.IRON_ORE, XMaterial.GOLD_ORE, XMaterial.DIAMOND_ORE,
            XMaterial.COPPER_ORE, XMaterial.DEEPSLATE_COAL_ORE, XMaterial.DEEPSLATE_IRON_ORE,
            XMaterial.DEEPSLATE_GOLD_ORE, XMaterial.DEEPSLATE_DIAMOND_ORE,
            XMaterial.DEEPSLATE_EMERALD_ORE, XMaterial.DEEPSLATE_REDSTONE_ORE,
            XMaterial.DEEPSLATE_LAPIS_ORE, XMaterial.DEEPSLATE_COPPER_ORE),
    /**
     * 红石机械
     * TNT、活塞、音符盒、红石块、红石灯、发射器、投掷器、侦测器、阳光探测器、标靶。
     */
    BLOCK_REDSTONE(XMaterial.TNT, XMaterial.PISTON, XMaterial.NOTE_BLOCK, XMaterial.REDSTONE_BLOCK,
            XMaterial.REDSTONE_LAMP, XMaterial.DISPENSER, XMaterial.DROPPER, XMaterial.OBSERVER,
            XMaterial.DAYLIGHT_DETECTOR, XMaterial.TARGET),
    /**
     * 自然方块
     * 灰化土、草方块、砂砾、沙子、泥土、砂土、菌丝、黏土、雪块、苔藓块、缠根泥土、泥巴。
     */
    BLOCK_NATURE(XMaterial.PODZOL, XMaterial.GRASS_BLOCK, XMaterial.GRAVEL, XMaterial.SAND,
            XMaterial.DIRT, XMaterial.COARSE_DIRT, XMaterial.MYCELIUM, XMaterial.CLAY,
            XMaterial.SNOW_BLOCK, XMaterial.MOSS_BLOCK, XMaterial.ROOTED_DIRT, XMaterial.MUD),
    /**
     * 金属方块
     * 铁块、金块、钻石块、绿宝石块、青金石块、红石块、煤块、铜块、下界合金块，以及铁、金、铜粗矿块。
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
    private static final Set<Material> GROUND_MATERIALS = EnumSet.noneOf(Material.class);

    static {
        for (Material material : Material.values()) {
            try {
                if (GROUND.contains(XMaterial.matchXMaterial(material))) {
                    GROUND_MATERIALS.add(material);
                }
            } catch (IllegalArgumentException ignored) {
                // 当前服务端存在但 XMaterial 未识别的材质不属于内置地面列表。
            }
        }
    }

    private final XMaterial[] materials;

    /**
     * 创建方块材质组合。
     *
     * @param materials 方块材质
     */
    BlockTrailTypeEnum(@NotNull XMaterial... materials) {
        // 仅轮换当前服务端存在的方块，避免新版本材质在低服务端解析为空。
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
     * 判断实体脚下方块是否允许显示拖尾。
     *
     * @param block 实体脚下方块
     * @return 是否允许
     */
    public boolean isGround(@NotNull Block block) {
        return GROUND_MATERIALS.contains(block.getType());
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
     * 获取原版方块材质。
     *
     * @param tick 刷新次数
     * @return 原版材质
     */
    @NotNull
    public Material legacyMaterial(long tick) {
        return Objects.requireNonNull(this.material(tick).get());
    }

    /**
     * 获取原版方块数据值。
     *
     * @param tick 刷新次数
     * @return 数据值
     */
    public byte legacyData(long tick) {
        return this.material(tick).getData();
    }

}
