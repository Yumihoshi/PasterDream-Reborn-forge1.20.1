package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import com.pasterdream.pasterdreammod.helper.fluidingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.init.ModRecipes;
import com.pasterdream.pasterdreammod.recipe.GenericPasterDreamRecipe;
import com.pasterdream.pasterdreammod.recipe.GenericPasterDreamRecipeMatchResult;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Optional;

public class DreamCauldronRecipe extends GenericPasterDreamRecipe
{
    public DreamCauldronRecipe(ResourceLocation id, List<FluidIngredient> fluidInputs, List<Ingredient> itemInputs, List<Ingredient> itemOutputs)
    {
        super(id, fluidInputs, itemInputs, List.of(), itemOutputs, 0);
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        return false;
    }

    @Override
    public Optional<GenericPasterDreamRecipeMatchResult> matchesWithSlots(List<FluidStack> fluidStacks, List<ItemStack> itemStacks)
    {
        return super.matchesWithSlots(fluidStacks, itemStacks);
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.DREAM_CAULDRON_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.DREAM_CAULDRON.get();
    }
}
