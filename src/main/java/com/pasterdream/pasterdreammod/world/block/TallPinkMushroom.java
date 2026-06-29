package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.worldgen.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TallPinkMushroom extends DoublePlantBlock implements BonemealableBlock {

    private static final VoxelShape SHAPE = box(2, 0, 2, 14, 16, 14);

    public TallPinkMushroom(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return SHAPE.move(offset.x, offset.y, offset.z);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender(level, pos);
    }

    // ---- BonemealableBlock ----

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        // Only operate from the lower half
        BlockPos lowerPos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        BlockState lowerState = level.getBlockState(lowerPos);

        var registry = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);

        BlockPos origin = find2x2Origin(level, lowerPos);
        if (origin != null) {
            // ---- 2×2 → jungle tree ----
            BlockState[] saved = new BlockState[8]; // 4 positions × 2 halves
            int idx = 0;
            for (int dx = 0; dx < 2; dx++) {
                for (int dz = 0; dz < 2; dz++) {
                    BlockPos pLower = origin.offset(dx, 0, dz);
                    BlockPos pUpper = pLower.above();
                    saved[idx++] = level.getBlockState(pLower);
                    saved[idx++] = level.getBlockState(pUpper);
                    level.removeBlock(pLower, false);
                    level.removeBlock(pUpper, false);
                }
            }

            ConfiguredFeature<?, ?> tree = registry.get(ModConfiguredFeatures.PINK_MUSHROOM_TREE);
            if (tree == null || !tree.place(level, level.getChunkSource().getGenerator(), random, origin)) {
                idx = 0;
                for (int dx = 0; dx < 2; dx++) {
                    for (int dz = 0; dz < 2; dz++) {
                        level.setBlock(origin.offset(dx, 0, dz), saved[idx++], 3);
                        level.setBlock(origin.offset(dx, 0, dz).above(), saved[idx++], 3);
                    }
                }
            }
        } else {
            // ---- single → huge mushroom ----
            // Remove the double plant
            BlockPos upperPos = lowerPos.above();
            BlockState upperState = level.getBlockState(upperPos);
            level.removeBlock(lowerPos, false);
            level.removeBlock(upperPos, false);

            ConfiguredFeature<?, ?> mushroom = registry.get(ModConfiguredFeatures.PINK_HUGE_MUSHROOM);
            if (mushroom == null || !mushroom.place(level, level.getChunkSource().getGenerator(), random, lowerPos)) {
                level.setBlock(lowerPos, lowerState, 3);
                level.setBlock(upperPos, upperState, 3);
            } else {
                scatterShroomlights(level, random, lowerPos);
            }
        }
    }

    // ---- shroomlight helper ----

    private void scatterShroomlights(ServerLevel level, RandomSource random, BlockPos center) {
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        BlockState shroomlight = ModBlocks.PINK_SHROOMLIGHT.get().defaultBlockState();

        for (int y = center.getY() + 2; y <= center.getY() + 7; y++) {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    int dist2 = dx * dx + dz * dz;
                    if (dist2 < 2 || dist2 > 9) continue;
                    if (random.nextFloat() > 0.3f) continue;

                    cursor.set(center.getX() + dx, y, center.getZ() + dz);
                    if (level.getBlockState(cursor).isAir() && hasAdjacentStemOrCap(level, cursor)) {
                        level.setBlock(cursor, shroomlight, 3);
                    }
                }
            }
        }
    }

    private boolean hasAdjacentStemOrCap(ServerLevel level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockState neighbor = level.getBlockState(pos.relative(dir));
            if (neighbor.is(ModBlocks.PINK_MUSHROOM_STEM.get())
                    || neighbor.is(ModBlocks.PINK_MUSHROOM_BLOCK.get())) {
                return true;
            }
        }
        return false;
    }

    // ---- 2×2 detection (uses lower halves) ----

    private BlockPos find2x2Origin(Level level, BlockPos pos) {
        Block selfType = level.getBlockState(pos).getBlock();

        for (int dx = 0; dx < 2; dx++) {
            for (int dz = 0; dz < 2; dz++) {
                BlockPos candidate = pos.offset(-dx, 0, -dz);
                if (is2x2Of(level, candidate, selfType))
                    return candidate;
            }
        }
        return null;
    }

    private boolean is2x2Of(Level level, BlockPos origin, Block expectedType) {
        for (int dx = 0; dx < 2; dx++) {
            for (int dz = 0; dz < 2; dz++) {
                BlockState s = level.getBlockState(origin.offset(dx, 0, dz));
                if (!s.is(expectedType) || s.getValue(HALF) != DoubleBlockHalf.LOWER)
                    return false;
            }
        }
        return true;
    }
}
