package com.pasterdream.pasterdreammod.world.block.desertherotomb;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DesertHeroTombBlockEntityRenderer extends GeoBlockRenderer<DesertHeroTombBlockEntity> {
    public DesertHeroTombBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DesertHeroTombBlockModel());
    }
}
