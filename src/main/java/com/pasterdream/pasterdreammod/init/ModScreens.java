package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.world.block.claypan.ClaypanScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModScreens
{
    public static void register(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            MenuScreens.register(ModMenus.CLAYPAN.get(), ClaypanScreen::new);
        });
    }
}
