package com.pasterdream.pasterdreammod.command.san;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class IsSanEnabled
{
    public static int setIsEnabled(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        boolean value = BoolArgumentType.getBool(context, "boolean");

        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            capability.setIsSanEnable(value);
        });

        if (value)
        {
            context.getSource().sendSuccess(() -> Component.translatable("已使能 " + player.getName().getString() + " 的精神值"), true);
        }
            else
            {
                context.getSource().sendSuccess(() -> Component.translatable("已禁用 " + player.getName().getString() + " 的精神值"), true);
            }

        return 1;
    }

    public static int getIsEnabled(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(ModCapabilities.SAN).ifPresent(capability ->
        {
            boolean value = capability.getIsSanEnabled();
            if(value)
            {
                context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + "的精神值为使能"), true);
            }
                else
                {
                    context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + "的精神值为禁用"), true);
                }
        });

        return 1;
    }
}
