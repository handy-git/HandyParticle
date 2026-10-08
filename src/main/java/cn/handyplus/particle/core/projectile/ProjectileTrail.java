package cn.handyplus.particle.core.projectile;

import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.util.ParticleVisibilityUtil;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * 单个抛射物的一次性拖尾。
 *
 * <p>跟随抛射物逐帧生成粒子，抛射物失效、命中或玩家关闭效果后立即结束。
 * 该任务不进入 HandyParticleCore 的任务表，由监听器自行管理生命周期。
 *
 * @author handy
 */
public final class ProjectileTrail extends HandyRunnable {

    /**
     * 任务所属抛射物。
     */
    private final Projectile projectile;
    /**
     * 拖尾效果类型。
     */
    private final ParticleTypeEnum type;
    /**
     * 当前效果对应的粒子显示对象。
     */
    private final ParticleDisplay display;

    /**
     * 创建抛射物拖尾。
     *
     * @param projectile 抛射物
     * @param type       拖尾效果
     */
    public ProjectileTrail(@NotNull Projectile projectile, @NotNull ParticleTypeEnum type) {
        this.projectile = projectile;
        this.type = type;
        ProjectileTrailTypeEnum trailType = type.getProjectileTrail();
        XParticle particle = trailType == null ? XParticle.CRIT : trailType.getParticle();
        double speed = trailType == null ? 0.0D : trailType.getSpeed();
        if (particle == XParticle.DUST) {
            this.display = ParticleDisplay.of(particle).withCount(1).withExtra(speed).withColor(Color.RED, 1.0F);
        } else {
            this.display = ParticleDisplay.of(particle).withCount(1).withExtra(speed);
        }
    }

    @Override
    public void run() {
        // 抛射物失效或落地后立即结束
        if (!projectile.isValid() || projectile.isOnGround()) {
            this.stop();
            return;
        }
        Location location = projectile.getLocation();
        Entity shooter = projectile.getShooter() instanceof Player ? (Player) projectile.getShooter() : projectile;
        ParticleVisibilityUtil.spawn(display, location, shooter);
    }

    /**
     * 停止任务，任务可能已被替换或调度器已退役。
     */
    public void stop() {
        try {
            this.cancel();
        } catch (IllegalStateException ignored) {
            // 任务可能已完成绑定
        }
    }

}
