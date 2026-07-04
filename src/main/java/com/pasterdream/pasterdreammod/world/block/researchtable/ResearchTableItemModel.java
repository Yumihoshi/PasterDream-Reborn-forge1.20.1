package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ResearchTableItemModel extends GeoModel<ResearchTableItem>
{
    @Override
    public ResourceLocation getModelResource(ResearchTableItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "geo/research_table.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ResearchTableItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/block/research_table.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ResearchTableItem animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "animations/research_table.animation.json");
    }
}
