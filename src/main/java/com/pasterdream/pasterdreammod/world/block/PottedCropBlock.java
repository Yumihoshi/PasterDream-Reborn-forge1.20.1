package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public class PottedCropBlock extends FlowerPotBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    private final Supplier<? extends Item> harvestItem;
    private final int harvestCount;
    private final Supplier<? extends Item> seedlingItem;
    private final Supplier<? extends Item> matureItem;

    public PottedCropBlock(Block content, Supplier<? extends Item> harvestItem, int harvestCount,
                           Supplier<? extends Item> seedlingItem, Supplier<? extends Item> matureItem,
                           Properties properties) {
        super(content, properties.randomTicks());
        this.harvestItem = harvestItem;
        this.harvestCount = harvestCount;
        this.seedlingItem = seedlingItem;
        this.matureItem = matureItem;
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) == 0 && level.getRawBrightness(pos, 0) >= 9) {
            level.setBlock(pos, state.setValue(AGE, 1), 3);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                  InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);

        // Empty hand → remove plant into inventory, restore empty pot
        if (held.isEmpty()) {
            if (!level.isClientSide()) {
                ItemStack drop = state.getValue(AGE) == 1
                        ? new ItemStack(matureItem.get())
                        : new ItemStack(seedlingItem.get());
                if (!player.addItem(drop)) {
                    popResource(level, pos, drop);
                }
                level.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 3);
            }
            return InteractionResult.SUCCESS;
        }

        // Shears/pliers on mature → harvest products, revert to seedling
        if (state.getValue(AGE) == 1 && (held.is(Tags.Items.SHEARS) || held.is(ModItems.PLIERS.get()))) {
            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(AGE, 0), 3);
                popResource(level, pos, new ItemStack(harvestItem.get(), harvestCount));
                held.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
