package cn.handyplus.particle;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.core.projectile.ProjectileParticleManager;
import cn.handyplus.particle.core.weapon.WeaponParticleManager;
import cn.handyplus.particle.listener.PaperEntityParticleRestoreListener;
import cn.handyplus.particle.util.BackUpUtil;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

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

        // 过滤不存在的监听
        List<String> ignoreList = HandySchedulerUtil.isPaper() ? null : Collections.singletonList(PaperEntityParticleRestoreListener.class.getName());
        initApi.addMetrics(34366)
                .initCommand("cn.handyplus.particle.command")
                .initListener("cn.handyplus.particle.listener", ignoreList)
                .initClickEvent("cn.handyplus.particle.listener.gui")
                .enablePlayerInput()
                .enableSql("cn.handyplus.particle.entity")
                .checkVersion();

        // 实体所在区块未加载时取不到实体，缓存记录后等区块加载再恢复
        EntityParticleRestoreUtil.load();

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

        // 开始备份
        BackUpUtil.init();

        MessageUtil.sendConsoleMessage("&a已成功载入服务器!");
        MessageUtil.sendConsoleMessage("&aAuthor:handy WIKI: https://ricedoc.handyplus.cn/wiki/HandyParticle/README/");
    }

    /**
     * 停止全部粒子任务并释放 HandyLib 资源。
     */
    @Override
    public void onDisable() {
        HandyParticleApi.disableAll();
        ProjectileParticleManager.clear();
        WeaponParticleManager.clear();
        InitApi.disable();
    }

}
