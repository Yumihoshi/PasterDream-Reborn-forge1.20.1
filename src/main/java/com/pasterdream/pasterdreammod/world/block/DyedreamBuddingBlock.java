package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.AmethystClusterBlock;

public class DyedreamBuddingBlock extends Block {
    private static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public DyedreamBuddingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(GROWTH_CHANCE) != 0) {
            return;
        }
        Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
        BlockPos targetPos = pos.relative(direction);
        BlockState targetState = level.getBlockState(targetPos);
        Block newBlock = null;
        if (targetState.isAir()) {
            newBlock = ModBlocks.SMALL_DYEDREAM_BUD.get();
        } else if (targetState.is(ModBlocks.SMALL_DYEDREAM_BUD.get())) {
            newBlock = ModBlocks.MEDIUM_DYEDREAM_BUD.get();
        } else if (targetState.is(ModBlocks.MEDIUM_DYEDREAM_BUD.get())) {
            newBlock = ModBlocks.LARGE_DYEDREAM_BUD.get();
        }
        if (newBlock != null) {
            BlockState newState = newBlock.defaultBlockState()
                    .setValue(AmethystClusterBlock.FACING, direction)
                    .setValue(AmethystClusterBlock.WATERLOGGED, targetState.is(Blocks.WATER));
            level.setBlockAndUpdate(targetPos, newState);
        }
    }
}
