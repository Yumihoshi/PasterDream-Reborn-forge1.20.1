package com.pasterdream.pasterdreammod.world.fluid;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.init.ModFluids;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class MeltdreamLiquidFluid extends ForgeFlowingFluid
{
    public static final ForgeFlowingFluid.Properties PROPERTIES = (new ForgeFlowingFluid.Properties(ModFluids.MELTDREAM_LIQUID_TYPE, ModFluids.MELTDREAM_LIQUID, ModFluids.FLOWING_MELTDREAM_LIQUID)).explosionResistance(100.0F).tickRate(3).bucket(ModItems.MELTDREAM_LIQUID_BUCKET).block(() -> (LiquidBlock) ModBlocks.MELTDREAM_LIQUID.get());

    private MeltdreamLiquidFluid()
    {
        super(PROPERTIES);
    }

    public static class Source extends MeltdreamLiquidFluid
    {
        public int getAmount(FluidState state)
        {
            return 8;
        }

        public boolean isSource(FluidState state)
        {
            return true;
        }
    }

    public static class Flowing extends MeltdreamLiquidFluid
    {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder)
        {
            super.createFluidStateDefinition(builder);
            builder.add(new Property[]{LEVEL});
        }

        public int getAmount(FluidState state)
        {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state)
        {
            return false;
        }
    }

    @Override
    public FluidType getFluidType()
    {
        return ModFluids.MELTDREAM_LIQUID_TYPE.get();
    }
}
