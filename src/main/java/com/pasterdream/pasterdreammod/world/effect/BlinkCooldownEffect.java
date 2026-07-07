package com.pasterdream.pasterdreammod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * 瞬身术冷却标记，存在期间无法再次使用瞬身术。
 */
public class BlinkCooldownEffect extends MobEffect {

    public BlinkCooldownEffect() {
        super(MobEffectCategory.NEUTRAL, 0x808080);
    }
}
