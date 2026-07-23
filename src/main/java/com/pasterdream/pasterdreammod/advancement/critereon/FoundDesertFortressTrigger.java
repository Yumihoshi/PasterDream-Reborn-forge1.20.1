package com.pasterdream.pasterdreammod.advancement.critereon;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * 自定义进度触发器 —— 发现沙漠堡垒结构。
 * 对应进度："沙漠遗迹"。
 */
public class FoundDesertFortressTrigger extends SimpleCriterionTrigger<FoundDesertFortressTrigger.TriggerInstance> {

    static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("pasterdream", "found_desert_fortress");

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json,
                                                       ContextAwarePredicate player,
                                                       DeserializationContext context) {
        return new TriggerInstance(player);
    }

    /**
     * 当玩家进入沙漠堡垒结构时调用。
     */
    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ContextAwarePredicate player) {
            super(ID, player);
        }

        /**
         * 创建条件实例 —— 用于"沙漠遗迹"进度。
         */
        public static TriggerInstance found() {
            return new TriggerInstance(ContextAwarePredicate.ANY);
        }

        @Override
        public @NotNull JsonObject serializeToJson(SerializationContext context) {
            return new JsonObject();
        }
    }
}
