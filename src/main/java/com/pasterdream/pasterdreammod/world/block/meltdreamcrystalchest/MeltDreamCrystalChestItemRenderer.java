package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MeltDreamCrystalChestItemRenderer extends GeoItemRenderer<MeltDreamCrystalChestItem>
{
    public MeltDreamCrystalChestItemRenderer()
    {
        super(new MeltDreamCrystalChestItemModel());
    }

    @Override
    public RenderType getRenderType(MeltDreamCrystalChestItem animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return RenderType.entityTranslucent(texture);
    }
}
