package com.pasterdream.pasterdreammod.world.block.desk;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class DeskMenu<T extends DeskBlockEntity> extends AbstractContainerMenu
{
    protected final T blockEntity;
    protected final Inventory playerInventory;

    public DeskMenu(MenuType<?> type, int id, Inventory playerInventory, T blockEntity)
    {
        super(type, id);
        this.blockEntity = blockEntity;
        this.playerInventory = playerInventory;

        IItemHandler handler = blockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(handler, 0, 77, 5));

        //玩家物品栏
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 5 + i * 18, 123));
        }

        for (int i = 0; i < 27; i++)
        {
            this.addSlot(new Slot(playerInventory, i + 9, 5 + (i % 9) * 18, 65 + (i / 9) * 18));
        }
    }

    public T getBlockEntity()
    {
        return blockEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        Slot slot = slots.get(index);
        if (!slot.hasItem())
        {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index == 0)
        {
            if (!this.moveItemStackTo(stack, 1, 37, true))
            {
                return ItemStack.EMPTY;
            }
        }
        else
            if (index >= 1 && index <= 36)
            {
                if (!this.moveItemStackTo(stack, 0, 1, true))
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

        if (stack.getCount() == copy.getCount())
        {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, stack);
        return copy;
    }

    @Override
    public boolean stillValid(Player player)
    {
        BlockPos blockPosition = blockEntity.getBlockPos();
        return player.level().getBlockEntity(blockPosition) == blockEntity && player.distanceToSqr(blockPosition.getX() + 0.5, blockPosition.getY() + 0.5, blockPosition.getZ() + 0.5) <= 64;
    }
}
