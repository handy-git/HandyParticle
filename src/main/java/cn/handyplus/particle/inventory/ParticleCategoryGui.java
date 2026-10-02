package cn.handyplus.particle.inventory;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
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
 * 粒子类别菜单
 *
 * @author handy
 */
public final class ParticleCategoryGui {

    private ParticleCategoryGui() {
    }

    private static final ParticleCategoryGui INSTANCE = new ParticleCategoryGui();

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleCategoryGui getInstance() {
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
        String title = ConfigUtil.CATEGORY_CONFIG.getString("title", "");
        int size = ConfigUtil.CATEGORY_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.CATEGORY_CONFIG.getString("sound");
        title = StrUtil.replace(title, "category", BaseUtil.getLangMsg("guiAllCategory"));
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.CATEGORY.getType(), title, size, sound);
        handyInventory.setPlayer(player);
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
        handyInventory.setGuiType(ParticleGuiTypeEnum.CATEGORY.getType());
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
     * 设置类别数据
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        Player player = handyInventory.getPlayer();
        String index = ConfigUtil.CATEGORY_CONFIG.getString("list.index");
        List<Integer> indexList = StrUtil.strToIntList(index);
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        handyInventory.setPageSize(indexList.size());
        ParticleCategoryTypeEnum[] categories = ParticleCategoryTypeEnum.values();
        // 类别固定，无需翻页
        handyInventory.setPageCount(categories.length);
        int i = 0;
        for (ParticleCategoryTypeEnum category : categories) {
            if (i >= indexList.size()) {
                break;
            }
            PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(player, category);
            String desc = saved == null ? category.getDesc() : this.getSelectedDesc(saved, category);
            ParticleGuiUtil.setParticleItem(ConfigUtil.CATEGORY_CONFIG, handyInventory, indexList.get(i++), category.getDesc(), desc, saved != null, category.name());
        }
    }

    /**
     * 获取当前类别已选效果的描述
     *
     * @param saved    已保存的效果
     * @param category 粒子类别
     * @return 效果描述
     */
    @NotNull
    private String getSelectedDesc(@NotNull PlayerParticleEntity saved, @NotNull ParticleCategoryTypeEnum category) {
        if (category == ParticleCategoryTypeEnum.WING) {
            WingModelEnum model = WingModelEnum.get(saved.getType());
            WingColorEnum color = WingColorEnum.get(saved.getColor());
            String modelDesc = model == null ? saved.getType() : model.getDesc();
            if (color == null && saved.getColor() == null) {
                return modelDesc;
            }
            String colorDesc = color == null ? saved.getColor() : color.getDesc();
            return modelDesc + " / " + colorDesc;
        }
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(saved.getType());
        String desc = type == null ? saved.getType() : type.getDesc();
        if (category == ParticleCategoryTypeEnum.TRAIL) {
            TrailModeTypeEnum mode = TrailModeTypeEnum.get(saved.getMode());
            desc += " / " + (mode == null ? TrailModeTypeEnum.DEFAULT.getDesc() : mode.getDesc());
            if (saved.getColor() != null) {
                desc += " / " + saved.getColor();
            }
        }
        return desc;
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        // 自定义按钮
        HandyInventoryUtil.setCustomButton(ConfigUtil.CATEGORY_CONFIG, handyInventory, "custom");
        // 返回按钮
        HandyInventoryUtil.setButton(ConfigUtil.CATEGORY_CONFIG, handyInventory, "back");
        // 关闭全部
        HandyInventoryUtil.setButton(ConfigUtil.CATEGORY_CONFIG, handyInventory, "closeAll");
    }

}
