package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(EatBlockGoal.class)
public abstract class EatBlockGoalMixin {

    @Redirect(method = "canUse",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;test(Ljava/lang/Object;)Z"))
    private boolean redirectPredicateCanUse(Predicate<BlockState> predicate, Object state) {
        return predicate.test((BlockState) state) || isStemGrass((BlockState) state);
    }

    @Redirect(method = "tick",
            at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;test(Ljava/lang/Object;)Z"))
    private boolean redirectPredicateTick(Predicate<BlockState> predicate, Object state) {
        return predicate.test((BlockState) state) || isStemGrass((BlockState) state);
    }

    @Redirect(method = "canUse",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectIsGrassBlockCanUse(BlockState state, Block block) {
        return state.is(block) || (block == Blocks.GRASS_BLOCK && state.is(ModBlocks.DYEDREAM_GRASS_BLOCK.get()));
    }

    @Redirect(method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectIsGrassBlockTick(BlockState state, Block block) {
        return state.is(block) || (block == Blocks.GRASS_BLOCK && state.is(ModBlocks.DYEDREAM_GRASS_BLOCK.get()));
    }

    @Redirect(method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean redirectSetBlock(Level level, BlockPos pos, BlockState state, int flags) {
        if (level.getBlockState(pos).is(ModBlocks.DYEDREAM_GRASS_BLOCK.get())) {
            return level.setBlock(pos, ModBlocks.DYEDREAM_DIRT.get().defaultBlockState(), flags);
        }
        return level.setBlock(pos, state, flags);
    }

    private static boolean isStemGrass(BlockState state) {
        return state.is(ModBlocks.STEM_GRASS.get()) || state.is(ModBlocks.TALL_STEM_GRASS.get());
    }
}
