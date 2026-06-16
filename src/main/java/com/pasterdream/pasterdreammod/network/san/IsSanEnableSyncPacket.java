package com.pasterdream.pasterdreammod.network.san;

import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.capability.san.ISan;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class IsSanEnableSyncPacket
{
    private final int playerId;
    private final boolean isEnabled;

    public IsSanEnableSyncPacket(Player player, ISan capability)
    {
        playerId = player.getId();
        isEnabled = capability.getIsSanEnabled();
    }

    private IsSanEnableSyncPacket(int playerId, boolean isEnabled)
    {
        this.playerId = playerId;
        this.isEnabled = isEnabled;
    }

    public static void encode(IsSanEnableSyncPacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeInt(packet.playerId);
        buffer.writeBoolean(packet.isEnabled);
    }

    public static IsSanEnableSyncPacket decode(FriendlyByteBuf buffer)
    {
        return new IsSanEnableSyncPacket(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(IsSanEnableSyncPacket packet, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            Player player = Minecraft.getInstance().player;
            if (player != null)
            {
                player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
                {
                    capability.setIsSanEnable(packet.isEnabled);
                });
            }
        });
        context.get().setPacketHandled(true);
    }
}
