package com.pasterdream.pasterdreammod.network.animationstatechange;

import com.pasterdream.pasterdreammod.world.block.geckolibblock.AnimatableSync;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AnimationStateChangePacket
{
    private final BlockPos pos;
    private final int animationState;

    public AnimationStateChangePacket(BlockPos pos, int animationState)
    {
        this.pos = pos;
        this.animationState = animationState;
    }

    public static void encode(AnimationStateChangePacket msg, FriendlyByteBuf buf)
    {
        buf.writeBlockPos(msg.pos);
        buf.writeInt(msg.animationState);
    }

    public static AnimationStateChangePacket decode(FriendlyByteBuf buf)
    {
        return new AnimationStateChangePacket(buf.readBlockPos(), buf.readInt());
    }

    public static void handle(AnimationStateChangePacket message, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            if (Minecraft.getInstance().level != null)
            {
                BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(message.pos);
                if (blockEntity instanceof AnimatableSync sync)
                {
                    sync.setAnimationState(message.animationState);
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
