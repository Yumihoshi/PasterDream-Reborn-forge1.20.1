package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class FishingLootsHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        FishingHook hook = event.getHookEntity();
        if (hook == null) return;

        Level level = hook.level();
        if (level.isClientSide) return;

        Vec3 origin = hook.position();
        BlockPos pos = BlockPos.containing(origin);

        // 环境幸运值
        double luck = 1.0;
        if (level.canSeeSkyFromBelowWater(pos)) {
            luck += 0.1;
        }
        if (level.getLevelData().isRaining()) {
            luck += 0.1;
        }
        if (level.getLevelData().isThundering()) {
            luck += 0.2;
        }
        if (!level.isDay()) {
            luck += 0.1;
        }
        luck += 0.5 * level.dimensionType().moonPhase(level.dayTime());

        // 拥有者相关幸运加成
        Entity owner = hook.getOwner();
        if (owner instanceof LivingEntity living) {
            if (living.hasEffect(MobEffects.LUCK)) {
                luck += 0.2;
            }
            if (living.hasEffect(MobEffects.UNLUCK)) {
                luck -= 0.9;
            }
            if (living.getAttribute(Attributes.LUCK) != null) {
                luck += living.getAttributeValue(Attributes.LUCK) * 0.1;
            }
            int fishingLuckLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_LUCK, living.getMainHandItem());
            if (fishingLuckLevel > 0) {
                luck += fishingLuckLevel * 0.1;
            }

            // TODO: star_wish_rod 尚未搬运
            // if (living.getMainHandItem().is(ModItems.STAR_WISH_ROD.get()) || living.getOffhandItem().is(ModItems.STAR_WISH_ROD.get())) {
            //     luck *= 2.2;
            // }
        }

        ItemStack treasure = null;

        // 主世界 + ocean 类群系 → 深海秘宝 (8% * luck)
        if (level.dimension() == Level.OVERWORLD
                && level.getBiome(pos).is(BiomeTags.IS_OCEAN)) {
            if (Math.random() < 0.08 * luck) {
                treasure = new ItemStack(ModItems.DEEP_SEA_TREASURE.get());
            }
        }

        // 染梦维度 + 染梦冻洋/染梦海洋 → 染梦深海秘宝 (10% * luck)
        if (level.dimension().location().equals(ResourceLocation.parse("pasterdream:dyedream_world"))
                && (level.getBiome(pos).is(ResourceLocation.parse("pasterdream:dyedream_frozen_ocean"))
                        || level.getBiome(pos).is(ResourceLocation.parse("pasterdream:dyedream_ocean")))) {
            if (Math.random() < 0.10 * luck) {
                treasure = new ItemStack(ModItems.DYEDREAM_DEEP_TREASURE.get());
            }
        }

        if (treasure == null) return;

        // super 变体：幸运值越高概率越大，luck >= 16.7 时封顶 50%
        if (Math.random() < Math.min(luck * 0.03, 0.5)) {
            treasure.getOrCreateTag().putBoolean("deep_treasure_super", true);
        }

        // 直接生成掉落物到世界中
        if (level instanceof ServerLevel serverLevel) {
            Entity target = owner != null ? owner : hook;
            double x = origin.x;
            double y = origin.y;
            double z = origin.z;
            double dx = target.getX() - x;
            double dy = target.getY() - y;
            double dz = target.getZ() - z;
            ItemEntity itemEntity = new ItemEntity(serverLevel, x, y, z, treasure);
            itemEntity.setPickUpDelay(5);
            itemEntity.setDeltaMovement(dx * 0.1, dy * 0.1 + Math.sqrt(Math.sqrt(dx * dx + dy * dy + dz * dz)) * 0.08, dz * 0.1);
            serverLevel.addFreshEntity(itemEntity);
        }
    }
}
