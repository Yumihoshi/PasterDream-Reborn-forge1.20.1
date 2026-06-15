package com.pasterdream.pasterdreammod.world.block.fluidblock;

import com.pasterdream.pasterdreammod.init.ModFluids;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ShadowLiquidBlock extends LiquidBlock
{
    public ShadowLiquidBlock()
    {
        super(() -> (FlowingFluid) ModFluids.FLOWING_SHADOW_LIQUID.get(), Properties.of().mapColor(MapColor.WATER).strength(100.0F).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
    }
}
