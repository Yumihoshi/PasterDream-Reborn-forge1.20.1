package com.pasterdream.pasterdreammod.world.dimension;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

/**
 * 染梦维度引用。只在此处集中定义常量，Mixin 中通过方法调用懒加载，
 * 以避免 Minecraft 类初始化阶段触发 Registries，进而与 Patchouli 等模组的
 * 早期类加载产生冲突。
 */
public final class DyedreamDimension {
    private DyedreamDimension() {}

    public static final ResourceKey<Level> DYEDREAM_WORLD = ResourceKey.create(
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "dimension")),
            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "dyedream_world"));
}
