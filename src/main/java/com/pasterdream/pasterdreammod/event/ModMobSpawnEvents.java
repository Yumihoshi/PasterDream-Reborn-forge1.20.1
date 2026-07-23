package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.worldgen.biome.ModBiomes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public class ModMobSpawnEvents {

    private ModMobSpawnEvents() {}

    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (event.loadedFromDisk()) return;
        if (!(event.getEntity() instanceof Sheep sheep)) return;

        var biomeKey = event.getLevel().getBiome(sheep.blockPosition()).unwrapKey();
        if (biomeKey.isPresent() && biomeKey.get().equals(ModBiomes.DYEDREAM_PLAINS)) {
            sheep.setColor(DyeColor.PINK);
        }
    }
}
