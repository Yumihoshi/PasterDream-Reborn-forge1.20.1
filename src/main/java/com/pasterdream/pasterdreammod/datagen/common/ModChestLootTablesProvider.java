package com.pasterdream.pasterdreammod.datagen.common;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;
public class ModChestLootTablesProvider implements LootTableSubProvider {
    /**
     *资料来源于 minecraft wiki:
     * bonus_rolls：（默认为0）根据战利品上下文提供的幸运值增加抽取次数。游戏会将玩家幸运值属性的值和钓鱼时工具上fishing_luck_bonus魔咒效果的等级相加后，与此字段的值相乘并向下取整，作为额外的抽取次数。
     * 换句话说，抽取次数的计算公式为：final_rolls = [rolls + luck * bonus_rolls]
     **/
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/picnic_basket"),//原loots_relic_9
                LootTable.lootTable()
                        // 幸运影响系数0.15，移除原版的2-4次抽取，改为固定4次抽取
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.15F))
                                .setRolls(ConstantValue.exactly(4.0F))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(4)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.APPLE)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.FRIED_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.PUMPKIN_PIE)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.MUSHROOM_STEW)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.COOKED_CHICKEN)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_MILK.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.BACON_AND_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.SWEET_BERRIES)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.COOKIE)
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_CUP_OF_HONEY_JUICE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.DRIED_KELP)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.CREAM_BUN_CAKE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.CHOCOLATE.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                        )
        );
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/dyedream_relic_chest_loot_common"),//原loots_relic_0
                LootTable.lootTable()
                        // 染梦物品，固定2抽取，幸运影响系数0.15
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.15F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DUST_PIECE.get())
                                        .setWeight(4)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.PINK_SLIME_BLOCK.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.MELT_DREAM_COIN.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(5))
                        )
                        // 植物，固定2抽取，幸运影响系数0.1
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.1F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SAPLING.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_COROLLA_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.CLOUD_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.LIGHT_BALL_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LILY_OF_THE_VALLEY.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.SINGULARITY_FERN.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.LINHT_FLOWER.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.PINK_MUSHROOM.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                        )

                        .withPool(LootPool.lootPool()
                                // 食材&食物，固定3抽取，幸运影响系数0.15
                                .setBonusRolls(ConstantValue.exactly(0.15F))
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.DOUGH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.FLOUR.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.DOUGH_WITH_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_FRUIT.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(ModItems.CAKE_BASE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.CHOCOLATE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.SUGAR)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_WATER.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.FRIED_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.BACON_AND_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.ODD_BACON_AND_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.RICE_CAKE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.SANDWICH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_CUP_OF_DYEDREAM_JUICE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.BUBBLE_GUM.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_YEAST.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.SANDWICH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                        )

                        .withPool(LootPool.lootPool()
                                // 建材，固定1抽取，不提供额外抽取次数
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.POLISHED_CALCITE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.CALCITE_TILES.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_QUARTZ.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 16.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_GLASS.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.CARVE_DYEDREAM_GLASS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 16.0F))))
                                .add(LootItem.lootTableItem(ModItems.PILLAR_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.CHISELED_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.BRICKS_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LOG.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_PLANKS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SAND.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 32.0F))))

                        )
                        .withPool(LootPool.lootPool()
                                // 装备&工具，固定1抽取，不提供额外抽取次数（部分物品未加入，暂时加入已加入物品到战利品表）
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.PALE_BONENEEDLE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.MORTAR.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.PLIERS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(4.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DYE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(2.0F))))
                                .add(LootItem.lootTableItem(Items.IRON_PICKAXE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_SWORD)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_SHOVEL)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_AXE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_HOE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.SHEARS)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.IRON_HELMET)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_CHESTPLATE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_LEGGINGS)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_BOOTS)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                        )
                        .withPool(LootPool.lootPool()
                                // 材料物品，固定1抽取，幸运影响系数0.25
                                .setBonusRolls(ConstantValue.exactly(0.25F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.RAW_DYEDREAM_ALLOY_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_ALLOY_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DUST.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_ALLOY_NUGGET.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(3.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_NUGGET.get())
                                        .setWeight(5)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(3.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(90))
                        )
                        .withPool(LootPool.lootPool()
                                // 染梦装备，固定1抽取，幸运影响系数1（概率太低了，所以加了幸运影响系数）
                                .setBonusRolls(ConstantValue.exactly(1.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_UPGRADE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SWORD.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_PICKAXE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_AXE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SHOVEL.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_HELMET.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_CHESTPLATE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LEGGINGS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_BOOTS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_UPGRADE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(400))
                        )
                        //饰品战利品后面添加

                        .withPool(LootPool.lootPool()
                                // 附魔书，固定1抽取，无幸运修正系数
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 20.0F))
                                                .allowTreasure()))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(3))
                        )
        );
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/dyedream_relic_chest_loot_rare"),//原loots_relic_1
                LootTable.lootTable()
                        // 染梦物品，固定1抽取，幸运影响系数0.2
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.2F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DUST_PIECE.get())
                                        .setWeight(4)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.PINK_SLIME_BLOCK.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.MELT_DREAM_COIN.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(9))
                        )
                        // 植物，固定2抽取，幸运影响系数0.1
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.1F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SAPLING.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_COROLLA_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.CLOUD_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.LIGHT_BALL_CROP_AGE_1.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LILY_OF_THE_VALLEY.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.SINGULARITY_FERN.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.LINHT_FLOWER.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.PINK_MUSHROOM.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                        )

                        .withPool(LootPool.lootPool()
                                // 食材&食物，固定3抽取，幸运影响系数0.15
                                .setBonusRolls(ConstantValue.exactly(0.15F))
                                .setRolls(ConstantValue.exactly(3.0F))
                                .add(LootItem.lootTableItem(ModItems.DOUGH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.FLOUR.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.DOUGH_WITH_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_FRUIT.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.CAKE_BASE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.CHOCOLATE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F))))
                                .add(LootItem.lootTableItem(Items.SUGAR)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_WATER.get())
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.FRIED_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.BACON_AND_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.ODD_BACON_AND_EGG.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.RICE_CAKE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(ModItems.SANDWICH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_CUP_OF_DYEDREAM_JUICE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.BUBBLE_GUM.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_YEAST.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.SANDWICH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                        )

                        .withPool(LootPool.lootPool()
                                // 建材，固定1抽取，不提供额外抽取次数
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.POLISHED_CALCITE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.CALCITE_TILES.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_QUARTZ.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_GLASS.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.CARVE_DYEDREAM_GLASS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 16.0F))))
                                .add(LootItem.lootTableItem(ModItems.PILLAR_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.CHISELED_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.BRICKS_DYEDREAM_QUARTZ_BLOCK.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LOG.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_PLANKS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SAND.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 24.0F))))

                        )
                        .withPool(LootPool.lootPool()
                                // 装备&工具，固定1抽取，幸运修正系数0.1
                                .setBonusRolls(ConstantValue.exactly(0.1F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.PALE_BONENEEDLE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.MORTAR.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(4.0F, 8.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DYE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(2.0F))))
                                .add(LootItem.lootTableItem(Items.IRON_PICKAXE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_SWORD)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_SHOVEL)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_AXE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_HOE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.SHEARS)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.IRON_HELMET)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_CHESTPLATE)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_LEGGINGS)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.IRON_BOOTS)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 4.0F))
                                                .allowTreasure()))
                        )
                        .withPool(LootPool.lootPool()
                                // 材料物品，固定1抽取，幸运影响系数0.25
                                .setBonusRolls(ConstantValue.exactly(0.25F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.RAW_DYEDREAM_ALLOY_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_ALLOY_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_DUST.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_ALLOY_NUGGET.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(3.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_NUGGET.get())
                                        .setWeight(5)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(3.0F, 5.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_INGOT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(80))
                        )
                        .withPool(LootPool.lootPool()
                                // 染梦装备，固定1抽取，幸运影响系数1（概率太低了，所以加了幸运影响系数）
                                .setBonusRolls(ConstantValue.exactly(1.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_UPGRADE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SWORD.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_PICKAXE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_AXE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_SHOVEL.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_HELMET.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_CHESTPLATE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_LEGGINGS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.DYEDREAM_BOOTS.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.TITANIUM_UPGRADE.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(400))
                        )
                        //饰品战利品后面添加

                        .withPool(LootPool.lootPool()
                                // 附魔书，固定1抽取，无幸运修正系数
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 20.0F))
                                                .allowTreasure()))
                                .add(EmptyLootItem.emptyItem()
                                        .setWeight(3))
                        )

        );
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/fisherman_hut_chest"),//原loots_relic_5
                LootTable.lootTable()
                        // 水产，改为固定2次抽取，不受幸运影响
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(Items.COD)
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 6.0F))))
                                .add(LootItem.lootTableItem(Items.SALMON)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.TROPICAL_FISH)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.PUFFERFISH)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.SEAGRASS)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(2.0F, 7.0F))))
                                .add(LootItem.lootTableItem(Items.KELP)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 5.0F))))
                                .add(LootItem.lootTableItem(Items.SEA_PICKLE)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                        )
                        //食材，抽取1次，无幸运修正
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.GLASS_JAR_OF_WATER.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.WATER_BUCKET)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.COARSE_SALT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(4.0F, 8.0F))))
                        )
                        //杂物，抽取1次，无幸运修正
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.INK_SAC)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.STRING)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.SCUTE)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.FISHING_ROD)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(10.0F, 20.0F))
                                                .allowTreasure()))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.FABRIC.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(1)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(
                                                        UniformGenerator.between(1.0F, 20.0F))
                                                .allowTreasure()))
                        )
                        //真活鱼真好吃，抽取1次，无幸运修正
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItems.WAFER_BISCUIT.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(ModItems.STUFFED_WAFER_COOKIES.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.BEETROOT_SOUP)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.DRIED_KELP)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 3.0F))))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(ModItems.BREAD_SLICE.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                        )
                        //食物，抽取1次，无幸运修正
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0F))
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(Items.COD_BUCKET)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.SALMON_BUCKET)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.PUFFERFISH_BUCKET)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.TROPICAL_FISH_BUCKET)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                                .add(LootItem.lootTableItem(Items.AXOLOTL_BUCKET)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(
                                                ConstantValue.exactly(1.0F))))
                        )
                        //钓鱼宝匣，抽取1次，0.25幸运修正系数
                        .withPool(LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.25F))
                                .setRolls(ConstantValue.exactly(2.0F))
                                .add(LootItem.lootTableItem(ModItems.DEEP_SEA_TREASURE.get())
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 2.0F))))
                        )
        );

        //测试战利品列表0
        consumer.accept(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/test_loot_table_0"), LootTable.lootTable()
                //抽取1次，0幸运抽取，每次1个命令方块
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(Items.COMMAND_BLOCK)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
                //抽取1次，0幸运抽取，每次2-16个娇小琴雨梦玩偶
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(ModItems.QYM_DOLL.get())
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2F, 16F)))))
                //抽取1次，0幸运抽取，每次2/3概率获得1个结构方块，1/3概率获得1个结构空位
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(Items.STRUCTURE_BLOCK)
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F))))
                        .add(LootItem.lootTableItem(Items.STRUCTURE_VOID)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
                //随机抽取2-16次，0幸运抽取，每次1个破碎粒子提供方块0
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(UniformGenerator.between(2F, 16F))
                        .add(LootItem.lootTableItem(ModItems.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_0.get())
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
                //抽取1次，16幸运影响，每次1个破碎粒子提供方块1
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(16F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(ModItems.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_1.get())
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
        );

        //测试战利品列表1
        consumer.accept(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/test_loot_table_1"), LootTable.lootTable()
                //抽取1次，0幸运抽取，每次1个连锁型命令方块
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(Items.CHAIN_COMMAND_BLOCK)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
        );

        //测试战利品列表2
        consumer.accept(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,"chests/test_loot_table_2"), LootTable.lootTable()
                //抽取1次，0幸运抽取，每次1个循环型命令方块
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0F))
                        .setRolls(ConstantValue.exactly(1F))
                        .add(LootItem.lootTableItem(Items.REPEATING_COMMAND_BLOCK)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1F)))))
        );
    }
}