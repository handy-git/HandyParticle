package cn.handyplus.particle.core.wing;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;

/**
 * 玩家粒子翅膀定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class WingTask extends HandyRunnable {
    /**
     * 任务所属玩家。
     */
    private final Entity entity;
    /**
     * 当前使用的翅膀组合。
     */
    private final WingTypeEnum wingType;

    /**
     * 刷新玩家当前坐标处的固定翅膀。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            HandyParticleCore.disable(entity);
            return;
        }
        WingUtil.render(entity, wingType);
    }
}
