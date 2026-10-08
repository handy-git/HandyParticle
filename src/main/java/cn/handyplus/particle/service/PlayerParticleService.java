package cn.handyplus.particle.service;

import cn.handyplus.lib.db.Db;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import cn.handyplus.particle.util.ParticleColorUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 玩家粒子设置数据访问。
 *
 * @author handy
 */
public final class PlayerParticleService {

    /**
     * 禁止实例化服务。
     */
    private PlayerParticleService() {
    }

    /**
     * 新增玩家粒子设置。
     *
     * @param entity 玩家粒子设置
     * @return 新增记录的主键
     */
    public static int add(@NotNull PlayerParticleEntity entity) {
        return Db.use(PlayerParticleEntity.class).execution().insert(entity);
    }

    /**
     * 查询玩家全部粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @return 玩家粒子设置列表
     */
    public static List<PlayerParticleEntity> findByUuid(@NotNull UUID playerUuid) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid);
        return db.execution().list();
    }

    /**
     * 查询玩家指定类别的粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @param category   粒子类别名称
     * @return 对应的粒子设置
     */
    public static Optional<PlayerParticleEntity> findByUuidAndCategory(@NotNull UUID playerUuid, @NotNull String category) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid)
                .eq(PlayerParticleEntity::getCategory, category);
        return db.execution().selectOne();
    }

    /**
     * 更新已有的玩家粒子设置。
     *
     * @param entity 玩家粒子设置
     */
    public static void update(@NotNull PlayerParticleEntity entity) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.update().set(PlayerParticleEntity::getPlayerName, entity.getPlayerName())
                .set(PlayerParticleEntity::getType, entity.getType())
                .set(PlayerParticleEntity::getColor, entity.getColor())
                .set(PlayerParticleEntity::getMode, entity.getMode())
                .set(PlayerParticleEntity::getScopes, entity.getScopes());
        db.execution().updateById(entity.getId());
    }

    /**
     * 单独更新玩家指定类别的生效范围。
     *
     * @param playerUuid 玩家 UUID
     * @param category   粒子类别
     * @param scopes     逗号分隔的生效范围，为空表示全部
     */
    public static void updateScopes(@NotNull UUID playerUuid, @NotNull ParticleCategoryTypeEnum category, @Nullable String scopes) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid)
                .eq(PlayerParticleEntity::getCategory, category.name());
        db.update().set(PlayerParticleEntity::getScopes, scopes);
        db.execution().update();
    }

    /**
     * 删除玩家粒子设置。
     *
     * @param playerUuid 玩家 UUID
     */
    public static void delete(@NotNull UUID playerUuid) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid);
        db.execution().delete();
    }

    /**
     * 删除玩家指定类别的粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @param category   粒子类别名称
     */
    public static void delete(@NotNull UUID playerUuid, @NotNull String category) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid)
                .eq(PlayerParticleEntity::getCategory, category);
        db.execution().delete();
    }

    /**
     * 保存玩家指定类别的粒子设置，已存在时覆盖。
     *
     * @param player       玩家
     * @param particleType 效果类型
     * @param mode         运动模式，非拖尾类别为空
     * @param color        粒子颜色
     */
    public static void save(@NotNull Player player, @NotNull ParticleTypeEnum particleType, @Nullable TrailModeTypeEnum mode, @Nullable Color color) {
        save(player, particleType, mode, color, null);
    }

    /**
     * 保存玩家指定类别的粒子设置，已存在时覆盖。
     *
     * @param player       玩家
     * @param particleType 效果类型
     * @param mode         运动模式，非拖尾类别为空
     * @param color        粒子颜色
     * @param scopes       生效范围，逗号分隔，为空表示全部
     */
    public static void save(@NotNull Player player, @NotNull ParticleTypeEnum particleType, @Nullable TrailModeTypeEnum mode,
                            @Nullable Color color, @Nullable String scopes) {
        String category = particleType.getCategory().name();
        Optional<PlayerParticleEntity> existing = findByUuidAndCategory(player.getUniqueId(), category);
        if (existing.isPresent()) {
            PlayerParticleEntity saved = existing.get();
            saved.setPlayerName(player.getName());
            saved.setType(particleType.name());
            saved.setColor(color == null ? null : ParticleColorUtil.format(color));
            saved.setMode(mode == null ? null : mode.name());
            saved.setScopes(scopes);
            update(saved);
            return;
        }
        add(PlayerParticleEntity.builder()
                .playerName(player.getName())
                .playerUuid(player.getUniqueId())
                .category(category)
                .type(particleType.name())
                .color(color == null ? null : ParticleColorUtil.format(color))
                .mode(mode == null ? null : mode.name())
                .scopes(scopes)
                .build());
    }

    /**
     * 保存玩家翅膀设置，已存在时覆盖。
     *
     * @param player 玩家
     * @param model  翅膀外形
     * @param color  颜色图案
     */
    public static void saveWing(@NotNull Player player, @NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        Optional<PlayerParticleEntity> existing = findByUuidAndCategory(player.getUniqueId(), ParticleCategoryTypeEnum.WING.name());
        if (existing.isPresent()) {
            PlayerParticleEntity saved = existing.get();
            saved.setPlayerName(player.getName());
            saved.setType(model.name());
            saved.setColor(color.name());
            saved.setMode(null);
            update(saved);
            return;
        }
        add(PlayerParticleEntity.builder()
                .playerName(player.getName())
                .playerUuid(player.getUniqueId())
                .category(ParticleCategoryTypeEnum.WING.name())
                .type(model.name())
                .color(color.name())
                .build());
    }

    /**
     * 删除玩家指定类别的粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @param category   粒子类别
     */
    public static void delete(@NotNull UUID playerUuid, @NotNull ParticleCategoryTypeEnum category) {
        delete(playerUuid, category.name());
    }

}
