package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModParticleTypes;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class ShadowCandleBlock extends Block {

	private static final VoxelShape SHAPE = box(6, 0, 6, 10, 8, 10);

	public ShadowCandleBlock() {
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.COLOR_BLACK)
				.sound(SoundType.CANDLE)
				.strength(0.1f, 0f)
				.lightLevel(s -> 13)
				.noOcclusion()
				.hasPostProcess((bs, br, bp) -> true)
				.emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 20);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		world.addParticle(ModParticleTypes.SOUL_PARTICLE.get(),
				pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5,
				0, 0.1, 0);
		world.scheduleTick(pos, this, 20);
	}
}
