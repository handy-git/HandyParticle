package cn.handyplus.particle.core.wing;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.BaseUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 翅膀外形。
 *
 * @author handy
 */
@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor
public enum WingModelEnum {
    /**
     * 天使翅膀。
     */
    ANGEL(WingModelData.ANGEL),
    /**
     * 蝴蝶翅膀。
     */
    BUTTERFLY(WingModelData.BUTTERFLY),
    /**
     * 精灵翅膀。
     */
    ELF(WingModelData.ELF),
    /**
     * 长翼翅膀。
     */
    LONG(WingModelData.LONG),
    /**
     * 蝴蝶二翅膀。
     */
    BUTTERFLY2(WingModelData.BUTTERFLY2),
    /**
     * 短翼翅膀。
     */
    SHORT(WingModelData.SHORT),
    /**
     * 恶魔翅膀。
     */
    DEMON(WingModelData.DEMON),
    /**
     * 暗夜翅膀。
     */
    NIGHT(WingModelData.NIGHT);

    /**
     * 模型点位及粒子类型。
     */
    private final int[][] shape;

    /**
     * 获取模型描述。
     *
     * @return 模型描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("wingModelDesc." + name());
    }

    /**
     * 获取所有模型名称。
     *
     * @return 模型名称
     */
    @NotNull
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        boolean chinese = "zh_CN".equalsIgnoreCase(BaseConstants.CONFIG.getString("language", "zh_CN"));
        for (WingModelEnum model : values()) {
            result.add(chinese ? model.name() + "(" + model.getDesc() + ")" : model.name());
        }
        return result;
    }

    /**
     * 解析模型名称。
     *
     * @param value 模型名称
     * @return 模型
     */
    public static WingModelEnum getEnumThrow(@NotNull String value) {
        String name = value.split("\\(", 2)[0].trim();
        for (WingModelEnum model : values()) {
            if (model.name().equalsIgnoreCase(name)) {
                return model;
            }
        }
        throw new IllegalArgumentException("not found wing model: " + value);
    }
}
