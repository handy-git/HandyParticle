package cn.handyplus.particle.core.projectile;

import com.cryptomorin.xseries.particles.XParticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 内置抛射物拖尾效果。
 *
 * <p>名称沿用 ProjectileEffects 的效果命名，粒子改为 XSeries 跨版本实现。
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public enum ProjectileTrailTypeEnum {
    /**
     * 魔法粒子。
     */
    MAGIC(XParticle.WITCH, 1.0D),
    /**
     * 绿色火花。
     */
    GREEN_SPARKLE(XParticle.HAPPY_VILLAGER, 1.0D),
    /**
     * 彩色尘土，可自定义颜色。
     */
    COLOR_DUST(XParticle.DUST, 1.0D),
    /**
     * 暴击。
     */
    CRIT(XParticle.CRIT, 1.0D),
    /**
     * 火花。
     */
    SPARKS(XParticle.FIREWORK, 1.0D),
    /**
     * 火焰。
     */
    FLAME(XParticle.FLAME, 0.0D),
    /**
     * 飘落字符。
     */
    GLYPH(XParticle.ENCHANTED_HIT, 0.0D),
    /**
     * 爱心。
     */
    HEART(XParticle.HEART, 0.0D),
    /**
     * 瞬间法术。
     */
    SPELL(XParticle.INSTANT_EFFECT, 0.0D),
    /**
     * 烟雾。
     */
    SMOKE(XParticle.SMOKE, 0.05D),
    /**
     * 浓烟。
     */
    LARGE_SMOKE(XParticle.LARGE_SMOKE, 0.05D),
    /**
     * 熔岩迸溅。
     */
    LAVA_POP(XParticle.LAVA, 0.0D),
    /**
     * 熔岩滴落。
     */
    LAVA_DRIP(XParticle.DRIPPING_LAVA, 0.0D),
    /**
     * 魔法暴击。
     */
    MAGIC_CRIT(XParticle.ENCHANTED_HIT, 0.5D),
    /**
     * 音符。
     */
    NOTE(XParticle.NOTE, 0.0D),
    /**
     * 附魔。
     */
    ENCHANT(XParticle.ENCHANT, 0.0D),
    /**
     * 传送门。
     */
    PORTAL(XParticle.PORTAL, 0.5D),
    /**
     * 药水旋涡。
     */
    POTION_SWIRL(XParticle.ENTITY_EFFECT, 1.0D),
    /**
     * 史莱姆。
     */
    SLIME(XParticle.ITEM_SLIME, 0.0D),
    /**
     * 雪花。
     */
    SNOW(XParticle.SNOWFLAKE, 0.1D),
    /**
     * 雪球。
     */
    SNOWBALL(XParticle.ITEM_SNOWBALL, 0.0D),
    /**
     * 水花。
     */
    SPLASH(XParticle.SPLASH, 1.0D),
    /**
     * 愤怒村民。
     */
    ANGRY_VILLAGER(XParticle.ANGRY_VILLAGER, 0.0D),
    /**
     * 虚空雾。
     */
    VOID_FOG(XParticle.SQUID_INK, 0.2D),
    /**
     * 水滴。
     */
    WATER_DRIP(XParticle.DRIPPING_WATER, 0.0D),
    /**
     * 末地烛。
     */
    END_ROD(XParticle.END_ROD, 0.0D),
    /**
     * 不死图腾。
     */
    TOTEM(XParticle.TOTEM_OF_UNDYING, 0.1D),
    /**
     * 灵魂火。
     */
    SOUL_FIRE(XParticle.SOUL_FIRE_FLAME, 0.0D),
    /**
     * 金色火花。
     */
    GOLD_SPARKS(XParticle.ELECTRIC_SPARK, 0.2D),
    /**
     * 龙息。
     */
    DRAGON_BREATH(XParticle.DRAGON_BREATH, 0.0D),
    /**
     * 菌丝。
     */
    MYCELIUM(XParticle.MYCELIUM, 0.0D),
    /**
     * 爆炸。
     */
    EXPLOSION(XParticle.EXPLOSION, 0.0D),
    /**
     * 爆炸发射。
     */
    EXPLOSION_EMITTER(XParticle.EXPLOSION_EMITTER, 0.0D);

    /**
     * 粒子类型。
     */
    private final XParticle particle;
    /**
     * 粒子自身额外速度。
     */
    private final double speed;

    /**
     * 列出所有抛射物效果名称。
     *
     * @return 效果名称列表
     */
    @NotNull
    public static List<String> getAll() {
        List<String> result = new ArrayList<>();
        for (ProjectileTrailTypeEnum type : values()) {
            result.add(type.name());
        }
        return result;
    }

}
