package com.pasterdream.pasterdreammod.init;

import com.mojang.brigadier.CommandDispatcher;
import com.pasterdream.pasterdreammod.command.PasterDreamDebug;
import net.minecraft.commands.CommandSourceStack;

public class ModCommands
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        PasterDreamDebug.register(dispatcher);
    }
}
