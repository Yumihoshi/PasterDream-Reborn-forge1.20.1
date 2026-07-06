package com.pasterdream.pasterdreammod.world.item.lootgenerator;

import com.pasterdream.pasterdreammod.world.block.ItemContainer.IItemContainerInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LootGeneratorItem extends Item
{

    public static final String TAG_LOOT_TABLE = "LootTable";

    public LootGeneratorItem()
    {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }


    public static void setLootTable(ItemStack stack, ResourceLocation lootTable)
    {
        if (stack.getItem() instanceof LootGeneratorItem)
        {
            stack.getOrCreateTag().putString(TAG_LOOT_TABLE, lootTable.toString());
        }
    }

    @Nullable
    public static ResourceLocation getLootTable(ItemStack stack)
    {
        if (stack.hasTag() && stack.getTag().contains(TAG_LOOT_TABLE))
        {
            return ResourceLocation.tryParse(stack.getTag().getString(TAG_LOOT_TABLE));
        }
        return null;
    }

    public static void clearLootTable(ItemStack stack)
    {
        if (stack.hasTag())
        {
            stack.getTag().remove(TAG_LOOT_TABLE);
            if (stack.getTag().isEmpty())
            {
                stack.setTag(null);
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();

        if (player != null && player.isShiftKeyDown())
        {
            player.swing(context.getHand(), true);

            ResourceLocation lootTableRL = getLootTable(stack);
            if (lootTableRL == null)
            {
                if (!level.isClientSide)
                {
                    player.displayClientMessage(
                            Component.translatable("message.pasterdream.loot_generator.no_loot_table"), true);
                }
                return InteractionResult.FAIL;
            }

            IItemHandler itemHandler = getItemHandler(level, pos, context.getClickedFace());
            if (itemHandler == null)
            {
                if (!level.isClientSide)
                {
                    player.displayClientMessage(
                            Component.translatable("message.pasterdream.loot_generator.not_container"), true);
                }
                return InteractionResult.FAIL;
            }

            if (!level.isClientSide)
            {
                LootTable loottable = level.getServer().getLootData().getLootTable(lootTableRL);
                if (loottable == LootTable.EMPTY)
                {
                    player.displayClientMessage(
                            Component.translatable("message.pasterdream.loot_generator.loot_table_not_found",
                                    lootTableRL.toString()), false);
                    return InteractionResult.FAIL;
                }

                LootParams.Builder paramsBuilder = new LootParams.Builder((ServerLevel) level)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos));
                if (player != null)
                {
                    paramsBuilder.withLuck(player.getLuck())
                            .withParameter(LootContextParams.THIS_ENTITY, player);
                }
                LootParams params = paramsBuilder.create(LootContextParamSets.CHEST);
                List<ItemStack> items = loottable.getRandomItems(params);

                for (ItemStack lootStack : items)
                {
                    if (lootStack.isEmpty()) continue;
                    for (int slot = 0; slot < itemHandler.getSlots(); slot++)
                    {
                        lootStack = itemHandler.insertItem(slot, lootStack, false);
                        if (lootStack.isEmpty()) break;
                    }
                }

                player.displayClientMessage(
                        Component.translatable("message.pasterdream.loot_generator.loot_generated"), true);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(context);
    }

    /**
     * 尝试从目标方块获取 IItemHandler，优先使用模组自己的容器接口，其次使用 Forge 能力系统
     */
    @Nullable
    private static IItemHandler getItemHandler(Level level, BlockPos pos, net.minecraft.core.Direction side)
    {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity == null) return null;

        if (blockEntity instanceof IItemContainerInventory containerInventory)
        {
            return containerInventory.getItemHandler();
        }

        return blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, side).orElse(null);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.pasterdream.loot_generator.usage.shift"));

        ResourceLocation lootTable = getLootTable(stack);
        if (lootTable != null)
        {
            tooltip.add(Component.translatable("tooltip.pasterdream.当前设置战利品表：")
                    .append(Component.literal(lootTable.toString()).withStyle(style ->
                            style.withColor(0x55FFFF).withItalic(true))));
        }
        else
        {
            tooltip.add(Component.translatable("tooltip.pasterdream.当前设置战利品表：")
                    .append(Component.translatable("tooltip.pasterdream.loot_table.not_set")));
        }
    }

}
