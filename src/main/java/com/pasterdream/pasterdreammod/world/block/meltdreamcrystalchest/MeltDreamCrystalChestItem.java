package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class MeltDreamCrystalChestItem extends BlockItem implements GeoItem
{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MeltDreamCrystalChestItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(itemStack, level, tooltip, flag);
        CompoundTag tag = itemStack.getTag();
        if(tag != null && tag.contains("LootTables"))
        {
            ListTag listTag = tag.getList("LootTables", Tag.TAG_COMPOUND);
            CompoundTag normalLootTable = !listTag.isEmpty() ? listTag.getCompound(0) : null;
            CompoundTag rareLootTable = listTag.size() > 1 ? listTag.getCompound(1) : null;
            CompoundTag legendLootTable = listTag.size() > 2 ? listTag.getCompound(2) : null;

            if(normalLootTable != null)
            {
                tooltip.add(Component.translatable("tooltip.pasterdream.normal_loot_table", normalLootTable.getString("LootTable"), normalLootTable.getInt("weight"), normalLootTable.getDouble("luck_multiplier_increase")));
                if(rareLootTable != null)
                {
                    tooltip.add(Component.translatable("tooltip.pasterdream.rare_loot_table", rareLootTable.getString("LootTable"), rareLootTable.getInt("weight"), rareLootTable.getDouble("luck_multiplier_increase")));
                    if(legendLootTable != null)
                    {
                        tooltip.add(Component.translatable("tooltip.pasterdream.legend_loot_table", legendLootTable.getString("LootTable"), legendLootTable.getInt("weight"), legendLootTable.getDouble("luck_multiplier_increase")));
                    }
                }
            }
        }
            else
            {
                tooltip.add(Component.translatable("tooltip.pasterdream.not_have_loot_table"));
            }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<GeoAnimatable>(this, "state", 0, state -> PlayState.STOP));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(new IClientItemExtensions()
        {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                if (renderer == null)
                {
                    renderer = new MeltDreamCrystalChestItemRenderer();
                }
                return renderer;
            }
        });
    }
}
