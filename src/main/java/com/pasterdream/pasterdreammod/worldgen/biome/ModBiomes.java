package com.pasterdream.pasterdreammod.worldgen.biome;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import com.pasterdream.pasterdreammod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {

    public static final ResourceKey<Biome> DYEDREAM_PLAINS =
            ResourceKey.create(Registries.BIOME,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_plains"));

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(DYEDREAM_PLAINS, new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(1.0f)
                .downfall(0.35f)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(0x79A6FF)
                        .fogColor(0xC0D8FF)
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .foliageColorOverride(0xFFFDC6F2)
                        .grassColorOverride(0xFFFDC6F2)
                        .ambientParticle(
                                new AmbientParticleSettings(ModParticleTypes.LEAVES_PARTICLE.get(), 0.005f))
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE,
                                new MobSpawnSettings.SpawnerData(EntityType.ALLAY, 20, 1, 2))
                        // TODO: pink_slime (weight 40, 2-4), pink_chicken (weight 10, 1-1)
                        .build())
                .generationSettings(new BiomeGenerationSettings.Builder(placedFeatures, carvers)
                        .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
                        //.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)
                        //.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON)
                        //.addFeature(GenerationStep.Decoration.LAKES,
                        //       MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND)
                        .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                                ModPlacedFeatures.DYEDREAM_TREE)
                        .build())
                .build());
    }
}
