package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.level.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PasterDreamMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PASTERDREAM_TAB = CREATIVE_MODE_TABS.register("pasterdream_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pasterdream.pasterdream_tab"))
                    .icon(() -> new ItemStack(ModBlocks.DYEDREAM_DIRT.get().asItem()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.DYEDREAM_DIRT.get().asItem());
                        output.accept(ModBlocks.DYEDREAM_GRASS_BLOCK.get().asItem());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        //注册物品
        //需要在模组主类的构造方法中调用
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
