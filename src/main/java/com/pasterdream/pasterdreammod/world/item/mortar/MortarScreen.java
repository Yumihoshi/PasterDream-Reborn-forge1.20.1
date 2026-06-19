package com.pasterdream.pasterdreammod.world.item.mortar;

import com.pasterdream.pasterdreammod.component.FluidSlot;
import com.pasterdream.pasterdreammod.helper.RenderHelper.GUIBackGroundRender;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.network.MortarFluidInteractPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class MortarScreen extends AbstractContainerScreen<MortarMenu>
{
    private FluidSlot inputFluidSlot0;
    private FluidSlot inputFluidSlot1;
    private FluidSlot inputFluidSlot2;
    private FluidSlot inputFluidSlot3;

    private FluidSlot outputFluidSlot0;
    private FluidSlot outputFluidSlot1;

    public MortarScreen(MortarMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
        this.imageWidth = 170;
        this.imageHeight = 180;
    }

    @Override
    protected void init()
    {
        super.init();

        inputFluidSlot0 = new FluidSlot(leftPos + 26, topPos + 5, () -> menu.getInputFluid(0), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(true, 0, button == 0));
        });

        inputFluidSlot1 = new FluidSlot(leftPos + 44, topPos + 5, () -> menu.getInputFluid(1), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(true, 1, button == 0));
        });

        inputFluidSlot2 = new FluidSlot(leftPos + 26, topPos + 23, () -> menu.getInputFluid(2), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(true, 2, button == 0));
        });

        inputFluidSlot3 = new FluidSlot(leftPos + 44, topPos + 23, () -> menu.getInputFluid(3), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(true, 3, button == 0));
        });

        outputFluidSlot0 = new FluidSlot(leftPos + 26, topPos + 67, () -> menu.getOutputFluid(0), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(false, 0, button == 0));
        });

        outputFluidSlot1 = new FluidSlot(leftPos + 44, topPos + 67, () -> menu.getOutputFluid(1), button ->
        {
            ModNetwork.CHANNEL.sendToServer(new MortarFluidInteractPacket(false, 1, button == 0));
        });

        Button MortarButton = Button.builder(Component.translatable("button.pasterdream.mortarbutton"), button ->
        {

        }).pos(leftPos + 44, topPos + 46).size(32, 16).build();

        addRenderableWidget(inputFluidSlot0);
        addRenderableWidget(inputFluidSlot1);
        addRenderableWidget(inputFluidSlot2);
        addRenderableWidget(inputFluidSlot3);
        addRenderableWidget(outputFluidSlot0);
        addRenderableWidget(outputFluidSlot1);
        addRenderableWidget(MortarButton);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY)
    {
        GUIBackGroundRender.rendMortarGUI(guiGraphics, leftPos + 21, topPos);
        GUIBackGroundRender.rendPasterDreamInventoryGUI(guiGraphics, leftPos, topPos + 96);
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
