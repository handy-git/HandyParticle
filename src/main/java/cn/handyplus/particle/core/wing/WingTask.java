package cn.handyplus.particle.core.wing;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * 实体粒子翅膀定时任务。
 *
 * @author handy
 */
public final class WingTask extends HandyRunnable {
    /**
     * 任务所属实体。
     */
    private final Entity entity;
    /**
     * 预先组合的翅膀点阵。
     */
    private final WingPoint[] points;

    /**
     * 创建翅膀任务。
     *
     * @param entity 实体
     * @param model  翅膀外形
     * @param color  颜色图案
     */
    public WingTask(@NotNull Entity entity, @NotNull WingModelEnum model, @NotNull WingColorEnum color) {
        this.entity = entity;
        this.points = WingUtil.createPoints(model, color);
    }

    /**
     * 刷新实体当前坐标处的固定翅膀。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        WingUtil.render(entity, points);
    }
}
