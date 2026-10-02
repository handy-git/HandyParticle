package cn.handyplus.particle.inventory;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.param.ParticleSelectContext;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 拖尾模式菜单
 *
 * @author handy
 */
public final class ParticleModeGui {

    private ParticleModeGui() {
    }

    private static final ParticleModeGui INSTANCE = new ParticleModeGui();

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleModeGui getInstance() {
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
        String title = ConfigUtil.MODE_CONFIG.getString("title", "");
        int size = ConfigUtil.MODE_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.MODE_CONFIG.getString("sound");
        title = StrUtil.replace(title, "category", category.getDesc());
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.MODE.getType(), title, size, sound);
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
        handyInventory.setGuiType(ParticleGuiTypeEnum.MODE.getType());
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
     * 设置模式数据
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        Player player = handyInventory.getPlayer();
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnumThrow(handyInventory.getSearchType());
        String index = ConfigUtil.MODE_CONFIG.getString("list.index");
        List<Integer> indexList = StrUtil.strToIntList(index);
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        TrailModeTypeEnum[] modes = TrailModeTypeEnum.values();
        String selected = this.findSelected(player, category);
        for (int i = 0; i < modes.length && i < indexList.size(); i++) {
            TrailModeTypeEnum mode = modes[i];
            ParticleGuiUtil.setParticleItem(ConfigUtil.MODE_CONFIG, handyInventory, indexList.get(i), mode.getDesc(), mode.getDesc(), mode.name().equals(selected), mode.name());
        }
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        // 返回按钮
        HandyInventoryUtil.setButton(ConfigUtil.MODE_CONFIG, handyInventory, "back");
        // 关闭按钮
        HandyInventoryUtil.setButton(ConfigUtil.MODE_CONFIG, handyInventory, "closeCategory", MapUtil.of("category", ParticleCategoryTypeEnum.getEnumThrow(handyInventory.getSearchType()).getDesc()));
        // 自定义按钮
        HandyInventoryUtil.setCustomButton(ConfigUtil.MODE_CONFIG, handyInventory, "custom");
    }

    /**
     * 获取已选模式
     *
     * @param player   玩家
     * @param category 粒子类别
     * @return 模式名称
     */
    @Nullable
    private String findSelected(@NotNull Player player, @NotNull ParticleCategoryTypeEnum category) {
        PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(player, category);
        return saved == null ? null : saved.getMode();
    }

}
