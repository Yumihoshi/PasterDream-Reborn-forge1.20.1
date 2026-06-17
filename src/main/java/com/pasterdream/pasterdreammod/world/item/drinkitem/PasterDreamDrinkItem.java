package com.pasterdream.pasterdreammod.world.item.drinkitem;

import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.helper.FluidContainerCapability.FluidContainerRegistry;
import com.pasterdream.pasterdreammod.helper.drinkproperties.PasterDreamDrinkProperties;
import com.pasterdream.pasterdreammod.network.meltdreamenergy.MeltDreamEnergySyncPacket;
import com.pasterdream.pasterdreammod.network.san.SanSyncPacket;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PasterDreamDrinkItem extends Item
{
    private final int useDuration;
    private final double sanAdd;
    private final double meltDreamEnergyAdd;

    public PasterDreamDrinkItem(PasterDreamDrinkProperties properties)
    {
        super(properties);
        this.useDuration = properties.getUseDuration();
        this.sanAdd = properties.getSanAdd();
        this.meltDreamEnergyAdd = properties.getMeltDreamEnergyAdd();
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return useDuration;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) //喝，为什么不喝？
    {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        FoodProperties food = stack.getFoodProperties(entity);
        if (food != null && entity instanceof Player player)
        {
            player.getFoodData().eat(food.getNutrition(), food.getSaturationModifier());
            for (var effectPair : food.getEffects())
            {
                if (level.random.nextFloat() < effectPair.getSecond())
                {
                    entity.addEffect(new MobEffectInstance(effectPair.getFirst()));
                }
            }
        }

        if (!level.isClientSide && entity instanceof Player player)
        {

            if (sanAdd != 0)
            {
                player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
                {
                    capability.addSanValue(sanAdd);
                    SanSyncPacket.sendToPlayer(player, capability);
                });
            }

            if (meltDreamEnergyAdd != 0)
            {
                player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
                {
                    capability.addMeltDreamEnergy(meltDreamEnergyAdd);
                    MeltDreamEnergySyncPacket.sendToPlayer(player, capability);
                });
            }
        }

        if (entity instanceof Player player && player.isCreative())
        {
            return stack;
        }

        Item emptyContainer = FluidContainerRegistry.getEntryForFillToEmpty(this).emptyItem;
        stack.shrink(1);

        if (stack.isEmpty())
        {
            if (emptyContainer != null)
            {
                return new ItemStack(emptyContainer);
            }
            return ItemStack.EMPTY;
        }
            else
            {
                if (!level.isClientSide && entity instanceof Player player)
                {
                    if (emptyContainer != null)
                    {
                        ItemStack containerStack = new ItemStack(emptyContainer);
                        if (!player.getInventory().add(containerStack))
                        {
                            player.drop(containerStack, false);
                        }
                    }
                }
                return stack;
            }
    }

    protected void onDrinkSpecial(Player player, Level level)
    {
        //默认无操作
    }
}
