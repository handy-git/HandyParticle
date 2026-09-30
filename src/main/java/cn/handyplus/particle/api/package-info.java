/**
 * HandyParticle 对外 API。
 *
 * <p>第三方插件通过本包启用或关闭粒子效果：
 * <pre>{@code
 * // 启用内置预置
 * HandyParticleApi.enable(player, ParticleTypeEnum.TRAIL_HEART);
 *
 * // 启用指定运动模式与颜色的拖尾
 * HandyParticleApi.enable(player, new CustomTrailParticle(
 *         ParticleTypeEnum.TRAIL_COLOR, TrailModeTypeEnum.HELIX, new Color(0x55CCFF)));
 *
 * // 关闭该实体 TRAIL 类别下的效果
 * ParticleTypeEnum.TRAIL_HEART.disable(player);
 * }</pre>
 *
 * <p>本包方法的签名中还会出现 {@code ParticleCategoryTypeEnum}、{@code TrailModeTypeEnum}、
 * {@code WingModelEnum}、{@code WingColorEnum}，它们同样属于公开契约。
 *
 * <p>所有方法请在主线程调用：内部会校验实体有效性，并通过调度器绑定任务生命周期。
 *
 * <p>注意 {@code HandyParticleApi.disable(Entity)} 与 {@code HandyParticleApi.disableAll()}
 * 是全局语义，会一并清除其他插件设置的效果。
 */
package cn.handyplus.particle.api;
