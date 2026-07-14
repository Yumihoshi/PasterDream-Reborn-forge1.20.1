package com.pasterdream.pasterdreammod.datagen.common;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends ForgeAdvancementProvider {

    public ModAdvancementProvider(PackOutput output,
                                  CompletableFuture<HolderLookup.Provider> registries,
                                  ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new StoryAdvancements()));
    }

    public static class StoryAdvancements implements AdvancementGenerator {

        @Override
        public void generate(HolderLookup.Provider registries,
                             Consumer<Advancement> saver,
                             ExistingFileHelper existingFileHelper) {

            // ========== 根进度：帕斯特之梦 ==========
            Advancement root = Advancement.Builder.advancement()
                    .display(
                            ModItems.MELT_DREAM_CRYSTAL_FRAGMENT.get(),
                            Component.translatable("advancements.pasterdream.story.root.title"),
                            Component.translatable("advancements.pasterdream.story.root.description"),
                            ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,
                                    "textures/screens/guiblock.png"),
                            FrameType.TASK,
                            false, false, false
                    )
                    .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                    .save(saver, ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,
                            "story/root"), existingFileHelper);

            // ========== 子进度：纯洁无暇 ==========
            Advancement pureAndFlawless = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.WHITE_COROLLA.get(),
                            Component.translatable("advancements.pasterdream.story.pure_and_flawless.title"),
                            Component.translatable("advancements.pasterdream.story.pure_and_flawless.description"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_white_corolla", InventoryChangeTrigger.TriggerInstance.hasItems(
                            ModItems.WHITE_COROLLA_CROP_AGE_1.get()))
                    .save(saver, ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,
                            "story/pure_and_flawless"), existingFileHelper);

            // ========== 子进度：使用苍白骨针（纯洁无暇的子进度） ==========
            Advancement.Builder.advancement()
                    .parent(pureAndFlawless)
                    .display(
                            ModItems.PALE_BONENEEDLE.get(),
                            Component.translatable("advancements.pasterdream.story.use_pale_boneneedle.title"),
                            Component.translatable("advancements.pasterdream.story.use_pale_boneneedle.description"),
                            null,
                            FrameType.TASK,
                            true, true, false
                    )
                    .addCriterion("used_boneneedle", new ImpossibleTrigger.TriggerInstance())
                    .save(saver, ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID,
                            "story/use_pale_boneneedle"), existingFileHelper);
        }
    }
}
