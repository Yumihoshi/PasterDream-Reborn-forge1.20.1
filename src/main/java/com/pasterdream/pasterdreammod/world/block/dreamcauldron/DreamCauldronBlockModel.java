package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DreamCauldronBlockModel extends GeoModel<DreamCauldronBlockEntity>
{
    @Override
    public ResourceLocation getModelResource(DreamCauldronBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/dream_cauldron.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DreamCauldronBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/dream_cauldron.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DreamCauldronBlockEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/dream_cauldron.animation.json");
    }
}
