package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.google.gson.JsonObject;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ResearchTableResearchRecipeSerializer implements RecipeSerializer<ResearchTableResearchRecipe>
{
    @Override
    public ResearchTableResearchRecipe fromJson(ResourceLocation id, JsonObject json)
    {
        FluidIngredient fluidInput = FluidIngredient.fromJson(json.getAsJsonArray("fluidInputs").get(0).getAsJsonObject());
        ItemIngredient pen = ItemIngredient.fromJson(json.getAsJsonArray("pen").get(0).getAsJsonObject());
        ItemIngredient unresolvedNote = ItemIngredient.fromJson(json.getAsJsonArray("unresolvedNote").get(0).getAsJsonObject());
        ItemIngredient output = ItemIngredient.fromJson(json.getAsJsonArray("output").get(0).getAsJsonObject());

        return new ResearchTableResearchRecipe(id, fluidInput, pen, unresolvedNote, output);
    }

    @Override
    public ResearchTableResearchRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer)
    {
        FluidIngredient fluidInput = FluidIngredient.fromNetwork(buffer);
        ItemIngredient pen = ItemIngredient.fromNetwork(buffer);
        ItemIngredient unresolvedNote = ItemIngredient.fromNetwork(buffer);
        ItemIngredient output = ItemIngredient.fromNetwork(buffer);

        return new ResearchTableResearchRecipe(id, fluidInput, pen, unresolvedNote, output);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ResearchTableResearchRecipe recipe)
    {
        recipe.getFluidInput().toNetwork(buffer);
        recipe.getPen().toNetwork(buffer);
        recipe.getUnresolvedNote().toNetwork(buffer);
        recipe.getOutput().toNetwork(buffer);
    }
}
