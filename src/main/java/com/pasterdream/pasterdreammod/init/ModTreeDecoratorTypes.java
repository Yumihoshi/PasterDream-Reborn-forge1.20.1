package com.pasterdream.pasterdreammod.init;

import com.mojang.serialization.Codec;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.worldgen.feature.PinkShroomlightTreeDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTreeDecoratorTypes {

    private static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, PasterDreamMod.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<PinkShroomlightTreeDecorator>> PINK_SHROOMLIGHT =
            TREE_DECORATOR_TYPES.register("pink_shroomlight",
                    () -> new TreeDecoratorType<>(Codec.unit(PinkShroomlightTreeDecorator.INSTANCE)));

    public static void register(IEventBus eventBus) {
        TREE_DECORATOR_TYPES.register(eventBus);
    }
}
