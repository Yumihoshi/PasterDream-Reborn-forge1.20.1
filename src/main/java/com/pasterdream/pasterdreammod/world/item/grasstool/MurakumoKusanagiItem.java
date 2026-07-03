package com.pasterdream.pasterdreammod.world.item.grasstool;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import com.pasterdream.pasterdreammod.init.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class MurakumoKusanagiItem extends SwordItem {

    private static final int COOLDOWN_TICKS = 80;

    public MurakumoKusanagiItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean retval = super.hurtEnemy(stack, target, attacker);
        Level level = target.level();

        if (stack.getOrCreateTag().getBoolean("skill")) {
            if (!level.isClientSide()) {
                level.playSound(null, target.blockPosition(), SoundEvents.DOLPHIN_AMBIENT_WATER, SoundSource.NEUTRAL, 1.5f, 1.0f);
                level.playSound(null, target.blockPosition(), ModSounds.SKILL1.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);
            } else {
                level.playLocalSound(target.getX(), target.getY(), target.getZ(), SoundEvents.DOLPHIN_AMBIENT_WATER, SoundSource.NEUTRAL, 1.5f, 1.0f, false);
                level.playLocalSound(target.getX(), target.getY(), target.getZ(), ModSounds.SKILL1.get(), SoundSource.NEUTRAL, 1.0f, 1.0f, false);
            }
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), target.getX(), target.getY(), target.getZ(), 64, 1.5, 1.8, 1.5, 0.2);
                serverLevel.sendParticles(ModParticleTypes.SPORE_PARTICLE.get(), target.getX(), target.getY(), target.getZ(), 64, 1.5, 1.8, 1.5, 0.2);
            }
            if (!target.getPersistentData().getBoolean("pasterdream:murakumo_kusanagi_hit")) {
                double pasterAtk = stack.getOrCreateTag().getDouble("paster_atk");
                double skillMultiplier = stack.getOrCreateTag().getDouble("skill_multiplier");
                target.hurt(attacker.damageSources().mobAttack(attacker), (float) (pasterAtk * skillMultiplier));
            }
            stack.getOrCreateTag().putBoolean("skill", false);
            target.getPersistentData().putBoolean("pasterdream:murakumo_kusanagi_hit", true);
        } else {
            if (!level.isClientSide()) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
            }
        }
        return retval;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(level, player, hand);
        ItemStack stack = ar.getObject();

        if (!stack.getOrCreateTag().getBoolean("skill")) {
            stack.getOrCreateTag().putBoolean("skill", true);
            double pasterAtk = player.getAttributeValue(Attributes.ATTACK_DAMAGE) + 3.75 * stack.getEnchantmentLevel(Enchantments.SHARPNESS);
            stack.getOrCreateTag().putDouble("paster_atk", pasterAtk);
            stack.getOrCreateTag().putDouble("skill_multiplier", 1.0);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ModParticleTypes.BUFF_0_PARTICLE.get(), player.getX(), player.getY() - 0.5, player.getZ(), 20, 0.5, 1, 0.5, 1);
            }
            if (!level.isClientSide()) {
                level.playSound(null, player.blockPosition(), ModSounds.SWORD1.get(), SoundSource.PLAYERS, 0.8f, 1.0f);
            } else {
                level.playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.SWORD1.get(), SoundSource.PLAYERS, 0.8f, 1.0f, false);
            }
            player.swing(InteractionHand.MAIN_HAND, true);
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }

        return ar;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.1"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.2"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.3"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.4"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.5"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.6"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.murakumo_kusanagi.7"));
    }
}
