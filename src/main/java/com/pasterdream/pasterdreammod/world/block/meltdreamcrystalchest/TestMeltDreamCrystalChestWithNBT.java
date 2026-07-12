package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

public class TestMeltDreamCrystalChestWithNBT
{
    public static ItemStack testNBT()
    {
        ItemStack itemStack = new ItemStack(ModItems.MELT_DREAM_CRYSTAL_CHEST.get());

        CompoundTag compoundTag = new CompoundTag();
        ListTag listTag = new ListTag();

        CompoundTag normalLootTable = new CompoundTag();
        normalLootTable.putString("LootTable", "pasterdream:chests/test_loot_table_0");
        normalLootTable.putInt("weight", 4);
        normalLootTable.putFloat("luck_multiplier_increase", 1F);
        listTag.add(normalLootTable);

        CompoundTag rareLootTable = new CompoundTag();
        rareLootTable.putString("LootTable", "pasterdream:chests/test_loot_table_1");
        rareLootTable.putInt("weight", 2);
        rareLootTable.putFloat("luck_multiplier_increase", 2F);
        listTag.add(rareLootTable);

        CompoundTag legendLootTable = new CompoundTag();
        legendLootTable.putString("LootTable", "pasterdream:chests/test_loot_table_2");
        legendLootTable.putInt("weight", 1);
        legendLootTable.putFloat("luck_multiplier_increase", 4F);
        listTag.add(legendLootTable);

        compoundTag.put("LootTables", listTag);
        itemStack.setTag(compoundTag);

        return itemStack;
    }

    public static ItemStack dyedreamMeltDreamCrystalChestNBT()
    {
        ItemStack itemStack = new ItemStack(ModItems.MELT_DREAM_CRYSTAL_CHEST.get());

        CompoundTag compoundTag = new CompoundTag();
        ListTag listTag = new ListTag();

        CompoundTag normalLootTable = new CompoundTag();
        normalLootTable.putString("LootTable", "pasterdream:chests/melt_dream_crystal_chest_dyedream_world_normal");
        normalLootTable.putInt("weight", 11);
        normalLootTable.putFloat("luck_multiplier_increase", 1F);
        listTag.add(normalLootTable);

        CompoundTag rareLootTable = new CompoundTag();
        rareLootTable.putString("LootTable", "pasterdream:chests/melt_dream_crystal_chest_dyedream_world_rare");
        rareLootTable.putInt("weight", 7);
        rareLootTable.putFloat("luck_multiplier_increase", 2F);
        listTag.add(rareLootTable);

        CompoundTag legendLootTable = new CompoundTag();
        legendLootTable.putString("LootTable", "pasterdream:chests/melt_dream_crystal_chest_dyedream_world_legend");
        legendLootTable.putInt("weight", 2);
        legendLootTable.putFloat("luck_multiplier_increase", 4F);
        listTag.add(legendLootTable);

        compoundTag.put("LootTables", listTag);
        itemStack.setTag(compoundTag);

        return itemStack;
    }
}
