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
import com.pasterdream.pasterdreammod.world.effect.GoldenrodTeaBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.SculkArmorBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.BlinkCooldownEffect;
import com.pasterdream.pasterdreammod.world.effect.CounterAttackBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.CookBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.DreamWishBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.SnowVowBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.CeciliaBlessingBuff;
import com.pasterdream.pasterdreammod.world.effect.DreamHarpOfWandererBuffEffect;
import com.pasterdream.pasterdreammod.world.effect.RestBuffEffect;
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
    public static final RegistryObject<MobEffect> GOLDENROD_TEA_BUFF =
            EFFECTS.register("goldenrod_tea_buff", GoldenrodTeaBuffEffect::new);
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
    public static final RegistryObject<MobEffect> COOK_BUFF =
            EFFECTS.register("cook_buff", CookBuffEffect::new);
    public static final RegistryObject<MobEffect> DREAM_WISH_BUFF =
            EFFECTS.register("dream_wish_buff", DreamWishBuffEffect::new);
    public static final RegistryObject<MobEffect> CECILIA_BLESSING_BUFF =
            EFFECTS.register("cecilia_blessing_buff", CeciliaBlessingBuff::new);
    public static final RegistryObject<MobEffect> REST_BUFF =
            EFFECTS.register("rest_buff", RestBuffEffect::new);
    public static final RegistryObject<MobEffect> DREAM_HARP_OF_WANDERER_BUFF =
            EFFECTS.register("dream_harp_of_wanderer_buff", DreamHarpOfWandererBuffEffect::new);
    public static final RegistryObject<MobEffect> COUNTER_ATTACK_BUFF =
            EFFECTS.register("counter_attack_buff", CounterAttackBuffEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
