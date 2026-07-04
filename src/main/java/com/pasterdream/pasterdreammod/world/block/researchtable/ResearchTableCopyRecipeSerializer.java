package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.google.gson.JsonObject;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ResearchTableCopyRecipeSerializer implements RecipeSerializer<ResearchTableCopyRecipe>
{
    @Override
    public ResearchTableCopyRecipe fromJson(ResourceLocation id, JsonObject json)
    {
        FluidIngredient fluidInput = FluidIngredient.fromJson(json.getAsJsonArray("fluidInputs").get(0).getAsJsonObject());
        ItemIngredient pen = ItemIngredient.fromJson(json.getAsJsonArray("pen").get(0).getAsJsonObject());
        ItemIngredient sourceBook = ItemIngredient.fromJson(json.getAsJsonArray("sourceBook").get(0).getAsJsonObject());
        ItemIngredient material = ItemIngredient.fromJson(json.getAsJsonArray("material").get(0).getAsJsonObject());

        return new ResearchTableCopyRecipe(id, fluidInput, pen, sourceBook, material);
    }

    @Override
    public ResearchTableCopyRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer)
    {
        FluidIngredient fluidInput = FluidIngredient.fromNetwork(buffer);
        ItemIngredient pen = ItemIngredient.fromNetwork(buffer);
        ItemIngredient sourceBook = ItemIngredient.fromNetwork(buffer);
        ItemIngredient material = ItemIngredient.fromNetwork(buffer);

        return new ResearchTableCopyRecipe(id, fluidInput, pen, sourceBook, material);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ResearchTableCopyRecipe recipe)
    {
        recipe.getFluidInput().toNetwork(buffer);
        recipe.getPen().toNetwork(buffer);
        recipe.getSourceBook().toNetwork(buffer);
        recipe.getMaterial().toNetwork(buffer);
    }
}
