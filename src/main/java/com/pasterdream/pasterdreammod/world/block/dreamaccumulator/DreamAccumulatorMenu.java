package com.pasterdream.pasterdreammod.world.block.dreamaccumulator;

import com.pasterdream.pasterdreammod.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DreamAccumulatorMenu extends AbstractContainerMenu
{
    private final DreamAccumulatorBlockEntity blockEntity;
    private final ContainerData data;

    public DreamAccumulatorMenu(int id, Inventory inventory, DreamAccumulatorBlockEntity blockEntity)
    {
        super(ModMenus.DREAM_ACCUMULATOR.get(), id);

        this.blockEntity = blockEntity;
        IItemHandler handler = blockEntity.getItemHandler();

        data = new SimpleContainerData(2);
        data.set(0, (int)blockEntity.getProgress());
        data.set(1, blockEntity.getMaxProgress());
        addDataSlots(data);

        addSlot(new SlotItemHandler(handler, 0, 74, 6)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }
        });
        addSlot(new SlotItemHandler(handler, 1, 74, 60));

        for (int i = 0; i < 9; i++)
        {
            addSlot(new Slot(inventory, i, 4 + i * 18, 160));
        }

        for (int i = 0; i < 27; i++)
        {
            addSlot(new Slot(inventory, i + 9, 4 + (i % 9) * 18, 102 + (i / 9) * 18));
        }
    }

    public DreamAccumulatorBlockEntity getBlockEntity()
    {
        return blockEntity;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return player.level().getBlockEntity(blockEntity.getBlockPos()) == blockEntity && player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) <= 64;
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

        //移出到背包
        if (index == 0 || index == 1)
        {
            if (!this.moveItemStackTo(stack, 2, 38, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else    //背包移入输入槽
            if (index >= 2 && index <= 37)
            {
                if (!this.moveItemStackTo(stack, 1, 1, false) || !this.moveItemStackTo(stack, 5, 6, false))
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
    public void broadcastChanges()
    {
        super.broadcastChanges();
        if (!blockEntity.getLevel().isClientSide)
        {
            this.data.set(0, (int)blockEntity.getProgress());
            this.data.set(1, blockEntity.getMaxProgress());
        }
    }

    public int getProgress()
    {
        return data.get(0);
    }

    public int getMaxProgress()
    {
        return data.get(1);
    }
}
