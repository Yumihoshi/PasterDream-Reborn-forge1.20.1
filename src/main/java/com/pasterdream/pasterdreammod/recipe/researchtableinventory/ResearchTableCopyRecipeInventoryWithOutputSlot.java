package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public record ResearchTableCopyRecipeInventoryWithOutputSlot(FluidStack fluidStack, ItemStack pen, ItemStack sourceBook, ItemStack material, ItemStack output){}
