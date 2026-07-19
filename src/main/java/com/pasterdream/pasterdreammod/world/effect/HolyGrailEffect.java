package com.pasterdream.pasterdreammod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HolyGrailEffect extends MobEffect {
    private static final String UUID = "4ed7ca6e-d48f-4654-a813-62a33733f7b3";

    public HolyGrailEffect() {
        super(MobEffectCategory.BENEFICIAL, -6341);
        this.addAttributeModifier(Attributes.MAX_HEALTH, UUID,
                15, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        entity.setHealth(entity.getMaxHealth());
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var hpAttr = entity.getAttribute(Attributes.MAX_HEALTH);
        if (hpAttr != null) {
            hpAttr.removeModifier(java.util.UUID.fromString(UUID));
            if (entity.getHealth() > entity.getMaxHealth()) {
                entity.setHealth(entity.getMaxHealth());
            }
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.getHealth() < entity.getMaxHealth()) {
            float healthPercent = entity.getHealth() / entity.getMaxHealth();
            int regenLevel;
            if (healthPercent >= 0.66f) {
                regenLevel = 0; // 生命恢复 I
            } else if (healthPercent >= 0.33f) {
                regenLevel = 1; // 生命恢复 II
            } else {
                regenLevel = 2; // 生命恢复 III
            }
            // 直接回血，与原版再生速率一致: 1 HP per (50 >> level) ticks
            // 每 20 tick 执行一次，按比例回复
            entity.heal(20.0f / (50 >> regenLevel));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0; // 每秒检查一次
    }
}
