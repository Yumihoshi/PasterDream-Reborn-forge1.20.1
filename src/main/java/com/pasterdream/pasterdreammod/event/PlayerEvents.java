package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.init.ModEffects;
import com.pasterdream.pasterdreammod.world.skill.EvasionEffectHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerEvents {

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        EvasionEffectHandler.onPlayerTick(player);
    }

    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!player.hasEffect(ModEffects.EVASION_BUFF.get())) return;

        var effect = player.getEffect(ModEffects.EVASION_BUFF.get());
        if (effect == null) return;

        int amplifier = effect.getAmplifier();
        int duration = effect.getDuration();
        player.removeEffect(ModEffects.EVASION_BUFF.get());

        // multi-level evasion: consume one level, keep remainder
        if (amplifier > 0) {
            player.addEffect(new MobEffectInstance(ModEffects.EVASION_BUFF.get(),
                    duration, amplifier - 1, false, false));
        }

        event.setAmount(0);
        event.setCanceled(true);

        if (player.level() instanceof ServerLevel serverLevel) {
            EvasionEffectHandler.execute(serverLevel, player);
        }
    }
}
