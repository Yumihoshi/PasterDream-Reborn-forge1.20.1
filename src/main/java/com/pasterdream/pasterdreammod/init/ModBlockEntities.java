package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.world.block.claypan.ClaypanBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PasterDreamMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ClaypanBlockEntity>> CLAYPAN = BLOCK_ENTITIES.register("claypan", () -> BlockEntityType.Builder.of(ClaypanBlockEntity::new, ModBlocks.CLAYPAN.get()).build(null));

    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }

}
