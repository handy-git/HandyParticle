package cn.handyplus.particle.core.weapon;

import com.cryptomorin.xseries.particles.XParticle;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 内置武器攻击粒子效果。
 *
 * <p>粒子取自 SwordEffects 与 PvPEffects 两个参考项目的效果清单，
 * 用 XSeries 跨版本实现。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum WeaponEffectTypeEnum {
    /**
     * 末影信号。
     */
    ENDER_SIGNAL(XParticle.PORTAL, 12),
    /**
     * 刷怪箱火焰。
     */
    SPAWNER_FLAME(XParticle.SMOKE, 14),
    /**
     * 雪铲。
     */
    SNOW_SHOVEL(XParticle.SNOWFLAKE, 16),
    /**
     * 受伤。
     */
    HURT(XParticle.ANGRY_VILLAGER, 10),
    /**
     * 浓烟。
     */
    SMOKE(XParticle.LARGE_SMOKE, 6),
    /**
     * 烈焰人火球。
     */
    FLAME(XParticle.FLAME, 12),
    /**
     * 爱心。
     */
    HEART(XParticle.HEART, 8),
    /**
     * 音符。
     */
    NOTE(XParticle.NOTE, 8),
    /**
     * 瞬间法术。
     */
    SPELL(XParticle.INSTANT_EFFECT, 10),
    /**
     * 传送门。
     */
    PORTAL(XParticle.PORTAL, 16),
    /**
     * 暴击。
     */
    CRIT(XParticle.CRIT, 14),
    /**
     * 气泡。
     */
    BUBBLE(XParticle.BUBBLE, 12),
    /**
     * 火焰。
     */
    FIRE(XParticle.FLAME, 12),
    /**
     * 横扫。
     */
    SWEEP(XParticle.SWEEP_ATTACK, 6),
    /**
     * 伤害指示。
     */
    DAMAGE_INDICATOR(XParticle.DAMAGE_INDICATOR, 8),
    /**
     * 雪球。
     */
    SNOWBALL(XParticle.ITEM_SNOWBALL, 14),
    /**
     * 史莱姆。
     */
    SLIME(XParticle.ITEM_SLIME, 8),
    /**
     * 水花。
     */
    SPLASH(XParticle.SPLASH, 12),
    /**
     * 村民喜悦。
     */
    VILLAGER_HAPPY(XParticle.HAPPY_VILLAGER, 10),
    /**
     * 女巫魔咒。
     */
    WITCH(XParticle.WITCH, 12),
    /**
     * 云。
     */
    CLOUD(XParticle.CLOUD, 8),
    /**
     * 火花。
     */
    FIREWORK(XParticle.FIREWORK, 8),
    /**
     * 大型爆炸。
     */
    LARGE_EXPLOSION(XParticle.EXPLOSION, 4),
    /**
     * 熔岩迸溅。
     */
    LAVA_POP(XParticle.LAVA, 10),
    /**
     * 灵光。
     */
    GLOW(XParticle.END_ROD, 8);

    /**
     * 粒子类型。
     */
    private final XParticle particle;
    /**
     * 单次攻击生成的粒子数量。
     */
    private final int count;

}
