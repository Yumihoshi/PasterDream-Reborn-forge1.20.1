package com.pasterdream.pasterdreammod.world.block.foxsculpture;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoxSculptureItemModel extends GeoModel<FoxSculptureItem> {
    @Override
    public ResourceLocation getModelResource(FoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/golden_fox_sculpture.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/golden_fox_sculpture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/golden_fox_sculpture.animation.json");
    }
}
