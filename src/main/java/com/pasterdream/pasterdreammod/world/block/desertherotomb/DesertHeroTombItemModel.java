package com.pasterdream.pasterdreammod.world.block.desertherotomb;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DesertHeroTombItemModel extends GeoModel<DesertHeroTombItem> {
    @Override
    public ResourceLocation getModelResource(DesertHeroTombItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/desert_hero_tomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DesertHeroTombItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/desert_hero_tomb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DesertHeroTombItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/desert_hero_tomb.animation.json");
    }
}
