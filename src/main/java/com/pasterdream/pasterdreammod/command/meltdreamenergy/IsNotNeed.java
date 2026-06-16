package com.pasterdream.pasterdreammod.command.meltdreamenergy;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pasterdream.pasterdreammod.capability.ModCapabilities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class IsNotNeed
{
    public static int setIsNotNeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        boolean value = BoolArgumentType.getBool(context, "boolean");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            capability.setIsOrNotNeedConsumeDreamEnergy(value);
        });

        if (value)
        {
            context.getSource().sendSuccess(() -> Component.translatable("已使能 " + player.getName().getString() + " 的融梦能量值消耗"), true);
        }
            else
            {
                context.getSource().sendSuccess(() -> Component.translatable("已禁用 " + player.getName().getString() + " 的融梦能量值消耗"), true);
            }

        return 1;
    }

    public static int getIsNotNeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");

        player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability ->
        {
            boolean value = capability.getIsOrNotNeedConsumeDreamEnergy();
            if(value)
            {
                context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + "的融梦能量值消耗状态为使能"), true);
            }
                else
                {
                    context.getSource().sendSuccess(() -> Component.translatable(player.getName().getString() + "的融梦能量值消耗状态为禁用"), true);
                }
        });

        return 1;
    }
}
