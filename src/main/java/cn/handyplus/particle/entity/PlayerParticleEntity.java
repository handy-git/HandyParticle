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
@TableName(value = "player_particle", comment = "玩家粒子设置")
public class PlayerParticleEntity {

    @TableField(value = "id", comment = "自增主键")
    private Integer id;

    @TableField(value = "player_uuid", comment = "玩家 UUID", length = 36, notNull = true, indexEnum = IndexEnum.UNIQUE)
    private UUID playerUuid;

    @TableField(value = "particle_type", comment = "翅膀组合或轨迹算法名称", notNull = true)
    private String particleType;

    @TableField(value = "particle_name", comment = "轨迹使用的 XParticle 名称，翅膀时为空")
    private String particleName;
}
