package com.pasterdream.pasterdreammod.world.block.lostswordtomb;

import com.pasterdream.pasterdreammod.init.ModCriteriaTriggers;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "pasterdream", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LostSwordTombHandler {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof LostSwordTombItem)) {
            return;
        }

        // 1% 概率触发
        if (RANDOM.nextFloat() >= 0.01f) {
            return;
        }

        // 消耗手中的剑冢
        stack.shrink(1);
        Entity target = event.getTarget();
        Level level = player.level();

        ItemEntity mossyStone = new ItemEntity(level, target.getX(), target.getY() + 0.5, target.getZ(),
                new ItemStack(Items.MOSSY_COBBLESTONE));
        mossyStone.setPickUpDelay(10);
        level.addFreshEntity(mossyStone);

        ItemEntity swordEmbryo = new ItemEntity(level, target.getX(), target.getY() + 0.5, target.getZ(),
                new ItemStack(ModItems.SWORD_EMBRYO.get()));
        swordEmbryo.setPickUpDelay(10);
        level.addFreshEntity(swordEmbryo);

        // 播放破碎音效
        level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.ANVIL_FALL, SoundSource.PLAYERS, 1.0f, 1.0f);

        // 授予进度：新概念拔剑
        if (player instanceof ServerPlayer sp) {
            ModCriteriaTriggers.NEW_STANDARD_SWORD_DRAWING.trigger(sp);
        }
    }
}
