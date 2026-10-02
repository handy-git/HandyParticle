package cn.handyplus.particle.listener.gui;

import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.input.InputField;
import cn.handyplus.lib.input.InputOptions;
import cn.handyplus.lib.input.InputResult;
import cn.handyplus.lib.input.PlayerInputApi;
import cn.handyplus.lib.internal.PlayerSchedulerUtil;
import cn.handyplus.lib.inventory.HandyInventory;
import cn.handyplus.lib.inventory.HandyInventoryUtil;
import cn.handyplus.lib.inventory.IHandyClickEvent;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleGuiTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import cn.handyplus.particle.inventory.ParticleColorGui;
import cn.handyplus.particle.inventory.ParticleModeGui;
import cn.handyplus.particle.param.ParticleSelectContext;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleColorUtil;
import cn.handyplus.particle.util.ParticleGuiUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.awt.Color;
import java.util.Collections;
import java.util.Map;

/**
 * 拖尾颜色菜单点击事件
 *
 * @author handy
 */
public final class ParticleColorClickEvent implements IHandyClickEvent {

    private static final String CANCEL_KEYWORD = "T";
    private static final String KEY_COLOR = "color";

    @Override
    public String guiType() {
        return ParticleGuiTypeEnum.COLOR.getType();
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
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.COLOR_CONFIG, "back")) {
            if (context != null && category != null) {
                handyInventory.syncOpen(ParticleModeGui.getInstance().createGui(handyInventory.getPlayer(), category, context));
            }
            return;
        }
        // 自定义颜色
        if (HandyInventoryUtil.isIndex(rawSlot, ConfigUtil.COLOR_CONFIG, "customColor")) {
            if (context != null) {
                this.openColorInput(handyInventory.getPlayer(), context);
            }
            return;
        }
        String action = handyInventory.getStrMap().get(rawSlot);
        if (action == null) {
            return;
        }
        if (!action.startsWith(ParticleGuiUtil.DATA_PREFIX) || context == null) {
            return;
        }
        // 点击色板颜色
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(context.getType());
        Color color = ParticleColorUtil.get(action.substring(ParticleGuiUtil.DATA_PREFIX.length()));
        if (type == null) {
            return;
        }
        if (color == null) {
            MessageUtil.sendMessage(handyInventory.getPlayer(), BaseUtil.getLangMsg("invalidColor"));
            return;
        }
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(context.getMode());
        ParticleGuiUtil.apply(handyInventory.getPlayer(), type, mode == null ? TrailModeTypeEnum.DEFAULT : mode, color);
        handyInventory.syncOpen(ParticleCategoryGui.getInstance().createGui(handyInventory.getPlayer()));
    }

    /**
     * 打开自定义颜色输入
     *
     * @param player  玩家
     * @param context 当前选择
     */
    private void openColorInput(Player player, ParticleSelectContext context) {
        Map<String, String> replaceMap = MapUtil.of("cancel", CANCEL_KEYWORD);
        InputOptions options = InputOptions.create()
                .title(BaseUtil.getLangMsg("guiColorInputTitle"))
                .description(Collections.singletonList(BaseUtil.getLangMsg("guiColorInputDescription", replaceMap)))
                .cancelKeyword(CANCEL_KEYWORD)
                .cancelMessage(BaseUtil.getLangMsg("guiColorInputCancel"))
                .input(Collections.singletonList(InputField.builder()
                        .key(KEY_COLOR)
                        .label(BaseUtil.getLangMsg("guiColorInputField"))
                        .chatPrompt(BaseUtil.getLangMsg("guiColorInputChatPrompt", replaceMap))
                        .initialValue("#FF0000")
                        .maxLength(9)
                        .build()));
        PlayerInputApi.open(player, options, (target, inputs) -> {
            Color color = ParticleColorUtil.get(inputs.get(KEY_COLOR));
            if (color == null) {
                MessageUtil.sendMessage(target, BaseUtil.getLangMsg("invalidColor"));
                return InputResult.RETRY;
            }
            this.applyInput(target, context, color);
            return InputResult.COMPLETE;
        }, target -> this.reopenBoard(target, context));
    }

    /**
     * 应用输入的颜色
     *
     * @param player  玩家
     * @param context 当前选择
     * @param color   颜色
     */
    private void applyInput(Player player, ParticleSelectContext context, Color color) {
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(context.getType());
        if (type == null) {
            return;
        }
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(context.getMode());
        ParticleGuiUtil.apply(player, type, mode == null ? TrailModeTypeEnum.DEFAULT : mode, color);
        PlayerSchedulerUtil.openInventory(player, ParticleCategoryGui.getInstance().createGui(player));
    }

    /**
     * 取消输入后返回色板
     *
     * @param player  玩家
     * @param context 当前选择
     */
    private void reopenBoard(Player player, ParticleSelectContext context) {
        ParticleTypeEnum type = ParticleTypeEnum.getEnum(context.getType());
        if (type != null) {
            PlayerSchedulerUtil.openInventory(player, ParticleColorGui.getInstance().createGui(player, type.getCategory(), context));
        }
    }

}
