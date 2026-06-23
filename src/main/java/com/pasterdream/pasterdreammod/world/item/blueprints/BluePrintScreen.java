package com.pasterdream.pasterdreammod.world.item.blueprints;

import com.pasterdream.pasterdreammod.component.ReadOnlySlot;
import com.pasterdream.pasterdreammod.component.arrowbutton.DownArrowButton;
import com.pasterdream.pasterdreammod.component.arrowbutton.UpArrowButton;
import com.pasterdream.pasterdreammod.helper.renderhelper.GUIBackGroundRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BluePrintScreen extends Screen
{
    private List<ReadOnlySlot> readOnlySlots = new ArrayList<>();
    private UpArrowButton upArrowButton;
    private DownArrowButton downArrowButton;
    private ListTag allListTag;
    private int allFloors;
    private int currentFloor = 0;
    private List<ItemStack> singleFloorItemStacks = new ArrayList<>();

    public BluePrintScreen(ListTag listTag)
    {
        super(Component.empty());
        allListTag = listTag;
    }

    @Override
    protected void init()
    {
        super.init();

        upArrowButton = new UpArrowButton(width / 2 + 60, height / 2 - 46, button -> prevPage());
        downArrowButton = new DownArrowButton(width / 2 + 60, height / 2 + 26, button -> nextPage());
        addRenderableWidget(upArrowButton);
        addRenderableWidget(downArrowButton);

        allFloors = allListTag.size();
        extractItemStacks();

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                int index = 5 * i + j;
                ReadOnlySlot readOnlySlot = new ReadOnlySlot(width / 2 - 44 + j * 18, height / 2 - 44 + i * 18, singleFloorItemStacks.get(index));
                readOnlySlots.add(readOnlySlot);
                addRenderableWidget(readOnlySlots.get(index));
            }
        }

        refreshFloorSlotPosition();
        refreshFloorItem();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        renderBackground(guiGraphics);
        GUIBackGroundRender.rendBlueprintGUI(guiGraphics, width / 2 - 46, height / 2 - 46);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, (currentFloor + 1) + "/" + allFloors, width / 2 + 68, height / 2 - 5, 0xFFFFFFFF);
    }

    private void extractItemStacks()
    {
        CompoundTag SingleFloorCompoundItems = allListTag.getCompound(currentFloor);
        ListTag singleFloorListItems = SingleFloorCompoundItems.getList("itemStacks", Tag.TAG_COMPOUND);
        singleFloorItemStacks = new ArrayList<>();
        for(int i = 0; i < 25; i++)
        {
            CompoundTag singleItemStack = singleFloorListItems.getCompound(i);
            if(singleItemStack.contains("id"))
            {
                Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(singleItemStack.getString("id")));
                CompoundTag nbt = null;
                if(singleItemStack.contains("tag"))
                {
                    nbt = singleItemStack.getCompound("tag");
                }

                singleFloorItemStacks.add(new ItemStack(item, 1, nbt));
            }
                else
                {
                    singleFloorItemStacks.add(ItemStack.EMPTY);
                }
        }
    }

    private void refreshFloorSlotPosition()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                readOnlySlots.get(5 * i + j).setPosition(width / 2 - 44 + j * 18, height / 2 - 44 + i * 18);
            }
        }
    }

    private void refreshFloorItem()
    {
        extractItemStacks();
        for(int i = 0; i < 25; i++)
        {
            readOnlySlots.get(i).setItemStack(singleFloorItemStacks.get(i));
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        if (delta > 0)
        {
            prevPage();
        }
        else
            if (delta < 0)
            {
                nextPage();
            }
        return true;
    }

    private void prevPage()
    {
        if (currentFloor > 0)
        {
            currentFloor--;
        }
            else
            {
                currentFloor = allFloors - 1;
            }
        refreshFloorItem();
    }

    private void nextPage()
    {
        if (currentFloor < allFloors - 1)
        {
            currentFloor++;
        }
            else
            {
                currentFloor = 0;
            }
        refreshFloorItem();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
