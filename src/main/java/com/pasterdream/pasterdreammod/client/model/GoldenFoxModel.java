package com.pasterdream.pasterdreammod.client.model;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.entity.GoldenFoxEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldenFoxModel extends GeoModel<GoldenFoxEntity> {
    @Override
    public ResourceLocation getModelResource(GoldenFoxEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/golden_fox.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldenFoxEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/entities/" + animatable.getTexture() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenFoxEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/golden_fox.animation.json");
    }
}
