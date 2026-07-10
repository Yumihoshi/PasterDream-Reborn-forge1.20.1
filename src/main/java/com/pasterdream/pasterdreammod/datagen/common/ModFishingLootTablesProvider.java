package com.pasterdream.pasterdreammod.datagen.common;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

/**
 * 钓鱼深海秘宝战利品表 datagen provider。
 * <p>
 * 每个 biome 一个 pool，带 location_check 条件过滤维度+群系。
 * pool 内用 quality 权重分离普通/super 变体，使 super 比例受 luck 影响：
 * <pre>
 *   normal: weight=30, quality=0
 *   super:  weight=1,  quality=1
 * </pre>
 * luck=1 时 super ≈ 6%，luck=5 时 ≈ 17%，逼近原作 min(luck*3%, 50%)。
 * 1.20.1 的 LocationPredicate 不支持 biome tag，因此逐个列出具体 biome。
 */
public class ModFishingLootTablesProvider implements LootTableSubProvider {

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        // 深海秘宝 —— 主世界全部 9 个 ocean 变体群系
        LootTable.Builder deepSea = LootTable.lootTable();
        for (String biome : new String[]{"ocean", "deep_ocean", "warm_ocean", "lukewarm_ocean", "cold_ocean",
                "deep_lukewarm_ocean", "deep_cold_ocean", "frozen_ocean", "deep_frozen_ocean"}) {
            deepSea.withPool(oceanPool(ModItems.DEEP_SEA_TREASURE.get(), Level.OVERWORLD,
                    ResourceKey.create(Registries.BIOME,
                            ResourceLocation.fromNamespaceAndPath("minecraft", biome))));
        }
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/deep_sea_treasure"),
                deepSea
        );

        // 染梦深海秘宝 —— 染梦维度 2 个海洋群系
        LootTable.Builder dyedreamDeepSea = LootTable.lootTable();
        for (String biome : new String[]{"dyedream_frozen_ocean", "dyedream_ocean"}) {
            dyedreamDeepSea.withPool(oceanPool(ModItems.DYEDREAM_DEEP_TREASURE.get(), DYEDREAM_WORLD,
                    ResourceKey.create(Registries.BIOME,
                            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, biome))));
        }
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "gameplay/fishing/dyedream_deep_treasure"),
                dyedreamDeepSea
        );
    }

    /**
     * 构建单个 biome 的 pool：location_check 过滤维度+群系，
     * quality 权重分离 normal/super，使 super 比例受 luck 影响。
     */
    private static LootPool.Builder oceanPool(net.minecraft.world.item.Item item, ResourceKey<Level> dimension, ResourceKey<Biome> biome) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .when(LocationCheck.checkLocation(
                        LocationPredicate.Builder.location()
                                .setDimension(dimension)
                                .setBiome(biome)
                ))
                .add(LootItem.lootTableItem(item).setWeight(30).setQuality(0))
                .add(LootItem.lootTableItem(item).setWeight(1).setQuality(1)
                        .apply(SetNbtFunction.setTag(superTag())));
    }

    private static CompoundTag superTag() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("deep_treasure_super", true);
        return tag;
    }
}
