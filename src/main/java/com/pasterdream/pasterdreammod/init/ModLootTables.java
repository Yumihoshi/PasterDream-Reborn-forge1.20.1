package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.functions.SpawnEntityFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLootTables {


    public static final ResourceLocation PICNIC_BASKET_LOOT =
           ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/picnic_basket");
    public static final ResourceLocation DYEDREAM_RELIC_CHEST_LOOT_COMMON =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/dyedream_relic_chest_loot_common");
    public static final ResourceLocation DYEDREAM_RELIC_CHEST_LOOT_RARE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/dyedream_relic_chest_loot_rare");
    public static final ResourceLocation FISHERMAN_HUT_CHEST_LOOT =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/fisherman_hut_chest");


    public static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTION_TYPES =
            DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, PasterDreamMod.MOD_ID);

    /** 在战利品表触发位置上方的生成实体 */
    public static final RegistryObject<LootItemFunctionType> SPAWN_ENTITY_FUNCTION_TYPE =
            LOOT_FUNCTION_TYPES.register("spawn_entity",
                    () -> new LootItemFunctionType(new SpawnEntityFunction.Serializer()));

    public static void register(IEventBus bus) {
        LOOT_FUNCTION_TYPES.register(bus);
    }
}
