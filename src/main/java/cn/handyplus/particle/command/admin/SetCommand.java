package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Optional;

/**
 * 为在线玩家设置粒子效果。
 *
 * @author handy
 */
public final class SetCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "set";
    }

    @Override
    public String permission() {
        return "handyParticle.set";
    }

    @Override
    public void tab(HandyTab tab) {
        HandyTab trailTab = tab.nextNull().next(context -> ParticleCategoryTypeEnum.getAll());

        trailTab.when(ParticleCategoryTypeEnum.TRAIL.name())
                .next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.TRAIL))
                .next(TrailModeTypeEnum.getAll());

        trailTab.when(ParticleCategoryTypeEnum.WING.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.WING));
        trailTab.when(ParticleCategoryTypeEnum.BLOCK.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.BLOCK));
        trailTab.when(ParticleCategoryTypeEnum.RAIN.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.RAIN));
        trailTab.when(ParticleCategoryTypeEnum.EVENT.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.EVENT));
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 参数是否正常
        AssertUtil.notTrue(args.length < 4, BaseUtil.getLangMsg("setUsage"));
        // 先校验命令结构，再解析类别和完整效果枚举名。
        String playerName = args[1];
        String effectName = args[3];
        String modeName = this.getArg(args, 4).orElse(TrailModeTypeEnum.DEFAULT.name());

        // 目标玩家必须在线，Core 才能创建对应的粒子任务。
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));

        // 类型
        String effect = effectName.split("\\(", 2)[0].toUpperCase(Locale.ROOT);
        ParticleTypeEnum type = ParticleTypeEnum.valueOf(effect);
        ParticleCategoryTypeEnum category = ParticleCategoryTypeEnum.valueOf(args[2].toUpperCase(Locale.ROOT));
        AssertUtil.notTrue(type.getCategory() != category, BaseUtil.getLangMsg("setUsage"));
        // 运动模式
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(modeName);
        // 开启粒子
        HandyParticleApi.enable(player, type, mode);
        // 效果启用成功后持久化
        this.save(player, type, mode);
        // 通知执行者和目标玩家。
        String description = type.getDesc();
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", description)));
        if (sender != player) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("setTargetNotice", MapUtil.of("type", description)));
        }
    }

    /**
     * 保存命令设置的效果类型，同一类别只保留一条记录。
     *
     * @param player   玩家
     * @param type     效果类型
     * @param modeName 运动模式
     */
    private void save(@NotNull Player player, @NotNull ParticleTypeEnum type, @Nullable TrailModeTypeEnum modeName) {
        Optional<PlayerParticleEntity> existing = PlayerParticleService.findByUuidAndCategory(player.getUniqueId(), type.getCategory().name());
        if (existing.isPresent()) {
            PlayerParticleEntity entity = existing.get();
            entity.setPlayerName(player.getName());
            entity.setType(type.name());
            entity.setMode(modeName != null ? modeName.name() : null);
            PlayerParticleService.update(entity);
            return;
        }
        PlayerParticleService.add(PlayerParticleEntity.builder()
                .playerName(player.getName())
                .playerUuid(player.getUniqueId())
                .category(type.getCategory().name())
                .type(type.name())
                .mode(modeName != null ? modeName.name() : null)
                .build());
    }

}
