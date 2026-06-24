package com.pasterdream.pasterdreammod.world.block.desk.dyedreamdesk;

import com.pasterdream.pasterdreammod.helper.renderhelper.GUIBackGroundRender;
import com.pasterdream.pasterdreammod.world.block.desk.DeskScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class DyedreamDeskScreen extends DeskScreen<DyedreamDeskMenu>
{
    public DyedreamDeskScreen(DyedreamDeskMenu menu, Inventory inv, Component title)
    {
        super(menu, inv, title);
    }

    @Override
    protected void renderDeskBg(GuiGraphics graphics, int leftPos, int topPos)
    {
        GUIBackGroundRender.rendDyedreamDeskGUI(graphics, leftPos + 71, topPos);
    }
}
