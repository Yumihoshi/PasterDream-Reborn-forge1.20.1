package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class DreamHarpOfWandererBuffEffect extends MobEffect {

    private static final String UUID_STRING = "d7b2bd54-dee3-47f6-b20b-b5556b221228";

    public DreamHarpOfWandererBuffEffect() {
        super(MobEffectCategory.BENEFICIAL, -1934926);
        this.addAttributeModifier(ModAttributes.SAN_VARIABILITY.get(), UUID_STRING, 4.8, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MAX_HEALTH, UUID_STRING, 4, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID_STRING, 0.01, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.addAttributeModifiers(entity, attributeMap, amplifier);
        entity.heal(Math.max(4 << (amplifier + 1), 0));
    }
}
