package com.pasterdream.pasterdreammod.recipe.dreamaccumulatorrecipe;

import net.minecraft.world.item.ItemStack;

public class DreamAccumulatorRecipeProcessor
{
    public static ItemStack processor(ItemStack recipeOutput, ItemStack inventoryOutput)
    {
        if(ItemStack.isSameItemSameTags(recipeOutput, inventoryOutput) && recipeOutput.getCount() + inventoryOutput.getCount() <= recipeOutput.getMaxStackSize())
        {
            ItemStack output = inventoryOutput.copy();
            output.grow(recipeOutput.getCount());
            return output;
        }
            else
            {
                if(inventoryOutput == ItemStack.EMPTY)
                {
                    return recipeOutput;
                }
                    else
                    {
                        return null;
                    }
            }
    }
}
