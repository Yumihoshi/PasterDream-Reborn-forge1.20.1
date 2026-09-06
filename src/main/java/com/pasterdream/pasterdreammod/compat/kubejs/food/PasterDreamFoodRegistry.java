package com.pasterdream.pasterdreammod.compat.kubejs.food;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * KubeJS 外部食物属性注册表。
 *
 * <p>通过 KJS 启动脚本（startup_scripts）中的 {@code PasterDreamFoods} 绑定注册任意
 * 物品（含原版与其他模组的食物）的 SAN 值与融梦能量加成，注册后自动生效：
 * <ul>
 *     <li>服务端进食时自动增减 SAN / 融梦能量（带网络同步）；</li>
 *     <li>客户端 ToolTip 自动追加对应加成行（格式与本模组食物一致）。</li>
 * </ul>
 *
 * <p>注册示例（startup_scripts）：
 * <pre>{@code
 * // 普通参数形式：sanAdd +1，meltDreamEnergyAdd +0.5
 * PasterDreamFoods.register('minecraft:apple', 1, 0.5);
 * // 对象参数形式
 * PasterDreamFoods.register('minecraft:golden_apple', { san: 2, meltDreamEnergy: 1.5 });
 * // 负值表示扣减
 * PasterDreamFoods.register('minecraft:poisonous_potato', -0.5, -0.2);
 * // 查询 / 移除 / 清空
 * PasterDreamFoods.get('minecraft:apple');
 * PasterDreamFoods.unregister('minecraft:apple');
 * PasterDreamFoods.clear();
 * }</pre>
 *
 * <p>注意：本模组自带的 {@code PasterDreamFoodItem} / {@code PasterDreamDrinkItem} 食物
 * 已自行处理 SAN / 融梦能量，注册它们不会产生额外效果，避免重复加成。
 */
public final class PasterDreamFoodRegistry
{
    private static final Map<ResourceLocation, FoodEffects> REGISTRY = new LinkedHashMap<>();

    private PasterDreamFoodRegistry()
    {
    }

    public static final class FoodEffects
    {
        private final double sanAdd;
        private final double meltDreamEnergyAdd;

        public FoodEffects(double sanAdd, double meltDreamEnergyAdd)
        {
            this.sanAdd = sanAdd;
            this.meltDreamEnergyAdd = meltDreamEnergyAdd;
        }

        public double getSanAdd()
        {
            return sanAdd;
        }

        public double getMeltDreamEnergyAdd()
        {
            return meltDreamEnergyAdd;
        }
    }

    /**
     * 以 {@code sanAdd, meltDreamEnergyAdd} 参数形式注册。
     *
     * @return 物品 id 合法时返回 true，否则 false（id 非法时不注册）。
     */
    public static boolean register(String itemId, double sanAdd, double meltDreamEnergyAdd)
    {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        if (id == null)
        {
            return false;
        }
        REGISTRY.put(id, new FoodEffects(sanAdd, meltDreamEnergyAdd));
        return true;
    }

    /**
     * 以对象形式注册，支持键：{@code san} / {@code sanAdd}、{@code meltDreamEnergy} /
     * {@code meltDreamEnergyAdd}，缺省为 0。
     */
    public static boolean register(String itemId, Map<?, ?> properties)
    {
        double sanAdd = numberValue(properties.get("san"), numberValue(properties.get("sanAdd"), 0));
        double meltDreamEnergyAdd = numberValue(properties.get("meltDreamEnergy"), numberValue(properties.get("meltDreamEnergyAdd"), 0));
        return register(itemId, sanAdd, meltDreamEnergyAdd);
    }

    public static boolean unregister(String itemId)
    {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        return id != null && REGISTRY.remove(id) != null;
    }

    public static void clear()
    {
        REGISTRY.clear();
    }

    public static boolean isRegistered(String itemId)
    {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        return id != null && REGISTRY.containsKey(id);
    }

    public static FoodEffects get(String itemId)
    {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        return id == null ? null : REGISTRY.get(id);
    }

    public static double getSan(String itemId)
    {
        FoodEffects effects = get(itemId);
        return effects == null ? 0 : effects.getSanAdd();
    }

    public static double getMeltDreamEnergy(String itemId)
    {
        FoodEffects effects = get(itemId);
        return effects == null ? 0 : effects.getMeltDreamEnergyAdd();
    }

    public static Map<ResourceLocation, FoodEffects> getAll()
    {
        return Collections.unmodifiableMap(REGISTRY);
    }

    /**
     * 按物品栈查询注册效果，未注册或物品 id 缺失时返回 null。
     */
    public static FoodEffects get(ItemStack stack)
    {
        Item item = stack.getItem();
        ResourceLocation id = net.minecraftforge.registries.ForgeRegistries.ITEMS.getKey(item);
        return id == null ? null : REGISTRY.get(id);
    }

    private static double numberValue(Object value, double defaultValue)
    {
        return value instanceof Number number ? number.doubleValue() : defaultValue;
    }
}