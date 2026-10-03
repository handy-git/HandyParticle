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
 * 玩家保存的粒子效果。
 *
 * @author handy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "handy_particle_player", comment = "玩家粒子设置")
public class PlayerParticleEntity {

    @TableField(value = "id", comment = "自增主键")
    private Integer id;

    @TableField(value = "player_name", comment = "玩家名称")
    private String playerName;

    @TableField(value = "player_uuid", comment = "玩家 UUID", length = 36, notNull = true, indexEnum = IndexEnum.UNIQUE, uniqueGroup = "player_category")
    private UUID playerUuid;

    @TableField(value = "category", comment = "效果类别", notNull = true, indexEnum = IndexEnum.UNIQUE, uniqueGroup = "player_category")
    private String category;

    @TableField(value = "type", comment = "类型名称", notNull = true)
    private String type;

    @TableField(value = "color", comment = "粒子颜色")
    private String color;

    @TableField(value = "mode", comment = "运动模式")
    private String mode;

    /**
     * 生效范围，英文逗号分隔，为空表示全部生效。
     *
     * <p>抛射物类别存抛射物类型名，武器类别存武器分组名，两者共用此列。
     */
    @TableField(value = "scopes", comment = "生效范围", length = 255)
    private String scopes;
}
