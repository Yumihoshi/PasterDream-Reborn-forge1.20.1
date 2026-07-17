package com.pasterdream.pasterdreammod.world.entity;

import com.pasterdream.pasterdreammod.init.ModEntities;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;

public class ThrownPinkEgg extends ThrowableItemProjectile {
    public ThrownPinkEgg(EntityType<? extends ThrownPinkEgg> type, Level level) {
        super(type, level);
    }

    public ThrownPinkEgg(PlayMessages.SpawnEntity packet, Level level) {
        this(ModEntities.THROWN_PINK_EGG.get(), level);
    }

    public ThrownPinkEgg(Level level, LivingEntity shooter) {
        super(ModEntities.THROWN_PINK_EGG.get(), shooter, level);
    }

    public ThrownPinkEgg(Level level, double x, double y, double z) {
        super(ModEntities.THROWN_PINK_EGG.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PINK_EGG.get();
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                        this.getX(), this.getY(), this.getZ(),
                        ((double) this.random.nextFloat() - 0.5D) * 0.08D,
                        ((double) this.random.nextFloat() - 0.5D) * 0.08D,
                        ((double) this.random.nextFloat() - 0.5D) * 0.08D);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            if (this.random.nextInt(5) == 0) {
                PinkChickenEntity chicken = ModEntities.PINK_CHICKEN.get().create(this.level());
                if (chicken != null) {
                    chicken.setBaby(true);
                    chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                    this.level().addFreshEntity(chicken);
                }
            }
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }
}
