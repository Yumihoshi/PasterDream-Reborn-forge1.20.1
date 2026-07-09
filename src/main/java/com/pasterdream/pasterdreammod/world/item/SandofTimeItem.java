package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SandofTimeItem extends Item {

    private static final String TAG_COOLDOWN = "TimeCooldown";

    public SandofTimeItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            long now = level.getGameTime();
            long lastUse = stack.getOrCreateTag().getLong(TAG_COOLDOWN);
            int cooldownTicks = Config.timeOfSandCooldownSeconds * 20;

            if (now - lastUse < cooldownTicks) {
                long remainingTicks = cooldownTicks - (now - lastUse);
                int remainingSeconds = (int) Math.ceil(remainingTicks / 20.0);
                player.displayClientMessage(
                        Component.translatable("message.pasterdream.sand_of_time.cooldown", remainingSeconds), true);
                return InteractionResultHolder.fail(stack);
            }

            stack.getOrCreateTag().putLong(TAG_COOLDOWN, now);
            boolean isDay = level.isDay();
            long newTime = isDay ? 18000 : 1000;
            serverLevel.setDayTime(newTime);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0f, isDay ? 0.5f : 1.5f);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {

    }
}
