package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.client.renderer.FoxFireRenderer;
import com.pasterdream.pasterdreammod.client.renderer.MeltDreamCrystalEntityRenderer;
import com.pasterdream.pasterdreammod.client.renderer.PinkChickenRenderer;
import com.pasterdream.pasterdreammod.client.renderer.TerraswordWaveRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModEntityRenderer {
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.TERRASWORD_WAVE.get(), TerraswordWaveRenderer::new);
        event.registerEntityRenderer(ModEntities.FOX_FIRE.get(), FoxFireRenderer::new);
        event.registerEntityRenderer(ModEntities.MELT_DREAM_CRYSTAL_ENTITY.get(), MeltDreamCrystalEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.PINK_CHICKEN.get(), PinkChickenRenderer::new);
    }
}
