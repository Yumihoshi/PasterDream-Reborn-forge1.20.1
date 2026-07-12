package com.pasterdream.pasterdreammod.world.item.curio;

import com.pasterdream.pasterdreammod.world.item.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class BlessingOfCeciliaItem extends Item implements ICurioItem {
    public BlessingOfCeciliaItem() {
        super(new Item.Properties().stacksTo(1).rarity(ModRarities.LEGENDARY));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(ModRarities.qualityTooltip(ModRarities.LEGENDARY));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect0"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect1"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect2"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect3"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.lore"));
    }
}
