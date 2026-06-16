package com.pasterdream.pasterdreammod.world.block.claypan;

import com.pasterdream.pasterdreammod.init.ModMenus;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ClaypanMenu extends AbstractContainerMenu
{
    private final ClaypanBlockEntity blockEntity;
    private final ContainerData data;

    public ClaypanMenu(int id, Inventory inventory, ClaypanBlockEntity blockEntity, ServerPlayer serverPlayer)
    {
        super(ModMenus.CLAYPAN.get(), id);
        this.blockEntity = blockEntity;

        data = new SimpleContainerData(2);
        data.set(0, blockEntity.getProgress());
        data.set(1, blockEntity.getMaxProgress());
        addDataSlots(data);

        LazyOptional<IItemHandler> itemHandlerCap = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER);
        itemHandlerCap.ifPresent(iItemHandler -> addSlot(new SlotItemHandler(iItemHandler, 0, 104, 6)));

        //玩家物品栏
        for (int i = 0; i < 9; i++)
        {
            addSlot(new Slot(inventory, i, 5 + i * 18, 100));
        }

        for (int i = 0; i < 27; i++)
        {
            addSlot(new Slot(inventory, i + 9, 5 + (i % 9) * 18, 42 + (i / 9) * 18));
        }

        addDataSlots(data);
    }

    public ClaypanMenu(int id, Inventory inventory, ClaypanBlockEntity blockEntity)
    {
        this(id, inventory, blockEntity, null);
    }

    public IFluidHandler getFluidHandler()
    {
        return blockEntity.getFluidTank();
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
        if (index == 0)
        {
            if (!this.moveItemStackTo(stack, 1, 37, true))
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
            this.data.set(0, blockEntity.getProgress());
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

    public ClaypanBlockEntity getBlockEntity()
    {
        return blockEntity;
    }
}
