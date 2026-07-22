package com.pasterdream.pasterdreammod.world.block.dreamaccumulator;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DreamAccumulatorBlockEntityRenderer extends GeoBlockRenderer<DreamAccumulatorBlockEntity>
{
    public DreamAccumulatorBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new DreamAccumulatorBlockModel());
    }
}
