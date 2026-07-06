package com.pasterdream.pasterdreammod.helper.itemwithnbt.lootgeneratorwithnbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class LootGeneratorWithNBT
{
    public static final String TAG_LOOT_TABLE = "LootTable";

    public static ItemStack create(ItemLike item, String lootTable)
    {
        ItemStack stack = new ItemStack(item);
        CompoundTag tag = new CompoundTag();
        tag.putString(TAG_LOOT_TABLE, lootTable);
        stack.setTag(tag);
        return stack;
    }

    public static ItemStack create(ItemLike item, ResourceLocation lootTable)
    {
        return create(item, lootTable.toString());
    }
}
