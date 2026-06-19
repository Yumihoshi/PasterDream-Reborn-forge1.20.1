package com.pasterdream.pasterdreammod.world.block.claypan;

import com.google.gson.JsonObject;
import com.pasterdream.pasterdreammod.helper.fluidingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.recipe.GenericPasterDreamRecipeSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class ClaypanRecipeSerializer extends GenericPasterDreamRecipeSerializer<ClaypanRecipe>
{
    @Override
    public ClaypanRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        List<FluidIngredient> fluidInputs = parseFluidIngredients(json, "fluidInputs");
        List<Ingredient> itemOutputs = parseItemIngredients(json, "itemOutputs");
        int processingTime = parseProcessingTime(json);
        return new ClaypanRecipe(recipeId, fluidInputs, itemOutputs, processingTime);
    }

    @Override
    public ClaypanRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
    {
        int inputfluidCount = buffer.readVarInt();
        List<FluidIngredient> fluidInputs = new ArrayList<>();
        for (int i = 0; i < inputfluidCount; i++)
        {
            fluidInputs.add(FluidIngredient.fromNetwork(buffer));
        }

        int outputItemCount = buffer.readVarInt();
        List<Ingredient> itemOutputs = new ArrayList<>();
        for (int i = 0; i < outputItemCount; i++)
        {
            itemOutputs.add(Ingredient.fromNetwork(buffer));
        }
        int processingTime = buffer.readVarInt();
        return new ClaypanRecipe(recipeId, fluidInputs, itemOutputs, processingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ClaypanRecipe recipe)
    {
        buffer.writeVarInt(recipe.getInputFluidIngredients().size());
        for (FluidIngredient fluidIngredient : recipe.getInputFluidIngredients())
        {
            fluidIngredient.toNetwork(buffer);
        }

        buffer.writeVarInt(recipe.getOutputItemIngredients().size());
        for (Ingredient ingredient : recipe.getOutputItemIngredients())
        {
            ingredient.toNetwork(buffer);
        }
        buffer.writeVarInt(recipe.getProcessingTime());
    }
}
