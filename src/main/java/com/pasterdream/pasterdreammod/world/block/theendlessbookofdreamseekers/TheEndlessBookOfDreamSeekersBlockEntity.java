package com.pasterdream.pasterdreammod.world.block.theendlessbookofdreamseekers;

import com.pasterdream.pasterdreammod.init.ModBlockEntities;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import com.pasterdream.pasterdreammod.network.animationstatechange.AnimationStateChangePacket;
import com.pasterdream.pasterdreammod.world.block.geckolibblock.AnimatableSync;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TheEndlessBookOfDreamSeekersBlockEntity extends BlockEntity implements GeoBlockEntity, AnimatableSync
{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int animationState = 0;
    private int tickCount = 0;
    private int useParticleTick = 0;
    private ItemStack pendingDropItem = ItemStack.EMPTY;

    public TheEndlessBookOfDreamSeekersBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.THE_ENDLESS_BOOK_OF_DREAM_SEEKERS.get(), pos, state);
    }

    public void serverTick(Level level, BlockPos pos)
    {
        tickCount++;
        if (tickCount >= 10)
        {
            tickCount = 0;
            if (level instanceof ServerLevel serverLevel)
            {
                serverLevel.sendParticles(ParticleTypes.ENCHANT,
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    6, 0.4, 0.4, 0.4, 1);
            }
        }

        if (useParticleTick > 0 && level instanceof ServerLevel serverLevel)
        {
            double px = pos.getX() + 0.5;
            double py = pos.getY() + 0.4;
            double pz = pos.getZ() + 0.5;
            int tick = useParticleTick;

            if (tick == 1)
            {
                level.playSound(null, pos, SoundEvents.BOOK_PAGE_TURN, SoundSource.NEUTRAL, 1.0F, 1.0F);
                serverLevel.sendParticles(ParticleTypes.ENCHANT, px, py, pz, 16, 1, 1, 1, 0.5);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), px, py, pz, 16, 1, 1, 1, 0.1);
            }
            else if (tick == 10)
            {
                level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.NEUTRAL, 1.0F, 1.0F);
                serverLevel.sendParticles(ParticleTypes.ENCHANT, px, py, pz, 16, 1, 1, 1, 0.5);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), px, py, pz, 16, 1, 1, 1, 0.1);
            }
            else if (tick == 20)
            {
                if (!pendingDropItem.isEmpty())
                {
                    Block.popResource(level, pos, pendingDropItem);
                    pendingDropItem = ItemStack.EMPTY;
                }
                serverLevel.sendParticles(ParticleTypes.ENCHANT, px, py, pz, 16, 1, 1, 1, 0.5);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), px, py, pz, 16, 1, 1, 1, 0.1);
            }
            else if (tick == 30)
            {
                level.playSound(null, pos, SoundEvents.BOOK_PUT, SoundSource.NEUTRAL, 1.0F, 1.0F);
                serverLevel.sendParticles(ParticleTypes.ENCHANT, px, py, pz, 16, 1, 1, 1, 0.5);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), px, py, pz, 16, 1, 1, 1, 0.1);
            }
            else if (tick == 40)
            {
                serverLevel.sendParticles(ParticleTypes.ENCHANT, px, py, pz, 16, 1, 1, 1, 0.5);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), px, py, pz, 16, 1, 1, 1, 0.1);
            }

            useParticleTick++;
            if (useParticleTick > 40)
                useParticleTick = 0;
        }
    }

    public void triggerUseParticles(ItemStack dropStack)
    {
        this.pendingDropItem = dropStack;
        this.useParticleTick = 1;
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slotIndex)
        {
            setChangedAndSync();
        }

        @Override
        public boolean isItemValid(int slotIndex, ItemStack stack)
        {
            return true;
        }
    };

    private final LazyOptional<IItemHandler> itemHandlerCap = LazyOptional.of(() -> itemHandler);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return itemHandlerCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        itemHandlerCap.invalidate();
    }

    private void setChangedAndSync()
    {
        setChanged();
        if (level != null && !level.isClientSide)
        {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "state", 0, this::stateController));
    }

    private PlayState stateController(AnimationState<TheEndlessBookOfDreamSeekersBlockEntity> state)
    {
        AnimationController<TheEndlessBookOfDreamSeekersBlockEntity> controller = state.getController();

        if(animationState == 0)
        {
            controller.setAnimation(RawAnimation.begin().thenLoop("0"));
        }
            else
            {
                controller.setAnimation(RawAnimation.begin().thenPlay("1"));
                if(controller.getAnimationState() == AnimationController.State.STOPPED)
                {
                    animationState = 0;
                }
            }

        return PlayState.CONTINUE;
    }

    public void setAnimationState(int state)
    {
        this.animationState = state;
        if (level != null && !level.isClientSide)
        {
            sendAnimationSync();
        }
    }

    private void sendAnimationSync()
    {
        AnimationStateChangePacket packet = new AnimationStateChangePacket(this.worldPosition, this.animationState);
        ModNetwork.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(this.worldPosition)), packet);
    }

    public int getAnimationState()
    {
        return animationState;
    }

    public ItemStackHandler getItemHandler()
    {
        return itemHandler;
    }
}
