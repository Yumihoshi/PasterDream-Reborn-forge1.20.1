package com.pasterdream.pasterdreammod.compat.jei.researchtablecopy;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import com.pasterdream.pasterdreammod.world.block.researchtable.ResearchTableCopyRecipe;

import java.util.ArrayList;
import java.util.List;

public class ResearchTableCopyJEIRecipe
{
    private List<FluidIngredient> inputFluidIngredients = new ArrayList<>();
    private List<ItemIngredient> inputItemIngredients = new ArrayList<>();
    private List<ItemIngredient> outputItemIngredients = new ArrayList<>();

    public ResearchTableCopyJEIRecipe(ResearchTableCopyRecipe recipe)
    {
        inputFluidIngredients.add(recipe.getFluidInput());

        inputItemIngredients.add(recipe.getPen());
        inputItemIngredients.add(recipe.getSourceBook());
        inputItemIngredients.add(recipe.getMaterial());

        outputItemIngredients.add(new ItemIngredient(recipe.getSourceBook().getItem(), recipe.getSourceBook().getTag(), 2 * recipe.getSourceBook().getCount(), recipe.getSourceBook().getNbt()));
    }

    public List<FluidIngredient> getInputFluidIngredients()
    {
        return inputFluidIngredients;
    }

    public List<ItemIngredient> getInputItemIngredients()
    {
        return inputItemIngredients;
    }

    public List<ItemIngredient> getOutputItemIngredients()
    {
        return outputItemIngredients;
    }
}
