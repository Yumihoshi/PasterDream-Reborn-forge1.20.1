package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.init.ModItems;
import com.pasterdream.pasterdreammod.init.ModSounds;
import com.pasterdream.pasterdreammod.world.item.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class FadedBlessingOfCeciliaItem extends Item {
    public FadedBlessingOfCeciliaItem() {
        super(new Item.Properties().stacksTo(1).rarity(ModRarities.COMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            var pos = ((BlockHitResult) hitResult).getBlockPos();
            if (level.getBlockState(pos).is(ModBlocks.MELTDREAM_LIQUID.get())) {
                if (!level.isClientSide()) {
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.BLESSING_OF_CECILIA.get()));
                    level.playSound(null, pos, ModSounds.DREAM0.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);
                }
                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(ModRarities.qualityTooltip(ModRarities.COMMON));
        list.add(Component.translatable("tooltip.pasterdream.faded_blessing_of_cecilia.hint"));
    }
}
