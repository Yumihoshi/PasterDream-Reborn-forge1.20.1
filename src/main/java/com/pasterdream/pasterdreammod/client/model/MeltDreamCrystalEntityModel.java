package com.pasterdream.pasterdreammod.client.model;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.entity.MeltDreamCrystalEntityEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MeltDreamCrystalEntityModel extends GeoModel<MeltDreamCrystalEntityEntity> {
    @Override
    public ResourceLocation getModelResource(MeltDreamCrystalEntityEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/melt_dream_crystal_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MeltDreamCrystalEntityEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/entities/" + animatable.getTexture() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(MeltDreamCrystalEntityEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/melt_dream_crystal_entity.animation.json");
    }
}
