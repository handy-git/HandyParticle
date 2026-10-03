package cn.handyplus.particle.inventory;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.util.ItemStackUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.weapon.WeaponGroupEnum;
import cn.handyplus.particle.core.weapon.WeaponParticleManager;
import cn.handyplus.particle.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 武器生效范围多选菜单。
 *
 * <p>左键切换勾选，结果立即写入内存与数据库，空列表视为全部生效。
 *
 * @author handy
 */
public final class ParticleWeaponGui {

    /**
     * 数据区槽位在 strMap 中使用的键前缀。
     */
    public static final String GROUP_PREFIX = "group:";
    /**
     * 全选动作名。
     */
    public static final String ACTION_SELECT_ALL = "selectAll";
    /**
     * 全不选动作名。
     */
    public static final String ACTION_CLEAR_ALL = "clearAll";

    private static final ParticleWeaponGui INSTANCE = new ParticleWeaponGui();

    private ParticleWeaponGui() {
    }

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleWeaponGui getInstance() {
        return INSTANCE;
    }

    /**
     * 创建gui
     *
     * @param player 玩家
     * @return gui
     */
    @NotNull
    public Inventory createGui(@NotNull Player player) {
        String title = ConfigUtil.WEAPON_CONFIG.getString("title", "");
        int size = ConfigUtil.WEAPON_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.WEAPON_CONFIG.getString("sound");
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.WEAPON_KIND.getType(), title, size, sound);
        handyInventory.setPlayer(player);
        handyInventory.setSearchType(ParticleCategoryTypeEnum.WEAPON.name());
        this.setInventoryDate(handyInventory);
        return handyInventory.getInventory();
    }

    /**
     * 设置数据
     *
     * @param handyInventory gui
     */
    public void setInventoryDate(@NotNull HandyInventory handyInventory) {
        handyInventory.setGuiType(ParticleGuiTypeEnum.WEAPON_KIND.getType());
        HandyInventoryUtil.refreshInventory(handyInventory.getInventory());
        handyInventory.getStrMap().clear();
        handyInventory.getSoundMap().clear();
        this.setData(handyInventory);
        this.setFunctionMenu(handyInventory);
    }

    /**
     * 渲染武器分组选项
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        List<Integer> indexList = StrUtil.strToIntList(ConfigUtil.WEAPON_CONFIG.getString("list.index"));
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        List<WeaponGroupEnum> selected = this.getSelected(handyInventory.getPlayer());
        int i = 0;
        for (WeaponGroupEnum group : WeaponGroupEnum.values()) {
            if (i >= indexList.size()) {
                break;
            }
            this.setGroupItem(handyInventory, indexList.get(i++), group, selected.contains(group));
        }
    }

    /**
     * 渲染单个武器分组选项
     *
     * @param handyInventory gui
     * @param slot           槽位
     * @param group          武器分组
     * @param selected       是否已勾选
     */
    private void setGroupItem(@NotNull HandyInventory handyInventory, int slot, @NotNull WeaponGroupEnum group, boolean selected) {
        String section = selected ? "selected" : "data";
        if (!ConfigUtil.WEAPON_CONFIG.getBoolean(section + ".enable", true)) {
            return;
        }
        // 单项材质可覆盖默认材质
        String itemPath = "list.items." + group.name();
        String material = ConfigUtil.WEAPON_CONFIG.getString(itemPath + ".material",
                ConfigUtil.WEAPON_CONFIG.getString(section + ".material", "PAPER"));
        String name = ConfigUtil.WEAPON_CONFIG.getString(section + ".name");
        List<String> lore = ConfigUtil.WEAPON_CONFIG.getStringList(section + ".lore");
        boolean isEnchant = ConfigUtil.WEAPON_CONFIG.getBoolean(section + ".isEnchant");
        int customModelData = ConfigUtil.WEAPON_CONFIG.getInt(section + ".custom-model-data");
        boolean hideFlag = ConfigUtil.WEAPON_CONFIG.getBoolean(section + ".hideFlag", true);
        String sound = ConfigUtil.WEAPON_CONFIG.getString(section + ".sound");

        Map<String, String> replaceMap = MapUtil.of("name", group.getDesc());
        String replacedName = StrUtil.replace(name, "name", group.getDesc());
        List<String> replacedLore = ItemStackUtil.loreReplaceMap(lore, replaceMap);
        ItemStack itemStack = ItemStackUtil.getItemStack(material, replacedName, replacedLore, isEnchant, customModelData, hideFlag, null);
        handyInventory.getInventory().setItem(slot, itemStack);
        handyInventory.getStrMap().put(slot, GROUP_PREFIX + group.name());
        if (StrUtil.isNotEmpty(sound)) {
            handyInventory.getSoundMap().put(slot, sound);
        }
    }

    /**
     * 读取玩家已勾选的武器分组，未设置时视为全选。
     *
     * @param player 玩家
     * @return 已勾选列表
     */
    @NotNull
    private List<WeaponGroupEnum> getSelected(@NotNull Player player) {
        WeaponParticleManager.WeaponRecord record = WeaponParticleManager.getRecord(player.getUniqueId());
        return record == null ? Arrays.asList(WeaponGroupEnum.values()) : record.getGroups();
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        HandyInventoryUtil.setButton(ConfigUtil.WEAPON_CONFIG, handyInventory, "back");
        Map<String, String> replaceMap = MapUtil.of("number", String.valueOf(WeaponGroupEnum.values().length),
                "selected", String.valueOf(this.getSelected(handyInventory.getPlayer()).size()));
        // setButton 支持变量替换但不会写 strMap，需手动登记点击动作
        this.setActionButton(handyInventory, ACTION_SELECT_ALL, replaceMap);
        this.setActionButton(handyInventory, ACTION_CLEAR_ALL, replaceMap);
        HandyInventoryUtil.setButton(ConfigUtil.WEAPON_CONFIG, handyInventory, "custom.pane");
    }

    /**
     * 渲染带变量替换的动作按钮，并登记点击动作供事件类识别。
     *
     * @param handyInventory gui
     * @param action         动作名
     * @param replaceMap     文本替换变量
     */
    private void setActionButton(@NotNull HandyInventory handyInventory, @NotNull String action, @NotNull Map<String, String> replaceMap) {
        HandyInventoryUtil.setButton(ConfigUtil.WEAPON_CONFIG, handyInventory, action, replaceMap);
        for (Integer index : StrUtil.strToIntList(ConfigUtil.WEAPON_CONFIG.getString(action + ".index"))) {
            handyInventory.getStrMap().put(index, action);
        }
    }

}
