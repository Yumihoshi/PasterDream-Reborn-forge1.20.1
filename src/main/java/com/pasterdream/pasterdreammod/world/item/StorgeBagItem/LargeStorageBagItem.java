package com.pasterdream.pasterdreammod.world.item.StorgeBagItem;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LargeStorageBagItem extends Item {

    public static final int SLOT_COUNT = 25; // 5x5
    private static final String TAG_INVENTORY = "BagItems";

    public LargeStorageBagItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    public static ListTag getInventoryTag(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(TAG_INVENTORY)) {
            ListTag items = new ListTag();
            for (int i = 0; i < SLOT_COUNT; i++) {
                items.add(new CompoundTag());
            }
            tag.put(TAG_INVENTORY, items);
        }
        return tag.getList(TAG_INVENTORY, 10);
    }

    public static void saveInventoryTag(ItemStack stack, ListTag items) {
        stack.getOrCreateTag().put(TAG_INVENTORY, items);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, tooltip, flag);
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.pasterdream.introduction.tooltip"));
            tooltip.add(Component.translatable("tooltip.pasterdream.large_storage_bag.tooltip1"));
        }
        else {
            tooltip.add(Component.translatable("tooltip.pasterdream.hold_shift"));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        if (entity instanceof ServerPlayer serverPlayer) {
            ItemStack stack = entity.getItemInHand(hand);
            NetworkHooks.openScreen(serverPlayer,
                    new SimpleMenuProvider(
                            (id, inv, player) -> new LargeStorageBagMenu(id, inv, stack, hand),
                            Component.empty()
                    ),
                    buf -> buf.writeByte(hand == InteractionHand.MAIN_HAND ? 0 : 1)
            );
        }
        return InteractionResultHolder.sidedSuccess(entity.getItemInHand(hand), world.isClientSide);
    }
}
