package cn.handyplus.particle.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * 粒子选择参数
 *
 * @author handy
 */
@Getter
@AllArgsConstructor
public final class ParticleSelectContext {

    private final String type;

    @Nullable
    private final String mode;

}
