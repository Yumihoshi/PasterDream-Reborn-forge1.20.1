package com.pasterdream.pasterdreammod.world.item.mortar;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

public class MortarInventory implements Container
{
    private final MortarDataHandler dataHandler;
    private static final int INPUT_SLOTS = 8;
    private static final int OUTPUT_SLOTS = 4;
    private static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;

    public MortarInventory(MortarDataHandler dataHandler)
    {
        this.dataHandler = dataHandler;
    }

    @Override
    public int getContainerSize()
    {
        return TOTAL_SLOTS;
    }

    @Override
    public boolean isEmpty()
    {
        for (int i = 0; i < INPUT_SLOTS; i++)
        {
            if (!dataHandler.getInputItem(i).isEmpty())
            {
                return false;
            }
        }
        for (int i = 0; i < OUTPUT_SLOTS; i++)
        {
            if (!dataHandler.getOutputItem(i).isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot)
    {
        if (slot < INPUT_SLOTS)
        {
            return dataHandler.getInputItem(slot);
        }
            else
            {
                return dataHandler.getOutputItem(slot - INPUT_SLOTS);
            }
    }

    @Override
    public ItemStack removeItem(int slot, int amount)
    {
        ItemStack stack = getItem(slot);
        if (stack.isEmpty())
        {
            return ItemStack.EMPTY;
        }

        if (amount >= stack.getCount())
        {
            setItem(slot, ItemStack.EMPTY);
            return stack;
        }
        return stack.split(amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot)
    {
        ItemStack stack = getItem(slot);
        setItem(slot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        if (slot < INPUT_SLOTS)
        {
            dataHandler.setInputItem(slot, stack);
        }
            else
            {
                dataHandler.setOutputItem(slot - INPUT_SLOTS, stack);
            }
    }

    @Override
    public void setChanged()
    {
        //已通过dataHandler持久化，不需要额外操作
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }

    @Override
    public void clearContent()
    {
        for (int i = 0; i < INPUT_SLOTS; i++)
        {
            dataHandler.setInputItem(i, ItemStack.EMPTY);
        }
        for (int i = 0; i < OUTPUT_SLOTS; i++)
        {
            dataHandler.setOutputItem(i, ItemStack.EMPTY);
        }
    }
}
