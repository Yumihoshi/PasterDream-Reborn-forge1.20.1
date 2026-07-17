package com.pasterdream.pasterdreammod.world.block;

import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class KeyShadowBookshelfBlock extends ShadowBookshelfBlock {
    public KeyShadowBookshelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(ModItems.SHADOW_DUNGEON_KEY.get()));
    }
}
