package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class ModMobDrops {

    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof Warden) {
            event.getDrops().add(new ItemEntity(
                    event.getEntity().level(),
                    event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                    new ItemStack(ModItems.SCULK_HEART.get())));
        }
        if (event.getEntity() instanceof ElderGuardian) {
            event.getDrops().add(new ItemEntity(
                    event.getEntity().level(),
                    event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                    new ItemStack(ModItems.ELDER_GUARDIAN_SCALE.get())));
        }
    }
}
