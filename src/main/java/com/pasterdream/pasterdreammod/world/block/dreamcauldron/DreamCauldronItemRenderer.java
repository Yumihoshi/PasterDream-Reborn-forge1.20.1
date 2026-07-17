package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DreamCauldronItemRenderer extends GeoItemRenderer<DreamCauldronItem>
{
    public DreamCauldronItemRenderer()
    {
        super(new DreamCauldronItemModel());
    }

    @Override
    public RenderType getRenderType(DreamCauldronItem animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return RenderType.entityTranslucent(texture);
    }
}
