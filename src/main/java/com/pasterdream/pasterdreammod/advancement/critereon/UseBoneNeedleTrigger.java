package com.pasterdream.pasterdreammod.advancement.critereon;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * 自定义进度触发器 —— 在梦维度中使用苍白骨针。
 * <p>
 * 支持两种匹配模式：
 * <ul>
 *   <li>{@link TriggerInstance#use()} — 任意使用骨针即触发（对应"哦，疼！"进度）</li>
 *   <li>{@link TriggerInstance#fellAndUse()} — 要求玩家摔落超过 10 格后使用（对应"人类坠出梦境"进度）</li>
 * </ul>
 */
public class UseBoneNeedleTrigger extends SimpleCriterionTrigger<UseBoneNeedleTrigger.TriggerInstance> {

    static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("pasterdream", "use_boneneedle");

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json,
                                                       ContextAwarePredicate player,
                                                       DeserializationContext context) {
        boolean falling = json.has("falling") && json.get("falling").getAsBoolean();
        return new TriggerInstance(player, falling);
    }

    /**
     * 当玩家在梦维度中使用骨针时调用。
     *
     * @param player     触发玩家
     * @param wasFalling 玩家使用骨针时是否处于摔落状态
     */
    public void trigger(ServerPlayer player, boolean wasFalling) {
        this.trigger(player, instance -> instance.matches(wasFalling));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        /** 是否要求玩家处于摔落状态 */
        private final boolean requireFalling;

        public TriggerInstance(ContextAwarePredicate player, boolean requireFalling) {
            super(ID, player);
            this.requireFalling = requireFalling;
        }

        /**
         * 用于"哦，疼！"进度。
         */
        public static TriggerInstance use() {
            return new TriggerInstance(ContextAwarePredicate.ANY, false);
        }

        /**
         * 用于"人类坠出梦境"进度。
         */
        public static TriggerInstance fellAndUse() {
            return new TriggerInstance(ContextAwarePredicate.ANY, true);
        }


        public boolean matches(boolean wasFalling) {
            if (requireFalling) {
                return wasFalling;
            }
            return true;
        }

        @Override
        public @NotNull JsonObject serializeToJson(SerializationContext context) {
            JsonObject obj = new JsonObject();
            if (requireFalling) {
                obj.addProperty("falling", true);
            }
            return obj;
        }
    }
}
