package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ResearchTableBlockModel extends GeoModel<ResearchTableBlockEntity>
{
    @Override
    public ResourceLocation getModelResource(ResearchTableBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/research_table.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ResearchTableBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/research_table.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ResearchTableBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/research_table.animation.json");
    }
}
