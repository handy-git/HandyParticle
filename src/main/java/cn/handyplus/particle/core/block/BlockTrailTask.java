package cn.handyplus.particle.core.block;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.constants.VersionCheckEnum;
import cn.handyplus.lib.internal.HandyRunnable;
import cn.handyplus.particle.core.HandyParticleCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 临时方块拖尾定时任务。
 *
 * @author handy
 */
public final class BlockTrailTask extends HandyRunnable {
    /**
     * 任务所属实体。
     */
    private final Entity entity;
    /**
     * 当前使用的方块拖尾类型。
     */
    private final BlockTrailTypeEnum type;
    /**
     * 自定义方块材质列表，使用内置类型时为空。
     */
    private final Material[] customMaterials;
    /**
     * 当前仍在显示临时方块的位置及剩余帧数。
     */
    private final Map<Location, Integer> locations = new HashMap<>();
    /**
     * 每个位置已收到临时方块外观的玩家。
     */
    private final Map<Location, Set<UUID>> viewers = new HashMap<>();
    /**
     * 当前方块拖尾刷新帧数。
     */
    private long tick;

    /**
     * 创建内置方块拖尾任务。
     *
     * @param entity 实体
     * @param type   方块拖尾类型
     */
    public BlockTrailTask(@NotNull Entity entity, @NotNull BlockTrailTypeEnum type) {
        this.entity = entity;
        this.type = type;
        this.customMaterials = null;
    }

    /**
     * 创建自定义方块拖尾任务。
     *
     * @param entity    实体
     * @param materials 自定义方块材质
     */
    public BlockTrailTask(@NotNull Entity entity, @NotNull Material[] materials) {
        this.entity = entity;
        this.type = null;
        this.customMaterials = materials.clone();
    }

    /**
     * 在实体脚下发送临时方块外观。
     */
    @Override
    public void run() {
        if (!entity.isValid()) {
            this.cleanup();
            HandyParticleCore.disable(entity);
            return;
        }
        this.expire();
        Location location = entity.getLocation().clone().subtract(0.0D, 1.0D, 0.0D);
        Block block = location.getBlock();
        if (!BlockTrailTypeEnum.BLOCK_WOOL.isGround(block)) {
            return;
        }
        // 统一使用方块整数坐标，避免实体移动产生重复 key。
        location = block.getLocation();
        locations.putIfAbsent(location, 2);
        Set<UUID> recipients = viewers.computeIfAbsent(location, ignored -> new HashSet<>());
        this.sendBlockChanges(location, recipients);
        tick++;
    }

    /**
     * 向当前范围内的玩家发送临时方块外观。
     *
     * @param location   方块位置
     * @param recipients 收到外观的玩家记录
     */
    @SuppressWarnings("deprecation")
    private void sendBlockChanges(@NotNull Location location, @NotNull Set<UUID> recipients) {
        if (BaseConstants.VERSION_ID >= VersionCheckEnum.V_1_13.getVersionId()) {
            BlockData blockData = this.type == null ? this.customMaterials[(int) (this.tick % this.customMaterials.length)].createBlockData() : this.type.blockData(this.tick);
            for (Player viewer : Bukkit.getOnlinePlayers()) {
                if (this.isNearby(location, viewer)) {
                    viewer.sendBlockChange(location, blockData);
                    recipients.add(viewer.getUniqueId());
                }
            }
            return;
        }
        Material material = this.type == null ? this.customMaterials[(int) (this.tick % this.customMaterials.length)] : this.type.legacyMaterial(this.tick);
        byte data = this.type == null ? 0 : this.type.legacyData(this.tick);
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            if (this.isNearby(location, viewer)) {
                viewer.sendBlockChange(location, material, data);
                recipients.add(viewer.getUniqueId());
            }
        }
    }

    /**
     * 判断玩家是否在临时方块的可见范围内。
     *
     * @param location 方块位置
     * @param viewer   玩家
     * @return 是否在范围内
     */
    private boolean isNearby(@NotNull Location location, @NotNull Player viewer) {
        if (viewer.getWorld() != location.getWorld()) {
            return false;
        }
        return viewer.getLocation().distanceSquared(location) <= 625.0D;
    }

    /**
     * 恢复任务覆盖过的方块外观。
     */
    public void cleanup() {
        for (Location location : locations.keySet()) {
            this.restore(location);
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
            this.restore(entry.getKey());
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
            this.restore(viewer, location);
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
