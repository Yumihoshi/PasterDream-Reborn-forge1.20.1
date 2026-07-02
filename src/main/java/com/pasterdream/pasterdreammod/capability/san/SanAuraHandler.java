package com.pasterdream.pasterdreammod.capability.san;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.helper.sanbiomeratemanager.SanBiomeRateManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class SanAuraHandler
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof ServerPlayer player))
        {
            return;
        }

        Level level = player.level();
        BlockPos pos = player.blockPosition();

        int light = level.getMaxLocalRawBrightness(pos);
        double lightRate = (light - 7) * 0.0001;

        ResourceLocation biomeId = level.getBiome(pos).unwrapKey().map(key -> key.location()).orElse(null);
        double biomeRate = biomeId != null ? SanBiomeRateManager.getRate(biomeId) : 0.0;

        double sanRate = lightRate + biomeRate;
        if (sanRate != 0)
        {
            SanHelper.addPlayerSanAndSync(player, sanRate);
        }
    }
}
