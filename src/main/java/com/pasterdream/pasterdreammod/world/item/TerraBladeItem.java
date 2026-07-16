package com.pasterdream.pasterdreammod.world.item;

import com.pasterdream.pasterdreammod.helper.cooldown.SkillCooldownHelper;
import com.pasterdream.pasterdreammod.capability.meltdreamenergy.MeltDreamEnergyHelper;
import com.pasterdream.pasterdreammod.init.ModEntities;
import com.pasterdream.pasterdreammod.init.ModItems;
import com.pasterdream.pasterdreammod.init.ModNetwork;
import com.pasterdream.pasterdreammod.init.ModSounds;
import com.pasterdream.pasterdreammod.network.skill.TerraBladeSwingPacket;
import com.pasterdream.pasterdreammod.world.entity.TerraswordWaveEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Comparator;
import java.util.List;

public class TerraBladeItem extends SwordItem {

    private static final double ENERGY_COST = 0.3;
    private static final double ENERGY_COST_WITH_CHARM = 0.1;
    private static final int COOLDOWN_TICKS = 100;
    private static final int COOLDOWN_WITH_CHARM = 40;

    public TerraBladeItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            boolean hasCharm = CuriosApi.getCuriosInventory(serverPlayer)
                    .map(inv -> inv.findFirstCurio(ModItems.TERRA_FLOATING_ISLAND.get()).isPresent())
                    .orElse(false);

            double cost = hasCharm ? ENERGY_COST_WITH_CHARM : ENERGY_COST;
            int cooldown = hasCharm ? COOLDOWN_WITH_CHARM : COOLDOWN_TICKS;

            double currentEnergy = MeltDreamEnergyHelper.getPlayerMeltDreamEnergy(serverPlayer);
            if (currentEnergy >= cost) {
                MeltDreamEnergyHelper.addPlayerMeltDreamEnergyAndSync(serverPlayer, -cost);
                CompoundTag tag = stack.getOrCreateTag();
                tag.putDouble("skill", 1);
                tag.putLong("skill_tick", level.getGameTime());
                // Calculate base attack for sword wave damage
                double pasterAtk = player.getAttributeValue(Attributes.ATTACK_DAMAGE)
                        + stack.getEnchantmentLevel(Enchantments.SHARPNESS) * 0.5;
                if (hasCharm) {
                    pasterAtk *= 1.3; // +30% with terra_floating_island
                }
                tag.putDouble("paster_atk", pasterAtk);
                tag.putDouble("skill_multiplier", 1.0);
                // Store enchantment levels for the sword wave entity
                tag.putInt("sweeping_edge", stack.getEnchantmentLevel(Enchantments.SWEEPING_EDGE));
                tag.putInt("smite", stack.getEnchantmentLevel(Enchantments.SMITE));
                tag.putInt("bane_of_arthropods", stack.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS));
                tag.putInt("fire_aspect", stack.getEnchantmentLevel(Enchantments.FIRE_ASPECT));
                tag.putInt("knockback", stack.getEnchantmentLevel(Enchantments.KNOCKBACK));
                tag.putInt("looting", stack.getEnchantmentLevel(Enchantments.MOB_LOOTING));
                tag.putBoolean("ignore_iframe", hasCharm);
                SkillCooldownHelper.applySharedCooldown(player, cooldown);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        net.minecraft.sounds.SoundEvents.ENCHANTMENT_TABLE_USE,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                player.displayClientMessage(Component.translatable("tooltip.pasterdream.terra_blade.no_energy"), true);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void executeSkillWave(Level level, Player player, ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int stage = (int) tag.getDouble("skill");
        if (stage < 1) return;

        Vec3 look = player.getLookAngle();
        double x = player.getX() + look.x;
        double y = player.getY() + 1.5;
        double z = player.getZ() + look.z;

        TerraswordWaveEntity wave = ModEntities.TERRASWORD_WAVE.get().spawn(
                (net.minecraft.server.level.ServerLevel) level,
                BlockPos.containing(x, y, z),
                MobSpawnType.MOB_SUMMONED);
        if (wave != null) {
            wave.setOwner(player);
            wave.setYRot(player.getYRot());
            wave.setXRot(player.getXRot());
            wave.setDeltaMovement(look.x * 2, look.y * 2, look.z * 2);

            CompoundTag swordTag = stack.getOrCreateTag();
            double atk = swordTag.getDouble("paster_atk");
            if (stage == 3) {
                atk *= 2;
            }
            atk *= SkillCooldownHelper.getSkillDamageMultiplier(player);
            CompoundTag waveData = wave.getPersistentData();
            waveData.putDouble("paster_atk", atk);
            waveData.putInt("sweeping_edge", swordTag.getInt("sweeping_edge"));
            waveData.putInt("smite", swordTag.getInt("smite"));
            waveData.putInt("bane_of_arthropods", swordTag.getInt("bane_of_arthropods"));
            waveData.putInt("fire_aspect", swordTag.getInt("fire_aspect"));
            waveData.putInt("knockback", swordTag.getInt("knockback"));
            waveData.putInt("looting", swordTag.getInt("looting"));
            waveData.putBoolean("ignore_iframe", swordTag.getBoolean("ignore_iframe"));

            float pitch = switch (stage) {
                case 2 -> 1.2f;
                case 3 -> 1.4f;
                default -> 1.0f;
            };
            float volume = switch (stage) {
                case 2 -> 0.9f;
                case 3 -> 1.0f;
                default -> 0.8f;
            };
            level.playSound(null, BlockPos.containing(player.getX(), player.getY(), player.getZ()),
                    ModSounds.SWORD_WAVE.get(), SoundSource.PLAYERS, volume, pitch);
        }

        if (stage < 3) {
            tag.putDouble("skill", stage + 1);
        } else {
            tag.putDouble("skill", 0);
        }
        tag.putLong("skill_tick", level.getGameTime());
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class ClientHandler {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.screen != null) return;

            ItemStack stack = mc.player.getMainHandItem();
            if (stack.getItem() instanceof TerraBladeItem) {
                if (stack.getOrCreateTag().getDouble("skill") >= 1) {
                    while (mc.options.keyAttack.consumeClick()) {
                        mc.player.swing(InteractionHand.MAIN_HAND);
                        ModNetwork.CHANNEL.sendToServer(new TerraBladeSwingPacket());
                    }
                }
            }
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.skill_name"));
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.desc1"));
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.desc2"));
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.desc3"));
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.desc4"));
        tooltip.add(Component.translatable("tooltip.pasterdream.terra_blade.desc5"));
    }
}
