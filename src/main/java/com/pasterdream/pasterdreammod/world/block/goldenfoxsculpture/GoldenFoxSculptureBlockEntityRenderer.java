package com.pasterdream.pasterdreammod.world.block.goldenfoxsculpture;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GoldenFoxSculptureBlockEntityRenderer extends GeoBlockRenderer<GoldenFoxSculptureBlockEntity> {
    public GoldenFoxSculptureBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new GoldenFoxSculptureBlockModel());
    }
}
