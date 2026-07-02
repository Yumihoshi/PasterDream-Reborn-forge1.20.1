package com.pasterdream.pasterdreammod.command.meltdreamenergy;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.meltdreamenergy.MeltDreamEnergyHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class IsNotNeed
{
    public static int setIsNotNeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        boolean isNeed = BoolArgumentType.getBool(context, "boolean");
        MeltDreamEnergyHelper.setPlayerMeltDreamEnergyIsNeed(player, isNeed);
        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的融梦能量消耗使能状态设置为 " + isNeed), true);
        return 1;
    }

    public static int getIsNotNeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的融梦能量消耗使能状态为 " + MeltDreamEnergyHelper.getPlayerMeltDreamEnergyIsNeed(player)), true);
        return 1;
    }
}
