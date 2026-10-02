package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import cn.handyplus.particle.inventory.ParticleEffectGui;
import cn.handyplus.particle.inventory.ParticleWingColorGui;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * 翅膀图案菜单点击事件
 *
 * @author handy
 */
public final class ParticleWingColorClickEvent implements IHandyClickEvent {

    @Override
    public String guiType() {
        return ParticleGuiTypeEnum.WING_COLOR.getType();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void rawSlotClick(HandyInventory handyInventory, InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        WingModelEnum model = (WingModelEnum) handyInventory.getObj();
        // 返回上一级
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.WING_COLOR_CONFIG, "back")) {
            handyInventory.syncOpen(ParticleEffectGui.getInstance().createGui(handyInventory.getPlayer(), ParticleCategoryTypeEnum.WING));
            return;
        }
        // 关闭粒子
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.WING_COLOR_CONFIG, "closeCategory")) {
            ParticleGuiUtil.clear(handyInventory.getPlayer(), ParticleCategoryTypeEnum.WING);
            ParticleWingColorGui.getInstance().setInventoryDate(handyInventory);
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null || !action.startsWith(ParticleGuiUtil.DATA_PREFIX)) {
            return;
        }
        // 点击图案
        WingColorEnum color = WingColorEnum.get(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
        if (color != null) {
            ParticleTypeEnum type = ParticleTypeEnum.wing(model, color);
            ParticleGuiUtil.apply(handyInventory.getPlayer(), type, null, null);
            handyInventory.syncOpen(ParticleCategoryGui.getInstance().createGui(handyInventory.getPlayer()));
        }
    }

}
