package cn.handyplus.particle.constants;

import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置命令支持的粒子效果类别。
 *
 * @author handy
 */
public enum ParticleCategoryTypeEnum {
    /**
     * 普通粒子拖尾。
     */
    TRAIL,
    /**
     * 翅膀效果。
     */
    WING,
    /**
     * 方块效果。
     */
    BLOCK,
    /**
     * 物品雨效果。
     */
    RAIN,
    /**
     * 纸屑效果。
     */
    CONFETTI,
    /**
     * 鞘翅拖尾效果，仅在玩家滑翔时生成。
     */
    ELYTRA,
    /**
     * 抛射物效果，仅对自己发射的抛射物生效。
     */
    PROJECTILE,
    /**
     * 武器攻击效果，仅近战伤害触发。
     */
    WEAPON;

    /**
     * 获取当前类别的本地化描述。
     *
     * @return 类别描述
     */
    public String getDesc() {
        return BaseUtil.getLangMsg("particleCategoryDesc." + this.name());
    }

    /**
     * 按名称查找粒子类别。
     *
     * <p>中文环境的 tab 补全会带上形如 {@code TRAIL(普通)} 的描述后缀，需先截断。
     *
     * @param value 类别名称，可为空
     * @return 粒子类别，未找到时返回 null
     */
    @Nullable
    public static ParticleCategoryTypeEnum getEnum(@Nullable String value) {
        if (value == null) {
            return null;
        }
        String name = ParticleConstants.trimDescription(value);
        for (ParticleCategoryTypeEnum category : values()) {
            if (category.name().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    /**
     * 按名称查找粒子类别。
     *
     * @param value 类别名称
     * @return 粒子类别
     */
    @NotNull
    public static ParticleCategoryTypeEnum getEnumThrow(@NotNull String value) {
        ParticleCategoryTypeEnum category = getEnum(value);
        if (category == null) {
            throw new IllegalArgumentException(BaseUtil.getLangMsg("invalidParticleCategory"));
        }
        return category;
    }

    /**
     * 列出命令支持的效果类别。
     *
     * @return 可补全的效果类别名称，中文环境下附带描述
     */
    @NotNull
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        boolean chinese = ParticleConstants.isChinese();
        for (ParticleCategoryTypeEnum category : values()) {
            result.add(chinese ? category.name() + "(" + category.getDesc() + ")" : category.name());
        }
        return result;
    }

    /**
     * 列出指定类别下的效果名称。
     *
     * @param category 效果类别
     * @return 类别对应的效果名称
     */
    @NotNull
    public static List<String> get(@NotNull ParticleCategoryTypeEnum category) {
        List<String> result = new ArrayList<>();
        boolean chinese = ParticleConstants.isChinese();
        for (ParticleTypeEnum type : ParticleTypeEnum.values()) {
            if (type.getCategory() == category) {
                String name = type.name();
                result.add(chinese ? name + "(" + type.getDesc() + ")" : name);
            }
        }
        return result;
    }

}
