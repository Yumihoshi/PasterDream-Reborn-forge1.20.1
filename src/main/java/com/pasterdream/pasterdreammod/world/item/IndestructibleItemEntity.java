package com.pasterdream.pasterdreammod.world.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IndestructibleItemEntity extends ItemEntity {
    public IndestructibleItemEntity(Level level, double x, double y, double z, ItemStack stack) {
        super(level, x, y, z, stack);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.IS_EXPLOSION)) return false;
        if (source == level().damageSources().cactus()) return false;
        return super.hurt(source, amount);
    }
}
