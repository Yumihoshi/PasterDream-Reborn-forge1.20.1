package com.pasterdream.pasterdreammod.mixin;

import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureBlockEntity.class)
public class StructureBlockEntityMixin
{
    @ModifyConstant(method = "load", constant = @Constant(intValue = 48))
    private int increaseSizeLimit(int original)
    {
        return 2147483647;
    }

    @ModifyConstant(method = "load", constant = @Constant(intValue = -48))
    private int decreaseOffsetLimit(int original)
    {
        return -2147483648;
    }
}
