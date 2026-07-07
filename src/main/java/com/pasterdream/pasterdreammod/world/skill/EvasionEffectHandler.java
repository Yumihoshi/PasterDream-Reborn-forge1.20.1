package com.pasterdream.pasterdreammod.world.skill;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import com.pasterdream.pasterdreammod.init.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class EvasionEffectHandler {

    private static final String TRAIL_TAG = "pasterdream_evasion_trail_until";
    private static final int TRAIL_DURATION = 10;
    private static final int TRAIL_INTERVAL = 2;

    public static void execute(ServerLevel level, Player player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        level.playSound(null, x, y, z, ModSounds.EVASION.get(), SoundSource.PLAYERS, 0.7f, 1.0f);

        level.sendParticles(ParticleTypes.CLOUD, x, y + 1.5, z, 12, 0.1, 0.4, 0.1, 0.05);
        level.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), x, y + 0.8, z, 32, 0.3, 0.5, 0.3, 0.1);
        level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y + 0.8, z, 24, 0.3, 0.5, 0.3, 0.1);

        player.setSprinting(true);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 7, 1, false, false));

        int trailUntil = level.getServer().getTickCount() + TRAIL_DURATION;
        player.getPersistentData().putInt(TRAIL_TAG, trailUntil);
    }

    public static void onPlayerTick(Player player) {
        if (!(player.level() instanceof ServerLevel level)) return;

        int trailUntil = player.getPersistentData().getInt(TRAIL_TAG);
        int now = level.getServer().getTickCount();
        if (trailUntil <= 0 || now >= trailUntil) {
            if (trailUntil > 0) {
                player.getPersistentData().remove(TRAIL_TAG);
            }
            return;
        }

        if ((now - (trailUntil - TRAIL_DURATION)) % TRAIL_INTERVAL != 0) return;

        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (player.getDeltaMovement().lengthSqr() > 0.01) {
            level.sendParticles(ParticleTypes.CLOUD, x, y + 1.5, z, 6, 0.1, 0.4, 0.1, 0.05);
            level.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), x, y + 0.8, z, 16, 0.3, 0.5, 0.3, 0.1);
        }

        if (now >= trailUntil) {
            player.getPersistentData().remove(TRAIL_TAG);
            player.setSprinting(false);
        }
    }
}
