package com.pasterdream.pasterdreammod.client.renderer;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.entity.PinkChickenEntity;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PinkChickenRenderer extends MobRenderer<PinkChickenEntity, ChickenModel<PinkChickenEntity>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/entities/pink_chicken.png");

    public PinkChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PinkChickenEntity entity) {
        return TEXTURE;
    }
}
