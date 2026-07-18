package com.pasterdream.pasterdreammod.worldgen.dimension;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.lang.reflect.Constructor;
import java.util.List;

public class ModLevelStems {

    public static final ResourceKey<LevelStem> DYEDREAM_WORLD =
            ResourceKey.create(Registries.LEVEL_STEM,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

    public static final ResourceKey<LevelStem> LAMP_SHADOW_WORLD =
            ResourceKey.create(Registries.LEVEL_STEM,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "lamp_shadow_world"));

    @SuppressWarnings("unchecked")
    private static MultiNoiseBiomeSource createMultiNoiseSource(
            Climate.ParameterList<Holder<Biome>> params) {
        try {
            Constructor<MultiNoiseBiomeSource> ctor = MultiNoiseBiomeSource.class
                    .getDeclaredConstructor(Either.class);
            ctor.setAccessible(true);
            return ctor.newInstance(Either.left(params));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MultiNoiseBiomeSource", e);
        }
    }

    public static void bootstrap(BootstapContext<LevelStem> context) {
        // 获取依赖注册表的引用
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);

        // 引用染梦维度群系
        Holder<Biome> dyedreamPlains = biomes.getOrThrow(ModBiomes.DYEDREAM_PLAINS);
        Holder<Biome> dyedreamMushroomMountains = biomes.getOrThrow(ModBiomes.DYEDREAM_MUSHROOM_MOUNTAINS);
        Holder<Biome> dyedreamSnowyPlains = biomes.getOrThrow(ModBiomes.DYEDREAM_SNOWY_PLAINS);
        Holder<Biome> dyedreamFrozenOcean = biomes.getOrThrow(ModBiomes.DYEDREAM_FROZEN_OCEAN);
        Holder<Biome> dyedreamOcean = biomes.getOrThrow(ModBiomes.DYEDREAM_OCEAN);
        // 引用自定义的维度类型和噪声设置
        Holder<DimensionType> dimType = dimensionTypes.getOrThrow(ModDimensionTypes.DYEDREAM_WORLD);
        Holder<NoiseGeneratorSettings> dimNoise = noiseSettings.getOrThrow(ModNoiseSettings.DYEDREAM_WORLD);

