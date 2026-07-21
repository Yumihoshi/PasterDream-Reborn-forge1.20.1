package com.pasterdream.pasterdreammod.mixin;

import net.minecraft.server.level.WorldGenRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

@Mixin(WorldGenRegion.class)
public class WorldGenRegionMixin {

    @Redirect(method = "destroyBlock",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/level/block/Block;popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"))
    private void suppressPopResource(Level level, BlockPos pos, ItemStack stack) {
        // suppress item entity creation during worldgen to avoid LegacyRandomSource threading crash
    }
}
