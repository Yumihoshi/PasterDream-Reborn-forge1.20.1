package com.pasterdream.pasterdreammod.world.block.researchtable;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ResearchTableBlockEntityRenderer extends GeoBlockRenderer<ResearchTableBlockEntity>
{
    public ResearchTableBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new ResearchTableBlockModel());
    }
}
