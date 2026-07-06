package com.pasterdream.pasterdreammod.world.item.curio;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class EmbryoNecklaceItem extends Item implements ICurioItem {
    public EmbryoNecklaceItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            list.add(Component.translatable("tooltip.pasterdream.embryo.tooltip1"));
            list.add(Component.translatable("tooltip.pasterdream.embryo.tooltip2"));
            list.add(Component.translatable("tooltip.pasterdream.embryo.tooltip3"));
            list.add(Component.translatable("tooltip.pasterdream.embryo.tooltip4"));
        }
        else {
            list.add(Component.translatable("tooltip.pasterdream.embryo.hold_shift"));
        }
    }
}
