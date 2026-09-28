package cn.handyplus.particle.core.trail;

import com.cryptomorin.xseries.particles.XParticle;
import lombok.AllArgsConstructor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 内置粒子拖尾。
 *
 * @author handy
 */
@AllArgsConstructor
public enum TrailTypeEnum {
    /**
     * 爱心粒子拖尾。
     */
    TRAIL_HEART("HEART", 0.0D),
    /**
     * 愤怒村民粒子拖尾。
     */
    TRAIL_ANGRY("VILLAGER_ANGRY", 0.0D),
    /**
     * 魔法暴击粒子拖尾。
     */
    TRAIL_MAGIC("CRIT_MAGIC", 0.0D),
    /**
     * 红石粒子拖尾。
     */
    TRAIL_FUN("REDSTONE", 0.0D, 1.0D, Particle.DustOptions.class),
    /**
     * 云朵粒子拖尾。
     */
    TRAIL_CLOUD("CLOUD", 0.0D),
    /**
     * 女巫法术粒子拖尾。
     */
    TRAIL_WITCH("SPELL_WITCH", 0.0D),
    /**
     * 传送门粒子拖尾。
     */
    TRAIL_ENDER("PORTAL", 0.0D),
    /**
     * 开心村民粒子拖尾。
     */
    TRAIL_GREEN("VILLAGER_HAPPY", 0.0D),
    /**
     * 烟花火花粒子拖尾。
     */
    TRAIL_SPARK("FIREWORKS_SPARK", 0.0D),
    /**
     * 火焰粒子拖尾。
     */
    TRAIL_FLAME("FLAME", 0.0D),
    /**
     * 瞬间法术粒子拖尾。
     */
    TRAIL_WHITE("SPELL_INSTANT", 0.0D),
    /**
     * 音符粒子拖尾。
     */
    TRAIL_NOTE("NOTE", 0.0D),
    /**
     * 雪花粒子拖尾。
     */
    TRAIL_SNOW("SNOW_SHOVEL", 0.0D),
    /**
     * 水滴粒子拖尾。
     */
    TRAIL_WATER("DRIP_WATER", 0.0D),
    /**
     * 熔岩滴落粒子拖尾。
     */
    TRAIL_LAVA("DRIP_LAVA", 0.0D),
    /**
     * 暴击粒子拖尾。
     */
    TRAIL_CRIT("CRIT", 0.0D),
    /**
     * 烟雾粒子拖尾。
     */
    TRAIL_SMOKE("SMOKE_NORMAL", 0.0D),
    /**
     * 生物法术粒子拖尾。
     */
    TRAIL_SPELL("SPELL_MOB_AMBIENT", 0.0D, 0.0D, Color.class),
    /**
     * 附魔台粒子拖尾。
     */
    TRAIL_ENCHANT("ENCHANTMENT_TABLE", 0.0D),
    /**
     * 水花粒子拖尾。
     */
    TRAIL_SPLASH("WATER_SPLASH", 0.0D),
    /**
     * 史莱姆粒子拖尾。
     */
    TRAIL_SLIME("SLIME", 0.0D),
    /**
     * 雪球粒子拖尾。
     */
    TRAIL_SNOWBALL("SNOWBALL", 0.0D),
    /**
     * 菌丝粒子拖尾。
     */
    TRAIL_VOID("TOWN_AURA", 0.0D),
    /**
     * 熔岩爆裂粒子拖尾。
     */
    TRAIL_LAVA_POP("LAVA", 0.0D),
    /**
     * 龙息粒子拖尾。
     */
    TRAIL_BREATH("DRAGON_BREATH", 0.0D),
    /**
     * 伤害指示粒子拖尾。
     */
    TRAIL_DAMAGE("DAMAGE_INDICATOR", 0.0D),
    /**
     * 末地烛粒子拖尾。
     */
    TRAIL_END_ROD("END_ROD", 0.0D),
    /**
     * 不死图腾粒子拖尾。
     */
    TRAIL_TOTEM("TOTEM", 0.0D, 0.1D),
    /**
     * 灵魂粒子拖尾。
     */
    TRAIL_SOUL("SOUL", 0.2D),
    /**
     * 灵魂火焰粒子拖尾。
     */
    TRAIL_SOUL_FIRE("SOUL_FIRE_FLAME", 0.2D),
    /**
     * 金色火花拖尾。
     */
    TRAIL_GOLD_SPARKS("ELECTRIC_SPARK", 0.2D),
    /**
     * 发光鱿鱼拖尾。
     */
    TRAIL_GLOW_SQUID("SQUID_INK", 0.2D),
    /**
     * 粉色纸屑拖尾。
     */
    TRAIL_PINK_CONFETTI("CHERRY_LEAVES", 0.2D);

