package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.init.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class DreamFertilizerItem extends Item {

    public DreamFertilizerItem() {
        super(new Item.Properties().stacksTo(64));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockState clickedState = level.getBlockState(pos);

        // Vanilla bone meal effect
        ItemStack fakeBoneMeal = new ItemStack(Items.BONE_MEAL);
        if (BoneMealItem.growCrop(fakeBoneMeal, level, pos) || BoneMealItem.growWaterPlant(fakeBoneMeal, level, pos, null)) {
            if (!level.isClientSide()) {
                level.levelEvent(2005, pos, 0);
            }
        }

        // Special conversions: stem_grass → tall_stem_grass
        if (clickedState.is(ModBlocks.STEM_GRASS.get())) {
            if (!level.isClientSide()) {
                level.removeBlock(pos, false);
                level.setBlock(pos, ModBlocks.TALL_STEM_GRASS.get().defaultBlockState(), 3);
                level.setBlock(pos.above(), ModBlocks.TALL_STEM_GRASS.get().defaultBlockState()
                        .setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
        }

        // Swing hand
        if (player != null) {
            player.swing(context.getHand());
        }

        // Shrink item
        if (!level.isClientSide() && (player == null || !player.isCreative())) {
            stack.shrink(1);
        }

        // Spawn particles
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ModParticleTypes.DREAMFERTILIZER_PARTICLE.get(),
                    pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5,
                    16, 0.6, 0.5, 0.6, 0.25);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
