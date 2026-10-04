package cn.handyplus.particle.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 玩家特殊状态粒子效果监听器。
 *
 * @author handy
 */
@HandyListener
public final class PlayerParticleStateListener implements Listener {

    /**
     * 已提示隐身状态的玩家。
     */
    private final Set<UUID> invisiblePlayers = new HashSet<>();

    /**
     * 玩家切换旁观者模式时提醒粒子暂时隐藏。
     *
     * @param event 游戏模式切换事件
     */
    @EventHandler
    public void onGameModeChange(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode() == GameMode.SPECTATOR) {
            MessageUtil.sendMessage(event.getPlayer(), BaseUtil.getLangMsg("particleStateSpectator"));
        }
    }

    /**
     * 玩家获得或解除隐身效果时更新提示状态。
     *
     * @param event 药水效果变化事件
     */
    @EventHandler
    public void onPotionEffect(@NotNull EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (event.getNewEffect() != null && event.getNewEffect().getType().equals(PotionEffectType.INVISIBILITY)) {
            if (this.invisiblePlayers.add(player.getUniqueId())) {
                MessageUtil.sendMessage(player, BaseUtil.getLangMsg("particleStateInvisible"));
            }
            return;
        }
        if (event.getOldEffect() != null
                && event.getOldEffect().getType().equals(PotionEffectType.INVISIBILITY)) {
            this.invisiblePlayers.remove(player.getUniqueId());
        }
    }

    /**
     * 清理退出玩家的提示状态。
     *
     * @param event 退出事件
     */
    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent event) {
        this.invisiblePlayers.remove(event.getPlayer().getUniqueId());
    }
}
