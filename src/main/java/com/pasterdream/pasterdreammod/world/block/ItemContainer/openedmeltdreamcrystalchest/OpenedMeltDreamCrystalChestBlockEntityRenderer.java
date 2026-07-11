package com.pasterdream.pasterdreammod.world.block.ItemContainer.openedmeltdreamcrystalchest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OpenedMeltDreamCrystalChestBlockEntityRenderer extends GeoBlockRenderer<OpenedMeltDreamCrystalChestBlockEntity>
{
    public OpenedMeltDreamCrystalChestBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new OpenedMeltDreamCrystalChestBlockModel());
    }
}
