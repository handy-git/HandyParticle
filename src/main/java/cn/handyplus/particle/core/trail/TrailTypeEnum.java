package cn.handyplus.particle.core.trail;

import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * 内置粒子拖尾。
 *
 * @author handy
 */
public enum TrailTypeEnum {
    /**
     * 爱心粒子拖尾。
     */
    TRAIL_HEART(XParticle.HEART, 0.0D),
    /**
     * 愤怒村民粒子拖尾。
     */
    TRAIL_ANGRY(XParticle.ANGRY_VILLAGER, 0.0D),
    /**
     * 魔法暴击粒子拖尾。
     */
    TRAIL_MAGIC(XParticle.ENCHANTED_HIT, 0.0D),
    /**
     * 红石粒子拖尾。
     */
    TRAIL_FUN(XParticle.DUST, 0.0D, 1.0D),
    /**
     * 云朵粒子拖尾。
     */
    TRAIL_CLOUD(XParticle.CLOUD, 0.0D),
    /**
     * 女巫法术粒子拖尾。
     */
    TRAIL_WITCH(XParticle.WITCH, 0.0D),
    /**
     * 传送门粒子拖尾。
     */
    TRAIL_ENDER(XParticle.PORTAL, 0.0D),
    /**
     * 开心村民粒子拖尾。
     */
    TRAIL_GREEN(XParticle.HAPPY_VILLAGER, 0.0D),
    /**
     * 烟花火花粒子拖尾。
     */
    TRAIL_SPARK(XParticle.FIREWORK, 0.0D),
    /**
     * 火焰粒子拖尾。
     */
    TRAIL_FLAME(XParticle.FLAME, 0.0D),
    /**
     * 瞬间法术粒子拖尾。
     */
    TRAIL_WHITE(XParticle.INSTANT_EFFECT, 0.0D),
    /**
     * 音符粒子拖尾。
     */
    TRAIL_NOTE(XParticle.NOTE, 0.0D),
    /**
     * 雪花粒子拖尾。
     */
    TRAIL_SNOW(XParticle.ITEM_SNOWBALL, 0.0D),
    /**
     * 水滴粒子拖尾。
     */
    TRAIL_WATER(XParticle.DRIPPING_WATER, 0.0D),
    /**
     * 熔岩滴落粒子拖尾。
     */
    TRAIL_LAVA(XParticle.DRIPPING_LAVA, 0.0D),
    /**
     * 暴击粒子拖尾。
     */
    TRAIL_CRIT(XParticle.CRIT, 0.0D),
    /**
     * 烟雾粒子拖尾。
     */
    TRAIL_SMOKE(XParticle.SMOKE, 0.0D),
    /**
     * 生物法术粒子拖尾。
     */
    TRAIL_SPELL(XParticle.ENTITY_EFFECT, 0.0D),
    /**
     * 附魔台粒子拖尾。
     */
    TRAIL_ENCHANT(XParticle.ENCHANT, 0.0D),
    /**
     * 水花粒子拖尾。
     */
    TRAIL_SPLASH(XParticle.SPLASH, 0.0D),
    /**
     * 史莱姆粒子拖尾。
     */
    TRAIL_SLIME(XParticle.ITEM_SLIME, 0.0D),
    /**
     * 雪球粒子拖尾。
     */
    TRAIL_SNOWBALL(XParticle.ITEM_SNOWBALL, 0.0D),
    /**
     * 菌丝粒子拖尾。
     */
    TRAIL_VOID(XParticle.MYCELIUM, 0.0D),
    /**
     * 熔岩爆裂粒子拖尾。
     */
    TRAIL_LAVA_POP(XParticle.LAVA, 0.0D),
    /**
     * 龙息粒子拖尾。
     */
    TRAIL_BREATH(XParticle.DRAGON_BREATH, 0.0D),
    /**
     * 伤害指示粒子拖尾。
     */
    TRAIL_DAMAGE(XParticle.DAMAGE_INDICATOR, 0.0D),
    /**
     * 末地烛粒子拖尾。
     */
    TRAIL_END_ROD(XParticle.END_ROD, 0.0D),
    /**
     * 不死图腾粒子拖尾。
     */
    TRAIL_TOTEM(XParticle.TOTEM_OF_UNDYING, 0.0D, 0.1D),
    /**
     * 灵魂粒子拖尾。
     */
    TRAIL_SOUL(XParticle.SOUL, 0.2D),
    /**
     * 灵魂火焰粒子拖尾。
     */
    TRAIL_SOUL_FIRE(XParticle.SOUL_FIRE_FLAME, 0.2D),
    /**
     * 金色火花拖尾。
     */
    TRAIL_GOLD_SPARKS(XParticle.ELECTRIC_SPARK, 0.2D),
    /**
     * 发光鱿鱼拖尾。
     */
    TRAIL_GLOW_SQUID(XParticle.SQUID_INK, 0.2D),
    /**
     * 粉色纸屑拖尾。
     */
    TRAIL_PINK_CONFETTI(XParticle.CHERRY_LEAVES, 0.2D);

    /**
     * XParticle 粒子。
     */
    @NotNull
    private final XParticle particle;
    /**
     * 相对实体位置的纵向偏移。
     */
    private final double y;
    /**
     * 粒子自身速度，与轨迹旋转速度无关。
     */
    private final double speed;

    /**
     * 创建使用默认速度的内置拖尾。
     *
     * @param particle XParticle 粒子
     * @param y        相对实体位置的纵向偏移
     */
    TrailTypeEnum(@NotNull XParticle particle, double y) {
        this(particle, y, 0.0D);
    }

    /**
     * 创建无需附加数据的内置拖尾。
     *
     * @param particle XParticle 粒子
     * @param y        相对实体位置的纵向偏移
     * @param speed    粒子自身速度
     */
    TrailTypeEnum(@NotNull XParticle particle, double y, double speed) {
        this.particle = particle;
        this.y = y;
        this.speed = speed;
    }

    /**
     * 在实体位置生成一次拖尾，每次发射 4 个粒子，三个轴的扩散偏移均为 1。
     *
     * @param entity 实体
     */
    public void spawn(@NotNull Entity entity) {
        Location location = entity.getLocation().clone().add(0.0D, y, 0.0D);
        spawn(location, 4, 1.0D);
    }

    /**
     * 在指定位置生成一次拖尾粒子。
     *
     * @param location 生成位置
     * @param amount   数量
     * @param offset   扩散范围
     */
    public void spawnAt(@NotNull Location location, int amount, double offset) {
        spawn(location, amount, offset);
    }

    /**
     * 使用对应附加数据生成粒子。
     *
     * @param location 粒子位置
     * @param amount   粒子数量
     * @param offset   三个坐标轴的扩散范围
     */
    private void spawn(@NotNull Location location, int amount, double offset) {
        if (particle == XParticle.DUST) {
            ParticleDisplay.of(particle)
                    .withCount(amount)
                    .offset(offset)
                    .withExtra(speed)
                    .withColor(java.awt.Color.RED, 1.0F)
                    .spawn(location);
        } else if (particle == XParticle.ENTITY_EFFECT) {
            ParticleDisplay.of(particle)
                    .withCount(amount)
                    .offset(offset)
                    .withExtra(speed)
                    .withColor(new java.awt.Color(255, 0, 255), 1.0F)
                    .spawn(location);
        } else {
            ParticleDisplay.of(particle)
                    .withCount(amount)
                    .offset(offset)
                    .withExtra(speed)
                    .spawn(location);
        }
    }

}
