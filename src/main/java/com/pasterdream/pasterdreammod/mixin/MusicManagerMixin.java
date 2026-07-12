package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.world.dimension.DyedreamDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public class MusicManagerMixin {

    @Shadow @Final private Minecraft minecraft;
    @Shadow private int nextSongDelay;

    @Inject(method = "tick", at = @At("RETURN"))
    private void pasterdream$shortenTransitionDelay(CallbackInfo ci) {
        LocalPlayer player = minecraft.player;
        if (player == null) return;
        if (!player.level().dimension().equals(DyedreamDimension.DYEDREAM_WORLD)) return;

        if (nextSongDelay > 60) {
            nextSongDelay = 60;
        }
    }
}
