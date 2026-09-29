package cn.handyplus.particle.core.block;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 临时方块拖尾定时任务。
 *
 * @author handy
 */
@RequiredArgsConstructor
public final class BlockTrailTask extends HandyRunnable {
    private final Entity entity;
    private final BlockTrailTypeEnum type;
    private final Map<Location, Integer> locations = new HashMap<>();
    private final Map<Location, Set<UUID>> viewers = new HashMap<>();
    private long tick;

    /**
     * 在实体脚下发送临时方块外观。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            cleanup();
            HandyParticleCore.disable(entity);
            return;
        }
        expire();
        Location location = entity.getLocation().clone().subtract(0.0D, 1.0D, 0.0D);
        Block block = location.getBlock();
        if (!type.isGround(block)) {
            return;
        }
        // 统一使用方块整数坐标，避免实体移动产生重复 key。
        location = block.getLocation();
        locations.putIfAbsent(location, 2);
        Set<UUID> recipients = viewers.computeIfAbsent(location, ignored -> new HashSet<>());
        List<Player> nearby = new ArrayList<>();
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (viewer.getWorld() != location.getWorld()) {
                continue;
            }
            if (viewer.getLocation().distanceSquared(location) > 625.0D) {
                continue;
            }
            nearby.add(viewer);
        }
        sendBlockChanges(location, nearby, recipients);
        tick++;
    }

    /**
     * 恢复任务覆盖过的方块外观。
     */
    public void cleanup() {
        for (Location location : locations.keySet()) {
            restore(location);
        }
        locations.clear();
        viewers.clear();
    }

    /**
     * 恢复超过存活时间的临时方块。
     */
    private void expire() {
        locations.entrySet().removeIf(entry -> {
            int remaining = entry.getValue() - 1;
            if (remaining > 0) {
                entry.setValue(remaining);
                return false;
            }
            restore(entry.getKey());
            return true;
        });
    }

    /**
     * 向收到过临时方块的玩家恢复真实外观。
     *
     * @param location 方块位置
     */
    private void restore(@NotNull Location location) {
        Set<UUID> recipients = viewers.remove(location);
        if (recipients == null) {
            return;
        }
        for (UUID playerId : recipients) {
            Player viewer = Bukkit.getPlayer(playerId);
            if (viewer == null) {
                continue;
            }
            if (viewer.getWorld() != location.getWorld()) {
                continue;
            }
            restore(viewer, location);
        }
    }

    /**
     * 为本轮观察者复用同一份方块外观数据。
     *
     * @param location   方块位置
     * @param nearby     范围内的玩家
     * @param recipients 收到外观的玩家记录
     */
    @SuppressWarnings("deprecation")
    private void sendBlockChanges(@NotNull Location location, @NotNull List<Player> nearby, @NotNull Set<UUID> recipients) {
        if (nearby.isEmpty()) {
            return;
        }
        if (BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_13.getVersionId()) {
            BlockData data = type.blockData(tick);
            for (Player viewer : nearby) {
                viewer.sendBlockChange(location, data);
                recipients.add(viewer.getUniqueId());
            }
            return;
        }
        Material material = type.legacyMaterial(tick);
        byte data = type.legacyData(tick);
        for (Player viewer : nearby) {
            viewer.sendBlockChange(location, material, data);
            recipients.add(viewer.getUniqueId());
        }
    }

    /**
     * 根据服务端版本恢复真实方块外观。
     *
     * @param viewer   观察者
     * @param location 方块位置
     */
    @SuppressWarnings({"deprecation", "removal"})
    private void restore(@NotNull Player viewer, @NotNull Location location) {
        Block block = location.getBlock();
        if (BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_13.getVersionId()) {
            viewer.sendBlockChange(location, block.getBlockData());
            return;
        }
        viewer.sendBlockChange(location, block.getType(), block.getData());
    }

}
