package com.pasterdream.pasterdreammod.network.meltdreamenergy;

import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.capability.meltdreamenergy.IMeltDreamEnergy;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MeltDreamEnergySyncPacket
{
    private final int playerId;
    private final double meltDreamEnergy;

    public MeltDreamEnergySyncPacket(Player player, IMeltDreamEnergy capability)
    {
        this.playerId = player.getId();
        this.meltDreamEnergy = capability.getMeltDreamEnergy();
    }

    private MeltDreamEnergySyncPacket(int playerId, double meltDreamEnergy)
    {
        this.playerId = playerId;
        this.meltDreamEnergy = meltDreamEnergy;
    }

    public static void encode(MeltDreamEnergySyncPacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeInt(packet.playerId);
        buffer.writeDouble(packet.meltDreamEnergy);
    }

    public static MeltDreamEnergySyncPacket decode(FriendlyByteBuf buffer)
    {
        return new MeltDreamEnergySyncPacket(buffer.readInt(), buffer.readDouble());
    }

    public static void handle(MeltDreamEnergySyncPacket packet, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            Player player = Minecraft.getInstance().player;
            if (player != null)
            {
                player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
                {
                    capability.setMeltDreamEnergy(packet.meltDreamEnergy);
                });
            }
        });
        context.get().setPacketHandled(true);
    }

    public static void sendToPlayer(Player player, IMeltDreamEnergy capability)
    {
        if (player instanceof ServerPlayer serverPlayer)
        {
            ModNetwork.sendMeltDreamEnergySyncPacketToPlayer(new MeltDreamEnergySyncPacket(player, capability), serverPlayer);
        }
    }
}
