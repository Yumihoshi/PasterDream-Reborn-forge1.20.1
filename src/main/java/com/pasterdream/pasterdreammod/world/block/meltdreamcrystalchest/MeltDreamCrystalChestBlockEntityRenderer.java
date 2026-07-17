package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import javax.annotation.Nullable;

public class MeltDreamCrystalChestBlockEntityRenderer extends GeoBlockRenderer<MeltDreamCrystalChestBlockEntity>
{
    public MeltDreamCrystalChestBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new MeltDreamCrystalChestBlockModel());
    }

    @Override
    public RenderType getRenderType(MeltDreamCrystalChestBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return RenderType.entityTranslucent(texture);
    }
}
