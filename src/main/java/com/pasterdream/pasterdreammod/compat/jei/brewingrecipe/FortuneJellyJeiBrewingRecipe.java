package com.pasterdream.pasterdreammod.compat.jei.brewingrecipe;

import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FortuneJellyJeiBrewingRecipe implements IJeiBrewingRecipe {
    private final List<ItemStack> inputs;
    private final List<ItemStack> ingredients;
    private final ItemStack output;
    private final int brewingSteps;

    public FortuneJellyJeiBrewingRecipe(List<ItemStack> inputs, List<ItemStack> ingredients, ItemStack output, int brewingSteps) {
        this.inputs = inputs;
        this.ingredients = ingredients;
        this.output = output;
        this.brewingSteps = brewingSteps;
    }

    @Override
    public List<ItemStack> getPotionInputs() {
        return inputs;
    }

    @Override
    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemStack getPotionOutput() {
        return output;
    }

    @Override
    public int getBrewingSteps() {
        return brewingSteps;
    }
}
