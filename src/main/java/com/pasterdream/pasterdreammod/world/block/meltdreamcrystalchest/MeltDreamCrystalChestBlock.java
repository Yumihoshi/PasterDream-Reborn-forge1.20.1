package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import com.pasterdream.pasterdreammod.init.ModBlockEntities;
import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MeltDreamCrystalChestBlock extends BaseEntityBlock
{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public MeltDreamCrystalChestBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPosition, BlockState blockState)
    {
        return new MeltDreamCrystalChestBlockEntity(blockPosition, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return box(0, 0, 0, 16, 15, 16);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (world.getBlockEntity(pos) instanceof MeltDreamCrystalChestBlockEntity chest && chest.getAnimationState() != 0) {
            return;
        }
        for (int l = 0; l < 2; ++l) {
            double x0 = pos.getX() + 0.35 + random.nextDouble() * 0.3;
            double y0 = pos.getY() + 0.25 + random.nextDouble() * 0.25;
            double z0 = pos.getZ() + 0.35 + random.nextDouble() * 0.3;
            double dx = (random.nextFloat() - 0.5) * 0.3;
            double dy = random.nextFloat() * 0.3;
            double dz = (random.nextFloat() - 0.5) * 0.3;
            world.addParticle((SimpleParticleType) ModParticleTypes.MELTDREAM_CRYSTAL_PARTICLE.get(), x0, y0, z0, dx, dy, dz);
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPosition, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer && level.getBlockEntity(blockPosition) instanceof MeltDreamCrystalChestBlockEntity meltDreamCrystalChest)
        {
            meltDreamCrystalChest.openChest(serverPlayer);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        if (itemStack.getTag() != null && itemStack.getTag().contains("LootTables", Tag.TAG_LIST) && level.getBlockEntity(pos) instanceof MeltDreamCrystalChestBlockEntity meltDreamCrystalChest)
        {
            meltDreamCrystalChest.setLootTablesNbt(itemStack.getTag().getList("LootTables", Tag.TAG_COMPOUND));
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type)
    {
        return type == ModBlockEntities.MELT_DREAM_CRYSTAL_CHEST.get() ? (blockLevel, pos, state, blockEntity) -> MeltDreamCrystalChestBlockEntity.tick(blockLevel, pos, state, (MeltDreamCrystalChestBlockEntity) blockEntity) : null;
    }
}
