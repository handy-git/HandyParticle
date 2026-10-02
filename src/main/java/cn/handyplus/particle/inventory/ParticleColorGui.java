package cn.handyplus.particle.inventory;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.util.ItemStackUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.param.ParticleSelectContext;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleColorUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 拖尾颜色菜单
 *
 * @author handy
 */
public final class ParticleColorGui {

    /**
     * 默认颜色
     */
    private static final String[] PRESET_COLORS = {
            "#FF0000", "#FF7F00", "#FFFF00", "#7FFF00", "#00FF00", "#00FF7F", "#00FFFF", "#007FFF",
            "#0000FF", "#7F00FF", "#FF00FF", "#FF007F", "#FFFFFF", "#C0C0C0", "#808080", "#000000"
    };

    private ParticleColorGui() {
    }

    private static final ParticleColorGui INSTANCE = new ParticleColorGui();

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleColorGui getInstance() {
        return INSTANCE;
    }

    /**
     * 创建gui
     *
     * @param player   玩家
     * @param category 类别
     * @param context  上级选择
     * @return gui
     */
    @NotNull
    public Inventory createGui(@NotNull Player player, @NotNull ParticleCategoryTypeEnum category, @NotNull ParticleSelectContext context) {
        String title = ConfigUtil.COLOR_CONFIG.getString("title", "");
        int size = ConfigUtil.COLOR_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.COLOR_CONFIG.getString("sound");
        title = StrUtil.replace(title, "category", category.getDesc());
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.COLOR.getType(), title, size, sound);
        handyInventory.setPlayer(player);
        handyInventory.setSearchType(category.name());
        handyInventory.setObj(context);
        this.setInventoryDate(handyInventory);
        return handyInventory.getInventory();
    }

    /**
     * 设置数据
     *
     * @param handyInventory gui
     */
    public void setInventoryDate(@NotNull HandyInventory handyInventory) {
        // 基础设置
        handyInventory.setGuiType(ParticleGuiTypeEnum.COLOR.getType());
        // 1.刷新
        HandyInventoryUtil.refreshInventory(handyInventory.getInventory());
        handyInventory.getStrMap().clear();
        handyInventory.getSoundMap().clear();
        // 2.设置数据
        this.setData(handyInventory);
        // 3.设置功能性菜单
        this.setFunctionMenu(handyInventory);
    }

    /**
     * 设置颜色数据
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        Player player = handyInventory.getPlayer();
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnumThrow(handyInventory.getSearchType());
        String index = ConfigUtil.COLOR_CONFIG.getString("colorBoard.index");
        List<Integer> indexList = StrUtil.strToIntList(index);
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        List<String> colors = this.listColors();
        String selected = this.findSelectedColor(player, category);
        for (int i = 0; i < colors.size() && i < indexList.size(); i++) {
            String color = colors.get(i);
            this.setColorItem(handyInventory, indexList.get(i), color, color.equalsIgnoreCase(selected));
        }
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        // 返回按钮
        HandyInventoryUtil.setButton(ConfigUtil.COLOR_CONFIG, handyInventory, "back");
        HandyInventoryUtil.setButton(ConfigUtil.COLOR_CONFIG, handyInventory, "customColor");
        // 自定义按钮
        HandyInventoryUtil.setCustomButton(ConfigUtil.COLOR_CONFIG, handyInventory, "custom");
    }

    /**
     * 获取色板颜色
     *
     * @return 颜色列表
     */
    @NotNull
    private List<String> listColors() {
        List<String> colors = ConfigUtil.COLOR_CONFIG.getStringList("colorBoard.colors");
        if (CollUtil.isNotEmpty(colors)) {
            return colors;
        }
        List<String> preset = new ArrayList<>(PRESET_COLORS.length);
        for (String color : PRESET_COLORS) {
            preset.add(color);
        }
        return preset;
    }

    /**
     * 设置颜色物品
     *
     * @param handyInventory gui
     * @param slot           槽位
     * @param color          颜色
     * @param selected       是否选中
     */
    private void setColorItem(@NotNull HandyInventory handyInventory, int slot, @NotNull String color, boolean selected) {
        String section = selected ? "colorBoardSelected" : "colorBoard";
        String name = ConfigUtil.COLOR_CONFIG.getString(section + ".name");
        name = StrUtil.replace(name, "${color}", color);
        List<String> lore = new ArrayList<>();
        List<String> loreConfig = ConfigUtil.COLOR_CONFIG.getStringList(section + ".lore");
        for (String line : loreConfig) {
            lore.add(StrUtil.replace(line, "${color}", color));
        }
        boolean enchantConfig = ConfigUtil.COLOR_CONFIG.getBoolean(section + ".isEnchant");
        boolean isEnchant = selected || enchantConfig;
        int customModelData = ConfigUtil.COLOR_CONFIG.getInt(section + ".custom-model-data");
        boolean hideFlag = ConfigUtil.COLOR_CONFIG.getBoolean(section + ".hideFlag", true);
        int amount = ConfigUtil.COLOR_CONFIG.getInt(section + ".amount", 1);
        String material = this.resolveMaterial(color, section);
        ItemStack itemStack = ItemStackUtil.getItemStack(material, name, lore, isEnchant, customModelData, hideFlag, null);
        itemStack.setAmount(amount);
        handyInventory.getInventory().setItem(slot, itemStack);
        // 设置点击数据
        handyInventory.getStrMap().put(slot, ParticleGuiUtil.DATA_PREFIX + color);
        String sound = ConfigUtil.COLOR_CONFIG.getString(section + ".sound");
        if (StrUtil.isNotEmpty(sound)) {
            handyInventory.getSoundMap().put(slot, sound);
        }
    }

    /**
     * 获取颜色材质
     *
     * @param color   颜色
     * @param section 配置段
     * @return 材质名称
     */
    @NotNull
    private String resolveMaterial(@NotNull String color, @NotNull String section) {
        String selected = ConfigUtil.COLOR_CONFIG.getString(section + ".colorMaterials." + color);
        if (StrUtil.isNotEmpty(selected)) {
            return selected;
        }
        String configured = ConfigUtil.COLOR_CONFIG.getString("colorBoard.colorMaterials." + color);
        if (StrUtil.isNotEmpty(configured)) {
            return configured;
        }
        String material = ConfigUtil.COLOR_CONFIG.getString(section + ".material");
        if (StrUtil.isNotEmpty(material)) {
            return material;
        }
        Color awt = ParticleColorUtil.get(color);
        return awt == null ? DyeColor.WHITE.name() + "_WOOL" : this.nearestWool(awt).name() + "_WOOL";
    }

    /**
     * 获取最接近的羊毛颜色
     *
     * @param color 目标颜色
     * @return 羊毛颜色
     */
    @NotNull
    private DyeColor nearestWool(@NotNull Color color) {
        DyeColor best = DyeColor.WHITE;
        long bestDistance = Long.MAX_VALUE;
        for (DyeColor dyeColor : DyeColor.values()) {
            org.bukkit.Color rgb = dyeColor.getColor();
            long dr = color.getRed() - rgb.getRed();
            long dg = color.getGreen() - rgb.getGreen();
            long db = color.getBlue() - rgb.getBlue();
            long distance = dr * dr + dg * dg + db * db;
            if (distance < bestDistance) {
                bestDistance = distance;
                best = dyeColor;
            }
        }
        return best;
    }

    /**
     * 获取已选颜色
     *
     * @param player   玩家
     * @param category 粒子类别
     * @return 颜色
     */
    @Nullable
    private String findSelectedColor(@NotNull Player player, @NotNull ParticleCategoryTypeEnum category) {
        PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(player, category);
        if (saved == null || StrUtil.isEmpty(saved.getColor())) {
            return null;
        }
        return saved.getColor();
    }

}
