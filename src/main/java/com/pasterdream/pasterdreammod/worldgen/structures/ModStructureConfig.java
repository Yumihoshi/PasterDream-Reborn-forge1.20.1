package com.pasterdream.pasterdreammod.worldgen.structures;

import com.pasterdream.pasterdreammod.helper.structuregenerate.StructureGenerationConfig;

import java.util.ArrayList;
import java.util.List;

public class ModStructureConfig
{
    public static List<StructureGenerationConfig> getStructureConfig()
    {
        List<StructureGenerationConfig> STRUCTURES = new ArrayList<>();

        STRUCTURES.add(new StructureGenerationConfig("pasterdream:oak_fisherman_hut", "pasterdream:can_fisherman_hut_spawn_biome", "surface_structures", "beard_thin", 0, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 15673946, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:spruce_fisherman_hut", "pasterdream:can_fisherman_hut_spawn_biome", "surface_structures", "beard_thin", 0, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 56179235, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:dyedream_crack", "minecraft:is_overworld", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 16, 4, 33554432, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:campsite_overworld", "pasterdream:can_campsite_spawn_biome", "surface_structures", "beard_thin", 0, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 72918463, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:the_lost_sword_tomb", "minecraft:is_jungle", "surface_structures", "beard_thin", 0, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 41827365, 1));
        //STRUCTURES.add(new StructureGenerationConfig("pasterdream:desert_fortress", "pasterdream:can_desert_fortress_spawn_biome", "surface_structures", "beard_thin", -31, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 83920174, 1));

        STRUCTURES.add(new StructureGenerationConfig("pasterdream:dyedream_church_0", "pasterdream:dyedream_world_biome", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 35795416, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:dyedream_church_2", "pasterdream:dyedream_world_biome", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 64595175, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:dyedream_church_4", "pasterdream:dyedream_world_biome", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 13271459, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:dyedream_church_6", "pasterdream:dyedream_world_biome", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 34549862, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:crystal_ball", "pasterdream:dyedream_world_biome", "surface_structures", "none", 64, "WORLD_SURFACE_WG", 1, 1, false, "rigid", "minecraft:empty", 1, 32, 8, 62594864, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:garden_decryption_misty_dreaming_lotus", "pasterdream:dyedream_world_biome", "surface_structures", "beard_thin", -4, "WORLD_SURFACE_WG", 64, 1, false, "rigid", "minecraft:empty", 1, 26, 13, 93746251, 1));
        STRUCTURES.add(new StructureGenerationConfig("pasterdream:garden_decryption_nippy_edelweiss", "pasterdream:can_garden_decryption_nippy_edelweiss_spawn_biome", "surface_structures", "beard_thin", -4, "WORLD_SURFACE_WG", 64, 1, false, "rigid", "minecraft:empty", 1, 18, 7, 1889395022, 1));

        return STRUCTURES;
    }
}
