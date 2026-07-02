package com.pasterdream.pasterdreammod.capability.san;

import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.network.san.SanSyncPacket;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.atomic.AtomicReference;

public class SanHelper
{
    public static void setPlayerSanAndSync(ServerPlayer player, double sanValue)
    {
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.setSanValue(sanValue);
            SanSyncPacket.sendToPlayer(player, capability);
        });
    }

    public static void addPlayerSanAndSync(ServerPlayer player, double sanValue)
    {
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.addSanValue(sanValue);
            SanSyncPacket.sendToPlayer(player, capability);
        });
    }

    public static double getPlayerSan(ServerPlayer player)
    {
        AtomicReference<Double> sanValue = new AtomicReference<>(null);
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            sanValue.set(capability.getSanValue());
        });
        return sanValue.get();
    }
}
