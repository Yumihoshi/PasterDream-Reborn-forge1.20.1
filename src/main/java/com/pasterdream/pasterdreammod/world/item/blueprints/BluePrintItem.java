package com.pasterdream.pasterdreammod.world.item.blueprints;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BluePrintItem extends Item
{
    public BluePrintItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, tooltip, flag);
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("content"))
        {
            tooltip.add(Component.translatable("tooltip.pasterdream." + tag.getString("content")));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide)
        {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("structureTag"))
            {
                ListTag listTag = tag.getList("structureTag", Tag.TAG_COMPOUND);
                Minecraft.getInstance().setScreen(new BluePrintScreen(listTag));
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
