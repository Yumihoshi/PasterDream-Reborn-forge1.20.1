package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulOreBlock extends Block {
    private static final VoxelShape SHAPE = box(0, 0, 0, 16, 14, 16);

    public SoulOreBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .sound(SoundType.SOUL_SOIL)
                .strength(2f, 1f)
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 15;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return Shapes.empty();
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean result = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    (SimpleParticleType) ModParticleTypes.SOUL_PARTICLE.get(),
                    pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5,
                    5, 0, 0.1, 0, 0.2);
        }
        return result;
    }
}
