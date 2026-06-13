package com.pasterdream.pasterdreammod.world.level.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

public class DyedreamGrassBlock extends Block {
    public DyedreamGrassBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(net.minecraft.world.level.material.MapColor.COLOR_PINK)
                .sound(SoundType.GRASS)
                .strength(0.75F, 0.5F));
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction direction, IPlantable plantable) {
        return true;
    }
}
