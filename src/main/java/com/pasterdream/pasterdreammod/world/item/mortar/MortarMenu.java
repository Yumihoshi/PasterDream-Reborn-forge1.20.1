package com.pasterdream.pasterdreammod.world.item.mortar;

import com.pasterdream.pasterdreammod.init.ModMenus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class MortarMenu extends AbstractContainerMenu
{
    private final MortarDataHandler dataHandler;
    private final ItemStack container;

    private IItemHandler itemHandler;

    public MortarMenu(int id, Inventory inventory, ItemStack container, InteractionHand hand)
    {
        super(ModMenus.MORTAR.get(), id);
        this.container = container;
        this.dataHandler = new MortarDataHandler(container);
        //this.inventory = new MortarInventory(dataHandler);
        this.itemHandler = new InvWrapper(inventory);



        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                int index = row * 4 + col;
                addSlot(new SlotItemHandler(itemHandler, index, 73 + col * 18, 6 + row * 18));
            }
        }

        for (int col = 0; col < 4; col++)
        {
            int index = 8 + col;
            addSlot(new SlotItemHandler(itemHandler, index, 73 + col * 18, 68)
            {
                @Override
                public boolean mayPlace(ItemStack stack)
                {
                    return false;
                }
            });
        }

        for (int col = 0; col < 9; col++)
        {
            addSlot(new Slot(inventory, col, 5 + col * 18, 159));
        }
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                addSlot(new Slot(inventory, col + row * 9 + 9, 5 + col * 18, 101 + row * 18));
            }
        }
    }

    public MortarDataHandler getDataHandler()
    {
        return dataHandler;
    }

    public ItemStack getContainer()
    {
        return container;
    }

    public FluidStack getInputFluid(int index)
    {
        return dataHandler.getInputFluid(index);
    }

    public void setInputFluid(int index, FluidStack stack)
    {
        dataHandler.setInputFluid(index, stack);
    }

    public FluidStack getOutputFluid(int index)
    {
        return dataHandler.getOutputFluid(index);
    }

    public void setOutputFluid(int index, FluidStack stack)
    {
        dataHandler.setOutputFluid(index, stack);
    }

    public ItemStack getInputItem(int index)
    {
        return dataHandler.getInputItem(index);
    }

    public void setInputItem(int index, ItemStack stack)
    {
        dataHandler.setInputItem(index, stack);
    }

    public ItemStack getOutputItem(int index)
    {
        return dataHandler.getOutputItem(index);
    }

    public void setOutputItem(int index, ItemStack stack)
    {
        dataHandler.setOutputItem(index, stack);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        //输入槽0-7,输出槽8-11,玩家背包12-47
        if (index < 12)
        {   //从机器移出到背包
            if (!this.moveItemStackTo(stack, 12, 48, true))
            {
                return ItemStack.EMPTY;
            }
        }
            else
            {   //从背包移入输入槽
                if (!this.moveItemStackTo(stack, 0, 8, false))
                {
                    return ItemStack.EMPTY;
                }
            }

        if (stack.isEmpty())
        {
            slot.set(ItemStack.EMPTY);
        }
            else
            {
                slot.setChanged();
            }
        return copy;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }
}
