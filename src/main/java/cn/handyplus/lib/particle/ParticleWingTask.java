package cn.handyplus.lib.particle;

import cn.handyplus.lib.internal.HandyRunnable;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * 玩家粒子翅膀定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
final class ParticleWingTask extends HandyRunnable {
    private final UUID playerId;
    private final ParticleWingType wingType;

    /**
     * 刷新玩家当前坐标处的固定翅膀。
     */
    @Override
    public void run() {
        ParticleWingUtil.render(playerId, wingType);
    }
}
