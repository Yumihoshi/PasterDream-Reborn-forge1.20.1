package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.init.ModEffects;
import com.pasterdream.pasterdreammod.init.ModItems;
import com.pasterdream.pasterdreammod.world.skill.EvasionEffectHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class PlayerEvents {

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath("pasterdream", "dyedream_world"));

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        EvasionEffectHandler.onPlayerTick(player);

        if (!player.level().isClientSide()) {
            int dreamTeleportTicks = player.getPersistentData().getInt("pasterdream:dream_teleport_ticks");
            if (dreamTeleportTicks > 0) {
                dreamTeleportTicks--;
                if (dreamTeleportTicks <= 0) {
                    if (player instanceof ServerPlayer serverPlayer
                            && !player.level().dimension().equals(DYEDREAM_WORLD)) {
                        // 重置床的 OCCUPIED 状态
                        CompoundTag data = player.getPersistentData();
                        if (data.contains("pasterdream:dream_bed_x")) {
                            BlockPos bedPos = new BlockPos(
                                    data.getInt("pasterdream:dream_bed_x"),
                                    data.getInt("pasterdream:dream_bed_y"),
                                    data.getInt("pasterdream:dream_bed_z"));
                            var bedState = player.level().getBlockState(bedPos);
                            if (bedState.hasProperty(BedBlock.OCCUPIED)) {
                                player.level().setBlock(bedPos,
                                        bedState.setValue(BedBlock.OCCUPIED, false), 3);
                            }
                        }
                        ServerLevel dyedream = serverPlayer.server.getLevel(DYEDREAM_WORLD);
                        if (dyedream != null) {
                            serverPlayer.teleportTo(dyedream, 0.5, 108, 0.5,
                                    serverPlayer.getYRot(), serverPlayer.getXRot());
                        }
                    }
                    player.getPersistentData().remove("pasterdream:dream_teleport_ticks");
                    player.getPersistentData().remove("pasterdream:dream_bed_x");
                    player.getPersistentData().remove("pasterdream:dream_bed_y");
                    player.getPersistentData().remove("pasterdream:dream_bed_z");
                } else {
                    player.getPersistentData().putInt("pasterdream:dream_teleport_ticks", dreamTeleportTicks);
                }
            }
        }
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

        // 反击戒指：成功闪避时获得反击 buff
        if (CuriosApi.getCuriosInventory(player)
                .map(h -> h.findFirstCurio(ModItems.COUNTER_RING.get()).isPresent())
                .orElse(false)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false));
            player.addEffect(new MobEffectInstance(ModEffects.COUNTER_ATTACK_BUFF.get(), 200, 0, false, false));
        }

        if (player.level() instanceof ServerLevel serverLevel) {
            EvasionEffectHandler.execute(serverLevel, player);
        }
    }

    /** 反击 buff 命中后移除：只让闪避后第一次攻击吃到加成 */
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (!player.hasEffect(ModEffects.COUNTER_ATTACK_BUFF.get())) return;
        player.removeEffect(ModEffects.COUNTER_ATTACK_BUFF.get());
        player.removeEffect(MobEffects.DAMAGE_BOOST);
    }

    public static void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        // 躺下给予3分钟休憩效果
        player.addEffect(new MobEffectInstance(ModEffects.REST_BUFF.get(),
                3600, 0, false, false));

        if (!player.hasEffect(ModEffects.DREAM_WISH_BUFF.get())) return;

        BlockPos pos = event.getPos();
        CompoundTag data = player.getPersistentData();
        data.putInt("pasterdream:dream_teleport_ticks", 60);
        data.putInt("pasterdream:dream_bed_x", pos.getX());
        data.putInt("pasterdream:dream_bed_y", pos.getY());
        data.putInt("pasterdream:dream_bed_z", pos.getZ());
    }
}
