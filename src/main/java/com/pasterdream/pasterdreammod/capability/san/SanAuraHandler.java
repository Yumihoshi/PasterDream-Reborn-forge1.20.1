package com.pasterdream.pasterdreammod.capability.san;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.helper.sanbiomeratemanager.SanBiomeRateManager;
import com.pasterdream.pasterdreammod.init.ModAttributes;
import com.pasterdream.pasterdreammod.world.san.SanEnvironmentModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 理智光环处理器：每 tick 评估环境修正与属性叠加，驱动 San 值变化。
 * <p>
 * 数据流：装备/效果/环境 → SAN_VARIABILITY 属性 → 每 tick 转化率 → San Capability
 * <p>
 * TODO: 低 San 惩罚效果（debuff）将重新设计，暂不接入。
 */
@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class SanAuraHandler {

    /** 将"每分钟变化量"转为"每 tick 变化量"的除数 */
    private static final double TICKS_PER_MINUTE = 1200.0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof ServerPlayer player)) {
            return;
        }

        Level level = player.level();
        BlockPos pos = player.blockPosition();

        // 1. 环境修正：维度 → 写入 SAN_VARIABILITY 属性（临时修饰符）
        SanEnvironmentModifier.apply(player);

        // 2. 读取属性叠加后的总变化率（每分钟变化量）
        double sanVariability = player.getAttributeValue(ModAttributes.SAN_VARIABILITY.get());
        double attributeRate = sanVariability / TICKS_PER_MINUTE;

        // 3. 群系修正（来自 data/pasterdream/san_biome_rates/）
        ResourceLocation biomeId = level.getBiome(pos).unwrapKey()
                .map(ResourceKey::location).orElse(null);
        double biomeRate = biomeId != null ? SanBiomeRateManager.getRate(biomeId) : 0.0;

        // 4. 光照修正：亮度 < 7 时 San 下降，亮度 > 7 时 San 回升
        int light = level.getMaxLocalRawBrightness(pos);
        double lightRate = (light - 7) * 0.0001;

        double totalRate = attributeRate + biomeRate + lightRate;
        if (totalRate != 0) {
            SanHelper.addPlayerSanAndSync(player, totalRate);
        }

        // TODO: 低 San debuff 检查 → 未来重新设计后接入
    }
}
