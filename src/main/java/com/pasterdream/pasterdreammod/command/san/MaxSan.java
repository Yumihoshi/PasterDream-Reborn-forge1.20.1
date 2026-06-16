package com.pasterdream.pasterdreammod.command.san;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.network.san.MaxSanSyncPacket;
import com.pasterdream.pasterdreammod.network.san.SanSyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class MaxSan
{
    public static int setMaxSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.setMaxSanValue(value);
            MaxSanSyncPacket.sendToPlayer(player, capability);
        });

        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的精神值最大值设置为 " + value), true);

        return 1;
    }

    public static int addMaxSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.addMaxSanValue(value);
            MaxSanSyncPacket.sendToPlayer(player, capability);
        });

        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的精神值最大值增加 " + value), true);

        return 1;
    }

    public static int getMaxSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的精神值最大值为 " + capability.getMaxSanValue()), true);
        });

        return 1;
    }
}
