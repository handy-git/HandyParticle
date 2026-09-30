package cn.handyplus.particle;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleType;
import cn.handyplus.particle.entity.EntityParticleEntity;
import cn.handyplus.particle.service.EntityParticleService;
import cn.handyplus.particle.util.ConfigUtil;
import cn.handyplus.particle.util.ParticleRestoreUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
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

        // 恢复当前已加载实体的粒子效果
        this.restoreEntityParticles();

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

    /**
     * 恢复当前已加载实体的粒子效果。
     *
     * <p>数据库只保存实体 UUID；仅恢复当前服务器仍能解析到的实体。
     * 无法解析的类别或效果视为历史脏数据，直接清理该记录并输出控制台提示。
     */
    private void restoreEntityParticles() {
        for (EntityParticleEntity saved : EntityParticleService.findAll()) {
            if (saved.getEntityUuid() == null) {
                continue;
            }
            Entity entity = Bukkit.getEntity(saved.getEntityUuid());
            if (entity == null || !entity.isValid()) {
                continue;
            }
            ParticleType particleType = ParticleRestoreUtil.parse("实体 " + saved.getEntityUuid(), saved.getCategory(),
                    saved.getType(), saved.getColor(), saved.getMode());
            if (particleType == null) {
                // 不清理的话每次启动都会重复尝试同一条无效记录
                EntityParticleService.delete(saved.getEntityUuid(), saved.getCategory());
                continue;
            }
            HandyParticleApi.enable(entity, particleType);
        }
    }

}
