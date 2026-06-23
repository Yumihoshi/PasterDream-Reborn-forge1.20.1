package com.pasterdream.pasterdreammod.world.item.dreamnotes;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.helper.renderhelper.GUIBackGroundRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DreamNotesScreen extends Screen
{
    private final String content;
    private int x;
    private int y;

    private int guiWidth;
    private int guiHeight;
    private ResourceLocation texture;
    private float scale;

    public DreamNotesScreen(String content)
    {
        super(Component.empty());
        this.content = content;
    }

    @Override
    protected void init()
    {
        super.init();
        DreamNotesInfo info = DreamNotesRegistry.getInfo(content);

        if (info != null)
        {
            guiWidth = info.width();
            guiHeight = info.height();
            texture = info.texture();
        }
            else
            {
                guiWidth = 151;
                guiHeight = 189;
                texture = ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/gui/dream_notes/dream_notes_test.png");

            }

        scale = 1f;

        for(int i = guiHeight; i > 256; i /= 2)
        {
            scale /= 2;
        }

        x = (int)((width - (guiWidth * scale)) / 2);
        y = (int)((height - (guiHeight * scale)) / 2);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (texture != null)
        {
            GUIBackGroundRender.rendDreamNotes(guiGraphics, texture, x, y, guiWidth, guiHeight, scale);
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
