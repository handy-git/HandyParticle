package cn.handyplus.particle;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public final class HandyParticle extends JavaPlugin {

    @Override
    public void onEnable() {
        InitApi initApi = InitApi.getInstance(this);
        ConfigUtil.reload();

        initApi.addMetrics(34366)
                .initCommand("cn.handyplus.particle.command")
                .initListener("cn.handyplus.particle.listener")
                .enableSql("cn.handyplus.particle.entity")
                .checkVersion();

        // 先恢复已加载区块，其他记录保留到实体加载时恢复。
        EntityParticleRestoreUtil.load();
        EntityParticleRestoreUtil.restoreLoadedChunks();

        String[] logo = {
                " _   _                 _       ____            _   _      _",
                "| | | | __ _ _ __   __| |_   _|  _ \\ __ _ _ __| |_(_) ___| | ___",
                "| |_| |/ _` | '_ \\ / _` | | | | |_) / _` | '__| __| |/ __| |/ _ \\",
                "|  _  | (_| | | | | (_| | |_| |  __/ (_| | |  | |_| | (__| |  __/",
                "|_| |_|\\__,_|_| |_|\\__,_|\\__, |_|   \\__,_|_|   \\__|_|\\___|_|\\___|",
                "                         |___/"
        };
        for (String line : logo) {
            MessageUtil.sendConsoleMessage("&3" + line);
        }
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
