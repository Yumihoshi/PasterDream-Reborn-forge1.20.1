package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(ForgeRegistries.ATTRIBUTES, PasterDreamMod.MOD_ID);

    public static final RegistryObject<Attribute> TELEPORTATION_CD =
            ATTRIBUTES.register("teleportation_cd", () -> new RangedAttribute(
                    "attribute.pasterdream.teleportation_cd", 1.0, 0.0, 10.0).setSyncable(true));
    public static final RegistryObject<Attribute> TELEPORTATION_CONSUME =
            ATTRIBUTES.register("teleportation_consume", () -> new RangedAttribute(
                    "attribute.pasterdream.teleportation_consume", 1.0, 0.0, 10.0).setSyncable(true));
    public static final RegistryObject<Attribute> TELEPORTATION_RANGE =
            ATTRIBUTES.register("teleportation_range", () -> new RangedAttribute(
                    "attribute.pasterdream.teleportation_range", 1.0, 0.0, 10.0).setSyncable(true));

    public static void register(IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    @SubscribeEvent
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, TELEPORTATION_CD.get());
        event.add(EntityType.PLAYER, TELEPORTATION_CONSUME.get());
        event.add(EntityType.PLAYER, TELEPORTATION_RANGE.get());
    }
}
