package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        tab.nextNull();
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playerName = this.getArg(args, 1).orElse(null);
        AssertUtil.notEmpty(playerName, BaseUtil.getLangMsg("clearUsage"));
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));
        HandyParticleApi.disable(player);
        PlayerParticleService.delete(player.getUniqueId());
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("clearSuccess", MapUtil.of("player", player.getName())));
    }

}
