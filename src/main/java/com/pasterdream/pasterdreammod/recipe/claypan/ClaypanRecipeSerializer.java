package com.pasterdream.pasterdreammod.recipe.claypan;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class ClaypanRecipeSerializer implements RecipeSerializer<ClaypanRecipeImplement>
{
    @Override
    public ClaypanRecipeImplement fromJson(ResourceLocation recipeId, JsonObject json)
    {
        JsonObject fluidObj = json.getAsJsonObject("fluid");
        String fluidName = fluidObj.get("fluid").getAsString();
        int amount = fluidObj.get("amount").getAsInt();
        FluidStack fluid = new FluidStack(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse(fluidName)), amount);
        ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(json.get("result").getAsString())));
        int processingTime = json.get("processingTime").getAsInt();
        return new ClaypanRecipeImplement(recipeId, fluid, result, processingTime);
    }

    @Nullable
    @Override
    public ClaypanRecipeImplement fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
    {
        FluidStack fluid = buffer.readFluidStack();
        ItemStack result = buffer.readItem();
        int processingTime = buffer.readInt();
        return new ClaypanRecipeImplement(recipeId, fluid, result, processingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, ClaypanRecipeImplement recipe)
    {
        buffer.writeFluidStack(recipe.getFluidInput());
        buffer.writeItem(recipe.getResultItem(null));
        buffer.writeInt(recipe.getProcessingTime());
    }
}
