package com.pasterdream.pasterdreammod.world.item.StorgeBagItem;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class StorageBagEventHandler {

    private static final Field HEALTH_FIELD;

    static {
        Field f = null;
        try {
            f = ItemEntity.class.getDeclaredField("f_31985_"); // health field in mapped names
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            try {
                f = ItemEntity.class.getDeclaredField("health");
                f.setAccessible(true);
            } catch (NoSuchFieldException ignored) {}
        }
        HEALTH_FIELD = f;
    }

    /**
     * 袋子物品实体被岩浆/火焰烧毁时，散落内容物
     */
    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        ItemStack stack = itemEntity.getItem();
        if (!isStorageBag(stack)) return;

        // 仅在被摧毁（血量耗尽）时触发，排除玩家捡起
        int health = getHealth(itemEntity);
        if (health > 0) return;

        scatterContents(itemEntity.level(), itemEntity, stack);
    }

    private static boolean isStorageBag(ItemStack stack) {
        return stack.getItem() instanceof StorageBagItem || stack.getItem() instanceof LargeStorageBagItem;
    }

    private static int getHealth(ItemEntity entity) {
        if (HEALTH_FIELD == null) return 0;
        try {
            return HEALTH_FIELD.getInt(entity);
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    /**
     * 散落 NBT 中的物品并清空
     */
    private static void scatterContents(Level level, ItemEntity bagEntity, ItemStack bagStack) {
        ListTag items;
        if (bagStack.getItem() instanceof StorageBagItem) {
            items = StorageBagItem.getInventoryTag(bagStack);
        } else if (bagStack.getItem() instanceof LargeStorageBagItem) {
            items = LargeStorageBagItem.getInventoryTag(bagStack);
        } else {
            return;
        }

        boolean hasContent = false;
        for (int i = 0; i < items.size(); i++) {
            ItemStack content = ItemStack.of(items.getCompound(i));
            if (!content.isEmpty()) {
                hasContent = true;
                ItemEntity drop = new ItemEntity(level,
                        bagEntity.getX(), bagEntity.getY() + 0.5, bagEntity.getZ(),
                        content);
                drop.setDefaultPickUpDelay();
                drop.setDeltaMovement(
                        (level.random.nextDouble() - 0.5) * 0.2,
                        level.random.nextDouble() * 0.3,
                        (level.random.nextDouble() - 0.5) * 0.2
                );
                level.addFreshEntity(drop);
            }
        }

        // 清空 NBT
        if (hasContent) {
            bagStack.getOrCreateTag().remove(
                    bagStack.getItem() instanceof StorageBagItem ? "BagItems" : "BagItems"
            );
        }
    }
}
