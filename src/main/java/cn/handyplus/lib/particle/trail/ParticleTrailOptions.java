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
     * 设置相对玩家的纵向偏移。
     *
     * @param value 相对玩家的纵向偏移
     * @return 当前参数
     */
    public ParticleTrailOptions y(double value) {
        y = finite(value, "y");
        return this;
    }

    /**
     * 设置轨迹半径。
     *
     * @param value 轨迹半径
     * @return 当前参数
     */
    public ParticleTrailOptions radius(double value) {
        radius = nonNegative(value, "radius");
        return this;
    }

    /**
     * 设置每次刷新的角度增量。
     *
     * @param value 每次刷新时的角度增量
     * @return 当前参数
     */
    public ParticleTrailOptions speed(double value) {
        speed = finite(value, "speed");
        return this;
    }

    /**
     * 设置每次刷新的高度增量。
     *
     * @param value 每次刷新时的高度增量
     * @return 当前参数
     */
    public ParticleTrailOptions verticalSpeed(double value) {
        verticalSpeed = nonNegative(value, "verticalSpeed");
        return this;
    }

    /**
     * 设置轨迹最大高度。
     *
     * @param value 最大高度
     * @return 当前参数
     */
    public ParticleTrailOptions maxHeight(double value) {
        maxHeight = nonNegative(value, "maxHeight");
        return this;
    }

    /**
     * 设置波浪轨迹长度。
     *
     * @param value 波浪轨迹长度
     * @return 当前参数
     */
    public ParticleTrailOptions length(double value) {
        length = positive(value, "length");
        return this;
    }

    /**
     * 设置波浪轨迹每帧增长的高度。
     *
     * @param value 正数高度
     * @return 当前参数
     */
    public ParticleTrailOptions grow(double value) {
        grow = positive(value, "grow");
        return this;
    }

    /**
     * 设置波浪轨迹的弧度增量。
     *
     * @param value 波浪轨迹的弧度增量
     * @return 当前参数
     */
    public ParticleTrailOptions radial(double value) {
        radial = finite(value, "radial");
        return this;
    }

    /**
     * 设置螺旋轨迹的初始旋转弧度。
     *
     * @param value 螺旋轨迹初始旋转弧度
     * @return 当前参数
     */
    public ParticleTrailOptions rotation(double value) {
        rotation = finite(value, "rotation");
        return this;
    }

    /**
     * 设置螺旋轨迹的旋转条数。
     *
     * @param value 螺旋轨迹的旋转条数
     * @return 当前参数
     */
    public ParticleTrailOptions strands(int value) {
        strands = positive(value, "strands");
        return this;
    }

    /**
     * 设置波浪轨迹的旋转条数。
     *
     * @param value 波浪轨迹的旋转条数
     * @return 当前参数
     */
    public ParticleTrailOptions waveStrands(int value) {
        waveStrands = positive(value, "waveStrands");
        return this;
    }

    /**
     * 设置螺旋轨迹的粒子步数。
     *
     * @param value 螺旋轨迹的粒子步数
     * @return 当前参数
     */
    public ParticleTrailOptions particles(int value) {
        particles = positive(value, "particles");
        return this;
    }

    /**
     * 设置波浪轨迹横向粒子数量。
     *
     * @param value 波浪轨迹横向粒子数量
     * @return 当前参数
     */
    public ParticleTrailOptions baseParticles(int value) {
        baseParticles = positive(value, "baseParticles");
        return this;
    }

    /**
     * 设置波浪轨迹基础粒子的发射间隔。
     *
     * @param value 波浪轨迹横向粒子的间隔帧数
     * @return 当前参数
     */
    public ParticleTrailOptions baseInterval(int value) {
        baseInterval = positive(value, "baseInterval");
        return this;
    }

    /**
     * 设置螺旋轨迹是否周期性旋转九十度。
     *
     * @param value 是否在螺旋往返时旋转九十度
     * @return 当前参数
     */
    public ParticleTrailOptions rotate90(boolean value) {
        rotate90 = value;
        return this;
    }

    /**
     * 校验有限数值。
     *
     * @param value 输入值
     * @param name  参数名
     * @return 输入值
     */
    private static double finite(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(name + " must be finite");
        }
        return value;
    }

    /**
     * 校验非负数值。
     *
     * @param value 输入值
     * @param name  参数名
     * @return 输入值
     */
    private static double nonNegative(double value, String name) {
        if (finite(value, name) < 0.0D) {
            throw new IllegalArgumentException(name + " must be non-negative");
        }
        return value;
    }

    /**
     * 校验正数值。
     *
     * @param value 输入值
     * @param name  参数名
     * @return 输入值
     */
    private static double positive(double value, String name) {
        if (finite(value, name) <= 0.0D) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }

    /**
     * 校验正整数。
     *
     * @param value 输入值
     * @param name  参数名
     * @return 输入值
     */
    private static int positive(int value, String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }
}
