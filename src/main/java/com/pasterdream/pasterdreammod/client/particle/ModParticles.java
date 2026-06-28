package com.pasterdream.pasterdreammod.client.particle;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.LEAVES_PARTICLE.get(), LeavesParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.SHARP_SWORD_SLASH.get(), SharpSwordSlashParticle.Provider::new);
    }
}
