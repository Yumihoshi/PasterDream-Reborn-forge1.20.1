package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public record ResearchTableResearchRecipeInventory(FluidStack fluidStack, ItemStack pen, ItemStack unresolvedNote, ItemStack output){}
