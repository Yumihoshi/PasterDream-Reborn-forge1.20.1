package com.pasterdream.pasterdreammod.command.san;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.san.SanHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class IsSanEnabled
{
    public static int setIsEnabled(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        boolean isEnabled = BoolArgumentType.getBool(context, "boolean");
        SanHelper.setIsSanEnabledAndSync(player, isEnabled);
        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的精神值使能状态设置为 " + isEnabled), true);
        return 1;
    }

    public static int getIsEnabled(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的精神值使能状态为 " + SanHelper.getIsSanEnabled(player)), true);
        return 1;
    }
}
