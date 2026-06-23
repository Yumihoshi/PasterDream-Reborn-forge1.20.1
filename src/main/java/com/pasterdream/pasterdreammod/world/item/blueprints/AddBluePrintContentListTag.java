package com.pasterdream.pasterdreammod.world.item.blueprints;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class AddBluePrintContentListTag
{
    public static ListTag weaponWorkshop()
    {
        CompoundTag bufferTag = new CompoundTag();

        ListTag Floor0Items = new ListTag();
        CompoundTag Floor0 = new CompoundTag();

        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());

        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.STONE_BRICKS.asItem()).toString());
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);

        Floor0Items.add(bufferTag);
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.DEEPSLATE_TILES.asItem()).toString());
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.STONE_BRICKS.asItem()).toString());
        Floor0Items.add(bufferTag);

        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);
        Floor0Items.add(bufferTag);

        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());
        Floor0Items.add(new CompoundTag());

        Floor0.put("itemStacks", Floor0Items);

        ListTag Floor1Items = new ListTag();
        CompoundTag Floor1 = new CompoundTag();

        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.POLISHED_BLACKSTONE_BRICKS.asItem()).toString());
        Floor1Items.add(bufferTag);
        Floor1Items.add(bufferTag);
        Floor1Items.add(new CompoundTag());
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.BEDROCK.asItem()).toString());
        Floor1Items.add(bufferTag);
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.CAULDRON.asItem()).toString());
        Floor1Items.add(bufferTag);

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.POLISHED_BLACKSTONE_BRICKS.asItem()).toString());
        Floor1Items.add(bufferTag);
        Floor1Items.add(bufferTag);
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.GRINDSTONE.asItem()).toString());
        Floor1Items.add(bufferTag);
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.ANVIL.asItem()).toString());
        Floor1Items.add(bufferTag);

        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());
        Floor1Items.add(new CompoundTag());

        Floor1.put("itemStacks", Floor1Items);

        ListTag Floor2Items = new ListTag();
        CompoundTag Floor2 = new CompoundTag();

        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.BRICKS.asItem()).toString());
        Floor2Items.add(bufferTag);
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.BLAST_FURNACE.asItem()).toString());
        Floor2Items.add(bufferTag);
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.BRICKS.asItem()).toString());
        Floor2Items.add(bufferTag);
        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.BLAST_FURNACE.asItem()).toString());
        Floor2Items.add(bufferTag);
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());

        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());

        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());
        Floor2Items.add(new CompoundTag());

        Floor2.put("itemStacks", Floor2Items);

        ListTag Floor3Items = new ListTag();
        CompoundTag Floor3 = new CompoundTag();

        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());

        bufferTag = new CompoundTag();
        bufferTag.putString("id", ForgeRegistries.ITEMS.getKey(Items.MUD_BRICK_SLAB.asItem()).toString());
        Floor3Items.add(bufferTag);
        Floor3Items.add(bufferTag);
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());

        Floor3Items.add(bufferTag);
        Floor3Items.add(bufferTag);
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());

        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());

        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());
        Floor3Items.add(new CompoundTag());

        Floor3.put("itemStacks", Floor3Items);

        ListTag AllItems = new ListTag();
        AllItems.add(Floor0);
        AllItems.add(Floor1);
        AllItems.add(Floor2);
        AllItems.add(Floor3);

        return AllItems;
    }
}
