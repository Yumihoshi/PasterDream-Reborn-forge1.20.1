package com.pasterdream.pasterdreammod.world.item.lootgenerator;


import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
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
