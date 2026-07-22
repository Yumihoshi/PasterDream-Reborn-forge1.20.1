package com.pasterdream.pasterdreammod.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class GoldenFoxTradeRecipeSerializer implements RecipeSerializer<GoldenFoxTradeRecipe> {
    @Override
    public GoldenFoxTradeRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
        ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
        return new GoldenFoxTradeRecipe(recipeId, ingredient, result);
    }

    @Override
    public GoldenFoxTradeRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        return new GoldenFoxTradeRecipe(recipeId, ingredient, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, GoldenFoxTradeRecipe recipe) {
        recipe.getIngredient().toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem(null));
    }
}
