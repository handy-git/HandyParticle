package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.core.MapUtil;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.particle.api.CustomTrailParticle;
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleCommandActionEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
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

    /**
     * 注册实体粒子命令的 Tab 补全。
     *
     * @param tab 命令补全节点
     */
    @Override
    public void tab(HandyTab tab) {
        ParticleCommandHelper.tab(tab, false);
    }

    /**
     * 按动作分发实体粒子命令。
     *
     * @param sender  命令执行者
     * @param command Bukkit 命令
     * @param label   命令别名
     * @param args    命令参数
     */
    @Override
    public void onCommand(CommandSender sender, Command command, String label, String[] args) {
        AssertUtil.notTrue(args.length < 2, BaseUtil.getLangMsg("paramFailureMsg"));
        String actionValue = args[1];
        Player player = AssertUtil.notPlayer(sender, BaseUtil.getLangMsg("entityPlayerOnly"));
        ParticleCommandActionEnum action = ParticleCommandActionEnum.get(actionValue);
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
     *
     * @param player 执行命令的玩家
     * @param args   命令参数
     */
    private void set(Player player, String[] args) {
        AssertUtil.notTrue(args.length < 4, BaseUtil.getLangMsg("paramFailureMsg"));
        String usage = BaseUtil.getLangMsg("entitySetUsage");
        String category = args[2];
        String type = args[3];
        String option = this.getArg(args, 4).orElse(null);
        String colorValue = this.getArg(args, 5).orElse(null);
        boolean hasExtra = this.getArg(args, 6).isPresent();
        ParticleCategoryTypeEnum categoryType = ParticleCategoryTypeEnum.getEnumThrow(category);
        if (categoryType == ParticleCategoryTypeEnum.WING) {
            this.setWing(player, args, usage);
            return;
        }
        ParticleTypeEnum particleType = ParticleTypeEnum.getEnumThrow(type);
        ParticleCommandHelper.EffectOptions options = ParticleCommandHelper.parseEffect(categoryType, particleType, option, colorValue, hasExtra, usage);
        Entity entity = this.getTarget(player);
        HandyParticleApi.enable(entity, categoryType == ParticleCategoryTypeEnum.TRAIL ? new CustomTrailParticle(particleType, options.mode, options.color) : particleType);
        this.save(entity, categoryType, particleType, options.mode, options.color);
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("entitySetSuccess", MapUtil.of("type", particleType.getDesc())));
    }

    /**
     * 设置视线实体翅膀。
     *
     * @param player 玩家
     * @param args   命令参数
     * @param usage  用法提示
     */
    private void setWing(Player player, String[] args, String usage) {
        AssertUtil.notTrue(args.length < 5, BaseUtil.getLangMsg("paramFailureMsg"));
        String modelValue = args[3];
        String colorValue = args[4];
        WingModelEnum model = WingModelEnum.getEnumThrow(modelValue);
        WingColorEnum color = WingColorEnum.getEnumThrow(colorValue);
        Entity entity = this.getTarget(player);
        ParticleTypeEnum particleType = ParticleTypeEnum.wing(model, color);
        HandyParticleApi.enable(entity, particleType);
        this.saveWing(entity, model, color);
        MessageUtil.sendMessage(player, BaseUtil.getLangMsg("entitySetSuccess", MapUtil.of("type", model.name() + "_" + color.name())));
    }

    /**
     * 保存实体翅膀设置。
     *
     * @param entity 实体
     * @param model  翅膀外形
     * @param color  颜色图案
     */
    private void saveWing(@NotNull Entity entity, @NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        UUID entityUuid = entity.getUniqueId();
        EntityParticleEntity saved = EntityParticleEntity.builder()
                .entityUuid(entityUuid)
                .category(ParticleCategoryTypeEnum.WING.name())
                .type(model.name())
                .color(color.name())
                .build();
        Optional<EntityParticleEntity> existing = EntityParticleService.find(entityUuid, ParticleCategoryTypeEnum.WING.name());
        if (existing.isPresent()) {
            saved.setId(existing.get().getId());
            EntityParticleService.update(saved);
            return;
        }
        EntityParticleService.add(saved);
    }

    /**
     * 清除视线实体粒子效果。
     *
     * @param player 执行命令的玩家
     * @param args   命令参数
     */
    private void clear(Player player, String[] args) {
        String category = this.getArg(args, 2).orElse(null);
        Entity entity = this.getTarget(player);
        UUID entityUuid = entity.getUniqueId();
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
     *
     * @param entity       目标实体
     * @param categoryType 粒子类别
     * @param particleType 粒子效果
     * @param mode         运动模式，非拖尾类别为空
     * @param color        自定义粒子颜色，可为空
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
