package com.pasterdream.pasterdreammod.world.level.block;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.item.ModItems;
import com.pasterdream.pasterdreammod.world.level.block.custom.DyedreamGrassBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PasterDreamMod.MOD_ID);

    public static final RegistryObject<Block> DYEDREAM_DIRT = registerBlock("dyedream_dirt",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .sound(SoundType.GRAVEL)
                    .strength(0.75F, 0.5F)), true);
    public static final RegistryObject<Block> DYEDREAM_GRASS_BLOCK = registerBlock("dyedream_grass_block",
            DyedreamGrassBlock::new,  true);


    //<T extends Block>这个泛型是为了让之后的自定义的方块注册时，不需要再去重新写注册方法
    //Block的子类都能用这个方法
    //注册方块物品
    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new BlockItem.Properties()));
    }

    //注册方块
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, boolean shouldRegisterBlockItem){
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        //注册物品
        if(shouldRegisterBlockItem)registerBlockItems(name, blocks);
        return blocks;
    }

    //注册事件
    //需要在主类的构造方法中调用
    public static void register(IEventBus eventBus){
        //注册方块
        BLOCKS.register(eventBus);
    }


}
