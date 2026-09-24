package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import org.bukkit.Bukkit;
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
        if (playerName == null) {
            MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("clearUsage"));
            return;
        }
        Player player = Bukkit.getPlayerExact(playerName);
        if (player == null) {
            MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("playerOffline"));
            return;
        }
        HandyParticleApi.disable(player);
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("clearSuccess").replace("{player}", player.getName()));
    }

}
