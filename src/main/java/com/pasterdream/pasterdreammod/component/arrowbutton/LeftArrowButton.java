package com.pasterdream.pasterdreammod.component.arrowbutton;

import com.pasterdream.pasterdreammod.helper.renderhelper.GUIBackGroundRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class LeftArrowButton extends Button
{
    public LeftArrowButton(int x, int y, OnPress onPress)
    {
        super(x, y, 22, 15, Component.empty(), onPress, DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        if(isHovered())
        {
            GUIBackGroundRender.rendLeftButtonHovered(guiGraphics, getX(), getY());
        }
            else
            {
                GUIBackGroundRender.rendLeftButton(guiGraphics, getX(), getY());
            }
    }
}
