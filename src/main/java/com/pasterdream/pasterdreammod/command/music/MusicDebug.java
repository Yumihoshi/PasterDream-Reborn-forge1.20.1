package com.pasterdream.pasterdreammod.command.music;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.logging.LogUtils;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModSounds;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.slf4j.Logger;

import java.util.Optional;

public class MusicDebug {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceKey<Level> DYEDREAM_WORLD =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));

    public static int checkMusic(CommandContext<CommandSourceStack> context) {
        ServerPlayer sp = null;
        try { sp = context.getSource().getPlayerOrException(); }
        catch (Exception e) { context.getSource().sendFailure(Component.literal("Player-only command")); return 0; }

        Level level = sp.level();
        Holder<Biome> biomeHolder = level.getBiome(sp.blockPosition());
        Biome biome = biomeHolder.value();
        Optional<Music> bgMusic = biome.getBackgroundMusic();

        // 打印维度信息
        ResourceLocation dimId = level.dimension().location();
        boolean inDyedream = level.dimension().equals(DYEDREAM_WORLD);
        context.getSource().sendSuccess(() -> Component.literal(
                "=== Music Debug ==="), false);
        context.getSource().sendSuccess(() -> Component.literal(
                "Dimension: " + dimId + " (is dyedream: " + inDyedream + ")"), false);

        // 打印群系信息
        ResourceLocation biomeId = level.registryAccess().registryOrThrow(Registries.BIOME).getKey(biome);
        context.getSource().sendSuccess(() -> Component.literal(
                "Biome: " + (biomeId != null ? biomeId.toString() : "unknown")), false);

        // 打印背景音乐状态
        if (bgMusic.isPresent()) {
            Music m = bgMusic.get();
            ResourceLocation soundId = m.getEvent().unwrapKey()
                    .map(ResourceKey::location).orElse(ResourceLocation.parse("unknown"));
            context.getSource().sendSuccess(() -> Component.literal(
                    "Background music: " + soundId +
                    " minDelay=" + m.getMinDelay() +
                    " maxDelay=" + m.getMaxDelay() +
                    " replace=" + m.replaceCurrentMusic()), false);
        } else {
            context.getSource().sendSuccess(() -> Component.literal(
                    "Background music: EMPTY (will fall back to vanilla game music)"), false);
        }

        // 日志输出
        LOGGER.info("[MusicDebug] Dimension={} Biome={} BackgroundMusic={}",
                dimId, biomeId, bgMusic.map(m -> m.getEvent().unwrapKey().map(ResourceKey::location).toString()).orElse("empty"));

        // 在正确维度时尝试直接播放
        if (inDyedream) {
            boolean isSnowy = (biomeId != null && (biomeId.getPath().contains("snowy") || biomeId.getPath().contains("frozen")));
            if (isSnowy) {
                sp.level().playSound(null, sp.blockPosition(),
                        ModSounds.SNOWFALL_DREAM_MUSIC.get(),
                        SoundSource.MUSIC, 0.5f, 1.0f);
                context.getSource().sendSuccess(() -> Component.literal(
                        "Played: snowfall_dream_music (snowy biome)"), false);
            } else {
                sp.level().playSound(null, sp.blockPosition(),
                        ModSounds.SWEET_DREAM_MUSIC.get(),
                        SoundSource.MUSIC, 0.5f, 1.0f);
                context.getSource().sendSuccess(() -> Component.literal(
                        "Played: sweet_dream_music (warm biome)"), false);
            }
        } else {
            context.getSource().sendSuccess(() -> Component.literal(
                    "Not in dyedream_world - no music played. Go to dyedream dimension first."), false);
        }

        return 1;
    }
}
