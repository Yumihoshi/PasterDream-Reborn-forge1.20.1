package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.init.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin {

    @Shadow
    private int duration;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(LivingEntity entity, Runnable callback, CallbackInfoReturnable<Boolean> cir) {
        MobEffectInstance self = (MobEffectInstance) (Object) this;
        MobEffect effect = self.getEffect();

        if ((effect == MobEffects.HUNGER || effect == MobEffects.CONFUSION)
                && entity.hasEffect(ModEffects.GOLDENROD_TEA_BUFF.get())) {
            this.duration = 0;
        }
    }
}
