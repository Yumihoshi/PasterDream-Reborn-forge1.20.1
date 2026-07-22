package com.pasterdream.pasterdreammod.world.block.goldenfoxsculpture;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import javax.annotation.Nullable;

public class GoldenFoxSculptureItemRenderer extends GeoItemRenderer<GoldenFoxSculptureItem> {
    public GoldenFoxSculptureItemRenderer() {
        super(new GoldenFoxSculptureItemModel());
    }

    @Override
    public RenderType getRenderType(GoldenFoxSculptureItem animatable, ResourceLocation texture,
                                     @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
