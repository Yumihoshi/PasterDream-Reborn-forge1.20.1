package com.pasterdream.pasterdreammod.advancement.critereon;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * 自定义进度触发器 —— 阅读特定内容的寻梦者笔记。
 * <p>
 * 支持两种匹配模式：
 * <ul>
 *   <li>{@link TriggerInstance#forContent(String)} — 仅匹配指定内容的笔记</li>
 *   <li>{@link TriggerInstance#any()} — 匹配任意笔记阅读事件</li>
 * </ul>
 */
public class ReadDreamNoteTrigger extends SimpleCriterionTrigger<ReadDreamNoteTrigger.TriggerInstance> {

    static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath("pasterdream", "read_dream_note");

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    @Override
    protected @NotNull TriggerInstance createInstance(@NotNull JsonObject json,
                                                       ContextAwarePredicate player,
                                                       DeserializationContext context) {
        String content = json.has("content") ? json.get("content").getAsString() : "";
        return new TriggerInstance(player, content);
    }

    /**
     * 当玩家阅读笔记时调用。
     *
     * @param player      触发玩家
     * @param contentKey  笔记内容的 NBT key
     */
    public void trigger(ServerPlayer player, String contentKey) {
        this.trigger(player, instance -> instance.matches(contentKey));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        /** 要匹配的笔记内容 key，空字符串表示匹配任意 */
        private final String content;

        public TriggerInstance(ContextAwarePredicate player, String content) {
            super(ID, player);
            this.content = content;
        }

        /**
         * 创建仅匹配特定内容 key 的实例。
         */
        public static TriggerInstance forContent(String content) {
            return new TriggerInstance(ContextAwarePredicate.ANY, content);
        }

        /**
         * 创建匹配任意笔记的实例。
         */
        public static TriggerInstance any() {
            return new TriggerInstance(ContextAwarePredicate.ANY, "");
        }

        public boolean matches(String contentKey) {
            if (content.isEmpty()) {
                return true;
            }
            return content.equals(contentKey);
        }

        @Override
        public @NotNull JsonObject serializeToJson(SerializationContext context) {
            JsonObject obj = new JsonObject();
            if (!content.isEmpty()) {
                obj.addProperty("content", content);
            }
            return obj;
        }
    }
}
