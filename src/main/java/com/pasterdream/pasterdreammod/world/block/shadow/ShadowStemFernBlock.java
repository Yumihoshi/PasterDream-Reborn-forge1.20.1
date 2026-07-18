package com.pasterdream.pasterdreammod.world.block.shadow;

import com.pasterdream.pasterdreammod.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public class ShadowStemFernBlock extends DoublePlantBlock {
    public ShadowStemFernBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        return box(3, 0, 3, 13, 16, 13).move(offset.x, offset.y, offset.z);
    }

    @Override
    public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        return groundState.is(ModBlockTags.SHADOW_PLANTS_CAN_PLACE_ON);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        if (blockstate.getValue(HALF) == DoubleBlockHalf.UPPER)
            return groundState.is(this) && groundState.getValue(HALF) == DoubleBlockHalf.LOWER;
        else
            return this.mayPlaceOn(groundState, worldIn, blockpos);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.PLAINS;
    }
}
