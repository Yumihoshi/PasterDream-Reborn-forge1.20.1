package com.pasterdream.pasterdreammod.world.item.dreamnotesbook;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DreamNotesBookItem extends Item
{
    public DreamNotesBookItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public Component getName(ItemStack itemStack)
    {
        CompoundTag compoundTag = itemStack.getTag();
        DreamNotesBookInfo dreamNotesBookInfo = (compoundTag != null && compoundTag.contains("content")) ? DreamNotesBookRegistry.getInfo(compoundTag.getString("content")) : null;
        return dreamNotesBookInfo != null ? dreamNotesBookInfo.title().copy().append(" - " + dreamNotesBookInfo.author()) : Component.literal("null - null");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        if (level.isClientSide)
        {
            CompoundTag compoundTag = itemStack.getTag();
            DreamNotesBookInfo dreamNotesBookInfo = (compoundTag != null && compoundTag.contains("content")) ? DreamNotesBookRegistry.getInfo(compoundTag.getString("content")) : null;
            Minecraft.getInstance().setScreen(new DreamNotesBookScreen(dreamNotesBookInfo));
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}
