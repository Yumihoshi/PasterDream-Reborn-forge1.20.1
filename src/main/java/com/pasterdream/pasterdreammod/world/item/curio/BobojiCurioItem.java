package com.pasterdream.pasterdreammod.world.item.curio;

import com.pasterdream.pasterdreammod.init.ModAttributes;
import com.pasterdream.pasterdreammod.world.item.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;

public class BobojiCurioItem extends Item implements ICurioItem {
    private static final UUID BLINK_CD_UUID = UUID.fromString("aacc6573-c216-4d94-a67b-e72377bbdd32");
    private static final UUID BLINK_RANGE_UUID = UUID.fromString("a3cad91f-ed19-4ea5-a904-f65078ecf73e");
    private static final UUID BLINK_CONSUME_UUID = UUID.fromString("32144ee0-f98a-48cf-964e-3c6b19c89350");
    private static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("7673dac1-b6fb-4ca0-90dd-fd3787c501e4");

    public BobojiCurioItem() {
        super(new Item.Properties().stacksTo(1).rarity(ModRarities.LEGENDARY));
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (slotContext.entity() != null) {
            AttributeInstance blinkCd = slotContext.entity().getAttribute(ModAttributes.BLINK_CD.get());
            if (blinkCd != null) {
                blinkCd.addPermanentModifier(new AttributeModifier(BLINK_CD_UUID,
                        "Boboji blink cd", -0.2, AttributeModifier.Operation.ADDITION));
            }
            AttributeInstance blinkRange = slotContext.entity().getAttribute(ModAttributes.BLINK_RANGE.get());
            if (blinkRange != null) {
                blinkRange.addPermanentModifier(new AttributeModifier(BLINK_RANGE_UUID,
                        "Boboji blink range", 0.1, AttributeModifier.Operation.ADDITION));
            }
            AttributeInstance blinkConsume = slotContext.entity().getAttribute(ModAttributes.BLINK_CONSUME.get());
            if (blinkConsume != null) {
                blinkConsume.addPermanentModifier(new AttributeModifier(BLINK_CONSUME_UUID,
                        "Boboji blink consume", -0.4, AttributeModifier.Operation.ADDITION));
            }
            AttributeInstance moveSpeed = slotContext.entity().getAttribute(Attributes.MOVEMENT_SPEED);
            if (moveSpeed != null) {
                moveSpeed.addPermanentModifier(new AttributeModifier(MOVEMENT_SPEED_UUID,
                        "Boboji movement speed", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (slotContext.entity() != null) {
            AttributeInstance blinkCd = slotContext.entity().getAttribute(ModAttributes.BLINK_CD.get());
            if (blinkCd != null) blinkCd.removeModifier(BLINK_CD_UUID);
            AttributeInstance blinkRange = slotContext.entity().getAttribute(ModAttributes.BLINK_RANGE.get());
            if (blinkRange != null) blinkRange.removeModifier(BLINK_RANGE_UUID);
            AttributeInstance blinkConsume = slotContext.entity().getAttribute(ModAttributes.BLINK_CONSUME.get());
            if (blinkConsume != null) blinkConsume.removeModifier(BLINK_CONSUME_UUID);
            AttributeInstance moveSpeed = slotContext.entity().getAttribute(Attributes.MOVEMENT_SPEED);
            if (moveSpeed != null) moveSpeed.removeModifier(MOVEMENT_SPEED_UUID);
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() != null) {
            return CuriosApi.getCuriosInventory(slotContext.entity()).map(handler ->
                            handler.findFirstCurio(stack.getItem()).isEmpty())
                    .orElse(true);
        }
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(ModRarities.qualityTooltip(ModRarities.LEGENDARY));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.stat.speed"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.stat.range"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.stat.cd"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.stat.consume"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.effect.elytra"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.effect.evasion"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.effect.share"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.effect.flavor"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.lore"));
        list.add(Component.translatable("tooltip.pasterdream.boboji_curio.dedication"));
    }
}
