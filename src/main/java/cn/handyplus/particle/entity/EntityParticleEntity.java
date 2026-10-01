package cn.handyplus.particle.entity;

import cn.handyplus.lib.annotation.TableField;
import cn.handyplus.lib.annotation.TableName;
import cn.handyplus.lib.db.IndexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * 实体粒子效果设置。
 *
 * @author handy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "handy_particle_entity", comment = "实体粒子设置")
public class EntityParticleEntity {

    @TableField(value = "id", comment = "自增主键")
    private Integer id;

    @TableField(value = "entity_uuid", comment = "实体UID", notNull = true, indexEnum = IndexEnum.UNIQUE, uniqueGroup = "entity_category")
    private UUID entityUuid;

    @TableField(value = "category", comment = "效果类别", notNull = true, indexEnum = IndexEnum.UNIQUE, uniqueGroup = "entity_category")
    private String category;

    @TableField(value = "type", comment = "粒子类型", notNull = true)
    private String type;

    @TableField(value = "color", comment = "粒子颜色")
    private String color;

    @TableField(value = "mode", comment = "运动模式")
    private String mode;
}
