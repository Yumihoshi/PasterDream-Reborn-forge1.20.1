package com.pasterdream.pasterdreammod.helper.tooltipadder;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class AddToolTip
{
    public static void addTooltip(ItemTooltipEvent event)
    {
        //用于向其他已有物品添加tooltip
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();

        if(stack.is(ModItems.MAGIC_STONE.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.magic_stone"));
        }
        if(stack.is(ModItems.MELT_DREAM_CRYSTAL_FRAGMENT.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.melt_dream_crystal_fragment"));
        }
        if(stack.is(ModItems.TITANIUM_UPGRADE.get()) || stack.is(ModItems.SCULK_UPGRADE.get()) || stack.is(ModItems.DYEDREAM_UPGRADE.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.upgrade_kit"));
        }
        if(stack.is(ModItems.SCULK_HELMET.get()) || stack.is(ModItems.SCULK_CHESTPLATE.get())
                || stack.is(ModItems.SCULK_LEGGINGS.get()) || stack.is(ModItems.SCULK_BOOTS.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.sculk_armor.1"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.sculk_armor.2"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.sculk_armor.3"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.sculk_armor.4"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.sculk_armor.5"));
        }
        if(stack.is(ModItems.DYEDREAM_HELMET.get()) || stack.is(ModItems.DYEDREAM_CHESTPLATE.get())
                || stack.is(ModItems.DYEDREAM_LEGGINGS.get()) || stack.is(ModItems.DYEDREAM_BOOTS.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.dyedream_armor.1"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.dyedream_armor.2"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.dyedream_armor.3"));
            tooltip.add(Component.translatable("tooltip.pasterdreammod.dyedream_armor.4"));
        }
        if(stack.is(ModItems.BROKEN_NOTE.get())){
            tooltip.add(Component.translatable("tooltip.pasterdream.broken_note"));
        }
        if(stack.is(ModItems.UNKNOWN_NOTE.get())){
            tooltip.add(Component.translatable("tooltip.pasterdream.unknown_note.1"));
            tooltip.add(Component.translatable("tooltip.pasterdream.unknown_note.2"));
        }
        if(stack.is(ModItems.MELT_DREAM_AURORIAN_STEEL.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.melt_dream_aurorian_steel"));
        }
        if(stack.is(ModItems.GLASS_JAR_OF_DREAM_JUICE.get())){
            tooltip.add(Component.translatable("tooltip.pasterdreammod.glass_jar_of_dream_juice"));
        }

    }
}
