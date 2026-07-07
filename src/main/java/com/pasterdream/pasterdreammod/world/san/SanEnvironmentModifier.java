package com.pasterdream.pasterdreammod.world.san;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModAttributes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.Level;

import java.util.UUID;

/**
 * 维度对 SAN_VARIABILITY 属性的修正。
 */
public class SanEnvironmentModifier {

    private static final UUID ENV_MODIFIER_UUID = UUID.fromString("1217d41a-1591-455a-8821-3356aad56a00");

    /** 每 tick 评估维度，更新 SAN_VARIABILITY 上的临时修正值。 */
    public static void apply(ServerPlayer player) {
        AttributeInstance attr = player.getAttribute(ModAttributes.SAN_VARIABILITY.get());
        if (attr == null) return;

        attr.removeModifier(ENV_MODIFIER_UUID);

        double mod = 0;
        ResourceKey<Level> dim = player.level().dimension();

        if (dim == DYEDREAM_WORLD) {
            mod += 4.8;
        }
        // TODO: 待对应维度搬运后启用:
        // lamp_shadow_world   → -2.4
        // aaroncos_arena_world → -9.6
        // wind_journey_world   → +1.2

        if (mod != 0) {
            attr.addTransientModifier(new AttributeModifier(ENV_MODIFIER_UUID,
                    "pasterdream.environment.san_variability", mod,
                    AttributeModifier.Operation.ADDITION));
        }
    }

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(net.minecraft.core.registries.Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

}
