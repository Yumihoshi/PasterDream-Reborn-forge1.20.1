package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PasterDreamMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SWORD1 =
            SOUNDS.register("sword1", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "sword1")));
    public static final RegistryObject<SoundEvent> SKILL1 =
            SOUNDS.register("skill1", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "skill1")));
    public static final RegistryObject<SoundEvent> SKILL2 =
            SOUNDS.register("skill2", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "skill2")));
    public static final RegistryObject<SoundEvent> COIN0 =
            SOUNDS.register("coin0", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "coin0")));
    public static final RegistryObject<SoundEvent> COIN1 =
            SOUNDS.register("coin1", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "coin1")));
    public static final RegistryObject<SoundEvent> SKILL0 =
            SOUNDS.register("skill0", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "skill0")));
    public static final RegistryObject<SoundEvent> PLIERS0 =
            SOUNDS.register("pliers0", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "pliers0")));

    public static final RegistryObject<SoundEvent> DOLL = SOUNDS.register("doll", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "doll")));
    public static final RegistryObject<SoundEvent> DYEDREAM_CRACK = SOUNDS.register("dyedream_crack", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_crack")));
    public static final RegistryObject<SoundEvent> DREAM0 = SOUNDS.register("dream0", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dream0")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
