package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
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
import cn.handyplus.particle.util.ParticleColorUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
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
        HandyTab trails = set.when(ParticleCategoryTypeEnum.TRAIL.name()).next(context -> ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.TRAIL));
        trails.next(TrailModeTypeEnum.getAll());
        trails.when(context -> Arrays.asList(ParticleTypeEnum.TRAIL_COLOR.name(),
                ParticleTypeEnum.TRAIL_COLOR.name() + "(" + ParticleTypeEnum.TRAIL_COLOR.getDesc() + ")"))
                .next(TrailModeTypeEnum.getAll()).next("#FFFFFF");
        set.when(ParticleCategoryTypeEnum.WING.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.WING));
        set.when(ParticleCategoryTypeEnum.BLOCK.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.BLOCK));
        set.when(ParticleCategoryTypeEnum.RAIN.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.RAIN));
        set.when(ParticleCategoryTypeEnum.CONFETTI.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.CONFETTI));
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
        String option = this.getArg(args, 4).orElse(null);
        Color color = null;
        TrailModeTypeEnum mode = null;
        if (categoryType == ParticleCategoryTypeEnum.TRAIL) {
            mode = option == null ? TrailModeTypeEnum.DEFAULT : TrailModeTypeEnum.get(option);
            AssertUtil.notNull(mode, usage);
            if (particleType.supportsColor()) {
                color = ParticleColorUtil.parse(this.getArg(args, 5).orElse(null));
            } else {
                AssertUtil.notTrue(this.getArg(args, 5).isPresent(), usage);
            }
        } else {
            AssertUtil.notTrue(option != null, usage);
            AssertUtil.notTrue(this.getArg(args, 5).isPresent(), usage);
        }
        AssertUtil.notTrue(this.getArg(args, 6).isPresent(), usage);
        HandyParticleApi.enable(entity, particleType, mode, color);
        save(entity, categoryType, particleType, mode, color);
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
    private void save(@NotNull Entity entity, @NotNull ParticleCategoryTypeEnum categoryType, @NotNull ParticleTypeEnum particleType, TrailModeTypeEnum mode, Color color) {
        UUID entityUuid = entity.getUniqueId();
        EntityParticleEntity saved = EntityParticleEntity.builder()
                .entityUuid(entityUuid)
                .category(categoryType.name())
                .type(particleType.name())
                .color(color == null ? null : ParticleColorUtil.format(color))
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
        Entity entity;
        if (BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_13.getVersionId()) {
            RayTraceResult trace = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 64.0D, 0.5D, target -> target != player);
            entity = trace == null ? null : trace.getHitEntity();
        } else {
            entity = this.findTargetLegacy(player);
        }
        AssertUtil.notNull(entity, BaseUtil.getLangMsg("entityNotFound"));
        return entity;
    }

    /**
     * 使用旧版 Bukkit API 近似检测视线实体。
     *
     * @param player 玩家
     * @return 命中的实体，不存在时返回 null
     */
    private Entity findTargetLegacy(@NotNull Player player) {
        Location eye = player.getEyeLocation();
        Vector direction = eye.getDirection().normalize();
        Entity target = null;
        double nearest = 64.0D;
        for (Entity candidate : player.getNearbyEntities(64.0D, 64.0D, 64.0D)) {
            if (candidate == player || !candidate.isValid()) {
                continue;
            }
            Vector toEntity = candidate.getLocation().add(0.0D, 1.0D, 0.0D).toVector().subtract(eye.toVector());
            double distance = toEntity.dot(direction);
            if (distance < 0.0D || distance > nearest) {
                continue;
            }
            Vector closest = direction.clone().multiply(distance);
            if (toEntity.subtract(closest).lengthSquared() <= 0.5625D) {
                nearest = distance;
                target = candidate;
            }
        }
        return target;
    }

}
