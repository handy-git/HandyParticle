package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.core.weapon.WeaponEffectTypeEnum;
import cn.handyplus.particle.core.weapon.WeaponGroupEnum;
import cn.handyplus.particle.core.weapon.WeaponParticleManager;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家近战攻击时在受击者身上生成粒子。
 *
 * <p>只处理玩家直接造成的伤害，箭等远程抛射物的伤害不触发，
 * 避免与抛射物尾迹叠加。
 *
 * @author handy
 */
@HandyListener
public final class WeaponParticleListener implements Listener {

    /**
     * 粒子相对受击者的高度偏移。
     */
    private static final double HEIGHT_OFFSET = 1.0D;
    /**
     * 粒子的随机扰动范围，与普通拖尾保持一致。
     */
    private static final double SPREAD = 0.1D;

    /**
     * 处理近战伤害，在受击者位置播放攻击粒子。
     *
     * @param event 伤害事件
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamage(@NotNull EntityDamageByEntityEvent event) {
        // 箭等抛射物造成的伤害不触发，避免与抛射物尾迹叠加
        if (event.getDamager() instanceof Projectile || !(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
        ParticleTypeEnum type = WeaponParticleManager.get(player);
        if (type == null) {
            return;
        }
        // 按玩家勾选的武器分组判定，空手需勾选 HAND 组
        WeaponGroupEnum group = WeaponGroupEnum.match(player.getInventory().getItemInMainHand());
        if (!WeaponParticleManager.matches(player, group)) {
            return;
        }
        this.spawn(event.getEntity().getLocation(), type);
    }

    /**
     * 在受击者位置播放攻击粒子。
     *
     * <p>用扰动偏移一次生成全部粒子，只发一个封包。
     *
     * @param location 受击者位置
     * @param type     效果类型
     */
    private void spawn(@NotNull Location location, @NotNull ParticleTypeEnum type) {
        WeaponEffectTypeEnum effect = type.getWeaponEffect();
        if (effect == null) {
            return;
        }
        ParticleDisplay.of(effect.getParticle())
                .withCount(effect.getCount())
                .offset(SPREAD)
                .spawn(location.clone().add(0.0D, HEIGHT_OFFSET, 0.0D));
    }

}
