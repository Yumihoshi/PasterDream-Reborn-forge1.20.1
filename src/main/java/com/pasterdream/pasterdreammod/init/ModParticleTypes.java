package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, PasterDreamMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> LEAVES_PARTICLE =
            PARTICLE_TYPES.register("leaves_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SHARP_SWORD_SLASH =
            PARTICLE_TYPES.register("sharp_sword_slash", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> DUST_0_PARTICLE =
            PARTICLE_TYPES.register("dust_0_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPORE_PARTICLE =
            PARTICLE_TYPES.register("spore_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BUFF_0_PARTICLE =
            PARTICLE_TYPES.register("buff_0_particle", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SOUL_PARTICLE =
            PARTICLE_TYPES.register("soul_particle", () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
