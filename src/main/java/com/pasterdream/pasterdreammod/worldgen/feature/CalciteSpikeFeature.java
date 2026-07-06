package com.pasterdream.pasterdreammod.worldgen.feature;

import com.mojang.serialization.Codec;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

/**
 * 方解石尖锥 —— 原作 stone_pillar_0 / stone_pillar_1 NBT 结构的高效 Feature 替代。
 * 生成锥形（类似原版冰刺）的方解石结构，内含约 10% 随机矿石。
 */
public class CalciteSpikeFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState CALCITE = Blocks.CALCITE.defaultBlockState();

    private static final BlockState STALICRIPE = ModBlocks.POLISHED_CALCITE_STALICRIPE.get().defaultBlockState();
    private static final BlockState SMALL_STALICRIPE = ModBlocks.SMALL_POLISHED_CALCITE_STALICRIPE.get().defaultBlockState();

    /** 矿石候选，权重按顺序：染梦石英矿石(5), 染梦尘矿石(3), 琥珀糖矿石(2) */
    private static final List<BlockState> ORE_POOL = List.of(
            ModBlocks.DYEDREAM_QUARTZ_ORE.get().defaultBlockState(),
            ModBlocks.DYEDREAM_DUST_ORE.get().defaultBlockState(),
            ModBlocks.AMBER_CANDY_ORE.get().defaultBlockState()
    );
    private static final int[] ORE_WEIGHTS = {5, 3, 2};
    private static final int ORE_WEIGHT_TOTAL = 10;

    /** 矿石替换概率 ~10% */
    private static final float ORE_CHANCE = 0.10f;

    /** 表面 stalicripe 概率 */
    private static final float STALICRIPE_CHANCE = 0.30f;

    public CalciteSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // 从 origin（高度图位置）向下扫描到方解石岩层，让尖锥扎根在方解石上。
        // 上层泥土/草方块被跳过，避免草方块被动退化。
        BlockPos.MutableBlockPos scan = new BlockPos.MutableBlockPos().set(origin);
        boolean foundCalcite = false;
        for (int dy = 0; dy < 16; dy++) {
            BlockState below = level.getBlockState(scan.move(0, -1, 0));
            if (below.is(Blocks.CALCITE)) {
                foundCalcite = true;
                break;
            }
        }
        if (foundCalcite) {
            // 尖锥从方解石层上方生成，嵌在泥土/草方块中
            origin = scan.above();
        }

        int height = random.nextIntBetweenInclusive(7, 13);
        int baseRadius = random.nextIntBetweenInclusive(2, 3);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int dy = 0; dy < height; dy++) {
            float progress = (float) dy / height;
            float radius = baseRadius * (1.0f - progress);
            int r = (int) Math.ceil(radius);

            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    double distSq = dx * dx + dz * dz;
                    if (distSq > radius * radius) continue;

                    pos.set(origin.getX() + dx, origin.getY() + dy, origin.getZ() + dz);

                    // 可替换的位置 + 染梦地表方块（模仿 calcite_boulder 替换泥土/草方块/沙子）
                    if (!canOverwrite(level.getBlockState(pos))) continue;

                    // 下方支撑检测：底层必须站在实心方块上，上层必须站在已放置的尖锥方块上
                    BlockState below = level.getBlockState(pos.below());
                    if (dy == 0) {
                        if (below.canBeReplaced() || below.is(Blocks.WATER)) continue;
                    } else {
                        if (!below.is(Blocks.CALCITE) && !isOreBlock(below) && below.canBeReplaced()) continue;
                    }

                    if (random.nextFloat() < ORE_CHANCE) {
                        level.setBlock(pos, pickOre(random), 3);
                    } else {
                        level.setBlock(pos, CALCITE, 3);
                    }
                }
            }
        }

        // 表面装饰：上方为空气/可替换的尖锥顶部有概率替换为 stalicripe
        for (int dy = 0; dy < height; dy++) {
            float progress = (float) dy / height;
            float radius = baseRadius * (1.0f - progress);
            int r = (int) Math.ceil(radius);

            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    double distSq = dx * dx + dz * dz;
                    if (distSq > radius * radius) continue;

                    pos.set(origin.getX() + dx, origin.getY() + dy, origin.getZ() + dz);
                    BlockState current = level.getBlockState(pos);
                    if (!current.is(Blocks.CALCITE) && !isOreBlock(current)) continue;

                    BlockState aboveState = level.getBlockState(pos.above());
                    if (aboveState.isAir() || aboveState.is(Blocks.WATER) || aboveState.canBeReplaced()) {
                        if (random.nextFloat() < STALICRIPE_CHANCE) {
                            level.setBlock(pos, random.nextBoolean() ? STALICRIPE : SMALL_STALICRIPE, 3);
                        }
                    }
                }
            }
        }

        return true;
    }

    /** 尖锥可以覆盖的方块：空气/水/植物等可替换方块 + 染梦地表方块 */
    private boolean canOverwrite(BlockState state) {
        return state.canBeReplaced()
                || state.is(ModBlocks.DYEDREAM_GRASS_BLOCK.get())
                || state.is(ModBlocks.DYEDREAM_DIRT.get())
                || state.is(ModBlocks.DYEDREAM_SAND.get());
    }

    private boolean isOreBlock(BlockState state) {
        for (BlockState ore : ORE_POOL) {
            if (state.is(ore.getBlock())) return true;
        }
        return false;
    }

    private BlockState pickOre(RandomSource random) {
        // 加权随机选择矿石（已预先归一化作 10 = ORE_WEIGHT_TOTAL）
        int roll = random.nextInt(ORE_WEIGHT_TOTAL);
        int cumulative = 0;
        for (int i = 0; i < ORE_POOL.size(); i++) {
            cumulative += ORE_WEIGHTS[i];
            if (roll < cumulative) {
                return ORE_POOL.get(i);
            }
        }
        return ORE_POOL.get(0);
    }
}
