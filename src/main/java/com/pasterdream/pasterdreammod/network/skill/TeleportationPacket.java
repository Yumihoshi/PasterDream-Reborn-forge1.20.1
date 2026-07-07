package com.pasterdream.pasterdreammod.network.skill;

import com.pasterdream.pasterdreammod.world.skill.TeleportationSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TeleportationPacket {

    public TeleportationPacket() {}

    public static void encode(TeleportationPacket message, FriendlyByteBuf buffer) {}

    public static TeleportationPacket decode(FriendlyByteBuf buffer) {
        return new TeleportationPacket();
    }

    public static void handle(TeleportationPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                TeleportationSkill.execute(player);
            }
        });
        context.get().setPacketHandled(true);
    }
}
