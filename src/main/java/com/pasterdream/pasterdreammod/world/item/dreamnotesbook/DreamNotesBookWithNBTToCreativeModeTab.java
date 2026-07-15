package com.pasterdream.pasterdreammod.world.item.dreamnotesbook;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class DreamNotesBookWithNBTToCreativeModeTab
{
    public static ItemStack buildNBT(String content)
    {
        ItemStack itemStack = new ItemStack(ModItems.DREAM_NOTES_BOOK.get());
        CompoundTag nbt = new CompoundTag();
        nbt.putString("content", content);
        itemStack.setTag(nbt);
        return itemStack;
    }
}
