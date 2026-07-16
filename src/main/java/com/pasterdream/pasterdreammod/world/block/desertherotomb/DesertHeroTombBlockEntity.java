package com.pasterdream.pasterdreammod.world.block.desertherotomb;

import com.pasterdream.pasterdreammod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DesertHeroTombBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Per-player quest state
    private final Map<UUID, PlayerProgress> playerProgressMap = new HashMap<>();

    // Delayed message scheduling
    private int tickCounter = 0;
    private int messagePhase = 0;
    @Nullable
    private UUID interactingPlayerUUID = null;

    public DesertHeroTombBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DESERT_HERO_TOMB.get(), pos, state);
    }

    // region: Per-player progress

    public static class PlayerProgress {
        private int questNumber = 0;
        private boolean task1Complete = false;
        private boolean task2Complete = false;
        private boolean task3Complete = false;

        public int getQuestNumber() { return questNumber; }
        public void setQuestNumber(int n) { this.questNumber = n; }

        public boolean isTask1Complete() { return task1Complete; }
        public void setTask1Complete(boolean v) { this.task1Complete = v; }

        public boolean isTask2Complete() { return task2Complete; }
        public void setTask2Complete(boolean v) { this.task2Complete = v; }

        public boolean isTask3Complete() { return task3Complete; }
        public void setTask3Complete(boolean v) { this.task3Complete = v; }

        public boolean allTasksComplete() {
            return task1Complete && task2Complete && task3Complete;
        }

        CompoundTag toNbt() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("Number", questNumber);
            tag.putBoolean("Task1", task1Complete);
            tag.putBoolean("Task2", task2Complete);
            tag.putBoolean("Task3", task3Complete);
            return tag;
        }

        static PlayerProgress fromNbt(CompoundTag tag) {
            PlayerProgress p = new PlayerProgress();
            p.questNumber = tag.getInt("Number");
            p.task1Complete = tag.getBoolean("Task1");
            p.task2Complete = tag.getBoolean("Task2");
            p.task3Complete = tag.getBoolean("Task3");
            return p;
        }
    }

    private PlayerProgress getOrCreateProgress(Player player) {
        return playerProgressMap.computeIfAbsent(player.getUUID(), uuid -> new PlayerProgress());
    }

    @Nullable
    private PlayerProgress getProgress(Player player) {
        return playerProgressMap.get(player.getUUID());
    }

    // Delegate getters (require player context)
    public int getQuestNumber(Player player) {
        PlayerProgress p = getProgress(player);
        return p != null ? p.getQuestNumber() : 0;
    }

    public void setQuestNumber(Player player, int questNumber) {
        getOrCreateProgress(player).setQuestNumber(questNumber);
        setChanged();
        syncToClient();
    }

    public boolean isTask1Complete(Player player) {
        PlayerProgress p = getProgress(player);
        return p != null && p.isTask1Complete();
    }

    public void setTask1Complete(Player player, boolean v) {
        getOrCreateProgress(player).setTask1Complete(v);
        setChanged();
        syncToClient();
    }

    public boolean isTask2Complete(Player player) {
        PlayerProgress p = getProgress(player);
        return p != null && p.isTask2Complete();
    }

    public void setTask2Complete(Player player, boolean v) {
        getOrCreateProgress(player).setTask2Complete(v);
        setChanged();
        syncToClient();
    }

    public boolean isTask3Complete(Player player) {
        PlayerProgress p = getProgress(player);
        return p != null && p.isTask3Complete();
    }

    public void setTask3Complete(Player player, boolean v) {
        getOrCreateProgress(player).setTask3Complete(v);
        setChanged();
        syncToClient();
    }

    public boolean allTasksComplete(Player player) {
        PlayerProgress p = getProgress(player);
        return p != null && p.allTasksComplete();
    }

    // endregion

    // region: Delayed message scheduling

    public void startDelayedMessages(Player player) {
        this.tickCounter = 1;
        this.messagePhase = 0;
        this.interactingPlayerUUID = player.getUUID();
        setChanged();
    }

    public void startDelayedMessagesPhase1(Player player) {
        this.tickCounter = 1;
        this.messagePhase = 1;
        this.interactingPlayerUUID = player.getUUID();
        setChanged();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DesertHeroTombBlockEntity be) {
        if (be.tickCounter > 0) {
            be.tickCounter++;
            DesertHeroTombHandler.processDelayedMessages(level, pos, be);
            int maxTick = be.messagePhase == 0 ? 90 : 40;
            if (be.tickCounter > maxTick) {
                be.tickCounter = 0;
                be.messagePhase = 0;
                be.interactingPlayerUUID = null;
            }
            be.setChanged();
        }
    }

    @Nullable
    public Player getInteractingPlayer(Level level) {
        if (interactingPlayerUUID == null) return null;
        return level.getPlayerByUUID(interactingPlayerUUID);
    }

    public int getTickCounter() {
        return tickCounter;
    }

    public int getMessagePhase() {
        return messagePhase;
    }

    // endregion

    // region: Animation

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle", 0, this::idleController));
        controllers.add(new AnimationController<>(this, "procedure", 0, this::procedureController));
    }

    private PlayState idleController(AnimationState<DesertHeroTombBlockEntity> state) {
        state.getController().setAnimation(RawAnimation.begin().thenLoop("0"));
        return PlayState.CONTINUE;
    }

    private PlayState procedureController(AnimationState<DesertHeroTombBlockEntity> state) {
        BlockState blockState = this.getBlockState();
        int animValue = 0;
        if (blockState.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty ip) {
            animValue = blockState.getValue(ip);
        }

        if (animValue != 0 && state.getController().getAnimationState() == AnimationController.State.STOPPED) {
            state.getController().setAnimation(RawAnimation.begin().thenPlay(String.valueOf(animValue)));
            if (state.getController().getAnimationState() == AnimationController.State.STOPPED) {
                if (blockState.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty ip) {
                    level.setBlock(this.getBlockPos(), blockState.setValue(ip, 0), 3);
                }
                state.getController().forceAnimationReset();
            }
        } else if (animValue == 0) {
            return PlayState.STOP;
        }
        return PlayState.CONTINUE;
    }

    // endregion

    // region: NBT & Sync

    private void syncToClient() {
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        CompoundTag progressMapTag = new CompoundTag();
        for (Map.Entry<UUID, PlayerProgress> entry : playerProgressMap.entrySet()) {
            progressMapTag.put(entry.getKey().toString(), entry.getValue().toNbt());
        }
        tag.put("PlayerProgressMap", progressMapTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        playerProgressMap.clear();
        CompoundTag progressMapTag = tag.getCompound("PlayerProgressMap");
        for (String key : progressMapTag.getAllKeys()) {
            try {
                UUID uuid = UUID.fromString(key);
                playerProgressMap.put(uuid, PlayerProgress.fromNbt(progressMapTag.getCompound(key)));
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        // Don't include per-player data in update tag (privacy + bandwidth)
        return super.getUpdateTag();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // endregion
}
