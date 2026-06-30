package com.pasterdream.pasterdreammod.tag;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> MOD_GLASS_PANE = bind("glass_panes");
    public static final TagKey<Item> STRIPPED_LOGS = createForgeTag("stripped_logs");
    public static final TagKey<Item> STRIPPED_WOOD = createForgeTag("stripped_wood");
    /** 可放入堆肥桶的物品标签，通过 TagsUpdatedEvent 自动注册到 ComposterBlock */
    public static final TagKey<Item> COMPOSTABLE = bind("compostable");

    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, pName));
    }

    private static TagKey<Item> createForgeTag(String pName){
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", pName));
    }

}
