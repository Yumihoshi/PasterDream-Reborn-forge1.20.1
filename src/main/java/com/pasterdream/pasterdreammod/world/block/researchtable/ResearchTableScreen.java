package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.pasterdream.pasterdreammod.helper.abstractcontainermenuwithfluidslot.AbstractContainerScreenWithFluidSlot;
import com.pasterdream.pasterdreammod.helper.renderhelper.GUIBackGroundRender;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.network.researchtable.ResearchTableCopyPacket;
import com.pasterdream.pasterdreammod.network.researchtable.ResearchTableResearchPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ResearchTableScreen extends AbstractContainerScreenWithFluidSlot<ResearchTableMenu>
{
    public ResearchTableScreen(ResearchTableMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
        this.imageWidth = 197;
        this.imageHeight = 201;
    }

    @Override
    protected void init()
    {
        super.init();

        Button copyButton = Button.builder(Component.translatable("button.pasterdream.copy_button"), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new ResearchTableCopyPacket(menu.getBlockEntity().getBlockPos()));
        }).pos(leftPos + 83, topPos + 35).size(31, 16).build();

        Button researchButton = Button.builder(Component.translatable("button.pasterdream.research_button"), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new ResearchTableResearchPacket(menu.getBlockEntity().getBlockPos()));
        }).pos(leftPos + 83, topPos + 80).size(31, 16).build();

        addRenderableWidget(copyButton);
        addRenderableWidget(researchButton);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY)
    {
        GUIBackGroundRender.rendResearchTableGUI(guiGraphics, leftPos, topPos);
        GUIBackGroundRender.rendPasterDreamInventoryGUI(guiGraphics, leftPos + 14, topPos + 117);
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {

    }
}
