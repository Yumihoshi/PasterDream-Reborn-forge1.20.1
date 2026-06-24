package com.pasterdream.pasterdreammod.world.block.ItemContainer.crate;

import com.pasterdream.pasterdreammod.world.block.ItemContainer.ItemContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class CrateScreen<T extends CrateMenu<?>> extends ItemContainerScreen<T>
{
    public CrateScreen(T menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);
    }

    public int setScreenWidth()
    {
        return 170;
    }

    public int setScreenHeight()
    {
        return 190;
    }

    public int setInventoryLeftPosition()
    {
        return leftPos;
    }

    public int setInventoryTopPosition()
    {
        return topPos + 106;
    }
}
