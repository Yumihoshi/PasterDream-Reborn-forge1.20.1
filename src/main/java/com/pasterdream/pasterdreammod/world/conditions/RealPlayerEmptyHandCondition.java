package com.pasterdream.pasterdreammod.world.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

/**
 * 战利品条件：破坏者必须是持有空手的真实玩家（非 FakePlayer）。
 * 用于区分玩家手动采集与自动化收割。
 */
public class RealPlayerEmptyHandCondition implements LootItemCondition
{
    public static final RealPlayerEmptyHandCondition INSTANCE = new RealPlayerEmptyHandCondition();

    /** 由 ModLootTables 在注册时赋值 */
    public static LootItemConditionType TYPE;

    private RealPlayerEmptyHandCondition() {}

    @Override
    public boolean test(LootContext context)
    {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (!(entity instanceof ServerPlayer))
        {
            return false;
        }
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        return tool != null && tool.isEmpty();
    }

    @Override
    public LootItemConditionType getType()
    {
        return TYPE;
    }

    public static LootItemCondition.Builder builder()
    {
        return () -> INSTANCE;
    }

    public static class ConditionSerializer implements Serializer<RealPlayerEmptyHandCondition>
    {
        @Override
        public void serialize(JsonObject json, RealPlayerEmptyHandCondition condition, JsonSerializationContext ctx)
        {
        }

        @Override
        public RealPlayerEmptyHandCondition deserialize(JsonObject json, JsonDeserializationContext ctx)
        {
            return INSTANCE;
        }
    }
}
