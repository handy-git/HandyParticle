package cn.handyplus.particle.service;

import cn.handyplus.lib.db.Db;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import org.jetbrains.annotations.NotNull;

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
                .set(PlayerParticleEntity::getMode, entity.getMode());
        db.execution().updateById(entity.getId());
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
     * 删除玩家指定类别的粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @param category   粒子类别
     */
    public static void delete(@NotNull UUID playerUuid, @NotNull ParticleCategoryTypeEnum category) {
        delete(playerUuid, category.name());
    }

}
