package com.pasterdream.pasterdreammod.world.item.mortar;

import com.pasterdream.pasterdreammod.init.ModItems;
import com.pasterdream.pasterdreammod.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class MortarCraftingRecipe extends ShapelessRecipe {

    public MortarCraftingRecipe(ResourceLocation id, String group, CraftingBookCategory category,
                                ItemStack result, NonNullList<Ingredient> ingredients) {
        super(id, group, category, result, ingredients);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < remaining.size(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.is(ModItems.MORTAR.get())) {
                remaining.set(i, stack.copyWithCount(1));
            }
        }
        return remaining;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MORTAR_CRAFTING_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<MortarCraftingRecipe> {
        private static final ShapelessRecipe.Serializer FALLBACK = new ShapelessRecipe.Serializer();

        @Override
        public MortarCraftingRecipe fromJson(ResourceLocation id, com.google.gson.JsonObject json) {
            ShapelessRecipe vanilla = FALLBACK.fromJson(id, json);
            return new MortarCraftingRecipe(vanilla.getId(), vanilla.getGroup(), vanilla.category(),
                    vanilla.getResultItem(RegistryAccess.EMPTY), vanilla.getIngredients());
        }

        @Override
        public MortarCraftingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ShapelessRecipe vanilla = FALLBACK.fromNetwork(id, buf);
            return new MortarCraftingRecipe(vanilla.getId(), vanilla.getGroup(), vanilla.category(),
                    vanilla.getResultItem(RegistryAccess.EMPTY), vanilla.getIngredients());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MortarCraftingRecipe recipe) {
            FALLBACK.toNetwork(buf, recipe);
        }
    }
}
