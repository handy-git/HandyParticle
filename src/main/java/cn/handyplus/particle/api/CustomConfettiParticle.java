package cn.handyplus.particle.api;

import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 使用自定义物品列表的纸屑效果。
 *
 * @author handy
 */
public final class CustomConfettiParticle implements ParticleType {
    /**
     * 自定义纸屑物品。
     */
    private final ItemStack[] items;

    /**
     * 创建自定义纸屑配置，复制每个物品以固定配置内容。
     *
     * @param items 至少一个非空物品
     */
    public CustomConfettiParticle(@NonNull ItemStack... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("items must not be empty");
        }
        this.items = new ItemStack[items.length];
        for (int index = 0; index < items.length; index++) {
            this.items[index] = Objects.requireNonNull(items[index], "item").clone();
        }
    }

    /**
     * 获取当前效果所属的粒子类别。
     *
     * @return 粒子类别
     */
    @Override
    @NotNull
    public ParticleCategoryTypeEnum getCategory() {
        return ParticleCategoryTypeEnum.CONFETTI;
    }

    /**
     * 启用自定义纸屑。
     *
     * @param entity 目标实体
     */
    @Override
    public void enable(@NotNull Entity entity) {
        ItemStack[] copies = new ItemStack[this.items.length];
        for (int index = 0; index < this.items.length; index++) {
            copies[index] = this.items[index].clone();
        }
        HandyParticleCore.setConfetti(entity, copies);
    }
}
