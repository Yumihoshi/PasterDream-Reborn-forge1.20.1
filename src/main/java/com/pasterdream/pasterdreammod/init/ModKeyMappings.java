package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.network.curio.KaichuOmamoriActivationPacket;
import com.pasterdream.pasterdreammod.network.skill.BlinkPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModKeyMappings {

    public static final KeyMapping BLINK = new KeyMapping(
            "key.pasterdream.blink", GLFW.GLFW_KEY_C, "key.categories.pasterdream");
    public static final KeyMapping KAICHU_OMAMORI = new KeyMapping(
            "key.pasterdream.kaichu_omamori", GLFW.GLFW_KEY_V, "key.categories.pasterdream");

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BLINK);
        event.register(KAICHU_OMAMORI);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null && Minecraft.getInstance().player != null) {
                while (ModKeyMappings.BLINK.consumeClick()) {
                    ModNetwork.CHANNEL.sendToServer(new BlinkPacket());
                }
                while (ModKeyMappings.KAICHU_OMAMORI.consumeClick()) {
                    ModNetwork.CHANNEL.sendToServer(new KaichuOmamoriActivationPacket());
                }
            }
        }
    }
}
