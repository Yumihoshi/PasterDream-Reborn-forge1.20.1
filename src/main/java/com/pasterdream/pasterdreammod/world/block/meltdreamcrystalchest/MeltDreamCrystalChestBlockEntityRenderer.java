package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MeltDreamCrystalChestBlockEntityRenderer extends GeoBlockRenderer<MeltDreamCrystalChestBlockEntity>
{
    public MeltDreamCrystalChestBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new MeltDreamCrystalChestBlockModel());
    }
}
