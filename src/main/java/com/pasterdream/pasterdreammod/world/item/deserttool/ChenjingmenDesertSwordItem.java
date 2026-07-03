package com.pasterdream.pasterdreammod.world.item.deserttool;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import com.pasterdream.pasterdreammod.init.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.List;

public class ChenjingmenDesertSwordItem extends SwordItem {

    private static final int COOLDOWN_TICKS = 200;

    public ChenjingmenDesertSwordItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (selected && entity instanceof LivingEntity living && !level.isClientSide()) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 0, false, false));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 0, false, false));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean retval = super.hurtEnemy(stack, target, attacker);
        if (stack.getOrCreateTag().getBoolean("skill")) {
            Level level = target.level();
            level.playSound(null, target.getX(), target.getY(), target.getZ(),
                    ModSounds.SKILL1.get(), target.getSoundSource(), 1.5f, 1.0f);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, target.getX(), target.getY(), target.getZ(), 64, 1.5, 1.8, 1.5, 0.2);
                serverLevel.sendParticles(ModParticleTypes.DUST_0_PARTICLE.get(), target.getX(), target.getY(), target.getZ(), 64, 1.5, 1.8, 1.5, 0.2);
            }
            double pasterAtk = stack.getOrCreateTag().getDouble("paster_atk");
            double skillMultiplier = stack.getOrCreateTag().getDouble("skill_multiplier");
            double lostHpRatio = stack.getOrCreateTag().getDouble("lost_hp_ratio");
            float bonusDamage = (float) ((5 + (lostHpRatio * 2 + 1) * pasterAtk) * skillMultiplier);
            target.hurt(attacker.damageSources().mobAttack(attacker), bonusDamage);
            stack.getOrCreateTag().putBoolean("skill", false);
        }
        return retval;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(level, player, hand);
        ItemStack stack = ar.getObject();

        if (!stack.getOrCreateTag().getBoolean("skill")) {
            stack.getOrCreateTag().putBoolean("skill", true);
            double pasterAtk = player.getAttributeValue(Attributes.ATTACK_DAMAGE) + stack.getEnchantmentLevel(Enchantments.SHARPNESS) * 0.5;
            stack.getOrCreateTag().putDouble("paster_atk", pasterAtk);
            stack.getOrCreateTag().putDouble("skill_multiplier", 1.0);
            double maxHealth = player.getMaxHealth();
            double currentHealth = player.getHealth();
            stack.getOrCreateTag().putDouble("lost_hp_ratio", (maxHealth - currentHealth) / maxHealth);

            if (!level.isClientSide()) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 60, 0, false, false));
            }

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ModParticleTypes.BUFF_0_PARTICLE.get(), player.getX(), player.getY() - 0.5, player.getZ(), 20, 0.5, 1, 0.5, 1);
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.SWORD1.get(), player.getSoundSource(), 0.8f, 1.0f);

            player.swing(InteractionHand.MAIN_HAND, true);
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }

        return ar;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.1"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.2"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.3"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.4"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.5"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.6"));
        tooltip.add(Component.translatable("tooltip.pasterdreammod.chenjingmen_desert_sword.7"));
    }
}
