package com.pasterdream.pasterdreammod.world.block.researchtable;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ResearchTableItemRenderer extends GeoItemRenderer<ResearchTableItem>
{
    public ResearchTableItemRenderer()
    {
        super(new ResearchTableItemModel());
    }
}
