package com.pasterdream.pasterdreammod.world.block.shadow;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ShadowNyliumBlock extends Block {

    public ShadowNyliumBlock(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canSurviveHere(state, level, pos)) {
            level.setBlockAndUpdate(pos, ModBlocks.SHADOW_STONE.get().defaultBlockState());
            return;
        }
        RandomSource localRandom = RandomSource.create();
        for (int i = 0; i < 4; i++) {
            BlockPos targetPos = pos.offset(
                    localRandom.nextInt(3) - 1,
                    localRandom.nextInt(5) - 3,
                    localRandom.nextInt(3) - 1);
            BlockState targetState = level.getBlockState(targetPos);
            if (targetState.is(ModBlocks.SHADOW_STONE.get()) && canSpreadTo(level, targetPos)) {
                level.setBlockAndUpdate(targetPos, this.defaultBlockState());
            }
        }
    }

    private static boolean canSurviveHere(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        return !aboveState.isSolidRender(level, above) && aboveState.getFluidState().getAmount() < 8;
    }

    private static boolean canSpreadTo(LevelReader level, BlockPos pos) {
        return canSurviveHere(null, level, pos);
    }
}
