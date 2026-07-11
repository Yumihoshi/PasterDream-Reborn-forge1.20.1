package com.pasterdream.pasterdreammod.world.block.foxsculpture;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoxSculptureBlockModel extends GeoModel<FoxSculptureBlockEntity> {
    @Override
    public ResourceLocation getModelResource(FoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/golden_fox_sculpture.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/golden_fox_sculpture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FoxSculptureBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/golden_fox_sculpture.animation.json");
    }
}
