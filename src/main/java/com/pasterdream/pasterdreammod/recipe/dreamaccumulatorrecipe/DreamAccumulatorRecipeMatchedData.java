package com.pasterdream.pasterdreammod.recipe.dreamaccumulatorrecipe;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

public record DreamAccumulatorRecipeMatchedData(Holder<Biome> biome, int time, ItemStack catalyst, double catalystMultiplier, ItemStack output){}
