package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DyedreamClusterBlock extends AmethystClusterBlock {
    public DyedreamClusterBlock(int height, int width, Properties properties) {
        super(height, width, properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        BlockPos basePos = pos.relative(direction);
        BlockState baseState = level.getBlockState(basePos);
        return baseState.isFaceSturdy(level, basePos, direction)
                || baseState.is(ModBlocks.DYEDREAM_BUDDING_BLOCK.get());
    }
}
