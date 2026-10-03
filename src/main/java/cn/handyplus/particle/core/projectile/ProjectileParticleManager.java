package cn.handyplus.particle.core.projectile;

import cn.handyplus.particle.api.ParticleTypeEnum;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抛射物效果开关记录。
 *
 * <p>抛射物效果没有常驻任务，落库仅表示玩家开启了效果，
 * 运行时只查内存记录，避免每个抛射物都访问数据库。
 *
 * @author handy
 */
public final class ProjectileParticleManager {

    private ProjectileParticleManager() {
    }

    /**
     * 玩家抛射物效果记录，键为玩家 UUID。
     */
    private static final Map<UUID, ProjectileRecord> ENABLED_MAP = new ConcurrentHashMap<>();

    /**
     * 记录玩家已开启的抛射物效果。
     *
     * @param playerId 玩家 UUID
     * @param type     效果类型
     * @param kinds    生效的抛射物类型，全选时传 null
     */
    public static void enable(@NotNull UUID playerId, @NotNull ParticleTypeEnum type, @Nullable List<ProjectileKindEnum> kinds) {
        ENABLED_MAP.put(playerId, new ProjectileRecord(type, kinds == null ? null : ProjectileKindEnum.serialize(kinds)));
    }

    /**
     * 移除玩家的抛射物效果记录。
     *
     * @param playerId 玩家 UUID
     */
    public static void disable(@NotNull UUID playerId) {
        ENABLED_MAP.remove(playerId);
    }

    /**
     * 读取玩家已开启的抛射物效果。
     *
     * @param player 玩家
     * @return 效果类型，未开启时返回 null
     */
    @Nullable
    public static ParticleTypeEnum get(@NotNull Player player) {
        ProjectileRecord record = ENABLED_MAP.get(player.getUniqueId());
        return record == null ? null : record.getType();
    }

    /**
     * 读取玩家已开启的抛射物效果记录。
     *
     * @param player 玩家
     * @return 记录，未开启时返回 null
     */
    @Nullable
    public static ProjectileRecord getRecord(@NotNull Player player) {
        return ENABLED_MAP.get(player.getUniqueId());
    }

    /**
     * 读取玩家已开启的抛射物效果记录。
     *
     * @param playerId 玩家 UUID
     * @return 记录，未开启时返回 null
     */
    @Nullable
    public static ProjectileRecord getRecord(@NotNull UUID playerId) {
        return ENABLED_MAP.get(playerId);
    }

    /**
     * 覆盖玩家已开启效果的生效抛射物范围。
     *
     * @param playerId 玩家 UUID
     * @param kinds    生效的抛射物类型，全选时传 null
     */
    public static void updateKinds(@NotNull UUID playerId, @Nullable List<ProjectileKindEnum> kinds) {
        ProjectileRecord record = ENABLED_MAP.get(playerId);
        if (record == null) {
            return;
        }
        record.setScopes(kinds == null ? null : ProjectileKindEnum.serialize(kinds));
    }

    /**
     * 判断该抛射物是否需要附加玩家的拖尾。
     *
     * @param player     发射者
     * @param projectile 抛射物
     * @return 需要附加时返回 true
     */
    public static boolean matches(@NotNull Player player, @NotNull Projectile projectile) {
        ProjectileRecord record = ENABLED_MAP.get(player.getUniqueId());
        if (record == null) {
            return false;
        }
        String saved = record.getScopes();
        // 未配置时视为全部生效
        if (saved == null || saved.isEmpty()) {
            return true;
        }
        ProjectileKindEnum kind = ProjectileKindEnum.match(projectile);
        if (kind == null) {
            return false;
        }
        // 必须按分隔符精确比对，不能用 contains
        // 例如取消 ARROW 后保存的是 SPECTRAL_ARROW，contains("ARROW") 会误判为命中
        for (String name : ProjectileKindEnum.split(saved)) {
            if (kind.name().equals(name)) {
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
     * 玩家抛射物效果记录。
     */
    public static final class ProjectileRecord {

        /**
         * 效果类型。
         */
        private final ParticleTypeEnum type;
        /**
         * 生效范围，逗号分隔，为空表示全部。
         */
        private String scopes;

        ProjectileRecord(@NotNull ParticleTypeEnum type, @Nullable String scopes) {
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
         * 获取生效的抛射物类型。
         *
         * @return 类型列表，未配置时返回全部
         */
        @NotNull
        public List<ProjectileKindEnum> getKinds() {
            return ProjectileKindEnum.parse(this.scopes);
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
