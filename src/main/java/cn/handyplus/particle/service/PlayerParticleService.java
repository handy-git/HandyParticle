package cn.handyplus.particle.service;

import cn.handyplus.lib.db.Db;
import cn.handyplus.particle.entity.PlayerParticleEntity;
import org.jetbrains.annotations.NotNull;

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
     * 查询玩家粒子设置。
     *
     * @param playerUuid 玩家 UUID
     * @return 玩家粒子设置
     */
    public static Optional<PlayerParticleEntity> findByUuid(@NotNull UUID playerUuid) {
        Db<PlayerParticleEntity> db = Db.use(PlayerParticleEntity.class);
        db.where().eq(PlayerParticleEntity::getPlayerUuid, playerUuid);
        return db.execution().selectOne();
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

}
