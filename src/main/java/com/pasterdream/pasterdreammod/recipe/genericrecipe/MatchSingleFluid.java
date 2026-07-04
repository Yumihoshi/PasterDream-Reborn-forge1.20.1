package com.pasterdream.pasterdreammod.recipe.genericrecipe;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class MatchSingleFluid
{
    public static FluidStack match(FluidIngredient fluidIngredient, FluidStack matchFluid)
    {
        if(fluidIngredient.isTag())
        {
            List<FluidStack> ListFluidStackFromTag = fluidIngredient.getListFluidStackFromTag();
            for(FluidStack fluidStack : ListFluidStackFromTag)
            {
                if(fluidStack.getFluid().equals(matchFluid.getFluid()))
                {
                    return fluidStack;
                }
            }
            return null;
        }
            else
            {
                FluidStack fluidStack = fluidIngredient.getFluidStack();
                if(fluidStack.getFluid().equals(matchFluid.getFluid()))
                {
                    return fluidStack;
                }
                    else
                    {
                        return null;
                    }
            }
    }
}
