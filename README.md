# HandyParticle

HandyParticle 是基于 HandyLib 的独立 Bukkit 粒子插件，为其他插件提供粒子翅膀与运动轨迹 API。

## 包结构

- `cn.handyplus.particle.api`：统一入口 `HandyParticleApi` 与效果类型 `ParticleTypeEnum`。
- `cn.handyplus.particle.core.trail`：轨迹参数、发射器与运动算法的内部实现。
- `cn.handyplus.particle.core.wing`：翅膀组合、点阵数据和渲染实现。
- `cn.handyplus.particle.listener`：粒子任务的生命周期监听器。

## 功能

- 内置 8 种翅膀模型和 7 种粒子图案，共 56 种固定组合。
- 提供运动轨迹算法，由调用方选择普通粒子；未指定时使用红色粉尘。
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
import cn.handyplus.particle.api.ParticleTypeEnum;
import cn.handyplus.particle.core.trail.TrailModeTypeEnum;
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
        return HandyParticleApi.enable(player, ParticleTypeEnum.WING_FIRE_ANGEL);
    }

    /**
     * 为在线玩家启用螺旋轨迹。
     *
     * @param player 在线玩家
     * @return 启用是否成功
     */
    public boolean enableTrail(Player player) {
        return HandyParticleApi.enable(player, ParticleTypeEnum.TRAIL_HEART, TrailModeTypeEnum.CIRCLE);
    }

    /**
     * 使用默认运动模式启用爱心拖尾。
     *
     * @param player 在线玩家
     * @return 启用是否成功
     */
    public boolean enableDefaultTrail(Player player) {
        return HandyParticleApi.enable(player, ParticleTypeEnum.TRAIL_HEART);
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
三参重载用于为普通拖尾指定运动模式。

## 翅膀组合

`ParticleTypeEnum` 的翅膀常量命名格式为 `{模型}_{图案}`。

模型：`ANGEL`、`BUTTERFLY`、`BUTTERFLY2`、`ELF`、`LONG`、`SHORT`、`DEMON`、`NIGHT`。

图案：`GRADIENT1`、`GRADIENT2`、`GRADIENT3`、`MUSHROOM`、`FIRE`、`COLORS`、`FLY`。

每个模型都支持全部 7 种图案，例如 `ANGEL_GRADIENT1`、`BUTTERFLY_COLORS`、`DEMON_FIRE` 和 `NIGHT_FLY`。

## 粒子运动算法

`ParticleTypeEnum` 内置 `CIRCLE`、`HELIX`、`SPIRAL`、`WAVES`、`PULSE` 和 `SHIELD`。两参调用使用红色粉尘，大小为 1。三参调用可传 XParticle 名称，例如 `"FLAME"`；`"DUST"` 和 `"REDSTONE"` 同样使用默认红色粉尘。
名称不存在、当前服务端不支持或其他粒子需要额外数据时，启用时抛出 `IllegalArgumentException`。内置轨迹单轮最多发射 200 个粒子。

## 生命周期

- HandyParticle 插件启动时初始化 HandyLib 和玩家粒子设置表，并注册登录、退出监听器。
- 启用效果时按玩家 UUID 保存类型和轨迹粒子名；主动关闭效果时删除设置。
- 粒子任务每 2 Tick 刷新一次，玩家退出时只停止任务，下次登录自动恢复已保存的设置。
- HandyParticle 插件关闭时停止全部任务并释放 HandyLib 资源，保留已保存的设置。

## 管理员指令

拥有 `handyparticle.reload` 权限的管理员可执行 `/handyparticle reload` 或 `/hp reload`，重新读取配置和语言文件。重载不会中断正在运行的粒子任务。

- `/hp set <玩家> <类别> <效果> [运动模式]`：设置内置粒子效果，类别为 `TRAIL`、`WING`、`BLOCK` 或 `RAIN`。
- `/hp clear <玩家>`：清除玩家当前的粒子效果。

设置和清除分别需要 `handyParticle.set`、`handyParticle.clear` 权限；玩家必须在线。效果和运动模式均通过 Tab 补全选择。
