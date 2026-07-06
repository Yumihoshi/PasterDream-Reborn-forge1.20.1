package com.pasterdream.pasterdreammod.world.item.lootgenerator;

import com.pasterdream.pasterdreammod.world.block.ItemContainer.IItemContainerInventory;
import com.pasterdream.pasterdreammod.world.block.ItemContainer.ItemContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Random;

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

            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity == null)
            {
                if (!level.isClientSide)
                {
                    player.displayClientMessage(
                            Component.translatable("message.pasterdream.loot_generator.not_container"), true);
                }
                return InteractionResult.FAIL;
            }

            IItemHandler itemHandler = getItemHandler(blockEntity, context.getClickedFace());
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
                // 检查战利品表是否存在
                if (level.getServer().getLootData().getLootTable(lootTableRL) == LootTable.EMPTY)
                {
                    player.displayClientMessage(
                            Component.translatable("message.pasterdream.loot_generator.loot_table_not_found",
                                    lootTableRL.toString()), false);
                    return InteractionResult.FAIL;
                }

                // 清空容器 + 写入 LootTable NBT
                clearContainer(itemHandler);
                long randomSeed = new Random().nextLong();
                setContainerLootTable(blockEntity, lootTableRL, randomSeed);

                player.displayClientMessage(
                        Component.translatable("message.pasterdream.loot_generator.loot_table_set",
                                lootTableRL.toString()), true);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return super.useOn(context);
    }

    /**
     * 尝试从方块实体获取 IItemHandler
     */
    @Nullable
    private static IItemHandler getItemHandler(BlockEntity blockEntity, net.minecraft.core.Direction side)
    {
        if (blockEntity instanceof IItemContainerInventory containerInventory)
        {
            return containerInventory.getItemHandler();
        }
        return blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, side).orElse(null);
    }

    /**
     * 清空容器所有槽位的物品
     */
    private static void clearContainer(IItemHandler itemHandler)
    {
        for (int slot = 0; slot < itemHandler.getSlots(); slot++)
        {
            ItemStack stackInSlot = itemHandler.getStackInSlot(slot);
            if (!stackInSlot.isEmpty())
            {
                itemHandler.extractItem(slot, stackInSlot.getCount(), false);
            }
        }
    }
    /**
     * 为容器写入 LootTable NBT 标签（原版结构箱子风格），容器下次打开时自动生成战利品
     */
    private static void setContainerLootTable(BlockEntity blockEntity, ResourceLocation lootTable, long seed)
    {
        if (blockEntity instanceof ItemContainerBlockEntity modContainer)
        {
            modContainer.setLootTable(lootTable, seed);
        }
        else if (blockEntity instanceof RandomizableContainerBlockEntity vanillaContainer)
        {
            vanillaContainer.setLootTable(lootTable, seed);
        }
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
