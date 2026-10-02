package cn.handyplus.particle.command.player;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.internal.PlayerSchedulerUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.inventory.ParticleCategoryGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家自助打开粒子选择gui。
 *
 * @author handy
 */
public final class GuiCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "gui";
    }

    @Override
    public String permission() {
        return "handyParticle.gui";
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    /**
     * 打开粒子选择gui。
     *
     * @param sender  命令执行者
     * @param command Bukkit 命令
     * @param label   命令别名
     * @param args    命令参数
     */
    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = AssertUtil.notPlayer(sender, BaseUtil.getLangMsg("entityPlayerOnly"));
        Inventory inventory = ParticleCategoryGui.getInstance().createGui(player);
        PlayerSchedulerUtil.openInventory(player, inventory);
    }

}
