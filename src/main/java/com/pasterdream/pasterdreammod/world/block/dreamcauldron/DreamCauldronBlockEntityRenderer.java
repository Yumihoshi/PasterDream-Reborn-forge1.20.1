package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import javax.annotation.Nullable;

public class DreamCauldronBlockEntityRenderer extends GeoBlockRenderer<DreamCauldronBlockEntity>
{
    public DreamCauldronBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new DreamCauldronBlockModel());
    }

    @Override
    public RenderType getRenderType(DreamCauldronBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return RenderType.entityTranslucent(texture);
    }
}
