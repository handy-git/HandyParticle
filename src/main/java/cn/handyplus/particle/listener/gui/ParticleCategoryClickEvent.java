package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import cn.handyplus.particle.inventory.ParticleEffectGui;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * 粒子类别菜单点击事件
 *
 * @author handy
 */
public final class ParticleCategoryClickEvent implements IHandyClickEvent {

    @Override
    public String guiType() {
        return ParticleGuiTypeEnum.CATEGORY.getType();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void rawSlotClick(HandyInventory handyInventory, InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        // 返回按钮
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.CATEGORY_CONFIG, "back")) {
            handyInventory.syncClose();
            return;
        }
        // 关闭全部粒子
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.CATEGORY_CONFIG, "closeAll")) {
            ParticleGuiUtil.clear(handyInventory.getPlayer(), null);
            ParticleCategoryGui.getInstance().setInventoryDate(handyInventory);
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null) {
            return;
        }

        if (!action.startsWith(ParticleGuiUtil.DATA_PREFIX)) {
            return;
        }
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.getEnum(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
        if (category == null) {
            return;
        }
        // 右键直接取消该类别粒子，无需进入子菜单
        if (event.isRightClick()) {
            if (ParticleGuiUtil.findCurrent(handyInventory.getPlayer(), category) == null) {
                // 本来就没设置，静默忽略
                return;
            }
            ParticleGuiUtil.clear(handyInventory.getPlayer(), category);
            ParticleCategoryGui.getInstance().setInventoryDate(handyInventory);
            return;
        }
        // 左键进入该类别的效果菜单
        handyInventory.syncOpen(ParticleEffectGui.getInstance().createGui(handyInventory.getPlayer(), category));
    }

}
