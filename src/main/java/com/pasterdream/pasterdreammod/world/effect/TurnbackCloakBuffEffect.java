package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 转身衣装 buff：激活后持续 90s，每 tick 环绕金色与灰尘粒子。
 */
public class TurnbackCloakBuffEffect extends MobEffect {

    public TurnbackCloakBuffEffect() {
        super(MobEffectCategory.BENEFICIAL, -1);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            double x = entity.getX();
            double y = entity.getY() + 1;
            double z = entity.getZ();
            serverLevel.sendParticles(ModParticleTypes.GOLDEN_PARTICLE.get(), x, y, z, 4, 0.4, 0.5, 0.4, 0.01);
            serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), x, y, z, 4, 0.4, 0.5, 0.4, 0.01);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}