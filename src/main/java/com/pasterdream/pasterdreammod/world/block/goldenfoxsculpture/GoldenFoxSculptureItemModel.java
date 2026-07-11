package com.pasterdream.pasterdreammod.world.block.goldenfoxsculpture;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldenFoxSculptureItemModel extends GeoModel<GoldenFoxSculptureItem> {
    @Override
    public ResourceLocation getModelResource(GoldenFoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/golden_fox_sculpture.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldenFoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/golden_fox_sculpture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenFoxSculptureItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/golden_fox_sculpture.animation.json");
    }
}
