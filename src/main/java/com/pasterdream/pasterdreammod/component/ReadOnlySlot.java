package com.pasterdream.pasterdreammod.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ReadOnlySlot extends AbstractWidget
{
    private ItemStack itemStack;

    public ReadOnlySlot(int x, int y, ItemStack itemStack)
    {
        super(x, y, 18, 18, Component.empty());
        setX(x);
        setY(y);
        this.itemStack = itemStack;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        guiGraphics.renderItem(itemStack, getX() + 1, getY() + 1);

        if(isMouseOver(mouseX, mouseY))
        {
            guiGraphics.fill(getX() + 1, getY() + 1, getX() + 17, getY() + 17, 0x7FFFFFFF);
            guiGraphics.renderTooltip(Minecraft.getInstance().font, itemStack, mouseX, mouseY);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput)
    {

    }

    public void setItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }
}
