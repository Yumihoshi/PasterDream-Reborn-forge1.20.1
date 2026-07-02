package com.pasterdream.pasterdreammod.command.san;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.san.SanHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class San
{
    public static int setSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");
        SanHelper.setPlayerSanAndSync(player, value);
        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的精神值设置为 " + value), true);
        return 1;
    }

    public static int addSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        double value = DoubleArgumentType.getDouble(context, "value");
        SanHelper.addPlayerSanAndSync(player, value);
        context.getSource().sendSuccess(() -> Component.translatable("已将 " + player.getName().getString() + " 的精神值增加 " + value), true);
        return 1;
    }

    public static int getSan(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + " 的精神值为 " + SanHelper.getPlayerSan(player)), true);
        return 1;
    }
}
