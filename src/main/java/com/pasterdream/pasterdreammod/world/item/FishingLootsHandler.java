package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PasterDreamMod.MOD_ID)
public class FishingLootsHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer sp) || player.level().isClientSide()) return;

        // 前置：完成原版成就"腥味十足的生意"
        var advancement = sp.server.getAdvancements().getAdvancement(
                ResourceLocation.parse("minecraft:husbandry/fishy_business"));
        if (advancement == null || !sp.getAdvancements().getOrStartProgress(advancement).isDone()) {
            return;
        }

        Level level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        BlockPos pos = BlockPos.containing(x, y, z);

        double luck = 1.0;

        // 水下能看到天空
        if (level.canSeeSkyFromBelowWater(pos)) {
            luck += 0.1;
        }
        // 天气
        if (level.getLevelData().isRaining()) {
            luck += 0.1;
        }
        if (level.getLevelData().isThundering()) {
            luck += 0.2;
        }
        // 夜晚
        if (!level.isDay()) {
            luck += 0.1;
        }
        // 月相
        luck += 0.5 * level.dimensionType().moonPhase(level.dayTime());
        // 幸运/霉运药水效果
        if (player.hasEffect(MobEffects.LUCK)) {
            luck += 0.2;
        }
        if (player.hasEffect(MobEffects.UNLUCK)) {
            luck -= 0.9;
        }
        // 玩家幸运属性
        if (player.getAttribute(Attributes.LUCK) != null) {
            luck += player.getAttributeValue(Attributes.LUCK) * 0.02;
        }
        // 海之眷顾附魔
        int fishingLuckLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_LUCK, player.getMainHandItem());
        if (fishingLuckLevel > 0) {
            luck += fishingLuckLevel * 0.1;
        }

        // TODO: star_wish_rod 尚未搬运，届时在此加入手持检测（luck * 2.2 + super 概率）
        // if (player.getMainHandItem().is(ModItems.STAR_WISH_ROD.get()) || player.getOffhandItem().is(ModItems.STAR_WISH_ROD.get())) {
        //     luck *= 2.2;
        //     player.getPersistentData().putBoolean("star_wish_rod", true);
        // }

        final double finalLuck = luck;
        level.getServer().execute(() -> {
            spawnTreasure(player, level, x, y, z, pos, finalLuck);
            player.getPersistentData().remove("star_wish_rod");
        });
    }

    private static void spawnTreasure(Player player, Level level, double x, double y, double z, BlockPos pos, double luck) {
        ItemStack treasure = null;

        // 主世界 + ocean 群系 → 深海秘宝 (2.5% * luck)
        if (level.dimension() == Level.OVERWORLD
                && level.getBiome(pos).is(ResourceLocation.parse("ocean"))) {
            if (Math.random() < 0.025 * luck) {
                treasure = new ItemStack(ModItems.DEEP_SEA_TREASURE.get());
            }
        }

        // 染梦维度 + 染梦冻洋 → 染梦深海秘宝 (2.9% * luck)
        if (level.dimension() == ResourceKey.create(Registries.DIMENSION,
                        ResourceLocation.parse("pasterdream:dyedream_world"))
                && level.getBiome(pos).is(ResourceLocation.parse("pasterdream:dyedream_frozen_ocean"))) {
            if (Math.random() < 0.029 * luck) {
                treasure = new ItemStack(ModItems.DYEDREAM_DEEP_TREASURE.get());
            }
        }

        if (treasure == null) return;

        // super 变体检测（star_wish_rod 搬运后从 persistentData 读取标记）
        if (player.getPersistentData().getBoolean("star_wish_rod")) {
            if (Math.random() < 0.1) {
                treasure.getOrCreateTag().putBoolean("deep_treasure_super", true);
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            ItemEntity entity = new ItemEntity(serverLevel, x, y, z, treasure);
            entity.setPickUpDelay(5);
            serverLevel.addFreshEntity(entity);
        }
    }
}
