package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModCriteriaTriggers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 沙漠堡垒结构检测 —— 玩家进入沙漠堡垒时触发进度。
 */
@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class DesertFortressStructureHandler {

    private static final ResourceKey<Structure> DESERT_FORTRESS =
            ResourceKey.create(Registries.STRUCTURE,
                    ResourceLocation.fromNamespaceAndPath("pasterdream", "desert_fortress"));

    private static final String COOLDOWN_TAG = "pasterdream_desert_fortress_check";
    private static final int CHECK_INTERVAL = 20; // 每秒检测一次

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer sp)) return;
        if (!(sp.level() instanceof ServerLevel serverLevel)) return;

        // 冷却控制，避免每tick都查询结构
        int serverTick = serverLevel.getServer().getTickCount();
        int lastCheck = sp.getPersistentData().getInt(COOLDOWN_TAG);
        if (serverTick - lastCheck < CHECK_INTERVAL) return;
        sp.getPersistentData().putInt(COOLDOWN_TAG, serverTick);

        // 获取沙漠堡垒结构定义
        Structure structure = serverLevel.registryAccess()
                .registryOrThrow(Registries.STRUCTURE)
                .get(DESERT_FORTRESS);
        if (structure == null) return;

        // 检测玩家是否处于该结构的边界内
        StructureStart start = serverLevel.structureManager()
                .getStructureWithPieceAt(sp.blockPosition(), structure);
        if (start == null || !start.isValid()) return;

        // 授予进度：沙漠遗迹
        ModCriteriaTriggers.FOUND_DESERT_FORTRESS.trigger(sp);
    }
}
