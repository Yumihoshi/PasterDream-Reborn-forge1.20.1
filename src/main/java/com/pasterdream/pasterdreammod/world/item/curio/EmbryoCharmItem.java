package com.pasterdream.pasterdreammod.world.item.curio;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class EmbryoCharmItem extends Item implements ICurioItem {
    public EmbryoCharmItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }
}
