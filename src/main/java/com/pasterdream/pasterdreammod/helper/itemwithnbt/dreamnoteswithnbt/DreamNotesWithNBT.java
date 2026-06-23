package com.pasterdream.pasterdreammod.helper.itemwithnbt.dreamnoteswithnbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DreamNotesWithNBT
{
    public static ItemStack dreamNotesWithNBT(ItemLike item, String NBTType, String NBTContent)
    {
        ItemStack DreamNotesItem = new ItemStack(item);
        CompoundTag tag = new CompoundTag();
        tag.putString(NBTType, NBTContent);
        DreamNotesItem.setTag(tag);
        return DreamNotesItem;
    }
}
