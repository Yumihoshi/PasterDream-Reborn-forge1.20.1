package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.effect.CheerUpBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.InsandBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.LethargyBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.TranceBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.DyedreamArmorBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.DyedreamPerfumeBuffEffect;
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
    public static final RegistryObject<MobEffect> DYEDREAM_PERFUME_BUFF =
            EFFECTS.register("dyedream_perfume_buff", DyedreamPerfumeBuffEffect::new);
    public static final RegistryObject<MobEffect> EVASION_BUFF =
            EFFECTS.register("evasion_buff", EvasionBuffEffect::new);
    public static final RegistryObject<MobEffect> BLINK_COOLDOWN =
            EFFECTS.register("blink_cooldown", BlinkCooldownEffect::new);
    public static final RegistryObject<MobEffect> SNOW_VOW_BUFF =
            EFFECTS.register("snow_vow_buff", SnowVowBuffEffect::new);
    public static final RegistryObject<MobEffect> CHEER_UP_BUFF =
            EFFECTS.register("cheer_up_buff", CheerUpBuffEffect::new);
    public static final RegistryObject<MobEffect> LETHARGY_BUFF =
            EFFECTS.register("lethargy_buff", LethargyBuffEffect::new);
    public static final RegistryObject<MobEffect> TRANCE_BUFF =
            EFFECTS.register("trance_buff", TranceBuffEffect::new);
    public static final RegistryObject<MobEffect> INSAND_BUFF =
            EFFECTS.register("insand_buff", InsandBuffEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
