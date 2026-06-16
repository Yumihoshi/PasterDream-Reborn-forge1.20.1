package com.pasterdream.pasterdreammod.compat.jei.claypanrecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ClaypanJEIRecipe
{
    private final FluidStack fluidInput;
    private final ItemStack output;
    private final int time;

    public ClaypanJEIRecipe(FluidStack fluidInput, ItemStack output, int time)
    {
        this.fluidInput = fluidInput;
        this.output = output;
        this.time = time;
    }

    public FluidStack getFluidInput()
    {
        return fluidInput;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public int getTime()
    {
        return time;
    }
}
