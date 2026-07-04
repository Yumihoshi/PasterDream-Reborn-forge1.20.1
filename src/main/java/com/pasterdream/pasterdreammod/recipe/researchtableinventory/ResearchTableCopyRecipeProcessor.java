package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ResearchTableCopyRecipeProcessor
{
    public static ResearchTableCopyRecipeInventoryWithOutputSlot processing(ResearchTableCopyRecipeInventory recipeRequire, ResearchTableCopyRecipeInventoryWithOutputSlot machineInventory, RandomSource random)
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

        ItemStack requireSourceBook = recipeRequire.sourceBook();
        ItemStack machineSourceBook = machineInventory.sourceBook();
        ItemStack lessSourceBook = ItemStack.EMPTY;
        if(ItemStack.isSameItem(requireSourceBook, machineSourceBook) && machineSourceBook.getCount() >= requireSourceBook.getCount())
        {
            lessSourceBook = machineSourceBook.copy();
            lessSourceBook.shrink(requireSourceBook.getCount());
        }
            else
            {
                return null;
            }

        ItemStack requireMaterial = recipeRequire.material();
        ItemStack machineMaterial = machineInventory.material();
        ItemStack lessMaterial = ItemStack.EMPTY;
        if(ItemStack.isSameItem(requireMaterial, machineMaterial) && machineMaterial.getCount() >= requireMaterial.getCount())
        {
            lessMaterial = machineMaterial.copy();
            lessMaterial.shrink(requireMaterial.getCount());
        }
            else
            {
                return null;
            }

        ItemStack machineOutput = machineInventory.output();
        ItemStack output = machineSourceBook.copy();
        output.setCount(2 * requireSourceBook.getCount());

        if(machineInventory.output().isEmpty())
        {
            return new ResearchTableCopyRecipeInventoryWithOutputSlot(lessFluidStack, lessPen, lessSourceBook, lessMaterial, output);
        }
        else
            if (ItemStack.isSameItemSameTags(machineOutput, output) && machineOutput.getCount() + output.getCount() <= output.getMaxStackSize())
            {
                output.setCount(output.getCount() + machineOutput.getCount());
                return new ResearchTableCopyRecipeInventoryWithOutputSlot(lessFluidStack, lessPen, lessSourceBook, lessMaterial, output);
            }
                else
                {
                    return null;
                }
    }
}
