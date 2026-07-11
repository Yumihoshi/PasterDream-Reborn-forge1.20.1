package com.pasterdream.pasterdreammod.client.model;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.entity.FoxFireEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoxFireModel extends GeoModel<FoxFireEntity> {
    @Override
    public ResourceLocation getModelResource(FoxFireEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/fox_fire.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoxFireEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/entities/fox_fire.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FoxFireEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/fox_fire.animation.json");
    }
}
