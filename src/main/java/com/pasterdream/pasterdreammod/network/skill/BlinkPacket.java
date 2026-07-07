package com.pasterdream.pasterdreammod.network.skill;

import com.pasterdream.pasterdreammod.world.skill.BlinkSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BlinkPacket {

    public BlinkPacket() {}

    public static void encode(BlinkPacket message, FriendlyByteBuf buffer) {}

    public static BlinkPacket decode(FriendlyByteBuf buffer) {
        return new BlinkPacket();
    }

    public static void handle(BlinkPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                BlinkSkill.execute(player);
            }
        });
        context.get().setPacketHandled(true);
    }
}
