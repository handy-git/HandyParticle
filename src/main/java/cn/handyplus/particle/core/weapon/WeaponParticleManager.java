package cn.handyplus.particle.core.weapon;

import cn.handyplus.particle.api.ParticleTypeEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 武器攻击效果开关记录。
 *
 * <p>与抛射物效果一样没有常驻任务，落库仅表示玩家开启了效果，
 * 运行时只查内存记录。
 *
 * @author handy
 */
public final class WeaponParticleManager {

    private WeaponParticleManager() {
    }

    /**
     * 玩家武器效果记录，键为玩家 UUID。
     */
    private static final Map<UUID, WeaponRecord> ENABLED_MAP = new ConcurrentHashMap<>();

    /**
     * 记录玩家已开启的武器效果。
     *
     * @param playerId 玩家 UUID
     * @param type     效果类型
     * @param groups   生效的武器分组，全选时传 null
     */
    public static void enable(@NotNull UUID playerId, @NotNull ParticleTypeEnum type, @Nullable List<WeaponGroupEnum> groups) {
        ENABLED_MAP.put(playerId, new WeaponRecord(type, groups == null ? null : WeaponGroupEnum.serialize(groups)));
    }

    /**
     * 移除玩家的武器效果记录。
     *
     * @param playerId 玩家 UUID
     */
    public static void disable(@NotNull UUID playerId) {
        ENABLED_MAP.remove(playerId);
    }

    /**
     * 读取玩家已开启的武器效果。
     *
     * @param player 玩家
     * @return 效果类型，未开启时返回 null
     */
    @Nullable
    public static ParticleTypeEnum get(@NotNull Player player) {
        WeaponRecord record = ENABLED_MAP.get(player.getUniqueId());
        return record == null ? null : record.getType();
    }

    /**
     * 读取玩家已开启的武器效果记录。
     *
     * @param playerId 玩家 UUID
     * @return 记录，未开启时返回 null
     */
    @Nullable
    public static WeaponRecord getRecord(@NotNull UUID playerId) {
        return ENABLED_MAP.get(playerId);
    }

    /**
     * 覆盖玩家已开启效果的生效武器范围。
     *
     * @param playerId 玩家 UUID
     * @param groups   生效的武器分组，全选时传 null
     */
    public static void updateGroups(@NotNull UUID playerId, @Nullable List<WeaponGroupEnum> groups) {
        WeaponRecord record = ENABLED_MAP.get(playerId);
        if (record == null) {
            return;
        }
        record.setScopes(groups == null ? null : WeaponGroupEnum.serialize(groups));
    }

    /**
     * 判断该手持武器是否需要触发攻击粒子。
     *
     * @param player 玩家
     * @param group  手持武器所属分组
     * @return 需要触发时返回 true
     */
    public static boolean matches(@NotNull Player player, @Nullable WeaponGroupEnum group) {
        WeaponRecord record = ENABLED_MAP.get(player.getUniqueId());
        if (record == null) {
            return false;
        }
        String saved = record.getScopes();
        // 未配置时视为全部生效
        if (saved == null || saved.isEmpty()) {
            return true;
        }
        // 手持物不在任何武器分组内时不触发
        if (group == null) {
            return false;
        }
        // 必须按分隔符精确比对，AXE 是 PICKAXE 的子串，contains 会误判
        for (String name : WeaponGroupEnum.split(saved)) {
            if (group.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清空全部记录，插件卸载时调用。
     */
    public static void clear() {
        ENABLED_MAP.clear();
    }

    /**
     * 玩家武器效果记录。
     */
    public static final class WeaponRecord {

        /**
         * 效果类型。
         */
        private final ParticleTypeEnum type;
        /**
         * 生效范围，逗号分隔，为空表示全部。
         */
        private String scopes;

        WeaponRecord(@NotNull ParticleTypeEnum type, @Nullable String scopes) {
            this.type = type;
            this.scopes = scopes;
        }

        /**
         * 获取效果类型。
         *
         * @return 效果类型
         */
        @NotNull
        public ParticleTypeEnum getType() {
            return this.type;
        }

        /**
         * 获取生效的武器分组。
         *
         * @return 分组列表，未配置时返回全部
         */
        @NotNull
        public List<WeaponGroupEnum> getGroups() {
            return WeaponGroupEnum.parse(this.scopes);
        }

        /**
         * 获取存储的逗号分隔文本。
         *
         * @return 文本，全选时返回 null
         */
        @Nullable
        public String getScopes() {
            return this.scopes;
        }

        /**
         * 覆盖生效范围。
         *
         * @param scopes 逗号分隔文本
         */
        void setScopes(@Nullable String scopes) {
            this.scopes = scopes;
        }
    }

}
