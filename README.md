# HandyParticle

HandyParticle 是基于 HandyLib 的粒子翅膀与运动轨迹依赖库。

## 包结构

- `cn.handyplus.lib.particle`：统一的 `HandyParticleApi`。
- `cn.handyplus.lib.particle.listener`：粒子任务的生命周期监听器。
- `cn.handyplus.lib.particle.trail`：轨迹参数、发射器与运动算法。
- `cn.handyplus.lib.particle.wing`：翅膀组合枚举、点阵数据和渲染实现。

## 功能

- 内置 8 种翅膀模型和 7 种粒子图案，共 56 种固定组合。
- 提供与粒子类型解耦的运动轨迹算法，由调用方决定发射的粒子。
- 同一玩家启用新效果时自动替换旧任务，玩家退出时自动停止任务。
- 插件关闭时统一清理全部粒子任务。

## 环境要求

- Java 8 目标字节码。
- HandyLib 3.43.2。
- Bukkit/Spigot/Paper 服务端。翅膀彩色粒子使用 `Particle.DustOptions`，不兼容缺少该类的 1.13 以下版本；Folia 尚未验证线程安全。

## Maven

在业务插件的 `pom.xml` 中添加以下依赖，业务插件还需提供对应版本的 HandyLib：

```xml
<dependencies>
    <dependency>
        <groupId>cn.handyplus.lib.particle</groupId>
        <artifactId>HandyParticle</artifactId>
        <version>1.0.0</version>
    </dependency>
    <dependency>
        <groupId>cn.handyplus.lib</groupId>
        <artifactId>HandyLib</artifactId>
        <version>3.43.2</version>
    </dependency>
</dependencies>
```

## 使用

先初始化 HandyLib，再初始化 HandyParticle。以下示例展示翅膀、轨迹和统一的关闭入口：

```java
import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.particle.HandyParticleApi;
import cn.handyplus.lib.particle.trail.ParticleTrailOptions;
import cn.handyplus.lib.particle.trail.ParticleTrailPatternEnum;
import cn.handyplus.lib.particle.wing.ParticleWingTypeEnum;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {

    /** {@inheritDoc} */
    @Override
    public void onEnable() {
        InitApi.getInstance(this);
        HandyParticleApi.init(this);
    }

    /** {@inheritDoc} */
    @Override
    public void onDisable() {
        HandyParticleApi.shutdown();
        InitApi.disable();
    }

    /**
     * 为在线玩家启用固定翅膀。
     *
     * @param player 在线玩家
     * @return 启用是否成功
     */
    public boolean enableWing(Player player) {
        return HandyParticleApi.enable(player, ParticleWingTypeEnum.ANGEL_FIRE);
    }

    /**
     * 为在线玩家启用螺旋轨迹。
     *
     * @param player 在线玩家
     * @return 启用是否成功
     */
    public boolean enableTrail(Player player) {
        return HandyParticleApi.enable(
                player,
                ParticleTrailPatternEnum.HELIX,
                ParticleTrailOptions.defaults().radius(4).particles(40),
                (target, location, tick) -> target.spawnParticle(Particle.FLAME, location, 1)
        );
    }

    /**
     * 关闭玩家当前的粒子效果。
     *
     * @param player 玩家
     */
    public void disableEffect(Player player) {
        HandyParticleApi.disable(player);
    }
}
```

模块已初始化、玩家在线且任务创建成功时，`enable` 返回 `true`；模块未初始化或玩家离线时返回 `false`。同一玩家启用翅膀或轨迹时，旧效果会被替换，两种效果不能同时启用。调用
`HandyParticleApi.disableAll()` 可关闭全部玩家的效果。

## 翅膀组合

`ParticleWingTypeEnum` 的常量命名格式为 `{模型}_{图案}`。

模型：`ANGEL`、`BUTTERFLY`、`BUTTERFLY2`、`ELF`、`LONG`、`SHORT`、`DEMON`、`NIGHT`。

图案：`GRADIENT1`、`GRADIENT2`、`GRADIENT3`、`MUSHROOM`、`FIRE`、`COLORS`、`FLY`。

每个模型都支持全部 7 种图案，例如 `ANGEL_GRADIENT1`、`BUTTERFLY_COLORS`、`DEMON_FIRE` 和 `NIGHT_FLY`。

## 粒子运动算法

内置 `CIRCLE`、`HELIX`、`SPIRAL`、`WAVES`、`PULSE` 和 `SHIELD`。算法负责计算位置，`ParticleEmitter` 由调用方实现，可以发射任意
Bukkit 粒子、彩色粒子或带额外数据的粒子。省略 `ParticleTrailOptions` 参数时使用默认值。
参数值必须有限，半径和高度不能为负，长度、每帧增长高度及粒子数量等计数必须大于零；无效参数会抛出 `IllegalArgumentException`。

## 生命周期

- `HandyParticleApi.init(plugin)` 注册玩家退出监听器，同一运行周期只初始化一次。
- 粒子任务每 2 Tick 刷新一次，玩家退出时自动停止并移除。
- `HandyParticleApi.shutdown()` 停止全部任务并重置模块状态；应在 `InitApi.disable()` 之前调用。
