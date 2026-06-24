package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DreamCauldronItemModel extends GeoModel<DreamCauldronItem>
{
    @Override
    public ResourceLocation getModelResource(DreamCauldronItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/dream_cauldron.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DreamCauldronItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/dream_cauldron.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DreamCauldronItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/dream_cauldron.animation.json");
    }
}
