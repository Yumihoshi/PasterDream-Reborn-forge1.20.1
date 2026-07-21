package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.world.block.PottedCropBlock;
import com.pasterdream.pasterdreammod.world.block.cropblock.MatureCropItem;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerPotBlock.class)
public class FlowerPotBlockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, Level level, BlockPos pos, Player player,
                        InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        if (!state.is(Blocks.FLOWER_POT)) return;

        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.isEmpty()) return;
        if (!(itemstack.getItem() instanceof BlockItem blockitem)) return;

        PottedCropBlock pottedCrop = ModBlocks.CROP_TO_POTTED.get(blockitem.getBlock());
        if (pottedCrop == null) return;

        int age = blockitem instanceof MatureCropItem ? 1 : 0;
        BlockState pottedState = pottedCrop.defaultBlockState().setValue(PottedCropBlock.AGE, age);

        player.awardStat(Stats.POT_FLOWER);
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        level.setBlock(pos, pottedState, 3);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
    }
}
