package com.pasterdream.pasterdreammod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GoldenrodTeaBuffEffect extends MobEffect {

    public GoldenrodTeaBuffEffect() {
        super(MobEffectCategory.BENEFICIAL, -395670);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.removeEffect(MobEffects.HUNGER);
        entity.removeEffect(MobEffects.CONFUSION);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }
}
