package com.pasterdream.pasterdreammod.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(NaturalSpawner.class)
public class NaturalSpawnerMixin {

    @Redirect(method = "getRandomPosWithin",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private static int safeNextInt(RandomSource random, int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
