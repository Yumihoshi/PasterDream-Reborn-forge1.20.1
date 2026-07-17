package com.pasterdream.pasterdreammod.world.item.prophecycard;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 预言卡右键效果接口。
 * 每种卡牌的 NBT 类型可注册一个对应效果，右键时自动分发。
 *
 * <pre>{@code
 * ProphecyCardItem.registerCardEffect("frost", (level, player, hand, stack) -> {
 *     // 冰冻效果逻辑...
 *     return InteractionResultHolder.success(stack);
 * });
 * }</pre>
 */
@FunctionalInterface
public interface ProphecyCardEffect {

    /**
     * @param level  当前世界
     * @param player 使用卡牌的玩家
     * @param hand   使用的手
     * @param stack  当前物品堆（可修改后返回）
     * @return 返回处理后的 InteractionResultHolder
     */
    InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand, ItemStack stack);
}
