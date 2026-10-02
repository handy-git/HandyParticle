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
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 粒子效果菜单
 *
 * @author handy
 */
public final class ParticleEffectGui {

    private ParticleEffectGui() {
    }

    private static final ParticleEffectGui INSTANCE = new ParticleEffectGui();

    /**
     * 获取实例
     *
     * @return gui实例
     */
    @NotNull
    public static ParticleEffectGui getInstance() {
        return INSTANCE;
    }

    /**
     * 创建gui
     *
     * @param player   玩家
     * @param category 类别
     * @return gui
     */
    @NotNull
    public Inventory createGui(@NotNull Player player, @NotNull ParticleCategoryTypeEnum category) {
        String title = ConfigUtil.EFFECT_CONFIG.getString("title", "");
        int size = ConfigUtil.EFFECT_CONFIG.getInt("size", BaseConstants.GUI_SIZE_54);
        String sound = ConfigUtil.EFFECT_CONFIG.getString("sound");
        title = StrUtil.replace(title, "category", category.getDesc());
        HandyInventory handyInventory = new HandyInventory(ParticleGuiTypeEnum.EFFECT.getType(), title, size, sound);
        handyInventory.setPlayer(player);
        handyInventory.setSearchType(category.name());
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
        handyInventory.setGuiType(ParticleGuiTypeEnum.EFFECT.getType());
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
     * 设置效果数据
     *
     * @param handyInventory gui
     */
    private void setData(@NotNull HandyInventory handyInventory) {
        Player player = handyInventory.getPlayer();
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnumThrow(handyInventory.getSearchType());
        String indexKey = category == ParticleCategoryTypeEnum.TRAIL ? "list.trailIndex" : "list.index";
        String index = ConfigUtil.EFFECT_CONFIG.getString(indexKey);
        List<Integer> indexList = StrUtil.strToIntList(index);
        if (CollUtil.isEmpty(indexList)) {
            return;
        }
        if (category == ParticleCategoryTypeEnum.WING) {
            WingModelEnum[] models = WingModelEnum.values();
            PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(player, category);
            int i = 0;
            for (WingModelEnum model : models) {
                if (i >= indexList.size()) {
                    break;
                }
                boolean selected = saved != null && model.name().equals(saved.getType());
                ParticleGuiUtil.setParticleItem(ConfigUtil.EFFECT_CONFIG, handyInventory, indexList.get(i++), model.getDesc(), model.getDesc(), selected, model.name());
            }
            return;
        }
        List<ParticleTypeEnum> types = this.listTypes(category);
        String selectedType = this.findSelectedType(player, category);
        int i = 0;
        for (ParticleTypeEnum type : types) {
            if (i >= indexList.size()) {
                break;
            }
            ParticleGuiUtil.setParticleItem(ConfigUtil.EFFECT_CONFIG, handyInventory, indexList.get(i++), type.getDesc(), type.getDesc(), type.name().equals(selectedType), type.name());
        }
    }

    /**
     * 设置功能性菜单
     *
     * @param handyInventory gui
     */
    private void setFunctionMenu(@NotNull HandyInventory handyInventory) {
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnumThrow(handyInventory.getSearchType());
        // 返回按钮
        HandyInventoryUtil.setButton(ConfigUtil.EFFECT_CONFIG, handyInventory, "back");
        // 自定义按钮
        if (category == ParticleCategoryTypeEnum.TRAIL) {
            HandyInventoryUtil.setButton(ConfigUtil.EFFECT_CONFIG, handyInventory, "custom.trailPane");
        } else {
            // 关闭按钮
            HandyInventoryUtil.setButton(ConfigUtil.EFFECT_CONFIG, handyInventory, "closeCategory", MapUtil.of("category", category.getDesc()));
            HandyInventoryUtil.setButton(ConfigUtil.EFFECT_CONFIG, handyInventory, "custom.pane");
        }
    }

    /**
     * 获取类别效果
     *
     * @param category 粒子类别
     * @return 效果列表
     */
    @NotNull
    private List<ParticleTypeEnum> listTypes(@NotNull ParticleCategoryTypeEnum category) {
        List<ParticleTypeEnum> types = new ArrayList<>();
        for (ParticleTypeEnum type : ParticleTypeEnum.values()) {
            if (type.getCategory() == category) {
                types.add(type);
            }
        }
        return types;
    }

    /**
     * 获取已选效果
     *
     * @param player   玩家
     * @param category 粒子类别
     * @return 效果名称
     */
    @Nullable
    private String findSelectedType(@NotNull Player player, @NotNull ParticleCategoryTypeEnum category) {
        PlayerParticleEntity saved = ParticleGuiUtil.findCurrent(player, category);
        if (saved == null) {
            return null;
        }
        return saved.getType();
    }

}
