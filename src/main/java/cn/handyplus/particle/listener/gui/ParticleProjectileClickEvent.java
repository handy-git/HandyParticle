package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.projectile.ProjectileKindEnum;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.inventory.ParticleEffectGui;
import cn.handyplus.particle.inventory.ParticleProjectileGui;
import cn.handyplus.particle.service.PlayerParticleService;
import cn.handyplus.particle.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 抛射物生效范围多选菜单点击事件。
 *
 * @author handy
 */
public final class ParticleProjectileClickEvent implements IHandyClickEvent {

    @Override
    @NotNull
    public String guiType() {
        return ParticleGuiTypeEnum.PROJECTILE_KIND.getType();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void rawSlotClick(@NotNull HandyInventory handyInventory, @NotNull InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        // 返回效果菜单
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.PROJECTILE_CONFIG, "back")) {
            handyInventory.syncOpen(ParticleEffectGui.getInstance()
                    .createGui(handyInventory.getPlayer(), ParticleCategoryTypeEnum.PROJECTILE));
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null) {
            return;
        }
        // 全选
        if (ParticleProjectileGui.ACTION_SELECT_ALL.equals(action)) {
            this.apply(handyInventory, new ArrayList<>(Arrays.asList(ProjectileKindEnum.values())));
            return;
        }
        // 全不选
        if (ParticleProjectileGui.ACTION_CLEAR_ALL.equals(action)) {
            this.apply(handyInventory, new ArrayList<>());
            return;
        }
        // 单项切换
        if (!action.startsWith(ParticleProjectileGui.KIND_PREFIX)) {
            return;
        }
        List<ProjectileKindEnum> selected = this.current(handyInventory.getPlayer());
        this.toggle(selected, action.substring(ParticleProjectileGui.KIND_PREFIX.length()));
        this.apply(handyInventory, selected);
    }

    /**
     * 切换指定抛射物的勾选状态。
     *
     * @param selected 当前勾选列表
     * @param name    抛射物类型名称
     */
    private void toggle(@NotNull List<ProjectileKindEnum> selected, @NotNull String name) {
        for (int index = 0; index < selected.size(); index++) {
            if (selected.get(index).name().equals(name)) {
                selected.remove(index);
                return;
            }
        }
        for (ProjectileKindEnum kind : ProjectileKindEnum.values()) {
            if (kind.name().equals(name)) {
                selected.add(kind);
                return;
            }
        }
    }

    /**
     * 读取玩家当前勾选列表，返回可修改副本。
     *
     * @param player 玩家
     * @return 勾选列表，未设置时返回全选
     */
    @NotNull
    private List<ProjectileKindEnum> current(@NotNull Player player) {
        ProjectileParticleManager.ProjectileRecord record = ProjectileParticleManager.getRecord(player);
        return record == null
                ? new ArrayList<>(Arrays.asList(ProjectileKindEnum.values()))
                : new ArrayList<>(record.getKinds());
    }

    /**
     * 保存勾选结果到内存与数据库并刷新菜单。
     *
     * @param handyInventory gui
     * @param selected       勾选列表，为空表示全部生效
     */
    private void apply(@NotNull HandyInventory handyInventory, @NotNull List<ProjectileKindEnum> selected) {
        Player player = handyInventory.getPlayer();
        ProjectileParticleManager.updateKinds(player.getUniqueId(), selected);
        PlayerParticleService.updateScopes(player.getUniqueId(), ParticleCategoryTypeEnum.PROJECTILE, ProjectileKindEnum.serialize(selected));
        if (selected.isEmpty()) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("guiProjectileClearAll"));
        } else {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("guiProjectileSelected",
                    MapUtil.of("number", String.valueOf(selected.size()))));
        }
        ParticleProjectileGui.getInstance().setInventoryDate(handyInventory);
    }

}
