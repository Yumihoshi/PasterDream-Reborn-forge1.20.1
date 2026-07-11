package com.pasterdream.pasterdreammod.world.block.goldenfoxsculpture;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldenFoxSculptureBlockModel extends GeoModel<GoldenFoxSculptureBlockEntity> {
    @Override
    public ResourceLocation getModelResource(GoldenFoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/golden_fox_sculpture.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldenFoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/golden_fox_sculpture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenFoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/golden_fox_sculpture.animation.json");
    }
}
