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
import cn.handyplus.particle.constants.ParticleCommandActionEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.service.PlayerParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
    public void tab(HandyTab tab) {
        HandyTab action = tab.next(ParticleCommandActionEnum.getAll());

        HandyTab set = action.when(ParticleCommandActionEnum.SET.getValue()).nextNull().next(ParticleCategoryTypeEnum.getAll());
        set.when(ParticleCategoryTypeEnum.TRAIL.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.TRAIL)).next(TrailModeTypeEnum.getAll());
        set.when(ParticleCategoryTypeEnum.WING.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.WING));
        set.when(ParticleCategoryTypeEnum.BLOCK.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.BLOCK));
        set.when(ParticleCategoryTypeEnum.RAIN.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.RAIN));
        set.when(ParticleCategoryTypeEnum.EVENT.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.EVENT));
        action.when(ParticleCommandActionEnum.CLEAR.getValue()).nextNull().next(ParticleCategoryTypeEnum.getAll());
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        ParticleCommandActionEnum action = ParticleCommandActionEnum.get(this.getArg(args, 1).orElse(null));
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
    private void set(CommandSender sender, String[] args) {
        String usage = BaseUtil.getLangMsg("setUsage");
        String playerName = this.getArg(args, 2, usage);
        // 类别
        String category = this.getArg(args, 3, usage);
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(category);
        // 类型
        String type = this.getArg(args, 4, usage);
        ParticleTypeEnum particleType = ParticleTypeEnum.getEnumThrow(type);
        // 玩家
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));
        // 判断是否匹配
        AssertUtil.notTrue(particleType.getCategory() != categoryType, usage);
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(this.getArg(args, 5).orElse(TrailModeTypeEnum.DEFAULT.name()));
        HandyParticleApi.enable(player, particleType, mode);
        this.save(player, particleType, mode);
        // 发消息
        MessageUtil.sendMessage(sender, BaseUtil.getLangMsg("setSuccess", MapUtil.of("player", player.getName(), "type", particleType.getDesc())));
        if (sender != player) {
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("setTargetNotice", MapUtil.of("type", particleType.getDesc())));
        }
    }

    /**
     * 清除玩家粒子效果。
     *
     * @param sender 执行者
     * @param args   命令参数
     */
    private void clear(CommandSender sender, String[] args) {
        String usage = BaseUtil.getLangMsg("clearUsage");
        String playerName = this.getArg(args, 2, usage);
        Player player = BaseUtil.getOnlinePlayer(playerName).orElse(null);
        AssertUtil.notNull(player, BaseUtil.getLangMsg("playerOffline"));
        String categoryName = this.getArg(args, 3).orElse(null);
        if (categoryName == null) {
            HandyParticleApi.disable(player);
            PlayerParticleService.delete(player.getUniqueId());
            sendClearMessage(sender, player,
                    BaseUtil.getLangMsg("clearSuccess", MapUtil.of("player", player.getName())),
                    BaseUtil.getLangMsg("clearTargetNotice"));
            return;
        }
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(categoryName);
        HandyParticleApi.disable(player, categoryType);
        PlayerParticleService.delete(player.getUniqueId(), categoryType);
        sendClearMessage(sender, player,
                BaseUtil.getLangMsg("clearCategorySuccess", MapUtil.of("player", player.getName(), "category", categoryType.getDesc())),
                BaseUtil.getLangMsg("clearCategoryTargetNotice", MapUtil.of("category", categoryType.getDesc())));
    }

    /**
     * 保存玩家的分类粒子效果。
     *
     * @param player       玩家
     * @param particleType 效果类型
     * @param mode         运动模式
     */
    private void save(@NotNull Player player, @NotNull ParticleTypeEnum particleType, @Nullable TrailModeTypeEnum mode) {
        Optional<PlayerParticleEntity> existing = PlayerParticleService.findByUuidAndCategory(player.getUniqueId(), particleType.getCategory().name());
        if (existing.isPresent()) {
            PlayerParticleEntity saved = existing.get();
            saved.setPlayerName(player.getName());
            saved.setType(particleType.name());
            saved.setMode(mode == null ? null : mode.name());
            PlayerParticleService.update(saved);
            return;
        }
        PlayerParticleEntity entity = PlayerParticleEntity.builder()
                .playerName(player.getName())
                .playerUuid(player.getUniqueId())
                .category(particleType.getCategory().name())
                .type(particleType.name())
                .mode(mode == null ? null : mode.name())
                .build();
        PlayerParticleService.add(entity);
    }

    /**
     * 向执行者和目标玩家发送清理结果。
     *
     * @param sender        执行者
     * @param player        目标玩家
     * @param senderMessage 执行者消息
     * @param targetMessage 目标玩家消息
     */
    private void sendClearMessage(CommandSender sender, Player player, String senderMessage, String targetMessage) {
        MessageUtil.sendMessage(sender, senderMessage);
        if (sender != player) {
            MessageUtil.sendMessage(player, targetMessage);
        }
    }

}
