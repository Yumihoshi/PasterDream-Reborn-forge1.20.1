package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class FishingLootsHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        // 真人玩家需完成"腥味十足的生意"成就；假玩家（女仆等）跳过这个检查
        if (player instanceof ServerPlayer sp && !(player instanceof FakePlayer)) {
            var advancement = sp.server.getAdvancements().getAdvancement(
                    ResourceLocation.parse("minecraft:husbandry/fishy_business"));
            if (advancement == null || !sp.getAdvancements().getOrStartProgress(advancement).isDone()) {
                return;
            }
        }

        Level level = player.level();
        BlockPos pos = player.blockPosition();

        // 计算幸运值
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
        if (player.hasEffect(MobEffects.LUCK)) {
            luck += 0.2;
        }
        if (player.hasEffect(MobEffects.UNLUCK)) {
            luck -= 0.9;
        }
        if (player.getAttribute(Attributes.LUCK) != null) {
            // 玩家幸运倍率是0.02
            luck += player.getAttributeValue(Attributes.LUCK) * 0.02;
        }
        int fishingLuckLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_LUCK, player.getMainHandItem());
        if (fishingLuckLevel > 0) {
            luck += fishingLuckLevel * 0.1;
        }

        // TODO: star_wish_rod 尚未搬运
        // if (player.getMainHandItem().is(ModItems.STAR_WISH_ROD.get()) || player.getOffhandItem().is(ModItems.STAR_WISH_ROD.get())) {
        //     luck *= 2.2;
        // }

        ItemStack treasure = null;

        // 主世界 + ocean 类群系 → 深海秘宝 (2.5% * luck)
        if (level.dimension() == Level.OVERWORLD
                && level.getBiome(pos).is(BiomeTags.IS_OCEAN)) {
            if (Math.random() < 0.025 * luck) {
                treasure = new ItemStack(ModItems.DEEP_SEA_TREASURE.get());
            }
        }

        // 染梦维度 + 染梦冻洋/染梦海洋 → 染梦深海秘宝 (2.9% * luck)
        if (level.dimension() == ResourceKey.create(Registries.DIMENSION,
                        ResourceLocation.parse("pasterdream:dyedream_world"))
                && (level.getBiome(pos).is(ResourceLocation.parse("pasterdream:dyedream_frozen_ocean"))
                        || level.getBiome(pos).is(ResourceLocation.parse("pasterdream:dyedream_ocean")))) {
            if (Math.random() < 0.029 * luck) {
                treasure = new ItemStack(ModItems.DYEDREAM_DEEP_TREASURE.get());
            }
        }

        if (treasure == null) return;

        // super 变体：幸运值越高概率越大，luck >= 3 时封顶 10%
        if (Math.random() < Math.min(luck * 0.03, 0.1)) {
            treasure.getOrCreateTag().putBoolean("deep_treasure_super", true);
        }

        // ItemFishedEvent.getDrops() 返回的是独立拷贝，修改不影响实际掉落
        // 因此直接生成 ItemEntity 到世界中
        if (level instanceof ServerLevel serverLevel) {
            double x = event.getHookEntity().getX();
            double y = event.getHookEntity().getY();
            double z = event.getHookEntity().getZ();
            ItemEntity entity = new ItemEntity(serverLevel, x, y, z, treasure);
            entity.setPickUpDelay(5);
            double dx = player.getX() - x;
            double dy = player.getY() - y;
            double dz = player.getZ() - z;
            entity.setDeltaMovement(dx * 0.1, dy * 0.1 + Math.sqrt(Math.sqrt(dx * dx + dy * dy + dz * dz)) * 0.08, dz * 0.1);
            serverLevel.addFreshEntity(entity);
        }
    }
}
