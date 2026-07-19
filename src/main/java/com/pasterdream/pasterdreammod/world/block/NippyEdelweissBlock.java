package com.pasterdream.pasterdreammod.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NippyEdelweissBlock extends FlowerBlock implements EntityBlock {

    public NippyEdelweissBlock(Properties properties) {
        super(() -> net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 100, properties);
    }

    @Override
    public int getEffectDuration() {
        return 100;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("§f▪ §9冰冻周围的水源 并在地面凝结成雪"));
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        world.sendParticles(ParticleTypes.SNOWFLAKE, x + 0.5, y + 0.5, z + 0.5, 16, 0.5, 0.5, 0.5, 0.05);
        for (int i = 0; i < 8; i++) {
            freezeNearby(world, pos);
        }
    }

    private static void freezeNearby(Level world, BlockPos pos) {
        if (world.isClientSide()) return;

        BlockEntity be = world.getBlockEntity(pos);
        if (be == null) return;

        int rx = Mth.nextInt(world.getRandom(), -3, 3);
        int ry = Mth.nextInt(world.getRandom(), -1, 0);
        int rz = Mth.nextInt(world.getRandom(), -3, 3);

        be.getPersistentData().putDouble("random_x", rx);
        be.getPersistentData().putDouble("random_y", ry);
        be.getPersistentData().putDouble("random_z", rz);

        BlockPos target = pos.offset(rx, ry, rz);
        BlockState targetState = world.getBlockState(target);
        if (targetState.is(Blocks.AIR)) {
            world.setBlock(target, Blocks.SNOW.defaultBlockState(), 3);
        } else if (targetState.is(Blocks.WATER)) {
            world.setBlock(target, Blocks.ICE.defaultBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NippyEdelweissBlockEntity(pos, state);
    }
}