    /**
     * 红石粒子的固定颜色与大小。
     */
    private static final Particle.DustOptions RED_DUST = new Particle.DustOptions(Color.RED, 1.0F);
    /**
     * 生物法术粒子的固定颜色。
     */
    private static final Color SPELL_COLOR = Color.fromRGB(255, 0, 255);

    /**
     * XParticle 粒子名称。
     */
    @NotNull
    private final String particleName;
    /**
     * 相对玩家位置的纵向偏移。
     */
    private final double y;
    /**
     * 粒子自身速度，与轨迹旋转速度无关。
     */
    private final double speed;
    /**
     * 需要附加数据时使用的发射方式。
     */
    @NotNull
    private final Class<?> dataType;

    /**
     * 创建使用默认速度的内置拖尾。
     *
     * @param particleName XParticle 粒子名称
     * @param y            相对玩家位置的纵向偏移
     */
    TrailTypeEnum(@NotNull String particleName, double y) {
        this(particleName, y, 0.0D, Void.class);
    }

    /**
     * 创建无需附加数据的内置拖尾。
     *
     * @param particleName XParticle 粒子名称
     * @param y            相对玩家位置的纵向偏移
     * @param speed        粒子自身速度
     */
    TrailTypeEnum(@NotNull String particleName, double y, double speed) {
        this(particleName, y, speed, Void.class);
    }

    /**
     * 在玩家位置生成一次拖尾，每次发射 4 个粒子，三个轴的扩散偏移均为 1。
     *
     * @param player   玩家
     * @param particle 已解析的粒子
     */
    public void spawn(@NotNull Player player, @NotNull Particle particle) {
        Location location = player.getLocation().clone().add(0.0D, y, 0.0D);
        spawn(player, particle, location, 4, 1.0D);
    }

    /**
     * 解析粒子并校验发射所需的数据类型。
     *
     * @return 当前服务端的粒子
     * @throws IllegalArgumentException 粒子不受支持或数据类型不匹配
     */
    @NotNull
    public Particle resolveParticle() {
        Particle particle = XParticle.of(particleName).map(XParticle::get).orElse(null);
        if (particle == null) {
            throw new IllegalArgumentException("Unsupported particle: " + particleName);
        }
        if (particle.getDataType() != dataType) {
            throw new IllegalArgumentException("Unsupported particle data: " + particleName);
        }
        return particle;
    }

    /**
     * 使用对应附加数据生成粒子。
     *
     * @param player   玩家
     * @param particle 已解析的粒子
     * @param location 粒子位置
     * @param amount   粒子数量
     * @param offset   三个坐标轴的扩散范围
     */
    private void spawn(@NotNull Player player, @NotNull Particle particle, @NotNull Location location, int amount, double offset) {
        // 红石和生物法术需要与 Bukkit 粒子数据类型一致的附加数据。
        if (dataType == Particle.DustOptions.class) {
            player.spawnParticle(particle, location, amount, offset, offset, offset, speed, RED_DUST);
        } else if (dataType == Color.class) {
            player.spawnParticle(particle, location, amount, offset, offset, offset, speed, SPELL_COLOR);
        } else {
            player.spawnParticle(particle, location, amount, offset, offset, offset, speed);
        }
    }

}
