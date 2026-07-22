package com.pasterdream.pasterdreammod.event;

import com.pasterdream.pasterdreammod.world.dimension.DyedreamDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModWorldGenEvents {

    private static final int WORLDTREE_X = 2002;
    private static final int WORLDTREE_Z = 1128;
    private static final int HEIGHT_OFFSET = -25;
    private static final ResourceLocation WORLDTREE_STRUCTURE =
            ResourceLocation.fromNamespaceAndPath("pasterdream", "dyedream_worldtree");

    private static volatile boolean worldtreeNeedsPlacement = false;

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;
        if (!serverLevel.dimension().equals(DyedreamDimension.DYEDREAM_WORLD)) return;

        WorldtreePlacedData data = WorldtreePlacedData.get(serverLevel);
        if (!data.isPlaced()) {
            worldtreeNeedsPlacement = true;
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!worldtreeNeedsPlacement) return;

        ServerLevel dyedream = event.getServer().getLevel(DyedreamDimension.DYEDREAM_WORLD);
        if (dyedream == null) return;

        WorldtreePlacedData data = WorldtreePlacedData.get(dyedream);
        if (data.isPlaced()) {
            worldtreeNeedsPlacement = false;
            return;
        }

        worldtreeNeedsPlacement = false;
        placeWorldtree(dyedream);
        data.setPlaced();
    }

    private static void placeWorldtree(ServerLevel serverLevel) {
        StructureTemplate template = serverLevel.getStructureManager()
                .get(WORLDTREE_STRUCTURE).orElse(null);
        if (template == null) return;

        int chunkX = WORLDTREE_X >> 4;
        int chunkZ = WORLDTREE_Z >> 4;
        serverLevel.getChunkSource().getChunk(chunkX, chunkZ, true);

        StructurePlaceSettings settings = new StructurePlaceSettings();
        int surfaceY = serverLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, WORLDTREE_X, WORLDTREE_Z);
        BlockPos placementPos = new BlockPos(WORLDTREE_X, surfaceY + HEIGHT_OFFSET, WORLDTREE_Z);
        template.placeInWorld(serverLevel, placementPos, placementPos, settings,
                serverLevel.getRandom(), 2);
    }

    public static class WorldtreePlacedData extends SavedData {
        private static final String DATA_NAME = "pasterdream_worldtree_placed";
        private boolean placed = false;

        public WorldtreePlacedData() {}

        public static WorldtreePlacedData load(CompoundTag tag) {
            WorldtreePlacedData data = new WorldtreePlacedData();
            data.placed = tag.getBoolean("placed");
            return data;
        }

        @Override
        public CompoundTag save(CompoundTag tag) {
            tag.putBoolean("placed", this.placed);
            return tag;
        }

        public static WorldtreePlacedData get(ServerLevel level) {
            return level.getDataStorage().computeIfAbsent(
                    WorldtreePlacedData::load, WorldtreePlacedData::new, DATA_NAME);
        }

        public boolean isPlaced() {
            return this.placed;
        }

        public void setPlaced() {
            this.placed = true;
            setDirty();
        }
    }
}
