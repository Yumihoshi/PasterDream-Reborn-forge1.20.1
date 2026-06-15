package com.pasterdream.pasterdreammod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import com.pasterdream.pasterdreammod.network.MeltDreamEnergySyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class PasterDreamDebug
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("pasterdreamdebug")
                .then(Commands.literal("meltdreamenergy")
                        .then(Commands.literal("energyvalue")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("value", DoubleArgumentType.doubleArg(-2147483648, 2147483647))
                                                        .executes(PasterDreamDebug::setMeltDreamEnergy)))
                                        .then(Commands.literal("add")
                                                .then(Commands.argument("value", DoubleArgumentType.doubleArg(-2147483648, 2147483647))
                                                        .executes(PasterDreamDebug::addMeltDreamEnergy)))
                                        .then(Commands.literal("get")
                                                .executes(PasterDreamDebug::getMeltDreamEnergy))))));
    }

    private static int setMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            capability.setMeltDreamEnergy(value);
            MeltDreamEnergySyncPacket.sendToPlayer(player, capability);
            context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的融梦能量设置为 " + value), true);
        });

        return 1;
    }

    private static int addMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            capability.addMeltDreamEnergy(value);
            MeltDreamEnergySyncPacket.sendToPlayer(player, capability);
            context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的融梦能量增加 " + value), true);
        });

        return 1;
    }

    private static int getMeltDreamEnergy(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的融梦能量值为 " + capability.getMeltDreamEnergy()), true);
        });

        return 1;
    }
}
