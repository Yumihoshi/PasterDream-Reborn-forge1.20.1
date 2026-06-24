package com.pasterdream.pasterdreammod.world.block.dreamcauldron;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DreamCauldronItemRenderer extends GeoItemRenderer<DreamCauldronItem>
{
    public DreamCauldronItemRenderer()
    {
        super(new DreamCauldronItemModel());
    }
}
