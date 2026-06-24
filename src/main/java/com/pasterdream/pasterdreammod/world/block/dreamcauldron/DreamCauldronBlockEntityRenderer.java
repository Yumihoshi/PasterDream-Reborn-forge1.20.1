package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DreamCauldronBlockEntityRenderer extends GeoBlockRenderer<DreamCauldronBlockEntity>
{
    public DreamCauldronBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new DreamCauldronBlockModel());
    }
}
