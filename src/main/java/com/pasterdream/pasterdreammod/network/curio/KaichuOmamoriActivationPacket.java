package com.pasterdream.pasterdreammod.network.curio;

import com.pasterdream.pasterdreammod.world.item.curio.KaichuOmamoriItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KaichuOmamoriActivationPacket {

    public KaichuOmamoriActivationPacket() {}

    public static void encode(KaichuOmamoriActivationPacket message, FriendlyByteBuf buffer) {}

    public static KaichuOmamoriActivationPacket decode(FriendlyByteBuf buffer) {
        return new KaichuOmamoriActivationPacket();
    }

    public static void handle(KaichuOmamoriActivationPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                // 从玩家视线方向获取目标方块位置
                BlockHitResult hit = (BlockHitResult) player.pick(5.0, 1.0F, false);
                BlockPos pos;
                if (hit.getType() != HitResult.Type.MISS) {
                    pos = hit.getBlockPos().relative(hit.getDirection());
                } else {
                    // 如果没有瞄准方块，则在玩家前方
                    pos = BlockPos.containing(
                            player.getX() + player.getLookAngle().x * 2,
                            player.getY() + player.getEyeHeight() + player.getLookAngle().y * 2,
                            player.getZ() + player.getLookAngle().z * 2);
                }
                KaichuOmamoriItem.tryActivate(player, player.level(), pos);
            }
        });
        context.get().setPacketHandled(true);
    }
}
