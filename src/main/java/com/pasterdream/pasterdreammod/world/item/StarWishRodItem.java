package com.pasterdream.pasterdreammod.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StarWishRodItem extends FishingRodItem {

    /** Lure 加成：竿自带 +2 lure speed，搭配原版 Lure III 可达 Lure V 上限 */
    private static final int LURE_SPEED_BONUS = 2;
    /** Luck 加成：竿自带海之眷顾 II，搭配原版 Luck III 可达 Luck V */
    private static final int LUCK_BONUS = 2;

    public StarWishRodItem() {
        super(new Properties().durability(1024).fireResistant().rarity(Rarity.UNCOMMON));
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.fishing != null) {
            if (!level.isClientSide) {
                int damage = player.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(damage, player, (p) -> p.broadcastBreakEvent(hand));
            }
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                int lureSpeed = Math.min(5, EnchantmentHelper.getFishingSpeedBonus(itemstack) + LURE_SPEED_BONUS);
                int luck = EnchantmentHelper.getFishingLuckBonus(itemstack) + LUCK_BONUS;
                level.addFreshEntity(new FishingHook(player, level, luck, lureSpeed));
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.pasterdream.star_wish_rod.desc"));
        tooltip.add(Component.translatable("tooltip.pasterdream.star_wish_rod"));
    }
}
