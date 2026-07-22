package com.pasterdream.pasterdreammod.recipe.dreamaccumulatorrecipe;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public interface IDreamAccumulatorRecipe
{
    List<TagKey<Biome>> getBiomeTags();
    int getTime();
    ItemIngredient getCatalyst();
    double getCatalystMultiplier();
    ItemStack getOutput();
}
