package com.pasterdream.pasterdreammod.compat.jei.researchtableresearch;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import com.pasterdream.pasterdreammod.world.block.researchtable.ResearchTableResearchRecipe;

import java.util.ArrayList;
import java.util.List;

public class ResearchTableResearchJEIRecipe
{
    private List<FluidIngredient> inputFluidIngredients = new ArrayList<>();
    private List<ItemIngredient> inputItemIngredients = new ArrayList<>();
    private List<ItemIngredient> outputItemIngredients = new ArrayList<>();

    public ResearchTableResearchJEIRecipe(ResearchTableResearchRecipe recipe)
    {
        inputFluidIngredients.add(recipe.getFluidInput());

        inputItemIngredients.add(recipe.getPen());
        inputItemIngredients.add(recipe.getUnresolvedNote());

        outputItemIngredients.add(new ItemIngredient(recipe.getOutput().getItem(), recipe.getOutput().getTag(), recipe.getOutput().getCount(), recipe.getUnresolvedNote().getNbt()));
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
