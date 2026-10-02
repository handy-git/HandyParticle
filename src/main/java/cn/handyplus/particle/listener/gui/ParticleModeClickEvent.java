package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import cn.handyplus.particle.inventory.ParticleColorGui;
import cn.handyplus.particle.inventory.ParticleEffectGui;
import cn.handyplus.particle.inventory.ParticleModeGui;
import cn.handyplus.particle.param.ParticleSelectContext;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * 拖尾模式菜单点击事件
 *
 * @author handy
 */
public final class ParticleModeClickEvent implements IHandyClickEvent {

    @Override
    public String guiType() {
        return ParticleGuiTypeEnum.MODE.getType();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void rawSlotClick(HandyInventory handyInventory, InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        Object obj = handyInventory.getObj();
        ParticleSelectContext context = obj instanceof ParticleSelectContext ? (ParticleSelectContext) obj : null;
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnum(handyInventory.getSearchType());
        // 返回上一级
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.MODE_CONFIG, "back")) {
            if (category != null) {
                handyInventory.syncOpen(ParticleEffectGui.getInstance().createGui(handyInventory.getPlayer(), category));
            }
            return;
        }
        // 关闭粒子
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.MODE_CONFIG, "closeCategory")) {
            ParticleGuiUtil.clear(handyInventory.getPlayer(), category);
            ParticleModeGui.getInstance().setInventoryDate(handyInventory);
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null) {
            return;
        }
        if (!action.startsWith(ParticleGuiUtil.DATA_PREFIX) || context == null || category == null) {
            return;
        }
        // 点击模式
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(context.getType());
        if (mode == null || type == null) {
            return;
        }
        // 需要颜色时打开色板
        if (type.supportsColor()) {
            ParticleSelectContext colorContext = new ParticleSelectContext(context.getType(), mode.name());
            handyInventory.syncOpen(ParticleColorGui.getInstance().createGui(handyInventory.getPlayer(), category, colorContext));
            return;
        }
        ParticleGuiUtil.apply(handyInventory.getPlayer(), type, mode, null);
        handyInventory.syncOpen(ParticleCategoryGui.getInstance().createGui(handyInventory.getPlayer()));
    }

}
