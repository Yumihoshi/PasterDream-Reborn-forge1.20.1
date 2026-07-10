package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public class MusicManagerMixin {

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

    @Shadow @Final private Minecraft minecraft;
    @Shadow private int nextSongDelay;

    /**
     * 在染梦维度内大幅缩短音乐切换延迟。
     * 原版公式 nextSongDelay = random(0, minDelay/2) 会造成 0-5 分钟等待，
     * 这里将非零延迟上限控制在 60 ticks（3 秒）以内。
     */
    @Inject(method = "tick", at = @At("RETURN"))
    private void pasterdream$shortenTransitionDelay(CallbackInfo ci) {
        LocalPlayer player = minecraft.player;
        if (player == null) return;
        if (!player.level().dimension().equals(DYEDREAM_WORLD)) return;

        if (nextSongDelay > 60) {
            nextSongDelay = 60;
        }
    }
}
