package com.pasterdream.pasterdreammod.network.curio;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CurioActivationPacket {

    public CurioActivationPacket() {}

    public static void encode(CurioActivationPacket message, FriendlyByteBuf buffer) {}

    public static CurioActivationPacket decode(FriendlyByteBuf buffer) {
        return new CurioActivationPacket();
    }

    public static void handle(CurioActivationPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft.getInstance().gameRenderer.displayItemActivation(
                    new ItemStack(ModItems.BLESSING_OF_CECILIA.get()));
        });
        context.get().setPacketHandled(true);
    }
}
