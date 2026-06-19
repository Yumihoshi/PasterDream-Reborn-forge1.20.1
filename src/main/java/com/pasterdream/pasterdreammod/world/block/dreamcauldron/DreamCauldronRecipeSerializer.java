package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import com.google.gson.JsonObject;
import com.pasterdream.pasterdreammod.helper.fluidingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.recipe.GenericPasterDreamRecipeSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class DreamCauldronRecipeSerializer extends GenericPasterDreamRecipeSerializer<DreamCauldronRecipe>
{
    @Override
    public DreamCauldronRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        List<FluidIngredient> fluidInputs = parseFluidIngredients(json, "fluidInputs");
        List<Ingredient> itemInputs = parseItemIngredients(json, "itemInputs");
        List<Ingredient> itemOutputs = parseItemIngredients(json, "itemOutputs");
        return new DreamCauldronRecipe(recipeId, fluidInputs, itemInputs, itemOutputs);
    }

    @Override
    public DreamCauldronRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
    {
        int fluidCount = buffer.readVarInt();
        List<FluidIngredient> fluidInputs = new ArrayList<>();
        for (int i = 0; i < fluidCount; i++)
        {
            fluidInputs.add(FluidIngredient.fromNetwork(buffer));
        }

        int inputItemCount = buffer.readVarInt();
        List<Ingredient> itemInputs = new ArrayList<>();
        for (int i = 0; i < inputItemCount; i++)
        {
            itemInputs.add(Ingredient.fromNetwork(buffer));
        }

        int outputItemCount = buffer.readVarInt();
        List<Ingredient> itemOutputs = new ArrayList<>();
        for (int i = 0; i < outputItemCount; i++)
        {
            itemOutputs.add(Ingredient.fromNetwork(buffer));
        }

        return new DreamCauldronRecipe(recipeId, fluidInputs, itemInputs, itemOutputs);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, DreamCauldronRecipe recipe)
    {
        buffer.writeVarInt(recipe.getInputFluidIngredients().size());
        for (FluidIngredient fluidIngredient : recipe.getInputFluidIngredients())
        {
            fluidIngredient.toNetwork(buffer);
        }

        buffer.writeVarInt(recipe.getInputItemIngredients().size());
        for (Ingredient ingredient : recipe.getInputItemIngredients())
        {
            ingredient.toNetwork(buffer);
        }

        buffer.writeVarInt(recipe.getOutputItemIngredients().size());
        for (Ingredient ingredient : recipe.getOutputItemIngredients())
        {
            ingredient.toNetwork(buffer);
        }
    }
}
