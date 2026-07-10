package com.pasterdream.pasterdreammod.world.item.StorgeBagItem;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StorageBagInventoryCapability implements ICapabilityProvider {

    public static final int SLOT_COUNT = 9; // 3x3

    private final ItemStackHandler inventory;
    private final LazyOptional<ItemStackHandler> holder;

    public StorageBagInventoryCapability(@Nullable CompoundTag existingNbt) {
        this.inventory = new ItemStackHandler(SLOT_COUNT);
        if (existingNbt != null && existingNbt.contains("Inventory")) {
            this.inventory.deserializeNBT(existingNbt.getCompound("Inventory"));
        }
        this.holder = LazyOptional.of(() -> inventory);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return holder.cast();
        }
        return LazyOptional.empty();
    }
}
