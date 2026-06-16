package com.pasterdream.pasterdreammod.command.meltdreamenergy;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.network.meltdreamenergy.MaxMeltDreamEnergySyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class MaxMeltDreamEnergy
{
    public static int setMaxMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            capability.setMaxMeltDreamEnergy(value);
            MaxMeltDreamEnergySyncPacket.sendToPlayer(player, capability);
        });

        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的最大融梦能量设置为 " + value), true);

        return 1;
    }

    public static int addMaxMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            capability.addMaxMeltDreamEnergy(value);
            MaxMeltDreamEnergySyncPacket.sendToPlayer(player, capability);
        });

        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的最大融梦能量增加 " + value), true);

        return 1;
    }

    public static int getMaxMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的最大融梦能量值为 " + capability.getMaxMeltDreamEnergy()), true);
        });

        return 1;
    }
}
