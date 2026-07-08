package com.pasterdream.pasterdreammod.world.effect;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class CookBuffEffect extends MobEffect {
    private static final UUID SAN_MODIFIER_UUID = UUID.fromString("de214503-66e4-4d52-84c6-0a856b297f87");

    public CookBuffEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xEDF1C3);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && !player.level().isClientSide()) {
            AttributeInstance attr = player.getAttribute(ModAttributes.SAN_VARIABILITY.get());
            if (attr != null && attr.getModifier(SAN_MODIFIER_UUID) == null) {
                attr.addTransientModifier(new AttributeModifier(SAN_MODIFIER_UUID,
                        "Cook buff SAN", 1.2 * (amplifier + 1), AttributeModifier.Operation.ADDITION));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    /** 兜底清理：效果被移除（过期/牛奶/命令）时立即清除修饰符 */
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()
                && event.getEffectInstance() != null
                && event.getEffectInstance().getEffect() instanceof CookBuffEffect) {
            AttributeInstance attr = player.getAttribute(ModAttributes.SAN_VARIABILITY.get());
            if (attr != null) {
                attr.removeModifier(SAN_MODIFIER_UUID);
            }
        }
    }
}
