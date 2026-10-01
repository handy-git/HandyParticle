package cn.handyplus.particle.service;

import cn.handyplus.lib.db.Db;
import cn.handyplus.particle.entity.EntityParticleEntity;
import cn.handyplus.particle.util.EntityParticleRestoreUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 实体粒子设置数据访问。
 *
 * @author handy
 */
public final class EntityParticleService {

    private EntityParticleService() {
    }

    /**
     * 查询实体指定类别的粒子设置。
     *
     * @param entityUuid 实体ID
     * @param category   粒子类别
     * @return 粒子设置
     */
    public static Optional<EntityParticleEntity> find(@NotNull UUID entityUuid, @NotNull String category) {
        Db<EntityParticleEntity> db = Db.use(EntityParticleEntity.class);
        db.where().eq(EntityParticleEntity::getEntityUuid, entityUuid)
                .eq(EntityParticleEntity::getCategory, category);
        return db.execution().selectOne();
    }

    /**
     * 查询全部实体粒子设置。
     *
     * @return 实体粒子设置
     */
    public static List<EntityParticleEntity> findAll() {
        return Db.use(EntityParticleEntity.class).execution().list();
    }

    /**
     * 新增实体粒子设置。
     *
     * @param entity 粒子设置
     * @return 主键
     */
    public static int add(@NotNull EntityParticleEntity entity) {
        int id = Db.use(EntityParticleEntity.class).execution().insert(entity);
        if (id > 0) {
            EntityParticleRestoreUtil.cache(entity);
        }
        return id;
    }

    /**
     * 更新实体粒子设置。
     *
     * @param entity 粒子设置
     */
    public static void update(@NotNull EntityParticleEntity entity) {
        Db<EntityParticleEntity> db = Db.use(EntityParticleEntity.class);
        db.update().set(EntityParticleEntity::getType, entity.getType())
                .set(EntityParticleEntity::getColor, entity.getColor())
                .set(EntityParticleEntity::getMode, entity.getMode());
        if (db.execution().updateById(entity.getId()) > 0) {
            EntityParticleRestoreUtil.cache(entity);
        }
    }

    /**
     * 删除实体的全部粒子设置。
     *
     * @param entityUuid 实体UID
     */
    public static void delete(@NotNull UUID entityUuid) {
        Db<EntityParticleEntity> db = Db.use(EntityParticleEntity.class);
        db.where().eq(EntityParticleEntity::getEntityUuid, entityUuid);
        if (db.execution().delete() > 0) {
            EntityParticleRestoreUtil.remove(entityUuid, null);
        }
    }

    /**
     * 删除实体指定类别的粒子设置。
     *
     * @param entityUuid 实体UID
     * @param category   粒子类别
     */
    public static void delete(@NotNull UUID entityUuid, @NotNull String category) {
        Db<EntityParticleEntity> db = Db.use(EntityParticleEntity.class);
        db.where().eq(EntityParticleEntity::getEntityUuid, entityUuid)
                .eq(EntityParticleEntity::getCategory, category);
        if (db.execution().delete() > 0) {
            EntityParticleRestoreUtil.remove(entityUuid, category);
        }
    }

}
