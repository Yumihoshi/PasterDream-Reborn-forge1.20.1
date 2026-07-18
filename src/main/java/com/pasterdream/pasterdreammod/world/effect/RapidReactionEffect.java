package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

public class RapidReactionEffect extends MobEffect {
    private static final String UUID = "ecd3ac4b-03fb-46e9-afbf-6a42d706bb18";
    public RapidReactionEffect() {
        super(MobEffectCategory.BENEFICIAL, -9315102);
        this.addAttributeModifier(ModAttributes.BLINK_CD.get(), UUID,
                -0.3, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(ForgeMod.STEP_HEIGHT_ADDITION.get(), UUID,
                0.5, AttributeModifier.Operation.ADDITION);
    }
}
