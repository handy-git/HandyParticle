package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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
        tab.nextNull().next(context -> typeSuggestions());
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playerName = this.getArg(args, 1).orElse(null);
        String typeName = this.getArg(args, 2).orElse(null);
        AssertUtil.notEmpty(playerName, BaseUtil.getLangMsg("setUsage"));
        AssertUtil.notEmpty(typeName, BaseUtil.getLangMsg("setUsage"));
        AssertUtil.isTrue(args.length == 3, BaseUtil.getLangMsg("setUsage"));

        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));

        ParticleTypeEnum type;
        try {
            String name = typeName.split("\\(", 2)[0].toUpperCase(Locale.ROOT);
            type = ParticleTypeEnum.valueOf(name);
            boolean enabled = HandyParticleApi.enable(player, type);
            AssertUtil.isTrue(enabled, BaseUtil.getLangMsg("playerOffline"));
        } catch (IllegalArgumentException exception) {
            MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("invalidParticleType"));
            return;
        }
        this.save(player, type);
        String description = type.getDesc();
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", description)));
        if (sender != player) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("setTargetNotice", MapUtil.of("type", description)));
        }
    }

    /**
     * 保存命令设置的效果类型，已有设置先删除再新增。
     *
     * @param player 玩家
     * @param type   效果类型
     */
    private void save(@NotNull Player player, @NotNull ParticleTypeEnum type) {
        Optional<PlayerParticleEntity> existing = PlayerParticleService.findByUuid(player.getUniqueId());
        if (existing.isPresent()) {
            PlayerParticleService.delete(player.getUniqueId());
        }
        PlayerParticleService.add(PlayerParticleEntity.builder()
                .playerUuid(player.getUniqueId())
                .particleType(type.name())
                .build());
    }

    /**
     * 列出当前可启用的翅膀与内置粒子类型。
     *
     * @return 可补全的效果类型名称
     */
    private List<String> typeSuggestions() {
        List<String> result = new ArrayList<>();
        boolean chinese = "zh_CN".equalsIgnoreCase(BaseConstants.CONFIG.getString("language", "zh_CN"));
        for (ParticleTypeEnum type : ParticleTypeEnum.values()) {
            result.add(chinese ? type.name() + "(" + type.getDesc() + ")" : type.name());
        }
        return result;
    }

}
