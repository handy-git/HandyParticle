package cn.handyplus.particle.core.trail;

import cn.handyplus.particle.util.ParticleVisibilityUtil;
import com.cryptomorin.xseries.particles.ParticleDisplay;
import com.cryptomorin.xseries.particles.XParticle;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;


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
    TRAIL_PINK_CONFETTI(XParticle.CHERRY_LEAVES, 0.2D),
    /**
     * 可指定颜色的 Dust 粒子拖尾。
     */
    TRAIL_COLOR(XParticle.DUST, 0.0D);

    /**
     * XParticle 粒子。
     */
    @NotNull
    private final XParticle particle;

    /**
     * 相对实体位置的纵向偏移。
     */
    @Getter
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
     * 判断当前粒子是否可指定颜色。
     *
     * @return 是否可指定颜色
     */
    public boolean supportsColor() {
        return this == TRAIL_COLOR;
    }

    /**
     * 使用预先配置的显示对象生成粒子。
     *
     * @param display  预先配置的显示对象
     * @param location 生成位置
     * @param entity   效果所属实体
     */
    void spawnAt(@NotNull ParticleDisplay display, @NotNull Location location, @NotNull Entity entity) {
        ParticleVisibilityUtil.spawn(display, location, entity);
    }

    /**
     * 创建当前拖尾任务使用的显示对象，粒子数量与扩散范围来自运动模式。
     *
     * @param mode  运动模式
     * @param color Dust 粒子颜色
     * @return 预先配置的显示对象
     */
    @NotNull
    ParticleDisplay createDisplay(@NotNull TrailModeTypeEnum mode, @NotNull Color color) {
        ParticleDisplay display = ParticleDisplay.of(particle).withExtra(speed).withCount(mode.getCount()).offset(mode.getOffset());
        if (this.supportsColor()) {
            return display.withColor(color, 1.0F);
        }
        if (particle == XParticle.DUST) {
            return display.withColor(Color.RED, 1.0F);
        } else if (particle == XParticle.ENTITY_EFFECT) {
            return display.withColor(new Color(255, 0, 255), 1.0F);
        }
        return display;
    }

}
