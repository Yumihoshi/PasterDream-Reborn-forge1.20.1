package com.pasterdream.pasterdreammod.helper.drinkandfoodproperties;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * 食物/饮品的 SAN 值与融梦能量 ToolTip 共享格式化工具，
 * 供本模组食物物品与 KubeJS 外部食物属性共用，保证显示格式一致。
 */
public final class FoodValueTooltips
{
    private FoodValueTooltips()
    {
    }

    /**
     * 追加 SAN 值 ToolTip 行（0 时不追加）。
     */
    public static void appendSanTooltip(List<Component> tooltip, double sanAdd)
    {
        if (sanAdd == 0)
        {
            return;
        }
        tooltip.add(Component.translatable("tooltip.pasterdreammod.san_value").withStyle(ChatFormatting.BLUE)
                .append(Component.literal(formatValue(sanAdd)).withStyle(sanAdd < 0 ? ChatFormatting.RED : ChatFormatting.BLUE)));
    }

    /**
     * 追加融梦能量 ToolTip 行（0 时不追加）。
     */
    public static void appendMeltDreamEnergyTooltip(List<Component> tooltip, double meltDreamEnergyAdd)
    {
        if (meltDreamEnergyAdd == 0)
        {
            return;
        }
        tooltip.add(Component.translatable("tooltip.pasterdreammod.melt_dream_energy").withStyle(ChatFormatting.BLUE)
                .append(Component.literal(formatValue(meltDreamEnergyAdd)).withStyle(ChatFormatting.BLUE)));
    }

    public static String formatValue(double value)
    {
        if (value == (long) value)
        {
            return String.format("%+d", (long) value);
        }
        return String.format("%+.1f", value);
    }
}