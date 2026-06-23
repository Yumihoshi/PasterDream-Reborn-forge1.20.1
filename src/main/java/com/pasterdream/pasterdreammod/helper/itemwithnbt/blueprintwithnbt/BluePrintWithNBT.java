package com.pasterdream.pasterdreammod.helper.itemwithnbt.blueprintwithnbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class BluePrintWithNBT
{
    public static ItemStack bluePrintWithNBT(ItemLike item, String NBTType, String NBTContent, ListTag structureTag)
    {
        ItemStack BluePrintItem = new ItemStack(item);
        CompoundTag tag = new CompoundTag();
        tag.putString(NBTType, NBTContent);
        tag.put("structureTag", structureTag);
        BluePrintItem.setTag(tag);
        return BluePrintItem;
    }
}
