package com.pasterdream.pasterdreammod.world.dimension;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 染梦维度事件处理器。
 * 群系背景音乐由 {@code ModBiomes} 中 {@code backgroundMusic()} 配合
 * {@code SituationalMusicMixin} 和 {@code MusicManagerMixin} 统一管理，
 * 此处不再额外播放音效，避免与 MusicManager 叠加。
 */
@Mod.EventBusSubscriber
public class DyedreamDimensionMusic {

    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        // 群系音乐由 ModBiomes.backgroundMusic() + SituationalMusicMixin 处理
    }
}
