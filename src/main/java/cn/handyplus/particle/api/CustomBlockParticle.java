package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 使用自定义方块材质列表的方块拖尾效果。
 *
 * @author handy
 */
public final class CustomBlockParticle implements ParticleType {
    /**
     * 自定义方块材质。
     */
    private final Material[] materials;

    /**
     * 创建自定义方块拖尾配置，并复制传入的材质列表。
     *
     * @param materials 至少一个可显示的方块材质
     */
    public CustomBlockParticle(@NonNull Material... materials) {
        if (materials.length == 0) {
            throw new IllegalArgumentException("materials must not be empty");
        }
        for (Material material : materials) {
            Objects.requireNonNull(material, "material");
            if (material == Material.AIR || !material.isBlock()) {
                throw new IllegalArgumentException("material must be a visible block: " + material.name());
            }
        }
        this.materials = materials.clone();
    }

    /**
     * 获取当前效果所属的粒子类别。
     *
     * @return 粒子类别
     */
    @Override
    @NotNull
    public ParticleCategoryTypeEnum getCategory() {
        return ParticleCategoryTypeEnum.BLOCK;
    }

    /**
     * 启用自定义方块拖尾。
     *
     * @param entity 目标实体
     */
    @Override
    public void enable(@NotNull Entity entity) {
        HandyParticleCore.setBlock(entity, this.materials.clone());
    }
}
