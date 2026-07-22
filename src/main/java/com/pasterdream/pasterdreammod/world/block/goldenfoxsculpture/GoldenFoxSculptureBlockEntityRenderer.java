package com.pasterdream.pasterdreammod.world.block.goldenfoxsculpture;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import javax.annotation.Nullable;

public class GoldenFoxSculptureBlockEntityRenderer extends GeoBlockRenderer<GoldenFoxSculptureBlockEntity> {
    public GoldenFoxSculptureBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new GoldenFoxSculptureBlockModel());
    }

    @Override
    public RenderType getRenderType(GoldenFoxSculptureBlockEntity animatable, ResourceLocation texture,
                                     @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
