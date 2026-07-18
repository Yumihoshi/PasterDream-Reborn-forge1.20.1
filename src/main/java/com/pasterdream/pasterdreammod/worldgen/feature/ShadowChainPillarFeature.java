package com.pasterdream.pasterdreammod.worldgen.feature;

import com.mojang.serialization.Codec;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * 阴影锁链柱 —— 由交替朝向的镂空矩形单元堆叠而成。
 * 每单元 4 格高 × 3 格宽 × 1 格厚，相邻单元宽朝向垂直交替，
 * 连接层形成 5 格十字形。
 */
public class ShadowChainPillarFeature extends Feature<NoneFeatureConfiguration> {

    private static final BlockState BLOCK = ModBlocks.SHADOW_STONE_BRICK_WALL.get().defaultBlockState();
    private static final int UNIT_HEIGHT = 4;
    private static final int MIN_UNITS = 5;
    private static final int MAX_UNITS = 24;

    public ShadowChainPillarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int units = random.nextIntBetweenInclusive(MIN_UNITS, MAX_UNITS);
        int totalHeight = units * UNIT_HEIGHT - (units - 1);

        BlockPos.MutableBlockPos check = new BlockPos.MutableBlockPos();
        for (int dy = 0; dy < totalHeight; dy++) {
            check.set(origin.getX(), origin.getY() + dy, origin.getZ());
            if (!canOverwrite(level.getBlockState(check))) {
                return false;
            }
        }

        if (level.getBlockState(origin.below()).canBeReplaced()) {
            return false;
        }

        int cx = origin.getX();
        int cy = origin.getY();
        int cz = origin.getZ();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        Set<BlockPos> placed = new HashSet<>();

        for (int i = 0; i < units; i++) {
            boolean wideOnX = (i % 2 == 0);
            int baseY = cy + i * (UNIT_HEIGHT - 1);
            placeUnit(level, pos, placed, cx, baseY, cz, wideOnX);
        }

        // 根据已知邻接关系强制设置 WallBlock 连接属性
        for (BlockPos p : placed) {
            BlockState current = level.getBlockState(p);
            if (!(current.getBlock() instanceof WallBlock)) continue;

            BlockState corrected = current
                    .setValue(WallBlock.UP, true)
                    .setValue(WallBlock.NORTH_WALL, wallSide(placed.contains(p.north())))
                    .setValue(WallBlock.SOUTH_WALL, wallSide(placed.contains(p.south())))
                    .setValue(WallBlock.EAST_WALL, wallSide(placed.contains(p.east())))
                    .setValue(WallBlock.WEST_WALL, wallSide(placed.contains(p.west())));

            if (corrected != current) {
                level.setBlock(p, corrected, Block.UPDATE_CLIENTS);
            }
        }

        return true;
    }

    private static WallSide wallSide(boolean connected) {
        return connected ? WallSide.LOW : WallSide.NONE;
    }

    private void placeUnit(WorldGenLevel level, BlockPos.MutableBlockPos pos,
                           Set<BlockPos> placed, int cx, int baseY, int cz, boolean wideOnX) {
        for (int dy = 0; dy < UNIT_HEIGHT; dy++) {
            int y = baseY + dy;
            boolean isEdge = (dy == 0 || dy == UNIT_HEIGHT - 1);

            if (wideOnX) {
                if (isEdge) {
                    setBlock(level, pos, placed, cx - 1, y, cz);
                    setBlock(level, pos, placed, cx, y, cz);
                    setBlock(level, pos, placed, cx + 1, y, cz);
                } else {
                    setBlock(level, pos, placed, cx - 1, y, cz);
                    setBlock(level, pos, placed, cx + 1, y, cz);
                }
            } else {
                if (isEdge) {
                    setBlock(level, pos, placed, cx, y, cz - 1);
                    setBlock(level, pos, placed, cx, y, cz);
                    setBlock(level, pos, placed, cx, y, cz + 1);
                } else {
                    setBlock(level, pos, placed, cx, y, cz - 1);
                    setBlock(level, pos, placed, cx, y, cz + 1);
                }
            }
        }
    }

    private void setBlock(WorldGenLevel level, BlockPos.MutableBlockPos pos,
                          Set<BlockPos> placed, int x, int y, int z) {
        pos.set(x, y, z);
        if (canOverwrite(level.getBlockState(pos))) {
            level.setBlock(pos, BLOCK, Block.UPDATE_ALL);
            placed.add(pos.immutable());
        }
    }

    private boolean canOverwrite(BlockState state) {
        return state.canBeReplaced()
                || state.is(ModBlocks.SHADOW_NYLIUM.get())
                || state.is(ModBlocks.SHADOW_STONE.get());
    }
}
