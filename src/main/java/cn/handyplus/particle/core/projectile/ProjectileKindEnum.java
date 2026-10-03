package cn.handyplus.particle.core.projectile;

import cn.handyplus.lib.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 玩家可发射的抛射物类型。
 *
 * <p>用于配置抛射物粒子对哪些抛射物生效，名称与服务端 EntityType 一致。
 * 激流三叉戟在服务端仍是 TRIDENT，不单独区分。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum ProjectileKindEnum {
    /**
     * 箭，含激流箭。
     */
    ARROW("ARROW"),
    /**
     * 光谱箭。
     */
    SPECTRAL_ARROW("SPECTRAL_ARROW"),
    /**
     * 三叉戟，含激流三叉戟。
     */
    TRIDENT("TRIDENT"),
    /**
     * 雪球。
     */
    SNOWBALL("SNOWBALL"),
    /**
     * 蛋。
     */
    EGG("EGG"),
    /**
     * 火球。
     */
    FIREBALL("FIREBALL"),
    /**
     * 烟花火箭。
     */
    FIREWORK_ROCKET("FIREWORK_ROCKET"),
    /**
     * 普通药水。
     */
    SPLASH_POTION("SPLASH_POTION"),
    /**
     * 激流药水。
     */
    LINGERING_POTION("LINGERING_POTION"),
    /**
     * 经验球。
     */
    EXPERIENCE_BOTTLE("EXPERIENCE_BOTTLE"),
    /**
     * 末影珍珠。
     */
    ENDER_PEARL("ENDER_PEARL"),
    /**
     * 潜影壳。
     */
    SHULKER_BULLET("SHULKER_BULLET"),
    /**
     * 风弹。
     */
    WIND_CHARGE("WIND_CHARGE");

    /**
     * 服务端 EntityType 名称。
     */
    private final String entityType;

    /**
     * 全部取消时存储的标记值。
     *
     * <p>数据库字段为空表示「未设置」，按全选处理；若要表达「一个都不生效」
     * 必须用这个独立的标记，否则两者无法区分。
     */
    public static final String NONE_MARKER = "NONE";

    /**
     * 获取本地化描述。
     *
     * @return 抛射物描述
     */
    @NotNull
    public String getDesc() {
        return BaseUtil.getLangMsg("projectileKindDesc." + name());
    }

    /**
     * 判断抛射物是否属于当前类型。
     *
     * @param projectile 抛射物
     * @return 属于当前类型时返回 true
     */
    public boolean matches(@NotNull Projectile projectile) {
        return projectile.getType().name().equals(this.entityType);
    }

    /**
     * 查找抛射物对应的类型。
     *
     * @param projectile 抛射物
     * @return 对应类型，枚举外抛射物返回 null
     */
    @Nullable
    public static ProjectileKindEnum match(@NotNull Projectile projectile) {
        for (ProjectileKindEnum kind : values()) {
            if (kind.matches(projectile)) {
                return kind;
            }
        }
        return null;
    }

    /**
     * 拆分逗号分隔的类型名称并统一转大写。
     *
     * @param value 逗号分隔的类型名称
     * @return 拆分后的名称列表
     */
    @NotNull
    public static List<String> split(@NotNull String value) {
        List<String> names = new ArrayList<>();
        for (String name : value.split(",")) {
            String key = name.trim().toUpperCase(Locale.ROOT);
            if (!key.isEmpty()) {
                names.add(key);
            }
        }
        return names;
    }

    /**
     * 解析逗号分隔的类型名称。
     *
     * <p>字段为空表示未设置，按全选处理；{@code NONE} 标记表示全部取消。
     *
     * @param value 逗号分隔的类型名称
     * @return 类型列表，未设置时返回全部，全部取消时返回空列表
     */
    @NotNull
    public static List<ProjectileKindEnum> parse(@Nullable String value) {
        // 空值表示未设置，按全选处理
        if (value == null || value.trim().isEmpty()) {
            return Arrays.asList(values());
        }
        // NONE 标记表示玩家主动取消全部
        if (NONE_MARKER.equalsIgnoreCase(value.trim())) {
            return new ArrayList<>();
        }
        List<ProjectileKindEnum> result = new ArrayList<>();
        for (String key : split(value)) {
            for (ProjectileKindEnum kind : values()) {
                if (kind.name().equals(key)) {
                    result.add(kind);
                    break;
                }
            }
        }
        // 解析不出任何有效类型时也视为未设置，保持旧数据兼容
        return result.isEmpty() ? Arrays.asList(values()) : result;
    }

    /**
     * 将类型列表序列化为逗号分隔文本。
     *
     * <p>全选返回 null（未设置），全不选返回 NONE 标记。
     *
     * @param kinds 类型列表
     * @return 存储文本
     */
    @NotNull
    public static String serialize(@NotNull List<ProjectileKindEnum> kinds) {
        if (kinds.isEmpty()) {
            return NONE_MARKER;
        }
        if (kinds.size() >= values().length) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (ProjectileKindEnum kind : kinds) {
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(kind.name());
        }
        return builder.toString();
    }

}
