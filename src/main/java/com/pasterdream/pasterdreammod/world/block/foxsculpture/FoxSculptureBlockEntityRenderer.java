package com.pasterdream.pasterdreammod.world.block.foxsculpture;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FoxSculptureBlockEntityRenderer extends GeoBlockRenderer<FoxSculptureBlockEntity> {
    public FoxSculptureBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new FoxSculptureBlockModel());
    }
}
