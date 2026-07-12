package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import com.pasterdream.pasterdreammod.init.ModBlockEntities;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.network.animationstatechange.AnimationStateChangePacket;
import com.pasterdream.pasterdreammod.world.block.geckolibblock.AnimatableSync;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MeltDreamCrystalChestBlockEntity extends BlockEntity implements GeoBlockEntity, AnimatableSync
{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int animationState = 0;

    public MeltDreamCrystalChestBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.MELT_DREAM_CRYSTAL_CHEST.get(), pos, state);
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet)
    {
        CompoundTag tag = packet.getTag();
        if (tag != null)
        {
            load(tag);
        }
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

    private PlayState stateController(AnimationState<MeltDreamCrystalChestBlockEntity> state)
    {
        AnimationController<MeltDreamCrystalChestBlockEntity> controller = state.getController();

        //if(animationState == 0)
        //{
        //    controller.setAnimation(RawAnimation.begin().thenLoop("0"));
        //}
        //else
        //    {
        //        controller.setAnimation(RawAnimation.begin().thenPlay("1"));
        //        if(controller.getAnimationState() == AnimationController.State.STOPPED)
        //        {
        //            animationState = 0;
        //        }
        //    }

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
}
