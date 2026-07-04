package com.pasterdream.pasterdreammod.network.researchtable;

import com.pasterdream.pasterdreammod.world.block.researchtable.ResearchTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class ResearchTableCopyPacket
{
    private final BlockPos blockPosition;

    public ResearchTableCopyPacket(BlockPos blockPosition)
    {
        this.blockPosition = blockPosition;
    }

    public static void encode(ResearchTableCopyPacket message, FriendlyByteBuf buffer)
    {
        buffer.writeBlockPos(message.blockPosition);
    }

    public static ResearchTableCopyPacket decode(FriendlyByteBuf buffer)
    {
        return new ResearchTableCopyPacket(buffer.readBlockPos());
    }

    public static void handle(ResearchTableCopyPacket message, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            ServerPlayer player = context.get().getSender();
            if (player == null)
            {
                return;
            }

            ServerLevel level = player.serverLevel();
            BlockEntity blockEntity = level.getBlockEntity(message.blockPosition);
            if (blockEntity instanceof ResearchTableBlockEntity researchTable)
            {
                researchTable.copyNote();
            }
        });
        context.get().setPacketHandled(true);
    }
}
