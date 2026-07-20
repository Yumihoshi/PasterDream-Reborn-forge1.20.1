package com.pasterdream.pasterdreammod.world.item;

import com.mojang.logging.LogUtils;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class FishingLootsHandler {

    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    public static final ResourceLocation DEEP_SEA_TREASURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/deep_sea_treasure");
    public static final ResourceLocation DYEDREAM_DEEP_SEA_TREASURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/dyedream_deep_sea_treasure");

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (!event.getName().equals(BuiltInLootTables.FISHING)) return;

        LootPool pool = event.getTable().getPool("main");
        if (pool == null) return;

        addEntry(pool, LootTableReference.lootTableReference(DEEP_SEA_TREASURE).setWeight(10).setQuality(2).build());
        addEntry(pool, LootTableReference.lootTableReference(DYEDREAM_DEEP_SEA_TREASURE).setWeight(10).setQuality(2).build());
    }

    @SuppressWarnings("unchecked")
    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        try {
            Field entriesField = ObfuscationReflectionHelper.findField(LootPool.class, "f_79023_");
            entriesField.setAccessible(true);

            LootPoolEntryContainer[] oldEntries = (LootPoolEntryContainer[]) entriesField.get(pool);
            ArrayList<LootPoolEntryContainer> newEntries = new ArrayList<>(List.of(oldEntries));

            if (newEntries.stream().anyMatch(e -> e == entry)) {
                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
            }

            newEntries.add(entry);

            LootPoolEntryContainer[] newEntriesArray = new LootPoolEntryContainer[newEntries.size()];
            newEntries.toArray(newEntriesArray);
            entriesField.set(pool, newEntriesArray);
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to inject fishing treasure into loot table", e);
        }
    }
}
