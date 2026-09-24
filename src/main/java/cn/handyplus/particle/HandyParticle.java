package cn.handyplus.particle;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public final class HandyParticle extends JavaPlugin {

    /**
     * 启用插件并初始化 HandyLib。
     */
    @Override
    public void onEnable() {
        InitApi initApi = InitApi.getInstance(this);
        ConfigUtil.reload();

        initApi.initCommand("cn.handyplus.particle.command")
                .initListener("cn.handyplus.particle.listener")
                .enableSql("cn.handyplus.particle.entity")
                .checkVersion();

        MessageUtil.sendConsoleMessage("&a已成功载入服务器!");
        MessageUtil.sendConsoleMessage("&aAuthor:handy WIKI: https://ricedoc.handyplus.cn/wiki/HandyParticle/README/");
    }

    /**
     * 停止全部粒子任务并释放 HandyLib 资源。
     */
    @Override
    public void onDisable() {
        HandyParticleApi.disableAll();
        InitApi.disable();
    }

}
