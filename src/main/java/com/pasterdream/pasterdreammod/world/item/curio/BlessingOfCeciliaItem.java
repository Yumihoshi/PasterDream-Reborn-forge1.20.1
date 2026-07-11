package com.pasterdream.pasterdreammod.world.item.curio;

import com.pasterdream.pasterdreammod.world.item.ModRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class BlessingOfCeciliaItem extends Item implements ICurioItem {
    public BlessingOfCeciliaItem() {
        super(new Item.Properties().stacksTo(1).rarity(ModRarities.LEGENDARY));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity == null || entity.level().isClientSide()) return;
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 1, false, false));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(ModRarities.qualityTooltip(ModRarities.LEGENDARY));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect0"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect1"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect2"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.effect3"));
        list.add(Component.translatable("tooltip.pasterdream.blessing_of_cecilia.lore"));
    }
}
