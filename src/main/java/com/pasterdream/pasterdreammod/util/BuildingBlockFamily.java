package com.pasterdream.pasterdreammod.util;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public record BuildingBlockFamily(
        RegistryObject<Block> base,
        RegistryObject<Block> stairs,
        RegistryObject<Block> slab,
        RegistryObject<Block> wall
) {}
