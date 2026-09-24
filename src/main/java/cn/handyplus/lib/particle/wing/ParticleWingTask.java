package cn.handyplus.lib.particle.wing;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.lib.particle.HandyParticleApi;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

/**
 * 玩家粒子翅膀定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class ParticleWingTask extends HandyRunnable {
    /**
     * 任务所属玩家。
     */
    private final Player player;
    /**
     * 当前使用的翅膀组合。
     */
    private final ParticleWingTypeEnum wingType;

    /**
     * 刷新玩家当前坐标处的固定翅膀。
     */
    @Override
    public void run() {
        if (!player.isOnline()) {
            HandyParticleApi.disable(player);
            return;
        }
        ParticleWingUtil.render(player, wingType);
    }
}
