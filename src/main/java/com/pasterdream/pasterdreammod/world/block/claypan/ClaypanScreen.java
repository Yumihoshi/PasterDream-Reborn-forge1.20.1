package com.pasterdream.pasterdreammod.world.block.claypan;

import com.mojang.blaze3d.systems.RenderSystem;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.component.FluidSlot;
import com.pasterdream.pasterdreammod.helper.RenderHelper.GUIBackGroundRender;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.network.FluidSlotInteractPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ClaypanScreen extends AbstractContainerScreen<ClaypanMenu>
{
    private FluidSlot fluidSlot;

    public ClaypanScreen(ClaypanMenu menu, Inventory inv, Component title)
    {
        super(menu, inv, title);
        this.imageWidth = 170;
        this.imageHeight = 121;
    }

    @Override
    protected void init()
    {
        super.init();
        fluidSlot = new FluidSlot(leftPos + 49, topPos + 5, () -> menu.getBlockEntity().getFluidTank().getFluid(), button ->
        {
            FriendlyByteBuf extraData = new FriendlyByteBuf(Unpooled.buffer());
            extraData.writeBlockPos(menu.getBlockEntity().getBlockPos());
            extraData.writeInt(0);
            ModNetwork.CHANNEL.sendToServer(new FluidSlotInteractPacket(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "block_entity"), extraData, button == 0));
        });
        addRenderableWidget(fluidSlot);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        GUIBackGroundRender.rendClaypanGUI(guiGraphics, leftPos + 44, topPos);
        GUIBackGroundRender.rendPasterDreamInventoryGUI(guiGraphics, leftPos, topPos + 37);

        int progress = menu.getProgress();
        int maxProgress = menu.getMaxProgress();
        if (maxProgress != 0)
        {
            GUIBackGroundRender.rendArrow(guiGraphics, leftPos + 74, topPos + 7, (double)progress / maxProgress);
        }

        int lastTick = maxProgress - progress;
        guiGraphics.drawCenteredString(minecraft.getInstance().font, String.format("%02d", (lastTick / 72000)) + "h" + String.format("%02d", ((lastTick % 72000) / 1200)) + "m" + String.format("%02d", ((lastTick % 1200) / 20)) + "s" + String.format("%02d", (lastTick % 20)) + "tick", leftPos + (imageWidth / 2), topPos + 29, 0xFFFFFFFF);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {

    }
}
