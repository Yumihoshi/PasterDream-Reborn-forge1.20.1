package com.pasterdream.pasterdreammod.world.block.meltdreamcrystalchest;

import com.pasterdream.pasterdreammod.init.*;
import com.pasterdream.pasterdreammod.network.animationstatechange.AnimationStateChangePacket;
import com.pasterdream.pasterdreammod.world.block.geckolibblock.AnimatableSync;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class MeltDreamCrystalChestBlockEntity extends BlockEntity implements GeoBlockEntity, AnimatableSync
{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private ListTag lootTablesNbt = new ListTag();
    private int animationState = 0; //0:关闭,1:普通,2:稀有,3:传奇
    private int totalTicks = -1;
    private int tickCounter = 0;
    private ServerPlayer openingPlayer;
    private List<LootTableEntry> lootEntries = new ArrayList<>();

    public MeltDreamCrystalChestBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.MELT_DREAM_CRYSTAL_CHEST.get(), pos, state);
    }

    public record LootTableEntry(String lootTable, int weight, float luckMultiplier, int quality) {}

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet)
    {
        CompoundTag tag = packet.getTag();
        if (tag != null)
        {
            load(tag);
        }
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        animationState = tag.getInt("AnimationState");
        totalTicks = tag.getInt("TicksUntilOpen");
        lootEntries.clear();
        lootTablesNbt = tag.getList("LootTables", Tag.TAG_COMPOUND);
        spawnLootEntriesWithLootTablesNbt();
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.putInt("AnimationState", animationState);
        tag.putInt("TicksUntilOpen", totalTicks);
        tag.put("LootTables", lootTablesNbt);
    }

    public void setLootTablesNbt(ListTag list)
    {
        lootTablesNbt = list.copy();
        setChangedAndSync();
    }

    public void spawnLootEntriesWithLootTablesNbt()
    {
        for (int i = 0; i < lootTablesNbt.size(); i++)
        {
            CompoundTag entryTag = lootTablesNbt.getCompound(i);
            String lootTable = entryTag.getString("LootTable");
            int weight = entryTag.getInt("weight");
            float luckMult = entryTag.getFloat("luck_multiplier_increase");
            lootEntries.add(new LootTableEntry(lootTable, weight, luckMult, i + 1));
        }
    }

    public void openChest(ServerPlayer player)
    {
        if (animationState != 0)
        {   //已打开
            return;
        }

        Level level = this.level;
        if (level == null || level.isClientSide)
        {
            return;
        }

        //抽奖决定品质
        spawnLootEntriesWithLootTablesNbt();
        int quality = rollQuality(player);
        if (quality != 1 && quality != 2 && quality != 3)
        {
            throw new RuntimeException("quality应为1，2或3，但是此时quality为" + quality);
        }

        //设置动画状态，同步到客户端
        animationState = quality;
        setChangedAndSync();

        //根据品质计算延迟tick，然后弹出战利品并替换方块
        totalTicks = switch (quality)
        {
            case 1 -> 70;  //普通:3.52s
            case 2 -> 90;  //稀有:4.52s
            case 3 -> 110; //传奇:5.52s
            default -> 0;
        };

        openingPlayer = player;
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public static void tick(Level level, BlockPos blockPosition, BlockState blockState, MeltDreamCrystalChestBlockEntity blockEntity)
    {
        if (level.isClientSide)
        {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) level;

        if (blockEntity.totalTicks > 0)
        {
            switch(blockEntity.animationState)
            {
                case 1 ->
                {
                    switch (blockEntity.tickCounter)
                    {
                        case 0  -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 2F, 1F);
                        case 15 -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.5F, 1F);
                        case 25 -> blockEntity.spawnLoot(blockEntity.openingPlayer, blockEntity.animationState);
                        case 39 -> blockEntity.replaceWithOpenChest();
                    }
                }

                case 2 ->
                {
                    switch (blockEntity.tickCounter)
                    {
                        case 0  -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 2F, 1F);
                        case 19 -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 3F, 1.2F);
                        case 32 -> serverLevel.playSound(null, blockEntity.worldPosition, ModSounds.MELT_DREAM_CRYSTAL_CHEST_OPEN_RARE.get(), SoundSource.BLOCKS, 1.5F, 1F);
                        case 45 -> blockEntity.spawnLoot(blockEntity.openingPlayer, blockEntity.animationState);
                        case 61 -> blockEntity.replaceWithOpenChest();
                    }
                }

                case 3 ->
                {
                    switch (blockEntity.tickCounter)
                    {
                        case 0  -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 2F, 1F);
                        case 19 -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 3F, 1.2F);
                        case 40 -> serverLevel.playSound(null, blockEntity.worldPosition, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 6F, 1.6F);
                        case 57 -> serverLevel.playSound(null, blockEntity.worldPosition, ModSounds.MELT_DREAM_CRYSTAL_CHEST_OPEN_LEGEND.get(), SoundSource.BLOCKS, 1.5F, 1F);
                        case 65 -> blockEntity.spawnLoot(blockEntity.openingPlayer, blockEntity.animationState);
                        case 81 -> blockEntity.replaceWithOpenChest();
                    }
                }
            }

            blockEntity.tickCounter++;
        }
    }

    private int rollQuality(Player player)
    {
        List<LootTableEntry> entries = lootEntries;
        if (entries.isEmpty())
        {
            System.err.println("entries.isEmpty() == true");
            return 0;
        }

        float luck = 0f;
        if (player != null)
        {
            luck = (float) player.getAttributeValue(Attributes.LUCK);
        }

        NavigableMap<Double, Integer> weightMap = new TreeMap<>();
        double total = 0;
        for (LootTableEntry entry : entries)
        {
            //权重 = 基础权重 * (1 + 幸运值 * 倍率)
            double effectiveWeight = entry.weight() * (1.0 + luck * entry.luckMultiplier());
            total += effectiveWeight;
            weightMap.put(total, entry.quality());
        }

        double rand = level.getRandom().nextDouble() * total;
        return weightMap.ceilingEntry(rand).getValue();
    }

    private void spawnLoot(ServerPlayer player, int quality)
    {
        if (!(level instanceof ServerLevel serverLevel))
        {
            return;
        }

        List<LootTableEntry> entries = lootEntries;
        LootTableEntry selected = null;
        for (LootTableEntry e : entries)
        {
            if (e.quality() == quality)
            {
                selected = e;
                break;
            }
        }

        if (selected == null)
        {
            return;
        }

        ResourceLocation tableId = ResourceLocation.parse(selected.lootTable());
        LootTable lootTable = serverLevel.getServer().getLootData().getLootTable(tableId);
        LootParams params = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(worldPosition))
                .create(LootContextParamSets.CHEST);

        List<ItemStack> loot = lootTable.getRandomItems(params);
        double x = worldPosition.getX() + 0.5;
        double y = worldPosition.getY() + 1;
        double z = worldPosition.getZ() + 0.5;

        double ySpeed = switch(animationState)
        {
            case 1 -> 0.375;
            case 2 -> 0.5;
            case 3 -> 1;
            default-> 2147483647;
        };

        for (ItemStack stack : loot)
        {
            if (!stack.isEmpty())
            {
                ItemEntity itemEntity = new ItemEntity(level, x, y, z, stack);
                itemEntity.setDeltaMovement((level.random.nextDouble() - 0.5) * 0.05, ySpeed, (level.random.nextDouble() - 0.5) * 0.05);
                level.addFreshEntity(itemEntity);
            }
        }
    }

    private void replaceWithOpenChest()
    {
        if (level == null || level.isClientSide)
        {
            return;
        }

        BlockState currentState = getBlockState();
        Direction facing = currentState.getValue(MeltDreamCrystalChestBlock.FACING);
        BlockState openState = ModBlocks.OPENED_MELT_DREAM_CRYSTAL_CHEST.get().defaultBlockState().setValue(MeltDreamCrystalChestBlock.FACING, facing);

        CompoundTag toolTag = new CompoundTag();
        toolTag.put("LootTables", lootTablesNbt.copy());

        CompoundTag blockEntityTag = new CompoundTag();
        CompoundTag Inventory = new CompoundTag();

        ListTag items = new ListTag();
        CompoundTag slotTag = new CompoundTag();
        slotTag.putByte("Count", (byte) 1);
        slotTag.putInt("Slot", 4);
        slotTag.putString("id", "pasterdream:melt_dream_crystal_chest_reset_tool");
        slotTag.put("tag", toolTag);
        items.add(slotTag);

        Inventory.put("Items", items);
        Inventory.putInt("Size", 9);

        blockEntityTag.put("Inventory", Inventory);
        blockEntityTag.putString("id", "pasterdream:opened_melt_dream_crystal_chest");
        blockEntityTag.putInt("x", worldPosition.getX());
        blockEntityTag.putInt("y", worldPosition.getY());
        blockEntityTag.putInt("z", worldPosition.getZ());

        System.out.println("blockEntityTag = " + blockEntityTag);

        LevelChunk chunk = level.getChunkAt(worldPosition);

        chunk.setBlockState(worldPosition, openState, false);
        chunk.removeBlockEntity(worldPosition);
        BlockEntity blockEntity = BlockEntity.loadStatic(worldPosition, openState, blockEntityTag);
        if (blockEntity != null)
        {
            blockEntity.setLevel(level);
            chunk.setBlockEntity(blockEntity);
        }
        chunk.setUnsaved(true);

        level.sendBlockUpdated(worldPosition, currentState, openState, 2);
    }

    private void setChangedAndSync()
    {
        setChanged();
        if (level != null && !level.isClientSide)
        {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            sendAnimationSync();
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "state", 0, this::stateController));
    }

    private PlayState stateController(AnimationState<MeltDreamCrystalChestBlockEntity> state)
    {
        AnimationController<MeltDreamCrystalChestBlockEntity> controller = state.getController();

        if(animationState == 0)
        {
            controller.setAnimation(RawAnimation.begin().thenLoop("0"));
        }
        else
            {
                controller.setAnimation(RawAnimation.begin().thenPlay(String.valueOf(animationState)));
            }

        return PlayState.CONTINUE;
    }

    public void setAnimationState(int state)
    {
        this.animationState = state;
        if (level != null && !level.isClientSide)
        {
            sendAnimationSync();
        }
    }

    private void sendAnimationSync()
    {
        AnimationStateChangePacket packet = new AnimationStateChangePacket(this.worldPosition, this.animationState);
        ModNetwork.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(this.worldPosition)), packet);
    }

    public int getAnimationState()
    {
        return animationState;
    }
}
