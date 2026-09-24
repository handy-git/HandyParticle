# HandyParticle

HandyParticle 是基于 HandyLib 的独立 Bukkit 粒子插件，为其他插件提供粒子翅膀与运动轨迹 API。

## 包结构

- `cn.handyplus.particle.api`：对外暴露的 `HandyParticleApi`。
- `cn.handyplus.particle.core.trail`：轨迹参数、发射器与运动算法。
- `cn.handyplus.particle.core.wing`：翅膀组合枚举、点阵数据和渲染实现。
- `cn.handyplus.particle.listener`：粒子任务的生命周期监听器。

## 功能

- 内置 8 种翅膀模型和 7 种粒子图案，共 56 种固定组合。
- 提供与粒子类型解耦的运动轨迹算法，由调用方决定发射的粒子。
- 同一玩家启用新效果时自动替换旧任务，玩家退出时自动停止任务。
- 插件关闭时统一清理全部粒子任务。

## 环境要求

- Java 8 目标字节码。
- HandyLib 3.43.3。
- Bukkit/Spigot/Paper/Folia 服务端。翅膀彩色粒子使用 `Particle.DustOptions`，不兼容缺少该类的 1.13 以下版本；Folia 使用玩家实体调度器，尚未经过实机验证。

## 安装与对接

服务器只需安装 `HandyParticle.jar`，插件已内置并隔离 HandyLib。业务插件在编译时添加 HandyParticle API 依赖，并在 `plugin.yml` 中声明依赖：

```xml
<dependencies>
    <dependency>
        <groupId>cn.handyplus.particle</groupId>
        <artifactId>HandyParticle</artifactId>
        <version>1.0.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

```yaml
depend: [HandyParticle]
```

## 使用

HandyParticle 会在插件启动时自动初始化。以下示例展示翅膀、轨迹和统一的关闭入口：

```java
import cn.handyplus.particle.api.HandyParticleApi;
import cn.handyplus.particle.core.trail.ParticleTrailPatternEnum;
import cn.handyplus.particle.core.wing.ParticleWingTypeEnum;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {

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
        return HandyParticleApi.enable(player, ParticleTrailPatternEnum.HELIX, "FLAME");
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

玩家在线时，`enable` 发起调度请求并返回 `true`；玩家离线时返回 `false`。Folia 实体若在提交瞬间退休，底层可能不创建任务，`enable` 无法据此返回 `false`。同一玩家启用翅膀或轨迹时，旧效果会被替换，两种效果不能同时启用。调用
`HandyParticleApi.disableAll()` 可关闭全部玩家的效果。
调用方若提供了自定义发射器，应在自身插件关闭前停止其创建的效果，避免停用后继续执行发射器代码。

## 翅膀组合

`ParticleWingTypeEnum` 的常量命名格式为 `{模型}_{图案}`。

模型：`ANGEL`、`BUTTERFLY`、`BUTTERFLY2`、`ELF`、`LONG`、`SHORT`、`DEMON`、`NIGHT`。

图案：`GRADIENT1`、`GRADIENT2`、`GRADIENT3`、`MUSHROOM`、`FIRE`、`COLORS`、`FLY`。

每个模型都支持全部 7 种图案，例如 `ANGEL_GRADIENT1`、`BUTTERFLY_COLORS`、`DEMON_FIRE` 和 `NIGHT_FLY`。

## 粒子运动算法

内置 `CIRCLE`、`HELIX`、`SPIRAL`、`WAVES`、`PULSE` 和 `SHIELD`。算法负责计算位置，`ParticleEmitter` 由调用方实现，可以发射任意
Bukkit 粒子、彩色粒子或带额外数据的粒子。省略 `ParticleTrailOptions` 参数时使用默认值。
普通粒子可直接传 XParticle 名称，例如 `"FLAME"`；名称不存在、当前服务端不支持或粒子需要额外数据时，启用时抛出 `IllegalArgumentException`。彩色粉尘等带数据的粒子仍需使用 `ParticleEmitter` 重载。
参数值必须有限，半径和高度不能为负，长度、每帧增长高度及粒子数量等计数必须大于零；无效参数会抛出 `IllegalArgumentException`。
为限制单轮计算量，`strands` 最多 200，`waveStrands` 最多 100，`baseParticles` 最多 50；内置轨迹单轮最多调用发射器 200 次，实际粒子数量由调用方决定。

## 生命周期

- HandyParticle 插件启动时自动注册玩家退出监听器并初始化 HandyLib。
- 粒子任务每 2 Tick 刷新一次，玩家退出时自动停止并移除。
- HandyParticle 插件关闭时停止全部任务并释放 HandyLib 资源。

## 重载配置

拥有 `handyparticle.reload` 权限的管理员可执行 `/handyparticle reload` 或 `/hp reload`，重新读取配置和语言文件。重载不会中断正在运行的粒子任务。
