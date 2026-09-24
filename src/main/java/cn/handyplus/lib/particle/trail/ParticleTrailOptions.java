package cn.handyplus.lib.particle.trail;

import lombok.Getter;

/**
 * 内置粒子轨迹的可选参数。
 *
 * @author handy
 */
@Getter
public final class ParticleTrailOptions {
    /**
     * 相对玩家的纵向偏移。
     */
    private double y = 0.0D;
    /**
     * 轨迹半径。
     */
    private double radius = 1.0D;
    /**
     * 每次刷新的角度增量。
     */
    private double speed = 30.0D;
    /**
     * 每次刷新的高度增量。
     */
    private double verticalSpeed = 0.1D;
    /**
     * 轨迹最大高度。
     */
    private double maxHeight = 2.2D;
    /**
     * 波浪轨迹长度。
     */
    private double length = 5.0D;
    /**
     * 波浪轨迹每帧增长的高度。
     */
    private double grow = 0.2D;
    /**
     * 波浪轨迹的弧度增量。
     */
    private double radial = Math.PI / 30.0D;
    /**
     * 螺旋轨迹的初始旋转弧度。
     */
    private double rotation = Math.PI / 4.0D;
    /**
     * 螺旋轨迹的旋转条数。
     */
    private int strands = 14;
    /**
     * 波浪轨迹的旋转条数。
     */
    private int waveStrands = 3;
    /**
     * 螺旋轨迹的粒子步数。
     */
    private int particles = 40;
    /**
     * 波浪轨迹横向粒子数量。
     */
    private int baseParticles = 15;
    /**
     * 波浪轨迹基础粒子的发射间隔。
     */
    private int baseInterval = 10;
    /**
     * 是否每隔一段时间旋转轨迹方向。
     */
    private boolean rotate90;

    /**
     * 创建默认参数。
     *
     * @return 默认参数
     */
    public static ParticleTrailOptions defaults() {
        return new ParticleTrailOptions();
    }

    /**
     * @param value 相对玩家的纵向偏移
     * @return 当前参数
     */
    public ParticleTrailOptions y(double value) {
        y = value;
        return this;
    }

    /**
     * @param value 轨迹半径
     * @return 当前参数
     */
    public ParticleTrailOptions radius(double value) {
        radius = value;
        return this;
    }

    /**
     * @param value 每次刷新时的角度增量
     * @return 当前参数
     */
    public ParticleTrailOptions speed(double value) {
        speed = value;
        return this;
    }

    /**
     * @param value 每次刷新时的高度增量
     * @return 当前参数
     */
    public ParticleTrailOptions verticalSpeed(double value) {
        verticalSpeed = value;
        return this;
    }

    /**
     * @param value 最大高度
     * @return 当前参数
     */
    public ParticleTrailOptions maxHeight(double value) {
        maxHeight = value;
        return this;
    }

    /**
     * @param value 波浪轨迹长度
     * @return 当前参数
     */
    public ParticleTrailOptions length(double value) {
        length = value;
        return this;
    }

    /**
     * 设置波浪轨迹每帧增长的高度。
     *
     * @param value 正数高度
     * @return 当前参数
     */
    public ParticleTrailOptions grow(double value) {
        if (value <= 0.0D || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("grow must be a finite positive number");
        }
        grow = value;
        return this;
    }

    /**
     * @param value 波浪轨迹的弧度增量
     * @return 当前参数
     */
    public ParticleTrailOptions radial(double value) {
        radial = value;
        return this;
    }

    /**
     * @param value 螺旋轨迹初始旋转弧度
     * @return 当前参数
     */
    public ParticleTrailOptions rotation(double value) {
        rotation = value;
        return this;
    }

    /**
     * @param value 螺旋轨迹的旋转条数
     * @return 当前参数
     */
    public ParticleTrailOptions strands(int value) {
        strands = Math.max(1, value);
        return this;
    }

    /**
     * @param value 波浪轨迹的旋转条数
     * @return 当前参数
     */
    public ParticleTrailOptions waveStrands(int value) {
        waveStrands = Math.max(1, value);
        return this;
    }

    /**
     * @param value 螺旋轨迹的粒子步数
     * @return 当前参数
     */
    public ParticleTrailOptions particles(int value) {
        particles = Math.max(1, value);
        return this;
    }

    /**
     * @param value 波浪轨迹横向粒子数量
     * @return 当前参数
     */
    public ParticleTrailOptions baseParticles(int value) {
        baseParticles = Math.max(1, value);
        return this;
    }

    /**
     * @param value 波浪轨迹横向粒子的间隔帧数
     * @return 当前参数
     */
    public ParticleTrailOptions baseInterval(int value) {
        baseInterval = Math.max(1, value);
        return this;
    }

    /**
     * @param value 是否在螺旋往返时旋转九十度
     * @return 当前参数
     */
    public ParticleTrailOptions rotate90(boolean value) {
        rotate90 = value;
        return this;
    }
}
