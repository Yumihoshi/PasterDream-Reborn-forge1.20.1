package com.pasterdream.pasterdreammod.recipe.dreamaccumulatorrecipe;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class MatchSingleBiome
{
    public static Holder<Biome> Match(List<TagKey<Biome>> biomes, Holder<Biome> biome)
    {
        if (biomes.isEmpty())
        {
            return biome;
        }

        for (TagKey<Biome> tag : biomes)
        {
            if (biome.is(tag))
            {
                return biome;
            }
        }
        return null;
    }
}
