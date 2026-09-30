package cn.handyplus.particle.constants;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
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
    CONFETTI;

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
     * @param value 类别名称
     * @return 粒子类别，未找到时返回 null
     */
    @Nullable
    public static ParticleCategoryTypeEnum getEnum(@NotNull String value) {
        for (ParticleCategoryTypeEnum category : values()) {
            if (category.name().equalsIgnoreCase(value)) {
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
            throw new RuntimeException("not found particleCategoryType: " + value);
        }
        return category;
    }

    /**
     * 列出命令支持的效果类别。
     *
     * @return 可补全的效果类别名称
     */
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (ParticleCategoryTypeEnum category : ParticleCategoryTypeEnum.values()) {
            result.add(category.name());
        }
        return result;
    }

    /**
     * 列出指定类别的效果名称。
     *
     * @param category 效果类别
     * @return 类别对应的效果名称
     */
    public static List<String> get(ParticleCategoryTypeEnum category) {
        List<String> result = new ArrayList<>();
        if (category == WING) {
            return WingModelEnum.getAll();
        }
        boolean chinese = "zh_CN".equalsIgnoreCase(BaseConstants.CONFIG.getString("language", "zh_CN"));
        for (ParticleTypeEnum type : ParticleTypeEnum.values()) {
            if (type.getCategory() == category) {
                String name = type.name();
                result.add(chinese ? name + "(" + type.getDesc() + ")" : name);
            }
        }
        return result;
    }

}
