package com.pasterdream.pasterdreammod.world.block.desk;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;


public abstract class DeskBlockEntity extends BlockEntity implements MenuProvider, IDeskInventory
{
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private final Component displayName;

    public DeskBlockEntity(BlockEntityType<?> type, BlockPos blockPosition, BlockState state, String nameKey)
    {
        super(type, blockPosition, state);
        this.displayName = Component.translatable(nameKey);
    }

    @Override
    public Component getDisplayName()
    {
        return this.displayName;
    }

    @Override
    public abstract AbstractContainerMenu createMenu(int id, Inventory inventory, Player player);

    @Override
    public ItemStackHandler getItemHandler()
    {
        return itemHandler;
    }

    @Override
    public void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
    }
}
