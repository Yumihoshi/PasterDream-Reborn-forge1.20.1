package com.pasterdream.pasterdreammod.capability;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.capability.meltdreamenergy.IMeltDreamEnergy;
import com.pasterdream.pasterdreammod.capability.meltdreamenergy.MeltDreamEnergyProvider;
import com.pasterdream.pasterdreammod.network.MeltDreamEnergySyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class EventRegister
{
    //注册能力
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public class CapabilityEvents
    {
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
        {
            event.register(IMeltDreamEnergy.class);
        }
    }

    //在FORGE总线中为玩家附加能力
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class PlayerAttachHandler
    {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
        {
            if (event.getObject() instanceof Player)
            {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "melt_dream_energy"), new MeltDreamEnergyProvider());
            }
        }

        @SubscribeEvent
        public static void onPlayerClone(PlayerEvent.Clone event)
        {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(oldCap -> event.getEntity().getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(newCap -> newCap.copyValueFromOtherMeltDreamEnergy(oldCap)));
            event.getOriginal().invalidateCaps();
            sync(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
        {
            sync(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
        {
            sync(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            sync(event.getEntity());
        }

        private static void sync(Player player)
        {
            if (!player.level().isClientSide)
            {
                player.getCapability(ModCapabilities.MELT_DREAM_ENERGY).ifPresent(capability -> MeltDreamEnergySyncPacket.sendToPlayer(player, capability));
            }
        }
    }
}
