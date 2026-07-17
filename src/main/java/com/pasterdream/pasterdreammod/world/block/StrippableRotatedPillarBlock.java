package com.pasterdream.pasterdreammod.world.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StrippableRotatedPillarBlock extends RotatedPillarBlock {
    private final Supplier<Block> strippedBlock;

    public StrippableRotatedPillarBlock(Properties properties, Supplier<Block> strippedBlock) {
        super(properties);
        this.strippedBlock = strippedBlock;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return strippedBlock.get().withPropertiesOf(state);
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
