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
import cn.handyplus.particle.entity.EntityParticleEntity;
import cn.handyplus.particle.service.EntityParticleService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

/**
 * 管理视线中的 Bukkit 实体粒子效果。
 *
 * @author handy
 */
public final class EntityCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "entity";
    }

    @Override
    public String permission() {
        return "handyParticle.entity";
    }

    @Override
    public void tab(HandyTab tab) {
        HandyTab action = tab.next(ParticleCommandActionEnum.getAll());

        HandyTab set = action.when(ParticleCommandActionEnum.SET.getValue()).next(ParticleCategoryTypeEnum.getAll());
        set.when(ParticleCategoryTypeEnum.TRAIL.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.TRAIL)).next(TrailModeTypeEnum.getAll());
        set.when(ParticleCategoryTypeEnum.WING.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.WING));
        set.when(ParticleCategoryTypeEnum.BLOCK.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.BLOCK));
        set.when(ParticleCategoryTypeEnum.RAIN.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.RAIN));
        set.when(ParticleCategoryTypeEnum.EVENT.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.EVENT));
        action.when(ParticleCommandActionEnum.CLEAR.getValue()).next(ParticleCategoryTypeEnum.getAll());
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = AssertUtil.notPlayer(sender, BaseUtil.getLangMsg("entityPlayerOnly"));
        ParticleCommandActionEnum action = ParticleCommandActionEnum.get(this.getArg(args, 1).orElse(null));
        if (action == ParticleCommandActionEnum.SET) {
            this.set(player, args);
            return;
        }
        if (action == ParticleCommandActionEnum.CLEAR) {
            this.clear(player, args);
            return;
        }
        throw new RuntimeException(BaseUtil.getLangMsg("entityUsage"));
    }

    /**
     * 设置视线实体粒子效果。
     */
    private void set(Player player, String[] args) {
        Entity entity = this.getTarget(player);
        String usage = BaseUtil.getLangMsg("entitySetUsage");
        // 粒子类别
        String category = this.getArg(args, 2, usage);
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(category);
        // 粒子类型
        String type = this.getArg(args, 3, usage);
        ParticleTypeEnum particleType = ParticleTypeEnum.getEnumThrow(type);
        // 判断类别是否匹配
        AssertUtil.notTrue(particleType.getCategory() != categoryType, usage);
        // 运动模式
        TrailModeTypeEnum mode = TrailModeTypeEnum.get(this.getArg(args, 4).orElse(TrailModeTypeEnum.DEFAULT.name()));
        HandyParticleApi.enable(entity, particleType, mode);
        save(entity, categoryType, particleType, mode);
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("entitySetSuccess", MapUtil.of("type", particleType.getDesc())));
    }

    /**
     * 清除视线实体粒子效果。
     */
    private void clear(Player player, String[] args) {
        Entity entity = this.getTarget(player);
        UUID entityUuid = entity.getUniqueId();
        String category = this.getArg(args, 2).orElse(null);
        if (category == null) {
            HandyParticleApi.disable(entity);
            EntityParticleService.delete(entityUuid);
            MessageUtil.sendMessage(player, BaseUtil.getLangMsg("entityClearSuccess"));
            return;
        }
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(category);
        HandyParticleApi.disable(entity, categoryType);
        EntityParticleService.delete(entityUuid, categoryType.name());
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("entityClearCategorySuccess", MapUtil.of("category", categoryType.getDesc())));
    }

    /**
     * 保存实体的分类粒子效果。
     */
    private void save(@NotNull Entity entity, @NotNull ParticleCategoryTypeEnum categoryType, @NotNull ParticleTypeEnum particleType, TrailModeTypeEnum mode) {
        UUID entityUuid = entity.getUniqueId();
        EntityParticleEntity saved = EntityParticleEntity.builder()
                .entityUuid(entityUuid)
                .category(categoryType.name())
                .type(particleType.name())
                .mode(mode == null ? null : mode.name())
                .build();
        Optional<EntityParticleEntity> existing = EntityParticleService.find(entityUuid, categoryType.name());
        if (existing.isPresent()) {
            saved.setId(existing.get().getId());
            EntityParticleService.update(saved);
            return;
        }
        EntityParticleService.add(saved);
    }

    /**
     * 获取玩家视线中的实体。
     *
     * @param player 玩家
     * @return 实体
     */
    @NotNull
    private Entity getTarget(@NotNull Player player) {
        RayTraceResult trace = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 64.0D, 0.5D, entity -> entity != player);
        Entity entity = trace == null ? null : trace.getHitEntity();
        AssertUtil.notNull(entity, BaseUtil.getLangMsg("entityNotFound"));
        return entity;
    }

}
