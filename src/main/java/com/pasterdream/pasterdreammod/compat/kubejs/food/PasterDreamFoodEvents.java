package com.pasterdream.pasterdreammod.compat.kubejs.food;

import com.pasterdream.pasterdreammod.capability.meltdreamenergy.MeltDreamEnergyHelper;
import com.pasterdream.pasterdreammod.capability.san.SanHelper;
import com.pasterdream.pasterdreammod.helper.drinkandfoodproperties.FoodValueTooltips;
import com.pasterdream.pasterdreammod.world.item.drinkandfooditem.PasterDreamDrinkItem;
import com.pasterdream.pasterdreammod.world.item.drinkandfooditem.PasterDreamFoodItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * KubeJS 外部食物属性的生效钩子：
 * <ul>
 *     <li>进食结束（服务端）：对注册过的外部食物应用 SAN / 融梦能量加成；</li>
 *     <li>ToolTip（客户端）：为注册过的外部食物追加加成行。</li>
 * </ul>
 *
 * <p>本模组自带的 {@link PasterDreamFoodItem} / {@link PasterDreamDrinkItem} 已自行处理
 * SAN / 融梦能量与 ToolTip，此处跳过，避免重复加成与重复行。
 */
public class PasterDreamFoodEvents
{
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event)
    {
        if (event.getEntity().level().isClientSide())
        {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player))
        {
            return;
        }
        ItemStack stack = event.getItem();
        if (!stack.isEdible())
        {
            return;
        }
        Item item = stack.getItem();
        if (item instanceof PasterDreamFoodItem || item instanceof PasterDreamDrinkItem)
        {
            return;
        }
        PasterDreamFoodRegistry.FoodEffects effects = PasterDreamFoodRegistry.get(stack);
        if (effects == null)
        {
            return;
        }
        if (effects.getSanAdd() != 0)
        {
            SanHelper.addPlayerSanAndSync(player, effects.getSanAdd());
        }
        if (effects.getMeltDreamEnergyAdd() != 0)
        {
            MeltDreamEnergyHelper.addPlayerMeltDreamEnergyAndSync(player, effects.getMeltDreamEnergyAdd());
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof PasterDreamFoodItem || item instanceof PasterDreamDrinkItem)
        {
            return;
        }
        PasterDreamFoodRegistry.FoodEffects effects = PasterDreamFoodRegistry.get(stack);
        if (effects == null)
        {
            return;
        }
        FoodValueTooltips.appendSanTooltip(event.getToolTip(), effects.getSanAdd());
        FoodValueTooltips.appendMeltDreamEnergyTooltip(event.getToolTip(), effects.getMeltDreamEnergyAdd());
    }
}