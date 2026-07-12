package com.pasterdream.pasterdreammod.world.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 战利品函数：在战利品表触发位置上方生成实体。
 * <p>
 * 通过 {@link LootContextParams#ORIGIN} 获取位置，在 Y 轴偏移处生成指定类型的实体。
 * 支持 {@code consume} 模式：生成实体后将物品栈清空，达到"只生成实体、不额外掉落"的效果。
 * </p>
 *
 * <h3>Datagen 用法</h3>
 * <pre>{@code
 * // 生成实体 + 保留物品掉落
 * .apply(SpawnEntityFunction.Builder.spawnAbove(EntityType.ZOMBIE, 1, 1.0))
 *
 * // 只生成实体，不额外掉落（挂在任意 LootItem 上即可）
 * .apply(SpawnEntityFunction.Builder.spawnOnly(EntityType.ZOMBIE, 1, 1.0))
 * }</pre>
 */
public class SpawnEntityFunction extends LootItemConditionalFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasterDreamMod.MOD_ID);

    private final EntityType<?> entityType;
    private final int count;
    private final double offsetY;
    /** true 时返回 ItemStack.EMPTY，吃掉载体物品 */
    private final boolean consume;

    SpawnEntityFunction(LootItemCondition[] conditions,
                        EntityType<?> entityType,
                        int count,
                        double offsetY,
                        boolean consume) {
        super(conditions);
        this.entityType = entityType;
        this.count = count;
        this.offsetY = offsetY;
        this.consume = consume;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext ctx) {
        Vec3 origin = ctx.getParamOrNull(LootContextParams.ORIGIN);
        LOGGER.info("[SpawnEntityFunction] run() called, origin={}, entityType={}, count={}, offsetY={}, consume={}",
                origin, ForgeRegistries.ENTITY_TYPES.getKey(entityType), count, offsetY, consume);
        if (origin == null) {
            LOGGER.warn("[SpawnEntityFunction] ORIGIN is null — cannot spawn entity");
            return consume ? ItemStack.EMPTY : stack;
        }

        ServerLevel serverLevel = ctx.getLevel();
        RandomSource random = ctx.getRandom();
        for (int i = 0; i < count; i++) {
            Entity entity = entityType.create(serverLevel);
            if (entity == null) {
                LOGGER.warn("[SpawnEntityFunction] entityType.create() returned null");
                continue;
            }

            double x = origin.x + (random.nextDouble() - 0.5) * 0.5;
            double y = origin.y + offsetY;
            double z = origin.z + (random.nextDouble() - 0.5) * 0.5;

            entity.setPos(x, y, z);
            serverLevel.addFreshEntity(entity);
            LOGGER.info("[SpawnEntityFunction] Spawned {} at ({}, {}, {})",
                    entity.getName().getString(), x, y, z);
        }
        return consume ? ItemStack.EMPTY : stack;
    }

    @Override
    public LootItemFunctionType getType() {
        return ModLootTables.SPAWN_ENTITY_FUNCTION_TYPE.get();
    }

    // ==================== Builder (datagen) ====================

    public static class Builder extends LootItemConditionalFunction.Builder<Builder> {
        private final EntityType<?> entityType;
        private final int count;
        private final double offsetY;
        private boolean consume = false;

        private Builder(EntityType<?> entityType, int count, double offsetY) {
            this.entityType = entityType;
            this.count = count;
            this.offsetY = offsetY;
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public SpawnEntityFunction build() {
            return new SpawnEntityFunction(getConditions(), entityType, count, offsetY, consume);
        }

        /** 快捷工厂：在原点上方生成实体，保留原始物品掉落 */
        public static Builder spawnAbove(EntityType<?> entityType, int count, double offsetY) {
            return new Builder(entityType, count, offsetY);
        }

        /** 快捷工厂：默认偏移 1.0（正上方一格），保留原始物品掉落 */
        public static Builder spawnAbove(EntityType<?> entityType, int count) {
            return new Builder(entityType, count, 1.0);
        }

        /** 快捷工厂：只生成实体，不掉落物品（载体物品会被消耗） */
        public static Builder spawnOnly(EntityType<?> entityType, int count, double offsetY) {
            Builder b = new Builder(entityType, count, offsetY);
            b.consume = true;
            return b;
        }
    }

    // ==================== Serializer (JSON) ====================

    public static class Serializer extends LootItemConditionalFunction.Serializer<SpawnEntityFunction> {

        @Override
        public void serialize(JsonObject json, SpawnEntityFunction func, JsonSerializationContext ctx) {
            super.serialize(json, func, ctx);
            ResourceLocation entityId = ForgeRegistries.ENTITY_TYPES.getKey(func.entityType);
            if (entityId != null) {
                json.addProperty("entity", entityId.toString());
            }
            json.addProperty("count", func.count);
            json.addProperty("offsetY", func.offsetY);
            if (func.consume) {
                json.addProperty("consume", true);
            }
        }

        @Override
        public SpawnEntityFunction deserialize(JsonObject json, JsonDeserializationContext ctx,
                                                LootItemCondition[] conditions) {
            String entityIdStr = GsonHelper.getAsString(json, "entity");
            int colon = entityIdStr.indexOf(':');
            ResourceLocation entityId = ResourceLocation.fromNamespaceAndPath(
                    entityIdStr.substring(0, colon),
                    entityIdStr.substring(colon + 1));
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityId);
            if (entityType == null) {
                throw new IllegalArgumentException("Unknown entity type: " + entityIdStr);
            }
            int count = GsonHelper.getAsInt(json, "count", 1);
            double offsetY = GsonHelper.getAsDouble(json, "offsetY", 1.0);
            boolean consume = GsonHelper.getAsBoolean(json, "consume", false);
            return new SpawnEntityFunction(conditions, entityType, count, offsetY, consume);
        }
    }
}
