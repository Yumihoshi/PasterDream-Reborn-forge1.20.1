package com.pasterdream.pasterdreammod.world.entity;

import com.pasterdream.pasterdreammod.init.ModEntities;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;

public class PinkChickenEntity extends Animal {
    public int eggTime = this.random.nextInt(6000) + 6000;

    public PinkChickenEntity(PlayMessages.SpawnEntity packet, Level level) {
        this(ModEntities.PINK_CHICKEN.get(), level);
    }

    public PinkChickenEntity(EntityType<PinkChickenEntity> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.xpReward = 2;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0, Ingredient.of(ModItems.DYEDREAM_FRUIT.get()), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8, 20) {
            @Override
            protected Vec3 getPosition() {
                RandomSource random = PinkChickenEntity.this.getRandom();
                return new Vec3(
                        PinkChickenEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16),
                        PinkChickenEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16),
                        PinkChickenEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16));
            }
        });
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15f, 1.0f);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(ModItems.PINK_EGG.get());
            this.eggTime = this.random.nextInt(6000) + 6000;
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageable) {
        PinkChickenEntity offspring = ModEntities.PINK_CHICKEN.get().create(serverLevel);
        if (offspring != null) {
            offspring.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(offspring.blockPosition()),
                    MobSpawnType.BREEDING, null, null);
        }
        return offspring;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Ingredient.of(ModItems.DYEDREAM_FRUIT.get()).test(stack);
    }

    public static void init() {
        SpawnPlacements.register(ModEntities.PINK_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, reason, pos, random) ->
                        level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
                                && level.getRawBrightness(pos, 0) > 8);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.FLYING_SPEED, 0.3)
                .add(Attributes.FOLLOW_RANGE, 16);
    }
}
