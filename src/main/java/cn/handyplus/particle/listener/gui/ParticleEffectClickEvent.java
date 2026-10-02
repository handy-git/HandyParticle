package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import cn.handyplus.particle.inventory.ParticleEffectGui;
import cn.handyplus.particle.inventory.ParticleModeGui;
import cn.handyplus.particle.inventory.ParticleWingColorGui;
import cn.handyplus.particle.param.ParticleSelectContext;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * 粒子效果菜单点击事件
 *
 * @author handy
 */
public final class ParticleEffectClickEvent implements IHandyClickEvent {

    @Override
    public String guiType() {
        return ParticleGuiTypeEnum.EFFECT.getType();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void rawSlotClick(HandyInventory handyInventory, InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnum(handyInventory.getSearchType());
        // 返回上一级
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.EFFECT_CONFIG, "back")) {
            handyInventory.syncOpen(ParticleCategoryGui.getInstance().createGui(handyInventory.getPlayer()));
            return;
        }
        // 关闭粒子
        if (category != ParticleCategoryTypeEnum.TRAIL && HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.EFFECT_CONFIG, "closeCategory")) {
            ParticleGuiUtil.clear(handyInventory.getPlayer(), category);
            ParticleEffectGui.getInstance().setInventoryDate(handyInventory);
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null) {
            return;
        }
        if (!action.startsWith(ParticleGuiUtil.DATA_PREFIX)) {
            return;
        }
        if (category == ParticleCategoryTypeEnum.WING) {
            WingModelEnum model = WingModelEnum.get(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
            if (model != null) {
                handyInventory.syncOpen(ParticleWingColorGui.getInstance().createGui(handyInventory.getPlayer(), model));
            }
            return;
        }
        // 点击效果
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
        if (type == null || category == null) {
            return;
        }
        // 非拖尾效果直接应用
        if (type.getCategory() != ParticleCategoryTypeEnum.TRAIL) {
            ParticleGuiUtil.apply(handyInventory.getPlayer(), type, null, null);
            handyInventory.syncOpen(ParticleCategoryGui.getInstance().createGui(handyInventory.getPlayer()));
            return;
        }
        ParticleSelectContext context = new ParticleSelectContext(type.name(), null);
        handyInventory.syncOpen(ParticleModeGui.getInstance().createGui(handyInventory.getPlayer(), category, context));
    }

}
