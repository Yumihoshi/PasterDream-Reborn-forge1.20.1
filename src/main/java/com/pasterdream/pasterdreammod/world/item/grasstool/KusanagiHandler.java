package com.pasterdream.pasterdreammod.world.item.grasstool;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "pasterdream", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KusanagiHandler {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide) return;

        Entity attacker = event.getSource().getEntity();
        if (!(attacker instanceof Player player)) return;

        ItemStack mainHand = player.getMainHandItem();
        ItemStack stack = null;
        if (mainHand.getItem() instanceof KusanagiItem) {
            stack = mainHand;
        } else {
            ItemStack offHand = player.getOffhandItem();
            if (offHand.getItem() instanceof KusanagiItem) {
                stack = offHand;
            }
        }

        if (stack == null) return;

        int killed = (int) stack.getOrCreateTag().getDouble("killed");
        stack.getOrCreateTag().putDouble("killed", killed + 1);
    }
}
