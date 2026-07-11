package com.pasterdream.pasterdreammod.client.renderer;

import com.pasterdream.pasterdreammod.client.model.FoxFireModel;
import com.pasterdream.pasterdreammod.world.entity.FoxFireEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class FoxFireRenderer extends GeoEntityRenderer<FoxFireEntity> {
    public FoxFireRenderer(EntityRendererProvider.Context context) {
        super(context, new FoxFireModel());
        withScale(2.4f);
    }

    @Override
    public RenderType getRenderType(FoxFireEntity animatable, ResourceLocation texture,
                                     @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
