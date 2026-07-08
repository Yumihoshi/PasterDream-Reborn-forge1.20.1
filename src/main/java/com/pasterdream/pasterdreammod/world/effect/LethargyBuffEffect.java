package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LethargyBuffEffect extends MobEffect {

    public LethargyBuffEffect() {
        super(MobEffectCategory.HARMFUL, -2907486);
    }

    @Override
    public String getDescriptionId() {
        return "effect.pasterdream.lethargy_buff";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        modifyBase(entity, true);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
        modifyBase(entity, false);
    }

    private void modifyBase(LivingEntity entity, boolean apply) {
        double sign = apply ? 1 : -1;
        if (entity.getAttribute(ModAttributes.BLINK_CD.get()) != null) {
            entity.getAttribute(ModAttributes.BLINK_CD.get())
                    .setBaseValue(entity.getAttribute(ModAttributes.BLINK_CD.get()).getBaseValue() + sign * 0.5);
        }
        if (entity.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED)
                    .setBaseValue(entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() + sign * -0.01);
        }
        if (entity.getAttribute(Attributes.ATTACK_SPEED) != null) {
            entity.getAttribute(Attributes.ATTACK_SPEED)
                    .setBaseValue(entity.getAttribute(Attributes.ATTACK_SPEED).getBaseValue() + sign * -0.1);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
