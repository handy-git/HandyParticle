package cn.handyplus.particle.inventory;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 翅膀图案菜单
 *
 * @author handy
 */
public final class ParticleWingColorGui {

    private ParticleWingColorGui() {
    }

    private static final ParticleWingColorGui INSTANCE = new ParticleWingColorGui();

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleWingColorGui getInstance() {
        return INSTANCE;
    }

    /**
     * 创建gui
     *
     * @param player 玩家
     * @param model  翅膀外形
     * @return gui
     */
    @NotNull
    public Inventory createGui(@NotNull Player player, @NotNull WingModelEnum model) {
        String title = ConfigUtil.WING_COLOR_CONFIG.getString("title", "");
        int size = ConfigUtil.WING_COLOR_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.WING_COLOR_CONFIG.getString("sound");
        title = StrUtil.replace(title, "model", model.getDesc());
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.WING_COLOR.getType(), title, size, sound);
        handyInventory.setPlayer(player);
        handyInventory.setSearchType(ParticleCategoryTypeEnum.WING.name());
        handyInventory.setObj(model);
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
        handyInventory.setGuiType(ParticleGuiTypeEnum.WING_COLOR.getType());
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
     * 设置图案数据
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        WingModelEnum model = (WingModelEnum) handyInventory.getObj();
        String index = ConfigUtil.WING_COLOR_CONFIG.getString("list.index");
        List<Integer> indexList = StrUtil.strToIntList(index);
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        WingColorEnum[] colors = WingColorEnum.values();
        int availableCount = 0;
        for (WingColorEnum color : colors) {
            if (ParticleGuiUtil.hasParticlePermission(handyInventory.getPlayer(), ParticleTypeEnum.wing(model, color))) {
                availableCount++;
            }
        }
        handyInventory.setPageSize(indexList.size());
        handyInventory.setPageCount(availableCount);
        PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(handyInventory.getPlayer(), ParticleCategoryTypeEnum.WING);
        int i = 0;
        for (WingColorEnum color : colors) {
            if (!ParticleGuiUtil.hasParticlePermission(handyInventory.getPlayer(), ParticleTypeEnum.wing(model, color))) {
                continue;
            }
            if (i >= indexList.size()) {
                break;
            }
            boolean selected = saved != null && model.name().equals(saved.getType()) && color.name().equals(saved.getColor());
            ParticleGuiUtil.setParticleItem(ConfigUtil.WING_COLOR_CONFIG, handyInventory, indexList.get(i++), color.getDesc(), color.getDesc(), selected, color.name());
        }
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        HandyInventoryUtil.setButton(ConfigUtil.WING_COLOR_CONFIG, handyInventory, "back");
        HandyInventoryUtil.setButton(ConfigUtil.WING_COLOR_CONFIG, handyInventory, "closeCategory", MapUtil.of("category", ParticleCategoryTypeEnum.WING.getDesc()));
        HandyInventoryUtil.setCustomButton(ConfigUtil.WING_COLOR_CONFIG, handyInventory, "custom");
    }

}
