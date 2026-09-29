package cn.handyplus.particle.core.block;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

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
    private long tick;

    /**
     * 在玩家脚下发送临时方块外观。
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
        // 统一使用方块整数坐标，避免玩家转身或在同一方块内移动产生重复 key。
        location = block.getLocation();
        locations.putIfAbsent(location, 2);
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (viewer.getWorld() == location.getWorld() && viewer.getLocation().distanceSquared(location) <= 625.0D) {
                sendBlockChange(viewer, location);
            }
        }
        tick++;
    }

    /**
     * 恢复任务覆盖过的方块外观。
     */
    public void cleanup() {
        for (Location location : locations.keySet()) {
            for (Player viewer : Bukkit.getOnlinePlayers()) {
                if (viewer.getWorld() == location.getWorld() && viewer.getLocation().distanceSquared(location) <= 625.0D) {
                    restore(viewer, location);
                }
            }
        }
        locations.clear();
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
     * 向附近玩家恢复真实方块外观。
     *
     * @param location 方块位置
     */
    private void restore(@NotNull Location location) {
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (viewer.getWorld() == location.getWorld() && viewer.getLocation().distanceSquared(location) <= 625.0D) {
                restore(viewer, location);
            }
        }
    }

    /**
     * 根据服务端版本向玩家发送临时方块。
     *
     * @param viewer   观察者
     * @param location 方块位置
     */
    @SuppressWarnings("deprecation")
    private void sendBlockChange(@NotNull Player viewer, @NotNull Location location) {
        if (BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_13.getVersionId()) {
            viewer.sendBlockChange(location, type.blockData(tick));
            return;
        }
        viewer.sendBlockChange(location, type.legacyMaterial(tick), type.legacyData(tick));
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
