package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

/**
 * 清除在线玩家的粒子效果。
 *
 * @author handy
 */
public final class ClearCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "clear";
    }

    @Override
    public String permission() {
        return "handyParticle.clear";
    }

    @Override
    public void tab(HandyTab tab) {
        tab.nextNull().next(ParticleCategoryTypeEnum.getAll());
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 解析并校验目标玩家。
        String playerName = this.getArg(args, 1).orElse(null);
        AssertUtil.notEmpty(playerName, BaseUtil.getLangMsg("clearUsage"));
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));

        // 没有类别参数时清理玩家全部粒子效果。
        String categoryName = this.getArg(args, 2).orElse(null);
        if (categoryName == null) {
            HandyParticleApi.disable(player);
            PlayerParticleService.delete(player.getUniqueId());
            this.sendClearMessage(sender, player,
                    BaseUtil.getLangMsg("clearSuccess", MapUtil.of("player", player.getName())),
                    BaseUtil.getLangMsg("clearTargetNotice"));
            return;
        }

        // 校验类别后只清理对应任务和数据库记录。
        categoryName = categoryName.toUpperCase(Locale.ROOT);
        AssertUtil.notTrue(!ParticleCategoryTypeEnum.getAll().contains(categoryName), BaseUtil.getLangMsg("clearUsage"));
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.valueOf(categoryName);
        HandyParticleApi.disable(player, category);
        PlayerParticleService.delete(player.getUniqueId(), category);
        this.sendClearMessage(sender, player,
                BaseUtil.getLangMsg("clearCategorySuccess", MapUtil.of("player", player.getName(), "category", category.getDesc())),
                BaseUtil.getLangMsg("clearCategoryTargetNotice", MapUtil.of("category", category.getDesc())));
    }

    /**
     * 向执行者和目标玩家发送清理结果。
     *
     * @param sender       执行者
     * @param player       目标玩家
     * @param senderMessage 执行者消息
     * @param targetMessage 目标玩家消息
     */
    private void sendClearMessage(CommandSender sender, Player player, String senderMessage, String targetMessage) {
        MessageUtil.sendMessage(sender, senderMessage);
        if (sender != player) {
            MessageUtil.sendMessage(player, targetMessage);
        }
    }

}
