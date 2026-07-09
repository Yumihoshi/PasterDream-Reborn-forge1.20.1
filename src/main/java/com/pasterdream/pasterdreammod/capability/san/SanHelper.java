package com.pasterdream.pasterdreammod.capability.san;

import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.network.san.MaxSanSyncPacket;
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
        AtomicReference<Double> sanValue = new AtomicReference<>(0.0);
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            sanValue.set(capability.getSanValue());
        });
        return sanValue.get();
    }

    public static void setIsSanEnabled(ServerPlayer player, boolean isEnabled)
    {
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.setIsSanEnable(isEnabled);
        });
    }

    public static boolean getIsSanEnabled(ServerPlayer player)
    {
        AtomicReference<Boolean> isEnabled = new AtomicReference<>(false);
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            isEnabled.set(capability.getIsSanEnabled());
        });
        return isEnabled.get();
    }

    public static void setPlayerMaxSanAndSync(ServerPlayer player, double maxSanValue)
    {
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.setMaxSanValue(maxSanValue);
            MaxSanSyncPacket.sendToPlayer(player, capability);
        });
    }

    public static void addPlayerMaxSanAndSync(ServerPlayer player, double maxSanValue)
    {
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.addMaxSanValue(maxSanValue);
            MaxSanSyncPacket.sendToPlayer(player, capability);
        });
    }

    public static double getPlayerMaxSan(ServerPlayer player)
    {
        AtomicReference<Double> maxSanValue = new AtomicReference<>(0.0);
        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            maxSanValue.set(capability.getMaxSanValue());
        });
        return maxSanValue.get();
    }
}
