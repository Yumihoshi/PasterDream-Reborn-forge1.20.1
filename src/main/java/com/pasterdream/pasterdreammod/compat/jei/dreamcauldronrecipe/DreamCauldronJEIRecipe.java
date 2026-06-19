package com.pasterdream.pasterdreammod.compat.jei.dreamcauldronrecipe;

import com.pasterdream.pasterdreammod.helper.fluidingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.world.block.dreamcauldron.DreamCauldronRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class DreamCauldronJEIRecipe
{
    private final List<FluidIngredient> fluidIngredients;
    private final List<Ingredient> itemIngredients;
    private final ItemStack outputItem;

    public DreamCauldronJEIRecipe(DreamCauldronRecipe recipe)
    {
        this.fluidIngredients = recipe.getInputFluidIngredients();
        this.itemIngredients = recipe.getInputItemIngredients();
        this.outputItem = recipe.getOutputItemIngredients().get(0).getItems()[0];
    }

    public List<FluidIngredient> getFluidIngredients()
    {
        return fluidIngredients;
    }

    public List<Ingredient> getItemIngredients()
    {
        return itemIngredients;
    }

    public ItemStack getOutput()
    {
        return outputItem;
    }
}
