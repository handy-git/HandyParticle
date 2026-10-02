package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.CustomTrailParticle;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleCommandActionEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 管理在线玩家的粒子效果。
 *
 * @author handy
 */
public final class PlayerCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "player";
    }

    @Override
    public String permission() {
        return "handyParticle.player";
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    /**
     * 注册玩家粒子命令的 Tab 补全。
     *
     * @param tab 命令补全节点
     */
    @Override
    public void tab(HandyTab tab) {
        ParticleCommandHelper.tab(tab, true);
    }

    /**
     * 按动作分发玩家粒子命令。
     *
     * @param sender  命令执行者
     * @param command Bukkit 命令
     * @param label   命令别名
     * @param args    命令参数
     */
    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        AssertUtil.notTrue(args.length < 2, BaseUtil.getLangMsg("paramFailureMsg"));
        String actionValue = args[1];
        ParticleCommandActionEnum action = ParticleCommandActionEnum.get(actionValue);
        if (action == ParticleCommandActionEnum.SET) {
            this.set(sender, args);
            return;
        }
        if (action == ParticleCommandActionEnum.CLEAR) {
            this.clear(sender, args);
            return;
        }
        throw new RuntimeException(BaseUtil.getLangMsg("playerUsage"));
    }

    /**
     * 设置玩家粒子效果。
     *
     * @param sender 执行者
     * @param args   命令参数
     */
    private void set(@NotNull CommandSender sender, @NotNull String[] args) {
        AssertUtil.notTrue(args.length < 5, BaseUtil.getLangMsg("paramFailureMsg"));
        String usage = BaseUtil.getLangMsg("setUsage");
        String playerName = args[2];
        String category = args[3];
        String type = args[4];
        String option = this.getArg(args, 5).orElse(null);
        String colorValue = this.getArg(args, 6).orElse(null);
        boolean hasExtra = this.getArg(args, 7).isPresent();
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(category);
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));
        if (categoryType == ParticleCategoryTypeEnum.WING) {
            this.setWing(sender, player, args);
            return;
        }
        ParticleTypeEnum particleType = ParticleTypeEnum.getEnumThrow(type);
        ParticleCommandHelper.EffectOptions options = ParticleCommandHelper.parseEffect(categoryType, particleType, option, colorValue, hasExtra, usage);
        HandyParticleApi.enable(player, categoryType == ParticleCategoryTypeEnum.TRAIL ? new CustomTrailParticle(particleType, options.mode, options.color) : particleType);
        PlayerParticleService.save(player, particleType, options.mode, options.color);
        // 发消息
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", particleType.getDesc())));
        if (sender != player) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("setTargetNotice", MapUtil.of("type", particleType.getDesc())));
        }
    }

    /**
     * 设置玩家翅膀。
     *
     * @param sender 执行者
     * @param player 目标玩家
     * @param args   命令参数
     */
    private void setWing(@NotNull CommandSender sender, @NotNull Player player, @NotNull String[] args) {
        AssertUtil.notTrue(args.length < 6, BaseUtil.getLangMsg("paramFailureMsg"));
        String usage = BaseUtil.getLangMsg("setWingUsage");
        String modelValue = args[4];
        String colorValue = args[5];
        boolean hasExtra = this.getArg(args, 6).isPresent();
        WingModelEnum model = WingModelEnum.getEnumThrow(modelValue);
        WingColorEnum color = WingColorEnum.getEnumThrow(colorValue);
        AssertUtil.notTrue(hasExtra, usage);
        HandyParticleApi.enable(player, ParticleTypeEnum.wing(model, color));
        PlayerParticleService.saveWing(player, model, color);
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", model.name() + "_" + color.name())));
        if (sender != player) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("setTargetNotice", MapUtil.of("type", model.name() + "_" + color.name())));
        }
    }

    /**
     * 清除玩家粒子效果。
     *
     * @param sender 执行者
     * @param args   命令参数
     */
    private void clear(@NotNull CommandSender sender, @NotNull String[] args) {
        AssertUtil.notTrue(args.length < 3, BaseUtil.getLangMsg("paramFailureMsg"));
        AssertUtil.notTrue(this.getArg(args, 4).isPresent(), BaseUtil.getLangMsg("clearUsage"));
        String playerName = args[2];
        String categoryName = this.getArg(args, 3).orElse(null);
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));
        if (categoryName == null) {
            HandyParticleApi.disable(player);
            PlayerParticleService.delete(player.getUniqueId());
            this.sendClearMessage(sender, player,
                    BaseUtil.getLangMsg("clearSuccess", MapUtil.of("player", player.getName())),
                    BaseUtil.getLangMsg("clearTargetNotice"));
            return;
        }
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(categoryName);
        HandyParticleApi.disable(player, categoryType);
        PlayerParticleService.delete(player.getUniqueId(), categoryType);
        this.sendClearMessage(sender, player,
                BaseUtil.getLangMsg("clearCategorySuccess", MapUtil.of("player", player.getName(), "category", categoryType.getDesc())),
                BaseUtil.getLangMsg("clearCategoryTargetNotice", MapUtil.of("category", categoryType.getDesc())));
    }

    /**
     * 向执行者和目标玩家发送清理结果。
     *
     * @param sender        执行者
     * @param player        目标玩家
     * @param senderMessage 执行者消息
     * @param targetMessage 目标玩家消息
     */
    private void sendClearMessage(@NotNull CommandSender sender, @NotNull Player player, @NotNull String senderMessage, @NotNull String targetMessage) {
        MessageUtil.sendMessage(sender, senderMessage);
        if (sender != player) {
            MessageUtil.sendMessage(player, targetMessage);
        }
    }

}
