package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.effect.DyedreamArmorBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.DyedreamUpBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.EvasionBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.SculkArmorBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.BlinkCooldownEffect;
import com.pasterdream.pasterdreammod.world.effect.SnowVowBuffEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PasterDreamMod.MOD_ID);

    public static final RegistryObject<MobEffect> SCULK_ARMOR_BUFF =
            EFFECTS.register("sculk_armor_buff", SculkArmorBuffEffect::new);
    public static final RegistryObject<MobEffect> DYEDREAM_ARMOR_BUFF =
            EFFECTS.register("dyedream_armor_buff", DyedreamArmorBuffEffect::new);
    public static final RegistryObject<MobEffect> DYEDREAM_UP_BUFF =
            EFFECTS.register("dyedream_up_buff", DyedreamUpBuffEffect::new);
    public static final RegistryObject<MobEffect> EVASION_BUFF =
            EFFECTS.register("evasion_buff", EvasionBuffEffect::new);
    public static final RegistryObject<MobEffect> BLINK_COOLDOWN =
            EFFECTS.register("blink_cooldown", BlinkCooldownEffect::new);
    public static final RegistryObject<MobEffect> SNOW_VOW_BUFF =
            EFFECTS.register("snow_vow_buff", SnowVowBuffEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
