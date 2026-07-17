package com.pasterdream.pasterdreammod.world.block.shadow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ThickShadowBlock extends Block {
    public ThickShadowBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK)
                .sound(SoundType.WOOL)
                .strength(1f, 0.75f)
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.getBlock() == this || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 10;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }
}