        // 多噪声群系源 — 参数值域[-2,2]，大陆性C是海洋/陆地的唯一区分维度
        //  群系     温度          湿度          大陆性         侵蚀          怪异度
        //  冻洋  T[-2.0,2.0]  H[-2.0,2.0]  C[-2.0,-0.19]  E[-2.0,2.0]  W[-2.0,-0.5]
        //  暖洋  T[-2.0,2.0]  H[-2.0,2.0]  C[-2.0,-0.19]  E[-2.0,2.0]  W[-0.5, 2.0]
        //  雪原  T[-2.0,-0.5] H[-2.0,0.5]  C[-0.19,2.0]  E[-0.5,2.0]  W[-2.0,2.0]
        //  平原  T[-0.7,0.7]  H[-2.0,2.0]  C[-0.19,2.0]  E[-2.0,2.0]  W[-2.0,2.0]
        //  菇山  T[ 0.5,2.0]  H[-0.5,2.0]  C[-0.19,2.0]  E[-0.5,2.0]  W[-2.0,2.0]
        //  估算占比: 冻洋~17% 暖洋~28% 雪原~13% 平原~30% 菇山~13%
        Climate.ParameterList<Holder<Biome>> biomeParams = new Climate.ParameterList<>(List.<Pair<Climate.ParameterPoint, Holder<Biome>>>of(
                // 染梦冻洋 — 暖/寒海域由怪异度[-2.0,-0.5]定义
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.span(-2.0F, -0.19F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-2.0F, -0.5F),
                                0L
                        ),
                        dyedreamFrozenOcean
                ),
                // 染梦海洋 — 怪异度[-0.5,2.0]与冻洋互补
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.span(-2.0F, -0.19F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-0.5F, 2.0F),
                                0L
                        ),
                        dyedreamOcean
                ),
                // 染梦雪原 — 寒冷陆地 T[-2.0,-0.5] H[-2.0,0.5] E[-0.5,2.0]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(-2.0F, -0.5F),
                                Climate.Parameter.span(-2.0F, 0.5F),
                                Climate.Parameter.span(-0.19F, 2.0F),
                                Climate.Parameter.span(-0.5F, 2.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                0L
                        ),
                        dyedreamSnowyPlains
                ),
                // 染梦平原 — 陆地主体 T[-0.7,0.7] H[-2.0,2.0] E[-2.0,2.0]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(-0.7F, 0.7F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.span(-0.19F, 2.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                0L
                        ),
                        dyedreamPlains
                ),
                // 粉顶菇山地 — 温暖高地 T[0.5,2.0] H[-0.5,2.0] E[-0.5,2.0]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(0.5F, 2.0F),
                                Climate.Parameter.span(-0.5F, 2.0F),
                                Climate.Parameter.span(-0.19F, 2.0F),
                                Climate.Parameter.span(-0.5F, 2.0F),
                                Climate.Parameter.point(0.0F),
                                Climate.Parameter.span(-2.0F, 2.0F),
                                0L
                        ),
                        dyedreamMushroomMountains
                )
        ));
        MultiNoiseBiomeSource biomeSource = createMultiNoiseSource(biomeParams);
        ChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(biomeSource, dimNoise);

        context.register(DYEDREAM_WORLD, new LevelStem(dimType, chunkGenerator));

        // ===== 灯影之下维度 =====
        Holder<Biome> shadowNyliumWastes = biomes.getOrThrow(ModBiomes.SHADOW_NYLIUM_WASTES);
        Holder<Biome> shadowForest = biomes.getOrThrow(ModBiomes.SHADOW_FOREST);
        Holder<Biome> shadowRuins = biomes.getOrThrow(ModBiomes.SHADOW_RUINS);
        Holder<DimensionType> lampShadowDimType = dimensionTypes.getOrThrow(ModDimensionTypes.LAMP_SHADOW_WORLD);
        Holder<NoiseGeneratorSettings> lampShadowNoise = noiseSettings.getOrThrow(ModNoiseSettings.LAMP_SHADOW_WORLD);

        Climate.ParameterList<Holder<Biome>> lampShadowBiomeParams = new Climate.ParameterList<>(List.<Pair<Climate.ParameterPoint, Holder<Biome>>>of(
                // shadow_nylium_wastes（菌索荒原）：海洋区域 C[-1, 0.2]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(0F, 1F),
                                Climate.Parameter.span(0F, 1F),
                                Climate.Parameter.span(-1F, 0.2F),
                                Climate.Parameter.span(-1F, 0F),
                                Climate.Parameter.span(-1F, 0F),
                                Climate.Parameter.point(0F),
                                0L
                        ),
                        shadowNyliumWastes
                ),
                // shadow_forest（阴影森林）：寒冷陆地 T[-1, 0.1] H[-1, -0.3] C[0.35, 1]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(-1F, 0.1F),
                                Climate.Parameter.span(-1F, -0.3F),
                                Climate.Parameter.span(0.35F, 1F),
                                Climate.Parameter.span(0.25F, 1F),
                                Climate.Parameter.span(0.25F, 1F),
                                Climate.Parameter.point(0F),
                                0L
                        ),
                        shadowForest
                ),
                // shadow_ruins（阴影古迹）：过渡区域 C[0.1, 0.35]
                Pair.of(
                        new Climate.ParameterPoint(
                                Climate.Parameter.span(0F, 0.1F),
                                Climate.Parameter.span(-0.3F, 0.1F),
                                Climate.Parameter.span(0.1F, 0.35F),
                                Climate.Parameter.span(-0.1F, 0.3F),
                                Climate.Parameter.span(-0.1F, 0.3F),
                                Climate.Parameter.point(0F),
                                0L
                        ),
                        shadowRuins
                )
        ));
        MultiNoiseBiomeSource lampShadowBiomeSource = createMultiNoiseSource(lampShadowBiomeParams);
        ChunkGenerator lampShadowChunkGenerator = new NoiseBasedChunkGenerator(lampShadowBiomeSource, lampShadowNoise);

        context.register(LAMP_SHADOW_WORLD, new LevelStem(lampShadowDimType, lampShadowChunkGenerator));
    }
}
