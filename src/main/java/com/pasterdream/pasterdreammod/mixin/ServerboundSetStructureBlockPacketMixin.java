package com.pasterdream.pasterdreammod.mixin;

import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerboundSetStructureBlockPacket.class)
public class ServerboundSetStructureBlockPacketMixin
{
    @ModifyConstant(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", constant = @Constant(intValue = 48))
    private int increaseSizeLimit(int original)
    {
        return 2147483647;
    }

    @ModifyConstant(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", constant = @Constant(intValue = -48))
    private int decreaseOffsetMin(int original)
    {
        return -2147483648;
    }
}
