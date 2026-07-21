package com.pasterdream.pasterdreammod.worldgen.feature;

import com.mojang.serialization.Codec;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ShadowRuinWallFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState[] BLOCK_POOL;
    private static final int[] BLOCK_WEIGHTS;
    private static final int WEIGHT_TOTAL;

    static {
        BLOCK_POOL = new BlockState[]{
                ModBlocks.SHADOW_STONE_BRICK.get().defaultBlockState(),
                ModBlocks.CRACKED_SHADOW_STONE_BRICK.get().defaultBlockState(),
                ModBlocks.NARROW_SHADOW_STONE_BRICK.get().defaultBlockState(),
                ModBlocks.SHADOW_STONE_TILES.get().defaultBlockState(),
                ModBlocks.SHADOW_STONE.get().defaultBlockState()
        };
        BLOCK_WEIGHTS = new int[]{9, 6, 3, 2, 1};
        int total = 0;
        for (int w : BLOCK_WEIGHTS) total += w;
        WEIGHT_TOTAL = total;
    }

    public ShadowRuinWallFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // 检查垂直通路 — 向上扫描到最大可能高度
        BlockPos.MutableBlockPos check = new BlockPos.MutableBlockPos();
        for (int dy = 0; dy < 8; dy++) {
            check.set(origin.getX(), origin.getY() + dy, origin.getZ());
            if (!canOverwrite(level.getBlockState(check))) {
                return false;
            }
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int variant = random.nextInt(100);
        if (variant < 50) {
            buildStraightWall(level, origin, random, pos);
        } else if (variant < 75) {
            buildLCorner(level, origin, random, pos);
        } else {
            buildPillar(level, origin, random, pos);
        }

        return true;
    }

    // ==================== 直墙段 ====================

    private void buildStraightWall(WorldGenLevel level, BlockPos origin, RandomSource random,
                                   BlockPos.MutableBlockPos pos) {
        Direction dir = random.nextBoolean() ? Direction.NORTH : Direction.EAST;
        int length = random.nextIntBetweenInclusive(4, 8);
        List<BlockPos> placed = new ArrayList<>();
        int baseY = origin.getY();

        // 第一遍：检查所有地表柱子下方方块是否为实心
        for (int i = 0; i < length; i++) {
            int ox = origin.getX() + dir.getStepX() * i;
            int oz = origin.getZ() + dir.getStepZ() * i;
            pos.set(ox, baseY - 1, oz);
            if (!level.getBlockState(pos).isSolid()) {
                return;
            }
        }

        // 第二遍：放置方块
        for (int i = 0; i < length; i++) {
            int h = random.nextIntBetweenInclusive(3, 6);
            int ox = origin.getX() + dir.getStepX() * i;
            int oz = origin.getZ() + dir.getStepZ() * i;

            for (int dy = 0; dy < h; dy++) {
                pos.set(ox, baseY + dy, oz);
                if (canOverwrite(level.getBlockState(pos))) {
                    level.setBlock(pos, pickBlock(random), Block.UPDATE_ALL);
                    placed.add(pos.immutable());
                }
            }
        }

        erode(level, placed, random, 0.15f);
    }

    // ==================== L 形墙角 ====================

    private void buildLCorner(WorldGenLevel level, BlockPos origin, RandomSource random,
                              BlockPos.MutableBlockPos pos) {
        Direction dirA = random.nextBoolean() ? Direction.NORTH : Direction.EAST;
        Direction dirB = dirA.getClockWise();
        int lenA = random.nextIntBetweenInclusive(3, 5);
        int lenB = random.nextIntBetweenInclusive(3, 5);
        int baseY = origin.getY();
        List<BlockPos> placed = new ArrayList<>();

        // 第一遍：检查所有地表柱子下方方块是否为实心
        pos.set(origin.getX(), baseY - 1, origin.getZ());
        if (!level.getBlockState(pos).isSolid()) return;

        for (int i = 1; i <= lenA; i++) {
            int ox = origin.getX() + dirA.getStepX() * i;
            int oz = origin.getZ() + dirA.getStepZ() * i;
            pos.set(ox, baseY - 1, oz);
            if (!level.getBlockState(pos).isSolid()) return;
        }
        for (int i = 1; i <= lenB; i++) {
            int ox = origin.getX() + dirB.getStepX() * i;
            int oz = origin.getZ() + dirB.getStepZ() * i;
            pos.set(ox, baseY - 1, oz);
            if (!level.getBlockState(pos).isSolid()) return;
        }

        // 墙角柱
        for (int dy = 0; dy < random.nextIntBetweenInclusive(4, 7); dy++) {
            pos.set(origin.getX(), baseY + dy, origin.getZ());
            if (canOverwrite(level.getBlockState(pos))) {
                level.setBlock(pos, pickBlock(random), Block.UPDATE_ALL);
                placed.add(pos.immutable());
            }
        }

        // 臂 A
        for (int i = 1; i <= lenA; i++) {
            int h = random.nextIntBetweenInclusive(3, 6);
            int ox = origin.getX() + dirA.getStepX() * i;
            int oz = origin.getZ() + dirA.getStepZ() * i;
            for (int dy = 0; dy < h; dy++) {
                pos.set(ox, baseY + dy, oz);
                if (canOverwrite(level.getBlockState(pos))) {
                    level.setBlock(pos, pickBlock(random), Block.UPDATE_ALL);
                    placed.add(pos.immutable());
                }
            }
        }

        // 臂 B
        for (int i = 1; i <= lenB; i++) {
            int h = random.nextIntBetweenInclusive(3, 6);
            int ox = origin.getX() + dirB.getStepX() * i;
            int oz = origin.getZ() + dirB.getStepZ() * i;
            for (int dy = 0; dy < h; dy++) {
                pos.set(ox, baseY + dy, oz);
                if (canOverwrite(level.getBlockState(pos))) {
                    level.setBlock(pos, pickBlock(random), Block.UPDATE_ALL);
                    placed.add(pos.immutable());
                }
            }
        }

        erode(level, placed, random, 0.12f);
    }

    // ==================== 散落石柱 ====================

    private void buildPillar(WorldGenLevel level, BlockPos origin, RandomSource random,
                             BlockPos.MutableBlockPos pos) {
        boolean is2x2 = random.nextFloat() < 0.4f;
        int height = random.nextIntBetweenInclusive(3, 7);
        int baseY = origin.getY();
        int size = is2x2 ? 2 : 1;

        // 第一遍：检查所有地表位置下方方块是否为实心
        for (int dx = 0; dx < size; dx++) {
            for (int dz = 0; dz < size; dz++) {
                pos.set(origin.getX() + dx, baseY - 1, origin.getZ() + dz);
                if (!level.getBlockState(pos).isSolid()) {
                    return;
                }
            }
        }

        // 第二遍：放置方块
        List<BlockPos> placed = new ArrayList<>();
        for (int dx = 0; dx < size; dx++) {
            for (int dz = 0; dz < size; dz++) {
                for (int dy = 0; dy < height; dy++) {
                    pos.set(origin.getX() + dx, baseY + dy, origin.getZ() + dz);
                    if (canOverwrite(level.getBlockState(pos))) {
                        level.setBlock(pos, pickBlock(random), Block.UPDATE_ALL);
                        placed.add(pos.immutable());
                    }
                }
            }
        }

        erode(level, placed, random, 0.18f);
    }

    // ==================== 侵蚀 ====================

    private void erode(WorldGenLevel level, List<BlockPos> placed, RandomSource random, float chance) {
        for (BlockPos p : placed) {
            BlockPos below = p.below();
            if (!placed.contains(below) && !level.getBlockState(below).canBeReplaced()) {
                continue;
            }
            if (random.nextFloat() < chance) {
                level.removeBlock(p, false);
            }
        }
    }

    // ==================== 工具方法 ====================

    private boolean canOverwrite(BlockState state) {
        return state.canBeReplaced()
                || state.is(ModBlocks.SHADOW_NYLIUM.get())
                || state.is(ModBlocks.SHADOW_STONE.get());
    }

    private BlockState pickBlock(RandomSource random) {
        int roll = random.nextInt(WEIGHT_TOTAL);
        int cumulative = 0;
        for (int i = 0; i < BLOCK_POOL.length; i++) {
            cumulative += BLOCK_WEIGHTS[i];
            if (roll < cumulative) {
                return BLOCK_POOL[i];
            }
        }
        return BLOCK_POOL[0];
    }
}
