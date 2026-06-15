package com.pasterdream.pasterdreammod.world.fluid;

import com.pasterdream.pasterdreammod.init.ModFluids;
import net.minecraftforge.fluids.FluidType;

public class UncookedDyeDreamFlowerTeaFluid extends PasterDreamBaseFluid
{
    @Override
    public FluidType getFluidType()
    {
        return ModFluids.UNCOOKED_DYE_DREAM_FLOWER_TEA_TYPE.get();
    }
}
