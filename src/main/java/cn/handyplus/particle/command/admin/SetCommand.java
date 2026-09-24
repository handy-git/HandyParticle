package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        tab.nextNull()
                .next(Arrays.stream(ParticleTypeEnum.values()).map(Enum::name).collect(Collectors.toList()))
                .next(context -> particleSuggestions(context.getArgs()));
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        String playerName = this.getArg(args, 1).orElse(null);
        String typeName = this.getArg(args, 2).orElse(null);
        AssertUtil.notEmpty(playerName, BaseUtil.getLangMsg("setUsage"));
        AssertUtil.notEmpty(typeName, BaseUtil.getLangMsg("setUsage"));

        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));

        ParticleTypeEnum type = ParticleTypeEnum.getEnumThrow(typeName);

        String particleName = this.getArg(args, 3).orElse(null);
        if (type.isTrail()) {
            AssertUtil.notEmpty(particleName, BaseUtil.getLangMsg("particleRequired"));
        }

        try {
            boolean enabled = type.isTrail() ? HandyParticleApi.enable(player, type, particleName) : HandyParticleApi.enable(player, type);
            AssertUtil.isTrue(enabled, BaseUtil.getLangMsg("playerOffline"));
        } catch (IllegalArgumentException exception) {
            MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("invalidParticleName"));
            return;
        }
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", type.name())));
    }

    /**
     * 仅为轨迹提示当前服务端可直接发射的粒子。
     *
     * @param args 当前命令参数
     * @return 可补全的粒子名称
     */
    private List<String> particleSuggestions(String[] args) {
        String typeName = this.getArg(args, 2).orElse(null);
        if (typeName == null) {
            return Collections.emptyList();
        }
        try {
            if (!ParticleTypeEnum.getEnumThrow(typeName).isTrail()) {
                return Collections.emptyList();
            }
        } catch (RuntimeException exception) {
            return Collections.emptyList();
        }
        return Arrays.stream(XParticle.values())
                .filter(xParticle -> {
                    Particle particle = xParticle.get();
                    return particle != null && (xParticle == XParticle.DUST || particle.getDataType() == Void.class);
                })
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
