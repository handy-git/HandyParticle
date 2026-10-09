package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.db.DbTypeEnum;
import cn.handyplus.lib.db.SqlConvertUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 转换数据
 *
 * @author handy
 */
public class ConvertCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "convert";
    }

    @Override
    public String permission() {
        return "handyParticle.convert";
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void tab(HandyTab handyTab) {
        // 真实格式：/hp convert [mysql|sqlite]
        handyTab.next(DbTypeEnum.getEnum());
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 参数是否正常
        AssertUtil.notTrue(args.length < 2, BaseUtil.getLangMsg("paramFailureMsg"));
        SqlConvertUtil.convert(args[1]);
    }

}
