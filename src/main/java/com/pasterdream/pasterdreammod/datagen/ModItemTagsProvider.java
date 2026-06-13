package com.pasterdream.pasterdreammod.datagen;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, PasterDreamMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // 自定义标签
        // tag(ModItemTags.EXAMPLE_TAG)
        //         .add(ModItems.EXAMPLE_ITEM.get());

        // 原版可锻造盔甲标签（支持纹饰）
        // tag(ItemTags.TRIMMABLE_ARMOR)
        //         .add(ModItems.EXAMPLE_HELMET.get())
        //         .add(ModItems.EXAMPLE_CHESTPLATE.get())
        //         .add(ModItems.EXAMPLE_LEGGINGS.get())
        //         .add(ModItems.EXAMPLE_BOOTS.get());
    }
}
