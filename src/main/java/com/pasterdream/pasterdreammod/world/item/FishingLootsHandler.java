package com.pasterdream.pasterdreammod.world.item;

import com.mojang.logging.LogUtils;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class FishingLootsHandler {

    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    public static final ResourceLocation DEEP_SEA_TREASURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/deep_sea_treasure");
    public static final ResourceLocation DYEDREAM_DEEP_SEA_TREASURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/dyedream_deep_sea_treasure");
    public static final ResourceLocation SHADOW_DEEP_SEA_TREASURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/shadow_deep_sea_treasure");

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));
    private static final ResourceKey<Level> LAMP_SHADOW_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "lamp_shadow_world"));
    private static final ResourceKey<Biome> SHADOW_OCEAN =
            ResourceKey.create(Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "shadow_ocean"));

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (!event.getName().equals(BuiltInLootTables.FISHING)) return;
        if (event.getTable().getPool("main") == null) return;

        event.setTable(LootTable.lootTable()
                // 主世界池 — 仅在主世界触发
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setDimension(Level.OVERWORLD)))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_FISH)
                                .setWeight(85).setQuality(-1))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_JUNK)
                                .setWeight(10).setQuality(-2))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_TREASURE)
                                .setWeight(5).setQuality(2))
                        .add(LootTableReference.lootTableReference(DEEP_SEA_TREASURE)
                                .setWeight(10).setQuality(2)))
                // 染梦维度池 — 仅在染梦维度触发
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setDimension(DYEDREAM_WORLD)))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_FISH)
                                .setWeight(85).setQuality(-1))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_JUNK)
                                .setWeight(10).setQuality(-2))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_TREASURE)
                                .setWeight(5).setQuality(2))
                        .add(LootTableReference.lootTableReference(DYEDREAM_DEEP_SEA_TREASURE)
                                .setWeight(10).setQuality(2)))
                // 浸影维度·垃圾池 — 灯影维度所有群系
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setDimension(LAMP_SHADOW_WORLD)))
                        .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_JUNK)
                                .setWeight(1)))
                // 浸影维度·秘宝池 — 仅在阴影之海触发
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location()
                                        .setDimension(LAMP_SHADOW_WORLD)
                                        .setBiome(SHADOW_OCEAN)))
                        .add(LootTableReference.lootTableReference(SHADOW_DEEP_SEA_TREASURE)
                                .setWeight(1)))
                .build());
        LOGGER.info("[FishingLoots] Replaced fishing loot table with dimension-aware pools");
    }
}
