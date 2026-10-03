package cn.handyplus.particle.command.admin;

import cn.handyplus.lib.command.HandyTab;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.constants.ParticleCategoryTypeEnum;
import cn.handyplus.particle.constants.ParticleCommandActionEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
import cn.handyplus.particle.core.wing.WingColorEnum;
import cn.handyplus.particle.core.wing.WingModelEnum;
import cn.handyplus.particle.util.ParticleColorUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.Arrays;

/**
 * 玩家和实体粒子命令共用的参数处理工具。
 *
 * @author handy
 */
final class ParticleCommandHelper {

    /**
     * 工具类禁止实例化。
     */
    private ParticleCommandHelper() {
    }

    /**
     * 构建玩家和实体命令共有的 Tab 补全树。
     *
     * @param tab       命令根补全节点
     * @param hasTarget 是否包含目标玩家参数
     */
    static void tab(@NotNull HandyTab tab, boolean hasTarget) {
        HandyTab action = tab.next(ParticleCommandActionEnum.getAll());
        // set
        HandyTab set = action.when(ParticleCommandActionEnum.SET.getValue());
        if (hasTarget) {
            set = set.nextNull();
        }
        // 类别
        set = set.next(ParticleCategoryTypeEnum.getAll());
        // 拖尾
        HandyTab trails = set.when(ParticleCategoryTypeEnum.TRAIL.name())
                .next(context -> ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.TRAIL));
        trails.next(TrailModeTypeEnum.getAll());
        // 自定义颜色的拖尾
        trails.when(context -> Arrays.asList(ParticleTypeEnum.TRAIL_COLOR.name(), ParticleTypeEnum.TRAIL_COLOR.name() + "(" + ParticleTypeEnum.TRAIL_COLOR.getDesc() + ")"))
                .next(TrailModeTypeEnum.getAll()).next("#FFFFFF");

        // 翅膀
        set.when(ParticleCategoryTypeEnum.WING.name()).next(WingModelEnum.getAll()).next(WingColorEnum.getAll());
        // 方块
        set.when(ParticleCategoryTypeEnum.BLOCK.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.BLOCK));
        // 物品雨
        set.when(ParticleCategoryTypeEnum.RAIN.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.RAIN));
        // 纸屑
        set.when(ParticleCategoryTypeEnum.CONFETTI.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.CONFETTI));
        // 鞘翅
        set.when(ParticleCategoryTypeEnum.ELYTRA.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.ELYTRA));
        // 抛射物
        set.when(ParticleCategoryTypeEnum.PROJECTILE.name()).next(ParticleCategoryTypeEnum.get(ParticleCategoryTypeEnum.PROJECTILE));

        // clear
        HandyTab clear = action.when(ParticleCommandActionEnum.CLEAR.getValue());
        if (hasTarget) {
            clear = clear.nextNull();
        }
        clear.next(ParticleCategoryTypeEnum.getAll());
    }

    /**
     * 解析普通粒子命令的类型、运动模式和颜色参数。
     *
     * @param categoryType 粒子类别
     * @param particleType 粒子类型
     * @param option       运动模式或非拖尾多余参数
     * @param colorValue   颜色文本
     * @param hasExtra     是否存在颜色后的多余参数
     * @param usage        用法提示
     * @return 解析后的粒子选项
     */
    @NotNull
    static EffectOptions parseEffect(@NotNull ParticleCategoryTypeEnum categoryType, @NotNull ParticleTypeEnum particleType, @Nullable String option, @Nullable String colorValue, boolean hasExtra, @NotNull String usage) {
        AssertUtil.notTrue(particleType.getCategory() != categoryType, usage);
        Color color = null;
        TrailModeTypeEnum mode = null;
        if (categoryType == ParticleCategoryTypeEnum.TRAIL) {
            mode = option == null ? TrailModeTypeEnum.DEFAULT : TrailModeTypeEnum.get(option);
            AssertUtil.notNull(mode, usage);
            if (particleType.supportsColor()) {
                color = ParticleColorUtil.parse(colorValue);
            } else {
                AssertUtil.notTrue(colorValue != null, usage);
            }
        } else {
            AssertUtil.notTrue(option != null, usage);
            AssertUtil.notTrue(colorValue != null, usage);
        }
        AssertUtil.notTrue(hasExtra, usage);
        return new EffectOptions(mode, color);
    }

    /**
     * 普通粒子命令解析结果。
     */
    @AllArgsConstructor
    static final class EffectOptions {
        /**
         * 运动模式，非拖尾类别为空。
         */
        final TrailModeTypeEnum mode;
        /**
         * 自定义粒子颜色。
         */
        final Color color;
    }

}
