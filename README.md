# HandyParticle

HandyParticle 是基于 HandyLib 的固定坐标粒子翅膀模块。

## 功能

- 内置 8 种翅膀模型和 7 种粒子图案，共 56 种固定组合
- 通过玩家 UUID 启用或关闭粒子翅膀
- 同一玩家重复启用时自动替换原有翅膀任务
- 玩家退出时自动停止对应任务
- 关闭插件时支持统一清理全部任务

## 环境要求

- Java 8 目标字节码
- HandyLib 3.43.2
- Bukkit API 兼容服务端

## Maven

```xml
<dependency>
    <groupId>cn.handyplus.lib.particle</groupId>
    <artifactId>HandyParticle</artifactId>
    <version>1.0.0</version>
</dependency>
```

HandyParticle 依赖 HandyLib，业务插件需要同时提供对应版本的 HandyLib。

## 初始化

在插件启用时先初始化 HandyLib，再初始化 HandyParticle：

```java
import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.particle.ParticleWingApi;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        InitApi.getInstance(this);
        ParticleWingApi.init(this);
    }

    @Override
    public void onDisable() {
        ParticleWingApi.shutdown();
        InitApi.disable();
    }
}
```

建议先执行 `ParticleWingApi.shutdown()`，再执行 `InitApi.disable()`，确保翅膀任务先从模块内部记录中移除。

## 使用

### 启用翅膀

传入在线玩家的 UUID 和对应组合枚举：

```java
boolean enabled = ParticleWingApi.enable(
        player.getUniqueId(),
        ParticleWingType.ANGEL_FIRE
);
```

玩家在线且任务创建成功时返回 `true`；玩家离线时返回 `false`。同一 UUID 再次调用会先停止旧任务，再启用新的组合。

### 关闭翅膀

```java
ParticleWingApi.disable(player.getUniqueId());
```

关闭全部玩家的粒子翅膀：

```java
ParticleWingApi.disableAll();
```

## 翅膀组合

枚举命名格式为：

```text
{模型}_{图案}
```

模型：

```text
ANGEL
BUTTERFLY
BUTTERFLY2
ELF
LONG
SHORT
DEMON
NIGHT
```

图案：

```text
GRADIENT1
GRADIENT2
GRADIENT3
MUSHROOM
FIRE
COLORS
FLY
```

每个模型都支持全部 7 种图案。例如：

```java
ParticleWingType.ANGEL_GRADIENT1
ParticleWingType.BUTTERFLY_COLORS
ParticleWingType.DEMON_FIRE
ParticleWingType.NIGHT_FLY
```

## 生命周期

- `ParticleWingApi.init(plugin)` 注册玩家退出监听器，同一运行周期只初始化一次。
- `ParticleWingApi.enable(uuid, type)` 每 2 Tick 刷新一次翅膀，仅向对应玩家发送粒子。
- 玩家退出时，对应翅膀任务会自动停止并移除。
- `ParticleWingApi.shutdown()` 会停止全部翅膀任务并重置模块初始化状态。