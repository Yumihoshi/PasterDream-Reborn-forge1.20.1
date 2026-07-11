package com.pasterdream.pasterdreammod.world.item.curio;

import com.pasterdream.pasterdreammod.capability.meltdreamenergy.MeltDreamEnergyHelper;
import com.pasterdream.pasterdreammod.init.ModEntities;
import com.pasterdream.pasterdreammod.init.ModSounds;
import com.pasterdream.pasterdreammod.world.entity.FoxFireEntity;
import com.pasterdream.pasterdreammod.world.item.ModRarities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class KaichuOmamoriItem extends Item implements ICurioItem {

    private static final double ENERGY_COST = 5.0;
    private static final int COOLDOWN_TICKS = 8 * 20; // 8 seconds

    public KaichuOmamoriItem() {
        super(new Item.Properties().stacksTo(1).rarity(ModRarities.LEGENDARY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            double currentEnergy = MeltDreamEnergyHelper.getPlayerMeltDreamEnergy(serverPlayer);
            if (currentEnergy < ENERGY_COST) return InteractionResult.FAIL;

            MeltDreamEnergyHelper.addPlayerMeltDreamEnergyAndSync(serverPlayer, -ENERGY_COST);
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

            BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
            FoxFireEntity foxFire = new FoxFireEntity(ModEntities.FOX_FIRE.get(), level);
            foxFire.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            foxFire.setOwner(player);
            level.addFreshEntity(foxFire);

            level.playSound(null, pos, ModSounds.FOX_FIRE.get(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.foxfire"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.vulnerable"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.regen"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.duration"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.energy_cost"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.effect.cooldown"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.equip"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.immune"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.luck"));
        list.add(Component.translatable("tooltip.pasterdream.kaichu_omamori.flavor"));
    }

}
