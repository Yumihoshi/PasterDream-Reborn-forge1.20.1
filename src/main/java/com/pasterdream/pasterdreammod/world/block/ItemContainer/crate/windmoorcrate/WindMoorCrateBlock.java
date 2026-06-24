package com.pasterdream.pasterdreammod.world.block.ItemContainer.crate.windmoorcrate;

import com.pasterdream.pasterdreammod.world.block.ItemContainer.crate.CrateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WindMoorCrateBlock extends CrateBlock
{
    public WindMoorCrateBlock(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new WindMoorCrateBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        Direction facing = state.getValue(FACING);
        if(facing == Direction.NORTH || facing == Direction.SOUTH)
        {
            return box(0, 0, 2, 16, 9, 14);
        }
            else
            {
                return box(2, 0, 0, 14, 9, 16);
            }
    }
}
