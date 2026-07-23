package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.init.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Warden.class)
public class WardenTargetMixin {

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void onSetTarget(LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player player
                && player.hasEffect(ModEffects.SCULK_ARMOR_BUFF.get())) {
            ci.cancel();
        }
    }

    @Inject(method = "serverAiStep", at = @At("HEAD"))
    private void onServerAiStep(CallbackInfo ci) {
        Warden self = (Warden) (Object) this;
        LivingEntity target = self.getTarget();
        if (target instanceof Player player
                && player.hasEffect(ModEffects.SCULK_ARMOR_BUFF.get())) {
            self.setTarget(null);
            self.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            self.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
        }
    }
}
