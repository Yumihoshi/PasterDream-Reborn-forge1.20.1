package com.pasterdream.pasterdreammod.client.renderer;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.client.model.Modelslime;
import com.pasterdream.pasterdreammod.world.entity.PinkSlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PinkSlimeRenderer extends MobRenderer<PinkSlimeEntity, Modelslime<PinkSlimeEntity>> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/entities/pink_slime.png");

    public PinkSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new Modelslime<>(context.bakeLayer(Modelslime.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PinkSlimeEntity entity) {
        return TEXTURE;
    }

    @Override
    protected boolean isBodyVisible(PinkSlimeEntity entity) {
        return !entity.isAlive();
    }
}
