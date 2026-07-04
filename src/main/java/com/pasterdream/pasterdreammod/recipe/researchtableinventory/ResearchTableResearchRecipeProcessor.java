package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ResearchTableResearchRecipeProcessor
{
    public static ResearchTableResearchRecipeInventory processing(ResearchTableResearchRecipeInventory recipeRequire, ResearchTableResearchRecipeInventory machineInventory, RandomSource random)
    {
        FluidStack requireFluidStack = recipeRequire.fluidStack();
        FluidStack machineFluidStack = machineInventory.fluidStack();
        FluidStack lessFluidStack = FluidStack.EMPTY;
        if(requireFluidStack.getFluid().equals(machineFluidStack.getFluid()))
        {
            if(machineFluidStack.getAmount() > requireFluidStack.getAmount())
            {
                lessFluidStack = new FluidStack(machineFluidStack.getFluid(), machineFluidStack.getAmount() - requireFluidStack.getAmount());
            }
            else
                if(machineFluidStack.getAmount() == requireFluidStack.getAmount())
                {
                    lessFluidStack = FluidStack.EMPTY;
                }
                    else
                    {
                        return null;
                    }
        }

        ItemStack requirePen = recipeRequire.pen();
        ItemStack machinePen = machineInventory.pen();
        ItemStack lessPen = ItemStack.EMPTY;
        if(ItemStack.isSameItem(requirePen, machinePen))
        {
            if(machinePen.isDamageableItem())
            {
                int penCost = (requirePen.hasTag() && requirePen.getTag().contains("Damage")) ? requirePen.getTag().getInt("Damage") : 1;
                int machinePenDurability = machinePen.getMaxDamage() - machinePen.getDamageValue();

                if(machinePenDurability >= penCost)
                {
                    lessPen = machinePen.copy();
                    lessPen.hurt(penCost, random, null);
                }
                    else
                    {
                        return null;
                    }
            }
                else
                {
                    lessPen = machinePen.copy();
                }
        }
            else
            {
                return null;
            }

        ItemStack requireUnresolvedNote = recipeRequire.unresolvedNote();
        ItemStack machineUnresolvedNote = machineInventory.unresolvedNote();
        ItemStack lessUnresolvedNote = ItemStack.EMPTY;
        if(ItemStack.isSameItem(requireUnresolvedNote, machineUnresolvedNote) && machineUnresolvedNote.getCount() >= requireUnresolvedNote.getCount())
        {
            lessUnresolvedNote = machineUnresolvedNote.copy();
            lessUnresolvedNote.shrink(requireUnresolvedNote.getCount());
        }
            else
            {
                return null;
            }

        ItemStack machineOutput = machineInventory.output();
        ItemStack output = new ItemStack(recipeRequire.output().getItem(), recipeRequire.output().getCount());
        output.setTag(machineUnresolvedNote.getTag());
        if(machineInventory.output().isEmpty())
        {
            return new ResearchTableResearchRecipeInventory(lessFluidStack, lessPen, lessUnresolvedNote, output);
        }
        else
            if (ItemStack.isSameItemSameTags(machineOutput, output) && machineOutput.getCount() + output.getCount() <= output.getMaxStackSize())
            {
                return new ResearchTableResearchRecipeInventory(lessFluidStack, lessPen, lessUnresolvedNote, new ItemStack(output.getItem(), machineOutput.getCount() + output.getCount(), output.getTag()));
            }
                else
                {
                    return null;
                }
    }
}
