package com.pasterdream.pasterdreammod.world.block.desertherotomb;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DesertHeroTombBlockModel extends GeoModel<DesertHeroTombBlockEntity> {
    @Override
    public ResourceLocation getModelResource(DesertHeroTombBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/desert_hero_tomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DesertHeroTombBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/desert_hero_tomb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DesertHeroTombBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/desert_hero_tomb.animation.json");
    }
}
