package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.world.block.claypan.ClaypanBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModBlockEntityRenderer
{
    public static void register(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            BlockEntityRenderers.register(ModBlockEntities.CLAYPAN.get(), ClaypanBlockEntityRenderer::new);
        });
    }
}
