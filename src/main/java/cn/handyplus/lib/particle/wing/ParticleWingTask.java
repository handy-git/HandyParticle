package cn.handyplus.lib.particle.wing;

import cn.handyplus.lib.internal.HandyRunnable;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * 玩家粒子翅膀定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ParticleWingTask extends HandyRunnable {
    /**
     * 任务所属玩家的 UUID。
     */
    private final UUID playerId;
    /**
     * 当前使用的翅膀组合。
     */
    private final ParticleWingTypeEnum wingType;

    /**
     * 刷新玩家当前坐标处的固定翅膀。
     */
    @Override
    public void run() {
        ParticleWingUtil.render(playerId, wingType);
    }
}
