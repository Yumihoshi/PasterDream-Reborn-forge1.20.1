package com.pasterdream.pasterdreammod.world.block.lostswordtomb;

import com.pasterdream.pasterdreammod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LostSwordTombBlockEntity extends BlockEntity {

    private int pullCount = 0;

    public LostSwordTombBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LOST_SWORD_TOMB.get(), pos, state);
    }

    public int getPullCount() {
        return pullCount;
    }

    public void incrementPullCount() {
        this.pullCount++;
        setChanged();
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        pullCount = tag.getInt("PullCount");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("PullCount", pullCount);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("PullCount", pullCount);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
