package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class CookBuffEffect extends MobEffect {
    private static final UUID SAN_MODIFIER_UUID = UUID.fromString("de214503-66e4-4d52-84c6-0a856b297f87");

    public CookBuffEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xEDF1C3);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && !player.level().isClientSide()) {
            AttributeInstance attr = player.getAttribute(ModAttributes.SAN_VARIABILITY.get());
            if (attr == null) return;

            MobEffectInstance instance = player.getEffect(this);
            if (instance == null) {
                attr.removeModifier(SAN_MODIFIER_UUID);
                return;
            }

            double value = 1.2 * (amplifier + 1);
            AttributeModifier existing = attr.getModifier(SAN_MODIFIER_UUID);
            if (existing == null || existing.getAmount() != value) {
                if (existing != null) attr.removeModifier(SAN_MODIFIER_UUID);
                attr.addPermanentModifier(new AttributeModifier(SAN_MODIFIER_UUID,
                        "Cook buff SAN", value, AttributeModifier.Operation.ADDITION));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0; // check every second, sufficient for maintaining the modifier
    }
}
