package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MeltDreamCrystalChestItemModel extends GeoModel<MeltDreamCrystalChestItem>
{
    @Override
    public ResourceLocation getModelResource(MeltDreamCrystalChestItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/melt_dream_crystal_chest.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MeltDreamCrystalChestItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/melt_dream_crystal_chest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MeltDreamCrystalChestItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/melt_dream_crystal_chest.animation.json");
    }
}
