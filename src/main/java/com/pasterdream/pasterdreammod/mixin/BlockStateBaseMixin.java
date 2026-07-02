package com.pasterdream.pasterdreammod.mixin;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin
{
    @Inject(method = "isSolid", at = @At("HEAD"), cancellable = true)
    private void pasterdream$forceSolid(CallbackInfoReturnable<Boolean> cir)
    {
        BlockState self = (BlockState) (Object) this;
        if (self.is(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "fluid_can_not_brush_nocollission_block"))))
        {
            cir.setReturnValue(true);
        }
    }
}
