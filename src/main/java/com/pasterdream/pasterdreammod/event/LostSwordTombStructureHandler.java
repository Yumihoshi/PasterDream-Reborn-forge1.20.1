package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class LostSwordTombStructureHandler {

    private static final ResourceKey<Structure> LOST_SWORD_TOMB =
            ResourceKey.create(Registries.STRUCTURE,
                    ResourceLocation.fromNamespaceAndPath("pasterdream", "the_lost_sword_tomb"));

    private static final String COOLDOWN_TAG = "pasterdream_lost_sword_tomb_check";
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

        // 获取失落剑冢结构定义
        Structure tombStructure = serverLevel.registryAccess()
                .registryOrThrow(Registries.STRUCTURE)
                .get(LOST_SWORD_TOMB);
        if (tombStructure == null) return;

        // 检测玩家是否处于该结构的边界内
        StructureStart start = serverLevel.structureManager()
                .getStructureWithPieceAt(sp.blockPosition(), tombStructure);
        if (start == null || !start.isValid()) return;

        // 授予进度：被遗忘的剑冢
        sp.getAdvancements().award(
                sp.getServer().getAdvancements().getAdvancement(
                        ResourceLocation.fromNamespaceAndPath("pasterdream", "adventure/forgotten_sword_tomb")),
                "found_tomb");
    }
}
