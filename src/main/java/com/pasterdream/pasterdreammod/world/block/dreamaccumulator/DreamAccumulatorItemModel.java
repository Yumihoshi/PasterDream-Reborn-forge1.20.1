package com.pasterdream.pasterdreammod.world.block.dreamaccumulator;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DreamAccumulatorItemModel extends GeoModel<DreamAccumulatorItem>
{
    @Override
    public ResourceLocation getModelResource(DreamAccumulatorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/dream_accumulator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DreamAccumulatorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/dream_accumulator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DreamAccumulatorItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/dream_accumulator.animation.json");
    }
}
