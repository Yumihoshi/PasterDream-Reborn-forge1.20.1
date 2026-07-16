package com.pasterdream.pasterdreammod.datagen.lang;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.init.ModEntities;
import com.pasterdream.pasterdreammod.init.ModItems;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, PasterDreamMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.LIFE_CRYSTAL.get(), "Life Crystal");
        add("message.pasterdream.life_crystal.already_used", "You have already absorbed a Life Crystal!");
        add("message.pasterdream.life_crystal.absorbed", "The Life Crystal shatters and flows into you §aMax Health +2");
        add(ModItems.DYEDREAM_QUARTZ.get(), "Dye Dream Quartz");
        add(ModItems.DYEDREAM_DUST.get(), "Dye Dream Dust");
        add(ModItems.DYEDREAM_DUST_PIECE.get(), "Dye Dream Dust Piece");
        add(ModItems.AMBER_CANDY.get(), "Amber Candy");
        add(ModItems.PINK_SLIMEBALL.get(), "Pink Slimeball");
        add(ModItems.RAW_DYEDREAM_ALLOY_INGOT.get(), "Raw Dye Dream Alloy Ingot");
        add(ModItems.DYEDREAM_ALLOY_INGOT.get(), "Dye Dream Alloy Ingot");
        add(ModItems.TITANIUM_INGOT.get(), "Titanium Ingot");
        add(ModItems.TITANIUM_NUGGET.get(), "Titanium Nugget");
        add(ModItems.RAW_TITANIUM.get(), "Raw Titanium");
        add(ModItems.DYEDREAM_ALLOY_NUGGET.get(), "Dye Dream Alloy Nugget");
        add(ModItems.MORTAR.get(), "Mortar");
        add(ModItems.PLIERS.get(), "Pliers");
        add(ModItems.DYEDREAM_DYE.get(), "Dye Dream Dye");
        add(ModItems.DYEDREAM_BUD_NUGGET.get(), "Dye Dream Bud Nugget");
        add(ModItems.GLASS_JAR.get(), "Glass Jar");
        add(ModItems.GLASS_JAR_OF_WATER.get(), "Water Jar");
        add(ModItems.GLASS_JAR_OF_MILK.get(), "Milk Jar");
        add(ModItems.GLASS_JAR_OF_YEAST.get(), "Glass Jar of Yeast");
        add(ModItems.GLASS_JAR_OF_GUIDING_DRUG.get(), "Glass Jar of Guiding Drug");
        add(ModItems.GLASS_JAR_OF_WIND_PLANT_EXTRACT.get(), "Glass Jar of Wind Plant Extract");
        add(ModItems.GLASS_JAR_OF_DYEDREAM_PERFUME.get(), "Glass Jar of Dyedream Perfume");
        add(ModItems.GLASS_JAR_OF_DREAM_JUICE.get(), "Glass Jar of Dream Juice");
        add("tooltip.pasterdreammod.glass_jar_of_dream_juice", "§7§oA sweet and dreamy taste that melds you into the dream");
        add(ModItems.GLASS_JAR_OF_GOLDENROD_TEA.get(), "Glass Jar of Goldenrod Tea");
        add(ModItems.GLASS_JAR_OF_INK.get(), "Glass Jar of Ink");
        add(ModItems.FEATHER_PEN.get(), "Feather Pen");
        add(ModItems.MELT_DREAM_LIQUID_BUCKET.get(), "Meltdream Liquid Bucket");
        add(ModItems.SHADOW_LIQUID_BUCKET.get(), "Shadow Liquid Bucket");
        add(ModItems.GLASS_CUP.get(), "Glass Cup");
        add(ModItems.GLASS_CUP_OF_APPLE_JUICE.get(), "Glass Cup of Apple Juice");
        add(ModItems.GLASS_CUP_OF_COOKED_DYEDREAM_FLOWER_TEA.get(), "Glass Cup of Cooked Dyedream Flower Tea");
        add(ModItems.GLASS_CUP_OF_DYEDREAM_JUICE.get(), "Glass Cup of Dyedream Juice");
        add(ModItems.GLASS_CUP_OF_HONEY_JUICE.get(), "Glass Cup of Honey Juice");
        add(ModItems.GLASS_CUP_OF_UNCOOKED_DYEDREAM_FLOWER_TEA.get(), "Glass Cup of Uncooked Dyedream Flower Tea");
        add(ModItems.GLASS_CUP_OF_WATERMELON_JUICE.get(), "Glass Cup of Watermelon Juice");
        add(ModItems.FLOUR.get(), "Flour");
        add(ModItems.DOUGH.get(), "Dough");
        add(ModItems.RYE_SEED.get(), "Rye Seed");
        add(ModItems.DOUGH_WITH_EGG.get(), "Dough with Egg");
        add(ModItems.COARSE_SALT.get(), "Coarse Salt");
        add(ModItems.SALT.get(), "Salt");
        add(ModItems.PINK_EGG.get(), "Pink Egg");
        add(ModItems.DYEDREAM_FRUIT.get(), "Dyedream Fruit");
        add(ModItems.FIG.get(), "Fig");
        add(ModItems.CHOCOLATE.get(), "Chocolate");
        add(ModItems.CAKE_BASE.get(), "Cake Base");
        add(ModItems.CREAM_BUN_CAKE.get(), "Cream Bun Cake");
        add(ModItems.BERRY_BUN_CAKE.get(), "Berry Bun Cake");
        add(ModItems.TUBER_BUN_CAKE.get(), "Tuber Bun Cake");
        add(ModItems.WATERMELON_BUN_CAKE.get(), "Watermelon Bun Cake");
        add(ModItems.PUMPKIN_BUN_CAKE.get(), "Pumpkin Bun Cake");
        add(ModItems.GLOW_BERRY_BUN_CAKE.get(), "Glow Berry Bun Cake");
        add(ModItems.DYEDREAM_FRUIT_BUN_CAKE.get(), "Dyedream Fruit Bun Cake");
        add(ModItems.CHOCOLATE_MATCHA_CAKE.get(), "Chocolate Matcha Cake");
        add(ModItems.MAGIC_STONE.get(), "Magic Stone");
        add(ModItems.RICE_CAKE.get(), "Rice Cake");
        add("item.pasterdream.rice_cake.describe", "A lingering spirit of desert might be interested in this...");
        add(ModItems.DYEDREAM_POPSICLE.get(), "Dyedream Popsicle");
        add(ModItems.FRIED_EGG.get(), "Fried Egg");
        add(ModItems.BACON_AND_EGG.get(), "Bacon and Egg");
        add(ModItems.ODD_BACON_AND_EGG.get(), "Odd Bacon and Egg");
        add(ModItems.HEART_CHOCOLATE.get(), "Heart Chocolate");
        add(ModItems.WHITE_HEART_CHOCOLATE.get(), "White Heart Chocolate");
        add(ModItems.PINK_HEART_CHOCOLATE.get(), "Pink Heart Chocolate");
        add(ModItems.BREAD_SLICE.get(), "Bread Slice");
        add(ModItems.SWISS_ROLL.get(), "Swiss Roll");
        add(ModItems.SANDWICH.get(), "Sandwich");
        add(ModItems.WAFER_BISCUIT.get(), "Wafer Biscuit");
        add(ModItems.STUFFED_WAFER_COOKIES.get(), "Stuffed Wafer Cookies");
        add(ModItems.GINGERBREAD_MAN.get(), "Gingerbread Man");
        add(ModItems.CANDY_CANE.get(), "Candy Cane");
        add(ModItems.POPPING_CANDY.get(), "Popping Candy");
        add(ModItems.YINHUL_COTTON_CANDY.get(), "Yinhul Cotton Candy");
        add(ModItems.MELT_DREAM_COTTON_CANDY.get(), "Melt Dream Cotton Candy");
        add(ModItems.BUBBLE_GUM.get(), "Bubble Gum");
        add(ModItems.LIGHT_ORGAN.get(), "Light Organ");
        add(ModItems.JELLYFISH_MUD.get(), "Jellyfish Mud");
        add(ModItems.JELLYFISH_JELLO.get(), "Jellfish Jello");
        add(ModItems.QUEER_SOUP.get(), "Queer Soup");
        add(ModItems.LEGENDARY_DRAGON_HORN_ICE_CREAM.get(), "Legendary Dragon Horn Ice Cream");
        add("item.pasterdream.legendary_dragon_horn_ice_cream.describe.0", "§9Permanently +10 Luck (until death)");
        add("item.pasterdream.legendary_dragon_horn_ice_cream.describe.1", "§9Effect does not stack");
        add("item.pasterdream.legendary_dragon_horn_ice_cream.client.success", "§eThe taste feels familiar, as if it came from Snow Tears Cold... wait, what?");
        add("item.pasterdream.legendary_dragon_horn_ice_cream.client.fail", "§7You have already tasted this ice cream flavor");
        add(ModItems.ELIXIR_BOTTLE.get(), "Elixir Bottle");
        add(ModItems.ELIXIR_BOTTLE_OF_MELT_DREAM.get(), "Elixir Bottle of Melt Dream");
        add(ModItems.ELIXIR_BOTTLE_OF_RAGE_ELIXIR.get(), "Elixir Bottle of Rage Elixir");
        add(ModItems.BUBBLE_TEA.get(), "Bubble Tea");
        add(ModItems.SWEET_DREAM_MUSIC_DISC.get(), "Music Disc");
        add(ModItems.SWEET_DREAM_MUSIC_DISC.get().getDescriptionId() + ".desc", "PasterDream - Sweet Dream");
        add(ModItems.SNOWFALL_DREAM_MUSIC_DISC.get(), "Music Disc");
        add(ModItems.SNOWFALL_DREAM_MUSIC_DISC.get().getDescriptionId() + ".desc", "PasterDream - Snowfall Dream");
        add(ModItems.PINEAPPLE_LOVE_SEA.get(), "Pineapple Love Sea");
        add(ModItems.BLUE_HEART_OF_THE_SEA.get(), "Blue Heart Of The Sea");
        add(ModItems.ELDER_GUARDIAN_SCALE.get(), "Elder Guardian Scale");
        add(ModItems.RED_DEW.get(), "Red Dew");
        add(ModItems.BLUE_DEW.get(), "Blue Dew");
        add(ModItems.DYEDREAM_COROLLA.get(), "Dyedream Corolla");
        add(ModItems.WHITE_COROLLA.get(), "White Corolla");
        add(ModItems.WHITE_CRYSTAL.get(), "White Crystal");
        add("tooltip.pasterdream.white_crystal", "§7Core material for crafting the White Calamity Sword");
        add(ModItems.LIGHT_BALL.get(), "Light Ball");
        add(ModItems.COTTON.get(), "Cotton");
        add(ModItems.SCULK_HEART.get(), "Sculk Heart");
        add(ModItems.BLACK_STICK.get(), "Black Stick");
        add(ModItems.SPOOL.get(), "Spool");
        add(ModItems.FABRIC.get(), "Fabric");
        add(ModItems.PROTECT_DECK.get(), "Protect Deck");
        add(ModItems.TITANIUM_UPGRADE.get(), "Titanium Upgrade");
        add(ModItems.SCULK_UPGRADE.get(), "Sculk Upgrade");
        add(ModItems.DYEDREAM_UPGRADE.get(), "Dyedream Upgrade");
        add(ModItems.DEEP_SEA_TREASURE.get(), "Deep Sea Treasure");
        add(ModItems.DYEDREAM_DEEP_TREASURE.get(), "Dyedream Deep Treasure");
        add(ModItems.ATTACK_ENHANCE_STONE.get(), "Attack Enhance Stone");
        add(ModItems.LUCK_ENHANCE_STONE.get(), "Luck Enhance Stone");
        add(ModItems.COPPER_SWORD.get(), "Copper Sword");
        add(ModItems.COPPER_PICKAXE.get(), "Copper Pickaxe");
        add(ModItems.COPPER_AXE.get(), "Copper Axe");
        add(ModItems.COPPER_SHOVEL.get(), "Copper Shovel");
        add(ModItems.COPPER_HOE.get(), "Copper Hoe");
        add(ModItems.COPPER_HELMET.get(), "Copper Helmet");
        add(ModItems.COPPER_CHESTPLATE.get(), "Copper Chestplate");
        add(ModItems.COPPER_LEGGINGS.get(), "Copper Leggings");
        add(ModItems.COPPER_BOOTS.get(), "Copper Boots");
        add(ModItems.TITANIUM_SWORD.get(), "Titanium Sword");
        add(ModItems.TITANIUM_PICKAXE.get(), "Titanium Pickaxe");
        add(ModItems.TITANIUM_AXE.get(), "Titanium Axe");
        add(ModItems.TITANIUM_SHOVEL.get(), "Titanium Shovel");
        add(ModItems.TITANIUM_HOE.get(), "Titanium Hoe");
        add(ModItems.MOLTEN_GOLD_SWORD.get(), "Molten Gold Sword");
        add(ModItems.MOLTEN_GOLD_PICKAXE.get(), "Molten Gold Pickaxe");
        add(ModItems.MOLTEN_GOLD_AXE.get(), "Molten Gold Axe");
        add(ModItems.MOLTEN_GOLD_SHOVEL.get(), "Molten Gold Shovel");
        add(ModItems.MOLTEN_GOLD_HOE.get(), "Molten Gold Hoe");
        add(ModItems.HELLFIRE_SWORD.get(), "Hellfire Sword");
        add(ModItems.HELLFIRE_PICKAXE.get(), "Hellfire Pickaxe");
        add(ModItems.MELT_DREAM_PICKAXE.get(), "Melt Dream Pickaxe");
        add(ModItems.MELT_DREAM_AXE.get(), "Melt Dream Axe");
        add(ModItems.MELT_DREAM_SHOVEL.get(), "Melt Dream Shovel");
        add(ModItems.MELT_DREAM_HOE.get(), "Melt Dream Hoe");
        add(ModItems.INFERNO_SWORD.get(), "Inferno Sword");
        add(ModItems.DYEDREAM_SWORD.get(), "Dyedream Sword");
        add(ModItems.DYEDREAM_PICKAXE.get(), "Dyedream Pickaxe");
        add(ModItems.DYEDREAM_AXE.get(), "Dyedream Axe");
        add(ModItems.DYEDREAM_SHOVEL.get(), "Dyedream Shovel");
        add(ModItems.DYEDREAM_HOE.get(), "Dyedream Hoe");
        add(ModItems.DYEDREAM_HAMMER.get(), "Dyedream Hammer");
        add(ModItems.SHARP_MELT_DREAM_SWORD.get(), "Dyedream Sharp Sword");
        add(ModItems.TIDE_SWORD.get(), "Tide Sword");
        add(ModItems.BEIHAI_RUO_TIDE_SWORD.get(), "Beihai Ruo Tide Sword");
        add(ModItems.KUSANAGI.get(), "Kusanagi");
        add(ModItems.MURAKUMO_KUSANAGI.get(), "Murakumo Kusanagi");
        add(ModItems.SWORD_EMBRYO.get(), "Sword Embryo");
        add(ModItems.THERMAL_DAGGER.get(), "Thermal Dagger");
        add(ModItems.DESERT_SWORD.get(), "Desert Greatsword");
        add(ModItems.CHENJINGMEN_DESERT_SWORD.get(), "Chenjingmen Desert");
        add(ModItems.BROKEN_HERO_SWORD.get(), "Broken Hero Sword");
        add(ModItems.TITANIUM_HELMET.get(), "Titanium Helmet");
        add(ModItems.TITANIUM_CHESTPLATE.get(), "Titanium Chestplate");
        add(ModItems.TITANIUM_LEGGINGS.get(), "Titanium Leggings");
        add(ModItems.TITANIUM_BOOTS.get(), "Titanium Boots");
        add(ModItems.SCULK_HELMET.get(), "Sculk Helmet");
        add(ModItems.SCULK_CHESTPLATE.get(), "Sculk Chestplate");
        add(ModItems.SCULK_LEGGINGS.get(), "Sculk Leggings");
        add(ModItems.SCULK_BOOTS.get(), "Sculk Boots");
        add(ModItems.DYEDREAM_HELMET.get(), "Dyedream Helmet");
        add(ModItems.DYEDREAM_CHESTPLATE.get(), "Dyedream Chestplate");
        add(ModItems.DYEDREAM_LEGGINGS.get(), "Dyedream Leggings");
        add(ModItems.DYEDREAM_BOOTS.get(), "Dyedream Boots");
        add(ModItems.DREAM_FERTILIZER.get(), "Dream Fertilizer");
        add(ModItems.DREAM_NOTES_DYEDREAM_WORLD.get(), "Dream Notes - Dyedream World");
        add(ModItems.DREAM_NOTES_LAMP_SHADOW_WORLD.get(), "Dream Notes - Lamp Shadow World");
        add(ModItems.DREAM_NOTES_WIND_JOURNEY_WORLD.get(), "Dream Notes - Wind Journey World");
        add(ModItems.BLUE_PRINT.get(), "Blue Print");
        add(ModItems.REED_ROD.get(), "Reed Rod");
        add(ModItems.MELT_DREAM_COIN.get(), "Melt Dream Coin");
        add(ModItems.MELT_DREAM_COIN_PILE.get(), "Melt Dream Coin Pile");
        add(ModItems.JUNGLE_SPORE.get(), "Jungle Spore");
        add(ModItems.PERGAMYN.get(), "Pergamyn");

        add(ModBlocks.DYEDREAM_DIRT.get(), "Dye Dream Dirt");
        add(ModBlocks.DYEDREAM_GRASS_BLOCK.get(), "Dye Dream Grass Block");
        add(ModBlocks.DYEDREAM_FARMLAND.get(), "Dye Dream Farmland");
        add(ModBlocks.DYEDREAM_LOG.get(), "Dye Dream Log");
        add(ModBlocks.DYEDREAM_WOOD.get(), "Dye Dream Wood");
        add(ModBlocks.DYEDREAM_LEAVES.get(), "Dye Dream Leaves");
        add(ModBlocks.DYEDREAM_WORLDTREE_LEAVES.get(), "Dye Dream Worldtree Leaves");
        add(ModBlocks.DYEDREAM_SAPLING.get(), "Dye Dream Sapling");
        add(ModBlocks.DYEDREAM_QUARTZ_ORE.get(), "Dye Dream Quartz Ore");
        add(ModBlocks.DYEDREAM_DUST_ORE.get(), "Dye Dream Dust Ore");
        add(ModBlocks.AMBER_CANDY_ORE.get(), "Amber Candy Ore");
        add(ModBlocks.TITANIUM_ORE.get(), "Titanium Ore");
        add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), "Deepslate Titanium Ore");
        add(ModBlocks.MOLTEN_GOLD_ORE.get(), "Molten Gold Ore");
        add(ModBlocks.SOUL_ORE.get(), "Soul Ore");
        add(ModBlocks.RAW_TITANIUM_BLOCK.get(), "Raw Titanium Block");
        add(ModBlocks.SALT_BLOCK.get(), "Salt Block");
        add(ModBlocks.TITANIUM_BLOCK.get(), "Titanium Block");
        add(ModBlocks.MOLTEN_GOLD_BLOCK.get(), "Molten Gold Block");
        add(ModBlocks.CHARGED_AMETHYST_BLOCK.get(), "Charged Amethyst Block");
        add(ModBlocks.DYEDREAM_QUARTZ_BLOCK.get(), "Dye Dream Quartz Block");
        add(ModBlocks.SMOOTH_DYEDREAM_QUARTZ_BLOCK.get(), "Smooth Dye Dream Quartz Block");
        add(ModBlocks.BRICKS_DYEDREAM_QUARTZ_BLOCK.get(), "Dye Dream Quartz Bricks");
        add(ModBlocks.PILLAR_DYEDREAM_QUARTZ_BLOCK.get(), "Dye Dream Quartz Pillar");
        add(ModBlocks.CHISELED_DYEDREAM_QUARTZ_BLOCK.get(), "Chiseled Dye Dream Quartz Block");
        add(ModBlocks.DYEDREAM_QUARTZ_BLOCK_STAIRS.get(), "Dye Dream Quartz Stairs");
        add(ModBlocks.DYEDREAM_QUARTZ_BLOCK_SLAB.get(), "Dye Dream Quartz Slab");
        add(ModBlocks.DYEDREAM_QUARTZ_BLOCK_WALL.get(), "Dye Dream Quartz Wall");
        add(ModBlocks.DYEDREAM_PLANKS.get(), "Dye Dream Planks");
        add(ModBlocks.DYEDREAM_STAIRS.get(), "Dye Dream Stairs");
        add(ModBlocks.DYEDREAM_SLAB.get(), "Dye Dream Slab");
        add(ModBlocks.DYEDREAM_FENCE.get(), "Dye Dream Fence");
        add(ModBlocks.DYEDREAM_FENCE_GATE.get(), "Dye Dream Fence Gate");
        add(ModBlocks.DYEDREAM_PANE.get(), "Dye Dream Pane");
        add(ModBlocks.DYEDREAM_DOOR.get(), "Dye Dream Door");
        add(ModBlocks.DYEDREAM_TRAPDOOR.get(), "Dye Dream Trapdoor");
        add(ModBlocks.DYEDREAM_PRESSURE_PLATE.get(), "Dye Dream Pressure Plate");
        add(ModBlocks.DYEDREAM_BUTTON.get(), "Dye Dream Button");
        add(ModBlocks.PINK_SLIME_BLOCK.get(), "Pink Slime Block");
        add(ModBlocks.PINK_MUSHROOM_BLOCK.get(), "Pink Mushroom Cap");
        add(ModBlocks.PINK_MUSHROOM_STEM.get(), "Pink Mushroom Stem");
        add(ModBlocks.PINK_MUSHROOM_PORES.get(), "Pink Mushroom Pores");
        add(ModBlocks.PINK_SHROOMLIGHT.get(), "Pink Shroomlight");
        add(ModBlocks.PINK_MUSHROOM.get(), "Pink Mushroom");
        add(ModBlocks.TALL_PINK_MUSHROOM.get(), "Tall Pink Mushroom");
        add(ModBlocks.DYEDREAM_SAND.get(), "Dye Dream Sand");
        add(ModBlocks.DYEDREAM_GLASS.get(), "Dye Dream Glass");
        add(ModBlocks.DYEDREAM_GLASS_PANE.get(), "Dye Dream Glass Pane");
        add(ModBlocks.CARVE_DYEDREAM_GLASS.get(), "Carved Dye Dream Glass");
        add(ModBlocks.CARVE_DYEDREAM_GLASS_PANE.get(), "Carved Dye Dream Glass Pane");
        add(ModBlocks.GOLD_CARVE_DYEDREAM_GLASS.get(), "Gilded Carved Dye Dream Glass");
        add(ModBlocks.GOLD_CARVE_DYEDREAM_GLASS_PANE.get(), "Gilded Carved Dye Dream Glass Pane");
        add(ModBlocks.DYEDREAM_BUDDING_BLOCK.get(), "Budding Dye Dream");
        add(ModBlocks.SMALL_DYEDREAM_BUD.get(), "Small Dye Dream Bud");
        add(ModBlocks.MEDIUM_DYEDREAM_BUD.get(), "Medium Dye Dream Bud");
        add(ModBlocks.LARGE_DYEDREAM_BUD.get(), "Large Dye Dream Bud");
        add(ModBlocks.DYEDREAM_BUD_BLOCK.get(), "Dye Dream Bud Block");
        add(ModBlocks.DYEDREAM_BUD_STAIRS.get(), "Dye Dream Bud Stairs");
        add(ModBlocks.DYEDREAM_BUD_SLAB.get(), "Dye Dream Bud Slab");
        add(ModBlocks.DYEDREAM_BUD_WALL.get(), "Dye Dream Bud Wall");
        add(ModBlocks.DYEDREAM_ICE.get(), "Dye Dream Ice");
        add(ModBlocks.DYEDREAM_PACKED_ICE.get(), "Dye Dream Packed Ice");
        add(ModBlocks.CLOUD.get(), "Cloud");
        add(ModBlocks.DARK_CLOUD.get(), "Dark Cloud");
        add(ModBlocks.BIG_BUBBLE.get(), "Big Bubble");
        add(ModBlocks.DYEDREAM_CRYSTAL_LANTERN.get(), "Dyedream Crystal Lantern");
        add(ModBlocks.DYEDREAM_LANTERN.get(), "Dye Dream Lantern");
        add(ModBlocks.DYEDREAM_ALLOY_BLOCK.get(), "Dye Dream Alloy Block");
        add(ModBlocks.POLISHED_CALCITE.get(), "Polished Calcite");
        add(ModBlocks.POLISHED_CALCITE_STAIRS.get(), "Polished Calcite Stairs");
        add(ModBlocks.POLISHED_CALCITE_SLAB.get(), "Polished Calcite Slab");
        add(ModBlocks.POLISHED_CALCITE_WALL.get(), "Polished Calcite Wall");
        add(ModBlocks.CALCITE_TILES.get(), "Calcite Tiles");
        add(ModBlocks.CALCITE_TILES_STAIRS.get(), "Calcite Tiles Stairs");
        add(ModBlocks.CALCITE_TILES_SLAB.get(), "Calcite Tiles Slab");
        add(ModBlocks.CALCITE_TILES_WALL.get(), "Calcite Tiles Wall");
        add(ModBlocks.ICE_STONE.get(), "Icestone");
        add(ModBlocks.ICE_BUD.get(), "Ice Bud");
        add(ModBlocks.QYM_DOLL.get(), "琴雨梦Doll");
        add(ModBlocks.UUZ_DOLL.get(), "幼幼紫Doll");
        add(ModBlocks.DYEDREAM_CRACK.get(), "Dyedream Crack");
        add(ModBlocks.CLAYPAN.get(), "Claypan");
        add(ModBlocks.DREAM_CAULDRON.get(), "Dream Cauldron");
        add(ModBlocks.DYEDREAM_DESK.get(), "Dyedream Desk");
        add(ModBlocks.SHADOW_DESK.get(), "Shadow Desk");
        add(ModBlocks.PICNIC_BASKET.get(), "Picnic Basket");
        add(ModBlocks.SHADOW_CHEST.get(), "Shadow Chest");
        add(ModBlocks.WIND_MOOR_CRATE.get(), "Wind Moor Crate");
        add(ModBlocks.THE_ENDLESS_BOOK_OF_DREAM_SEEKERS.get(), "The Endless Book Of Dream Seekers");
        add(ModBlocks.RESEARCH_TABLE.get(), "Research Table");
        add(ModBlocks.LOST_SWORD_TOMB.get(), "Lost Sword Tomb");
        add(ModBlocks.GOLDEN_FOX_SCULPTURE.get(), "Golden Fox Sculpture");
        add(ModBlocks.FOX_SCULPTURE.get(), "Fox Sculpture");
        add(ModBlocks.DESERT_HERO_TOMB.get(), "Desert Hero Tomb");
        add(ModBlocks.MELT_DREAM_CRYSTAL_CHEST.get(), "Melt Dream Crystal Chest");
        add(ModBlocks.OPENED_MELT_DREAM_CRYSTAL_CHEST.get(), "Opened Melt Dream Crystal Chest");

        add(ModBlocks.DYEDREAM_COROLLA_CROP.get(), "Dyedream Corolla");
        add(ModItems.DYEDREAM_COROLLA_CROP_AGE_1.get(), "Mature Dyedream Corolla");
        add(ModBlocks.WHITE_COROLLA_CROP.get(), "White Corolla Crop");
        add(ModItems.WHITE_COROLLA_CROP_AGE_1.get(), "Mature White Corolla Crop");
        add(ModBlocks.LIGHT_BALL_CROP.get(), "Light Ball Crop");
        add(ModItems.LIGHT_BALL_CROP_AGE_1.get(), "Mature Light Ball Crop");
        add(ModBlocks.CLOUD_CROP.get(), "Cloud Crop");
        add(ModItems.CLOUD_CROP_AGE_1.get(), "Mature Cloud Crop");
        add(ModBlocks.COTTON_CROP.get(), "Cotton Crop");
        add(ModItems.COTTON_CROP_AGE_1.get(), "Mature Cotton Crop");

        add(ModBlocks.GOLDENROD.get(), "Goldenrod");
        add(ModBlocks.FERRARIA_CRISPA.get(), "Ferraria Crispa");
        add(ModBlocks.EUSTOMA.get(), "Eustoma");
        add(ModBlocks.MALVA_SINENSIS_CAVAN.get(), "Malva Sinensis Cavan");
        add(ModBlocks.LINHT_FLOWER.get(), "Linht Flower");
        add(ModBlocks.DREAMING_LOTUS.get(), "Dreaming Lotus");
        add(ModBlocks.MISTY_DREAMING_LOTUS.get(), "Misty Dreaming Lotus");
        add(ModBlocks.DYEDREAM_LILY_OF_THE_VALLEY.get(), "Dyedream Lily of the Valley");
        add(ModBlocks.BLAZE_FLOWER.get(), "Blaze Flower");
        add(ModBlocks.WHITE_ORCHID_FLOWER.get(), "White Orchid Flower");
        add(ModBlocks.EDELWEISS.get(), "Edelweiss");
        add(ModBlocks.NIPPY_EDELWEISS.get(), "Nippy Edelweiss");
        add(ModBlocks.DYEDREAM_LILY_PAD.get(), "Dye Dream Lily Pad");
        add(ModBlocks.DYEDREAM_LOTUS.get(), "Dye Dream Lotus");

        add(ModBlocks.DYEDREAM_MOSS.get(), "Dyedream Moss");
        add(ModBlocks.STEM_GRASS.get(),"Stem Grass");
        add(ModBlocks.TALL_STEM_GRASS.get(),"Tall Stem Grass");
        add(ModBlocks.SINGULARITY_FERN.get(), "Singularity Fern");
        add(ModBlocks.CRIMSON_THORNS.get(), "Crimson Thorns");
        add(ModBlocks.OATS.get(), "oats");
        add(ModBlocks.RYE.get(), "Rye");
        add(ModBlocks.POLISHED_CALCITE_STALICRIPE.get(), "Polished Calcite Stalicripe");
        add(ModBlocks.SMALL_POLISHED_CALCITE_STALICRIPE.get(), "Small Polished Calcite Stalicripe");
        add(ModBlocks.DYEDREAM_SEAGRASS.get(), "Dyedream Seagrass");
        add(ModBlocks.REED.get(), "Reed");
        add(ModBlocks.DYEDREAM_VINE.get(), "Dyedream Vine");
        add(ModBlocks.JUNGLE_SPORANGIUM.get(), "Jungle Sporangium");
        add(ModBlocks.FOURLEAF_CLOVER.get(),"fourleaf clover");

        add(ModItems.MELT_DREAM_CRYSTAL_FRAGMENT.get(), "Melt Dream Crystal Fragment");
        add(ModItems.MELT_DREAM_AURORIAN_STEEL.get(), "Melt Dream Aurorian Steel");
        add("tooltip.pasterdreammod.melt_dream_aurorian_steel", "§7§oKids, did you ask?");


        add(ModItems.DEBUG_SWORD.get(), "Debug Sword");
        add(ModItems.LOOT_GENERATOR.get(), "Loot Generator");
        add(ModItems.MELT_DREAM_CRYSTAL_CHEST_RESET_TOOL.get(), "Melt Dream Crystal Chest Reset Tool");
        add(ModBlocks.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_0.get(), "Model Break Particle Provider Block 0");
        add(ModBlocks.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_1.get(), "Model Break Particle Provider Block 1");
        add(ModBlocks.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_2.get(), "Model Break Particle Provider Block 2");
        add(ModBlocks.MODEL_BREAK_PARTICLE_PROVIDER_BLOCK_3.get(), "Model Break Particle Provider Block 3");

        add("fluid.pasterdream.melt_dream_liquid","Melt Dream Liquid");
        add("fluid.pasterdream.shadow_liquid","Shadow Liquid");

        add("fluid.pasterdream.apple_juice","Apple Juice(fluid)");
        add("fluid.pasterdream.dream_juice","Dream Juice(fluid)");
        add("fluid.pasterdream.dyedream_flower_tea","Dyedream Flower Tea(fluid)");
        add("fluid.pasterdream.dyedream_juice","Dyedream Juice(fluid)");
        add("fluid.pasterdream.dyedream_perfume","Dyedream Perfume(fluid)");
        add("fluid.pasterdream.goldenrod_tea","Goldenrod Tea(fluid)");
        add("fluid.pasterdream.guiding_drug","Guiding Drug(fluid)");
        add("fluid.pasterdream.honey_juice","Honey Juice(fluid)");
        add("fluid.pasterdream.rage_elixir","Rage Elixir(fluid)");
        add("fluid.pasterdream.uncooked_dyedream_flower_tea","Uncooked Dyedream Flower Tea(fluid)");
        add("fluid.pasterdream.watermelon_juice","Watermelon Juice(fluid)");
        add("fluid.pasterdream.wind_plant_extract","Wind Plant Extract(fluid)");
        add("fluid.pasterdream.yeast","Yeast(fluid)");
        add("fluid.pasterdream.ink","Ink(fluid)");

        add("itemGroup.pasterdream.pasterdream_food_tab", "Paster Dream | Food & Drinks");
        add("itemGroup.pasterdream.pasterdream_items_tab", "Paster Dream | Items");
        add("itemGroup.pasterdream.pasterdream_gear_tab", "Paster Dream | Gear");
        add("itemGroup.pasterdream.pasterdream_blocks_tab", "Paster Dream | Blocks");
        add("itemGroup.pasterdream.pasterdream_plants_tab", "Paster Dream | Plants");
        add("itemGroup.pasterdream.pasterdream_equipment_tab", "Paster Dream | Equipment");
        add("itemGroup.pasterdream.pasterdream_dream_notes_tab", "Paster Dream | Dream Notes");
        add("itemGroup.pasterdream.pasterdream_dream_debug_tab", "Paster Dream | Debug");
        add("itemGroup.pasterdream.pasterdream_expansion_tab", "Paster Dream | Expansion");
        add("itemGroup.pasterdream.pasterdream_memento_tab", "Paster Dream | Mementos");

        add("item.pasterdream.duke_coin_curio", "Duke Coin Curio");
        add("tooltip.pasterdream.duke_coin_curio.effect.luck", "§7▪ §9+7 Luck");
        add("tooltip.pasterdream.duke_coin_curio.effect.blink_cd", "§7▪ §c+7 Blink Cooldown");
        add("tooltip.pasterdream.duke_coin_curio.flavor", "§7§o-- 7 Days to Die");

        add("button.pasterdream.mortarbutton", "grinding");
        add("button.pasterdream.copy_button", "Copy");
        add("button.pasterdream.research_button", "Research");

        add("tooltip.pasterdreammod.magic_stone", "§7§oWhat kind of mage doesn't carry a few magic stones?");
        add("tooltip.pasterdreammod.goldenrod_tea", "§dContinuously removes Hunger and Nausea");
        add("tooltip.pasterdreammod.dyedream_perfume", "§dClears insomnia and prevents phantom attacks");
        add("tooltip.pasterdreammod.dyedream_perfume.flavor", "§7§oWhen you're wondering why perfume is meant to be drunk,\nmaybe you should first consider: are you dreaming?");
        add("tooltip.pasterdreammod.melt_dream_crystal_fragment", "§7All creatures with souls in this world have dreams\nThose fleeting memories that cannot be recalled upon waking\nShatter and melt, buried across the world, condensing into crystals\nWaiting to be discovered by new souls");
        add("tooltip.pasterdreammod.upgrade_kit", "§7Used at the smithing table to upgrade equipment Can preserve the original enhancement properties of the equipment");
        add("tooltip.pasterdream.enhance_stone.usage", "§7Used to enhance synthesis embryos at the Weapon Workshop");
        add("tooltip.pasterdream.thermal_dagger.swim", "§7▪ §9+0.5 Swim Speed when in main hand");
        add("tooltip.pasterdream.thermal_dagger", "§7§o-- Subnautica");
        add("tooltip.pasterdream.deep_treasure", "§7Splashing waves push us into the distance");
        add("tooltip.pasterdream.attack_enhance_stone.effect", "§7▪ §9Attack Damage +0.1~+0.5");
        add("tooltip.pasterdream.luck_enhance_stone.effect", "§7▪ §9Luck +1~+2");
        add("tooltip.pasterdream.右键打开GUI", "§7Press right mouse button to open GUI");
        add("tooltip.pasterdream.此方块仅用于提供粒子效果，生存模式无法获取", "§7This block only use to provide particle effect, Can't get on Survival mode");
        add("tooltip.pasterdream.左键实体：直接删除此实体", "§7Left Button Press Entity: Delete This Entity");
        add("tooltip.pasterdream.左键方块：模拟破坏此方块", "§7Left Button Press Block: Simulate Destroy This Block");
        add("tooltip.pasterdream.右键实体：在聊天框打印此实体NBT", "§7Right Button Press Entity: Print This Entity's NBT In The Chat Box");
        add("tooltip.pasterdream.右键方块：在聊天框打印此方块BlockState和NBT", "§7Right Button Press Block: Print This Entity's BlockState And NBT In The Chat Box");
        add("tooltip.pasterdream.用于生成战利品，对着箱子点击右键以生成", "§7Used to generate loot, right-click on the chest to generate it");
        add("tooltip.pasterdream.当前设置战利品表：", "§7Current loot table settings:");
        add("tooltip.pasterdream.loot_table.not_set", "§7No set");
        add("tooltip.pasterdream.loot_generator.usage.shift", "§7Use Shift + Right Click on the container to generate loot in the container");

        add("message.pasterdream.loot_generator.no_loot_table", "§cLoot generator has no loot table set");
        add("message.pasterdream.loot_generator.not_container", "§cTarget block is not a container");
        add("message.pasterdream.loot_generator.loot_table_not_found", "§cLoot table %s does not exist");
        add("message.pasterdream.loot_generator.loot_table_set", "§aContainer cleared and loot table set: %s, will generate when opened");
        add("option.pasterdream.Q: 模拟无工具破坏", "Q: Simulate Break Without Tool");
        add("option.pasterdream.W: 模拟下界合金镐破坏", "W: Simulate Break With Netherite Pickaxe");
        add("option.pasterdream.E: 模拟下界合金镐时运III破坏", "E: Simulate Break With Fortune III Netherite Pickaxe");
        add("option.pasterdream.A: 模拟下界合金镐精准采集破坏", "A: Simulate Break With Silk Touch Netherite Pickaxe");
        add("option.pasterdream.S: 获取对应的BlockItem并掉落，并将方块设置为空气", "S: Get Corresponding BlockItem And Drop, And Set Block To Air");
        add("option.pasterdream.D: 将方块设置为空气的同时不触发方块更新", "D: Set The Block To Air Without NeighborChange");

        add("tooltip.pasterdream.normal_loot_table", "§3Normal Loot Table：%s，Weight: %d，Luck Multiplier Increase: %f");
        add("tooltip.pasterdream.rare_loot_table", "§aRare Loot Table：%s，Weight: %d，Luck Multiplier Increase: %f");
        add("tooltip.pasterdream.legend_loot_table", "§dLegend Loot Table：%s，Weight: %d，Luck Multiplier Increase: %f");
        add("tooltip.pasterdream.not_have_loot_table", "§cNot Have Loot Table");
        add("tooltip.pasterdream.按住Shift+右键点击打开的融梦水晶箱可复位（写入此物品NBT中包含的战利品列表）", "§7Press Shift And Right Mouse Button Click Opened Melt Dream Crystal Chest Can Reset(Write LootTable From NBT Which In This Item)");

        add("message.pasterdream.融梦水晶箱重置工具只能重置打开的融梦水晶箱", "Melt Dream Crystal Chest Reset Tool Only Can Reset Opened Melt Dream Crystal Chest");

        add("effect.pasterdream.sculk_armor_buff", "Sculk Echo");
        add("effect.pasterdream.dyedream_armor_buff", "Dyedream Protection");
        add("effect.pasterdream.dyedream_up_buff", "Dyedream Tool Boost");
        add("tooltip.pasterdreammod.sculk_armor.1", "§7Set Bonus: §2Sculk Echo");
        add("tooltip.pasterdreammod.sculk_armor.2", "§7▪ §9Max Health +4");
        add("tooltip.pasterdreammod.sculk_armor.3", "§7▪ §9Enhanced when below Y=0");
        add("tooltip.pasterdreammod.sculk_armor.4", "§7▪ §9Immune to Sculk's Darkness");
        add("tooltip.pasterdreammod.sculk_armor.5", "§7▪ §9Gain Speed and Resistance when hit by Darkness");
        add("tooltip.pasterdreammod.dyedream_hammer", "§7▪ §9Area Mining 3×3×3");
        add("tooltip.pasterdreammod.molten_gold_sword.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.molten_gold_sword.2", "§7▪ §9Sets target on fire for 3 seconds");
        add("tooltip.pasterdreammod.molten_gold_tool.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.molten_gold_tool.2", "§7▪ §9Right-click to consume hunger for Haste");
        add("tooltip.pasterdreammod.molten_gold_tool.3", "§7▪ §9Cooldown: 10s");
        add("tooltip.pasterdreammod.hellfire_sword.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.hellfire_sword.2", "§7▪ §9Ignites target for 4 seconds");
        add("tooltip.pasterdreammod.hellfire_sword.3", "§7▪ §9If already burning, adds 2 seconds (stackable)");
        add("tooltip.pasterdreammod.hellfire_tool.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.hellfire_tool.2", "§7▪ §9Right-click to consume hunger for Haste");
        add("tooltip.pasterdreammod.hellfire_tool.3", "§7▪ §9Cooldown: 8s");
        add("tooltip.pasterdreammod.inferno_sword.1", "Inlaid: §bNether Star");
        add("tooltip.pasterdreammod.inferno_sword.2", "Combat Art: §6Bone-melting Slash");
        add("tooltip.pasterdreammod.inferno_sword.3", "§7▪ §9Converts damage type to Lava");
        add("tooltip.pasterdreammod.inferno_sword.4", "§7▪ §9Deals 2+ATK+FireSeconds*0.6 bonus damage");
        add("tooltip.pasterdreammod.inferno_sword.5", "§7▪ §9If fire >10s, reduces target speed by 30%");
        add("tooltip.pasterdreammod.inferno_sword.6", "§7▪ §9Clears target's fire effect");
        add("tooltip.pasterdreammod.inferno_sword.7", "§7▪ §9Cooldown: 10s");
        add("tooltip.pasterdreammod.inferno_sword.8", "§7Passive:");
        add("tooltip.pasterdreammod.inferno_sword.9", "§7▪ §9Ignites 4s; if burning, adds 3s");
        add("tooltip.pasterdreammod.melt_dream_tool.1", "While held");
        add("tooltip.pasterdreammod.melt_dream_tool.2", "§7▪ §bMelt-Repair: 0.01E/1 Durability");
        add("tooltip.pasterdreammod.dyedream_armor.1", "§7Set Bonus: §dDyedream Protection");
        add("tooltip.pasterdreammod.dyedream_armor.2", "§7▪ §9Max Health +4");
        add("tooltip.pasterdreammod.dyedream_armor.3", "§7▪ §9Gain 2 Absorption hearts every 30s");
        add("tooltip.pasterdreammod.dyedream_armor.4", "§7▪ §9Enhances wielded Dyedream tools");
        add("tooltip.pasterdreammod.sharp_melt_dream_sword.1", "Inlaid: §dMelt Dream Crystal Fragment");
        add("tooltip.pasterdreammod.sharp_melt_dream_sword.2", "Combat Art: §bSora-giri");
        add("tooltip.pasterdreammod.sharp_melt_dream_sword.3", "§7▪ §9Slash and launch enemies upward, deals 2+ATK*1.5 bonus damage");
        add("tooltip.pasterdreammod.sharp_melt_dream_sword.4", "§7▪ §9Cooldown: 5s");
        add("tooltip.pasterdreammod.sharp_melt_dream_sword.5", "§7▪ §9Cooldown reduced to 3s while Dyedream Protection is active");
        add("tooltip.pasterdreammod.tide_sword.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.tide_sword.2", "§7▪ §9Grants Water Breathing while held");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.1", "Inlaid: §3Heart of the Sea");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.2", "Combat Art: §3Surging Current");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.3", "§7▪ §9Dash forward in water, 80% damage reduction during dash");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.4", "§7▪ §9Triggers on-hit when target is in water");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.5", "§7▪ §9Deals 3+ATK*1.2 bonus damage");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.6", "§7▪ §9Cooldown: 2.5s");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.7", "§7Passive:");
        add("tooltip.pasterdreammod.beihai_ruo_tide_sword.8", "§7▪ §9Grants Water Breathing while held");
        add("tooltip.pasterdreammod.kusanagi.1", "Souls: §7%d/200");
        add("tooltip.pasterdreammod.kusanagi.2", "§7▪ §9Applies Poison on hit");
        add("tooltip.pasterdreammod.kusanagi.evolve", "The weapon has grown to a new stage");
        add("tooltip.pasterdreammod.murakumo_kusanagi.1", "Inlaid: §7200 Souls");
        add("tooltip.pasterdreammod.murakumo_kusanagi.2", "Combat Art: §bExtreme Sharpness");
        add("tooltip.pasterdreammod.murakumo_kusanagi.3", "§7▪ §9Triggers on first hit against a target");
        add("tooltip.pasterdreammod.murakumo_kusanagi.4", "§7▪ §9Deals 7+Sharpness*BaseATK/2 bonus damage");
        add("tooltip.pasterdreammod.murakumo_kusanagi.5", "§7▪ §9Cooldown: 4s");
        add("tooltip.pasterdreammod.murakumo_kusanagi.6", "§7Passive:");
        add("tooltip.pasterdreammod.murakumo_kusanagi.7", "§7▪ §9Applies longer Poison on hit");
        add("tooltip.pasterdreammod.desert_sword.1", "Inlaid: §7None");
        add("tooltip.pasterdreammod.desert_sword.2", "§7▪ §9Move Speed -15% and Resistance +20% while held");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.1", "Inlaid: §eLonely Family Letter");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.2", "Combat Art: §eDesperate Counterattack");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.3", "§7▪ §9Grants brief Absorption when skill is activated");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.4", "§7▪ §9Deals 5+(LostHP%*2+1)*ATK bonus damage on hit");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.5", "§7▪ §9Cooldown: 10s");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.6", "Passive:");
        add("tooltip.pasterdreammod.chenjingmen_desert_sword.7", "§7▪ §9Move Speed -15% and Resistance +20% while held");
        add("tooltip.pasterdreammod.san_value", "SAN: ");
        add("tooltip.pasterdreammod.melt_dream_energy", "Melt Dream Energy: ");

        add("tooltip.pasterdream.developerNameList", "§dDeveloper Name List");
        add("tooltip.pasterdream.dyedreamCreak", "§dDyedream Creak");
        add("tooltip.pasterdream.dyedreamWorld", "§dDyedream World");
        add("tooltip.pasterdream.pinkSlime", "§dPink Slime");
        add("tooltip.pasterdream.whiteCorolla", "§dWhite Corolla");
        add("tooltip.pasterdream.paleBoneNeedle", "§dPale Bone Needle");
        add("tooltip.pasterdream.dreamFertilizer", "§dDream Fertilizer");
        add("tooltip.pasterdream.dreamAccumulator", "§dDream Accumulator");

        add("tooltip.pasterdream.theLurkersInTheShadow", "§8The Lurkers In The Shadow");
        add("tooltip.pasterdream.infestedChurch", "§8Infested Church");
        add("tooltip.pasterdream.depositionShadow", "§8Deposition Shadow");
        add("tooltip.pasterdream.shadowTravelogue", "§8Shadow Travelogue");
        add("tooltip.pasterdream.shadowDungeon", "§8Shadow Dungeon");
        add("tooltip.pasterdream.scare", "§8Scare");

        add("tooltip.pasterdream.notHaveWingsBirdHaveSpreadWingsDreamToo", "§aNot Have Wings Bird Have Spread Wings Dream Too");

        add("tooltip.pasterdream.wraponWorkshop", "§bWeapon Work Shop");

        add("biome.pasterdream.dyedream_plains","Dyedream Plains");
        add("biome.pasterdream.dyedream_mushroom_mountains","Dyedream Mushroom Mountains");
        add("biome.pasterdream.dyedream_snowy_plains","Dyedream Snowy Plains");
        add("biome.pasterdream.dyedream_frozen_ocean","Dyedream Frozen Ocean");

        add("recipe.pasterdream.copy", "Copy");
        add("recipe.pasterdream.research", "Research");

        add("book.pasterdream.title.花园解密:迷梦冶梦莲", "Garden Decode: Misty Dreaming Lotus");
        add("book.pasterdream.title.花园解密:凛冽雪绒花", "Garden Decode: Piercing Edel Weiss");
        add("book.pasterdream.title.花园解密:九尾狐", "Garden Decode: Golden Fox");
        add("book.pasterdream.title.染梦游记 其一", "Dyedream Travelogue 1");
        add("book.pasterdream.title.染梦游记 其二", "Dyedream Travelogue 2");
        add("book.pasterdream.title.染梦游记 其三", "Dyedream Travelogue 3");
        add("book.pasterdream.title.染梦教堂 其一", "Dream Church 1");
        add("book.pasterdream.title.染梦教堂 其二", "Dream Church 2");
        add("book.pasterdream.title.染梦教堂 其三", "Dream Church 3");
        add("book.pasterdream.title.染梦水晶球", "Dyedream Crystal Ball");
        add("book.pasterdream.title.祈愿树", "Dream Wishing Tree");
        add("book.pasterdream.title.温暖的“寒风”", "Warm Cold Wind");
        add("book.pasterdream.title.粉顶蘑菇屋", "Pink Agalic House");
        add("book.pasterdream.title.融梦涌泉井", "Melt Dream Liquid Well");
        add("book.pasterdream.title.浮空岛日记", "Floating Island Diary");
        add("book.pasterdream.title.落叶归根 裂荚归冠", "Falling Leaves Return To Their Roots, Cracked Pods Return To Their Crowns");
        add("book.pasterdream.title.梦境漂泊", "Dream Floating");
        add("book.pasterdream.title.气泡生态球", "Big Bubble");
        add("book.pasterdream.title.染梦实验室", "Dream Laboratory");
        add("book.pasterdream.title.来往于梦", "Interacting With Dream");
        add("book.pasterdream.title.染梦世界树", "Dyedream World Tree");
        add("book.pasterdream.title.代达罗斯之翼与浮空岛", "Wings Of 代达罗斯 And Floating Island");
        add("book.pasterdream.title.探求秘辛", "Desert Cottage");
        add("book.pasterdream.title.研究笔记:黑金属", "Research Notes: Black Metal");
        add("book.pasterdream.title.灯影游记 其二", "Lamp Shadow Travelogue 2");
        add("book.pasterdream.title.黑暗之地", "Shadow Place");
        add("book.pasterdream.title.阴影小木屋", "Shadow Fungus House");
        add("book.pasterdream.title.阴影地下工作室", "Shadow Underground Workroom");
        add("book.pasterdream.title.>被阴影浸染的字迹潦草的笔记<", ">Shadow Dyed Sloppy Handwriting Notes<");
        add("book.pasterdream.title.关于黑暗之手的随笔", "Regarding Shadow Hands Essay");
        add("book.pasterdream.title.与黑色双手的决战和败北", "Decisive Battle With Shadow Hands And Fail");
        add("book.pasterdream.title.破风的骑士", "Wind Break Knight");
        add("book.pasterdream.title.清晨的新风", "Morning New Wind");

        // Dew Tooltip
        add("tooltip.pasterdream.drink_effect", "§7When consumed:");
        add("tooltip.pasterdream.red_dew.effect", "§7▪ §9Instant Health I");
        add("tooltip.pasterdream.blue_dew.effect", "§7▪ §9Water Breathing I (1:00)");

        add("tooltip.pasterdream.melt_dream_coin", "§7Right-click with 10+ to combine into a coin pile");
        add("tooltip.pasterdream.melt_dream_coin_pile", "§7Right-click to split into 10 Melt Dream Coins");
        add("tooltip.pasterdream.melt_dream_coin.value", "§7▪ §6Face Value : ");

        add("tooltip.pasterdream.pliers.1", "§7▪ §9Can harvest crops and plants");
        add("tooltip.pasterdream.pliers.2", "§7▪ §9Sneak + right-click to collect blocks in plier_plants tag");

        // Broken Note
        add(ModItems.BROKEN_NOTE.get(), "Broken Note");
        add(ModItems.UNKNOWN_NOTE.get(), "Unknown Note");
        add(ModItems.PALE_BONENEEDLE.get(), "Pale Boneneedle");
        add(ModItems.ROOTS_PALE_BONENEEDLE.get(), "Roots Pale Boneneedle");
        add("tooltip.pasterdream.broken_note", "§7We still need to learn more...");
        add("tooltip.pasterdream.unknown_note.1", "Unknown Content");
        add("tooltip.pasterdream.unknown_note.2", "§7Needs to be analyzed at a §eResearch Table");

        // Pale Boneneedle
        add("tooltip.pasterdream.pale_boneneedle", "§7Awakens you from dreams...");
        add("tooltip.pasterdream.roots_pale_boneneedle.1", "§7Awakens you from dreams... and returns to the marked location");
        add("tooltip.pasterdream.roots_pale_boneneedle.2", "§7Sneak + right-click to set a waypoint");
        add("tooltip.pasterdream.roots_pale_boneneedle.3", "§7If no waypoint is set, returns to spawn by default");
        add("message.pasterdream.roots_pale_boneneedle.waypoint_set", "Waypoint recorded");

        //Other Items
        add(ModItems.SAND_OF_TIME.get(), "Time of Sand");
        add("message.pasterdream.sand_of_time.cooldown", "This Sand of Time is resting after a hard day of work and needs %s seconds to recover");
        add("tooltip.pasterdream.sand_of_time.1", "§7Right click to change through the world's day and night");
        add("tooltip.pasterdream.sand_of_time.2", "§7This item will go into cooldown for %s §7seconds after use");
        add("tooltip.pasterdream.sand_of_time.3", "§7§o-- The hourglass will remember the time we forgot");
        add("tooltip.pasterdream.sand_of_time.4", "%s seconds remaining");
        add("tooltip.pasterdream.sand_of_time.5", "§aReady to use");
        add(ModItems.STORAGE_BAG.get(), "Storage Bag");
        add(ModItems.LARGE_STORAGE_BAG.get(), "Large Storage Bag");
        add(ModItems.DREAM_HARP_OF_WANDERER.get(), "DreamHarp of Wanderer");
        add("message.pasterdream.dream_harp_of_wanderer.no_energy", "Not enough Melt Dream Energy");
        add("tooltip.pasterdream.dream_harp_of_wanderer.line1", "§7Plays to provide effects to players within 10 blocks (1:00):");
        add("tooltip.pasterdream.dream_harp_of_wanderer.line2", "§7▪ §9Max HP+4, restore 4 HP, Speed+10%");
        add("tooltip.pasterdream.dream_harp_of_wanderer.line3", "§7▪ §9+4.8 SAN Aura, immediately restore 5 SAN");
        add("tooltip.pasterdream.dream_harp_of_wanderer.line4", "§7▪ §9Cooldown: 30s");
        add("tooltip.pasterdream.dream_harp_of_wanderer.line5", "§7▪ §4Melt Dream Energy Cost: 3");
        add("tooltip.pasterdream.storage_bag.tooltip", "§7A regular bag with 9 storage slots");
        add("tooltip.pasterdream.large_storage_bag.tooltip1", "§7Use Ender powers to expand the bag's storage, giving it 25 slots");


        // Custom Rarity
        add("rarity.pasterdream.common", "Common");
        add("rarity.pasterdream.excellent", "Excellent");
        add("rarity.pasterdream.superior", "Superior");
        add("rarity.pasterdream.master", "Master");
        add("rarity.pasterdream.ancient", "Ancient");
        add("rarity.pasterdream.epic", "Epic");
        add("rarity.pasterdream.legendary", "Legendary");
        add("rarity.pasterdream.miracle", "Miracle");
        add("tooltip.pasterdream.quality", "Quality: %s %s");

        // Charms
        add(ModItems.EMBRYO_CHARM.get(), "Embryo Charm");
        add(ModItems.GOLD_CHARM.get(), "Gold Charm");
        add("tooltip.pasterdream.gold_charm.piglin", "§7▪ §9Makes piglins neutral");
        add(ModItems.ENDEYE_CHARM.get(), "Endeye Charm");
        add("tooltip.pasterdream.endeye_charm.enderman", "§7▪ §9Makes endermen neutral");
        add(ModItems.SEA_CHARM.get(), "Sea Charm");
        add(ModItems.CARAPAX_CHARM.get(), "Carapax Charm");
        add(ModItems.WORLDTREE_SEEDPOD.get(), "Worldtree Seedpod");
        add(ModItems.GARLAND.get(), "Garland");
        add(ModItems.BLESSING_OF_CECILIA.get(), "Blessing of Cecilia");
        add("tooltip.pasterdream.blessing_of_cecilia.effect0", "§7▪ §9Passive: Forced 20% DMG Reduction");
        add("tooltip.pasterdream.blessing_of_cecilia.effect1", "§7 Upon receiving fatal damage:");
        add("tooltip.pasterdream.blessing_of_cecilia.effect2", "§7▪ §9Grants 5s of damage immunity");
        add("tooltip.pasterdream.blessing_of_cecilia.effect3", "§7▪ §9And grants 10s of Resistance V, Regeneration X, Absorption V and Speed II");
        add("tooltip.pasterdream.blessing_of_cecilia.lore", "§7§o--I will protect you, forever and ever...");
        add(ModItems.FADED_BLESSING_OF_CECILIA.get(), "Faded Blessing of Cecilia");
        add("tooltip.pasterdream.faded_blessing_of_cecilia.effect0", "§7▪ §9Passive: Resistance I");
        add("tooltip.pasterdream.faded_blessing_of_cecilia.hint", "§7Perhaps we should submerge it in Melt Dream Spring");
        add(ModItems.FOURLEAF_CLOVER_CURIO.get(), "Fourleaf Clover");
        add("tooltip.pasterdream.fourleaf_clover_curio.flavor", "§7§oWhich leaf represents luck?");
        add(ModItems.SNOW_VOW_HEAD.get(), "Snow Vow");
        add("tooltip.pasterdream.snow_vow_head.effect.area", "§7▪ §9Grants nearby players within 7 blocks an effect");
        add(ModItems.QYM_BUTTERFLY_STAR_HAIRPIN.get(), "QYM's Butterfly Star Hairpin");
        add("tooltip.pasterdream.qym_butterfly_star_hairpin.effect", "§7▪ §9All mobs will not actively attack you");
        add("tooltip.pasterdream.qym_butterfly_star_hairpin.lore", "§7§oIf you aim for the stars in the night sky, even if you get lost, you are still among them.");
        add(ModItems.HIYORI_BUTTERFLY_HAIRPIN.get(), "Hiyori Butterfly Hairpin");
        add("tooltip.pasterdream.hiyori_butterfly_hairpin.lore", "§7§o-- Hamidashi, therefore Creative");
        add(ModItems.ALLKINDS_RING.get(), "Allkinds Ring");
        add("tooltip.pasterdream.allkinds_ring.flavor", "§7§oGather the power of all things, forge this ring");
        add("tooltip.pasterdream.allkinds_ring.lore", "§7§o-- Szuperlina Deonne");
        add("tooltip.pasterdream.allkinds_ring.skill_cd", "§7▪ §9-5% Skill Cooldown");
        add("tooltip.pasterdream.allkinds_ring.skill_dmg", "§7▪ §9+5% Skill Damage");
        add("tooltip.pasterdream.allkinds_ring.blink_cd", "§7▪ §9-5% Blink Cooldown");
        add(ModItems.LIGHT_BUTTERFLY_CURIO.get(), "Light Butterfly Curio");
        add("tooltip.pasterdream.light_butterfly_curio.effect", "§7▪ §9Grants Night Vision in low light");
        add("tooltip.pasterdream.light_butterfly_curio.energy", "§7▪ §4Melt Dream Energy cost: 0.24/min");
        add(ModItems.BRIGHT_BUTTERFLY_CURIO.get(), "Bright Butterfly Curio");
        add("tooltip.pasterdream.bright_butterfly_curio.effect.night_vision", "§7▪ §9Grants Night Vision in low light");
        add("tooltip.pasterdream.bright_butterfly_curio.effect.darkness_immune", "§7▪ §9Immune to Darkness effect");
        add(ModItems.BOBOJI_CURIO.get(), "Boboji's Gorgeous Feather");
        add("tooltip.pasterdream.boboji_curio.stat.speed", "§7▪ §9+5% Speed");
        add("tooltip.pasterdream.boboji_curio.stat.range", "§7▪ §9+0.1 Blink Range");
        add("tooltip.pasterdream.boboji_curio.stat.cd", "§7▪ §9-0.2 Blink Cooldown");
        add("tooltip.pasterdream.boboji_curio.stat.consume", "§7▪ §9-0.4 Blink Consume");
        add("tooltip.pasterdream.boboji_curio.effect.elytra", "§7▪ §9No longer extends Blink cooldown when wearing Elytra");
        add("tooltip.pasterdream.boboji_curio.effect.evasion", "§7▪ §9Evade one instance of damage within 0.25s after Blink");
        add("tooltip.pasterdream.boboji_curio.effect.share", "§7▪ §9Evasion time doubles and is shared with nearby allies");
        add("tooltip.pasterdream.boboji_curio.effect.flavor", "§7▪ §eAdds sound effects and particle trails to Blink");
        add("tooltip.pasterdream.boboji_curio.lore", "§7§oDedicated to the one who once accompanied me");
        add("tooltip.pasterdream.boboji_curio.dedication", "§7§o-- A yellow-green budgerigar named Boboji");

        add(ModItems.KAICHU_OMAMORI.get(), "Kaichu Omamori");
        add("tooltip.pasterdream.kaichu_omamori.effect.foxfire", "§7▪ §9Generates a Foxfire field in a 12×12 area");
        add("tooltip.pasterdream.kaichu_omamori.effect.vulnerable", "§7▪ §9Non-player creatures take 20% more damage and get Slowness V effect");
        add("tooltip.pasterdream.kaichu_omamori.effect.regen", "§7▪ §9Grants the player Regeneration");
        add("tooltip.pasterdream.kaichu_omamori.effect.duration", "§7▪ §9Duration: %s seconds");
        add("tooltip.pasterdream.kaichu_omamori.effect.energy_cost", "§f▪ §4Melt Dream Energy -5");
        add("tooltip.pasterdream.kaichu_omamori.effect.cooldown", "§7▪ §9Cooldown: %s seconds");
        add("tooltip.pasterdream.kaichu_omamori.flavor", "§o§7 -- Alirea custom item");
        add("key.pasterdream.kaichu_omamori", "Activate Kaichu Omamori");
        add("tooltip.pasterdream.kaichu_omamori.equip", "§7When equip:");
        add("tooltip.pasterdream.kaichu_omamori.luck", "§7▪ §9+5Luck");
        add("tooltip.pasterdream.kaichu_omamori.hotkey", "§7▪ §9Press §e%s §9to release Foxfire field");

        add("tooltip.pasterdream.snow_vow_head.effect.bonus", "§7▪ §9Luck +3  Immune to burning and freezing");
        add("effect.pasterdream.snow_vow_buff", "Snow Vow");
        add("tooltip.pasterdream.fourleaf_clover_curio.effect.health", "§7▪ §9+1 Max Health");
        add("tooltip.pasterdream.fourleaf_clover_curio.effect.luck", "§7▪ §9+6 Luck");
        add("tooltip.pasterdream.worldtree_seedpod.condition", "§7▪ §9In Dyedream World, open sky, Y>160, daytime, standing on Worldtree Leaves");
        add("tooltip.pasterdream.worldtree_seedpod.hunger", "§7▪ §4Constantly increases exhaustion");
        add("tooltip.pasterdream.worldtree_seedpod.energy", "§7▪ §9Melt Dream Energy +360/h");
        add("tooltip.pasterdream.worldtree_seedpod.flavor", "§7§o-- Leaves fall to root, pods burst to crown");
        //Necklaces
        add(ModItems.EMBRYO_NECKLACE.get(), "Embryo Necklace");
        add(ModItems.RABBIT_FOOT_NECKLACE.get(), "Rabbit Foot Necklace");
        add("tooltip.pasterdream.rabbit_foot_necklace.effect", "§7▪ §9Grants Jump Boost II & +1 Luck");
        add(ModItems.FEATHER_NECKLACE.get(), "Feather Necklace");
        add("tooltip.pasterdream.feather_necklace.effect1", "§7▪ §9-0.05 Blink Consumption");
        add("tooltip.pasterdream.feather_necklace.effect2", "§7▪ §9+0.2 Blink Range");
        add(ModItems.HEALTH_NECKLACE.get(), "Health Necklace");
        add("tooltip.pasterdream.health_necklace.effect1", "§7▪ §9+2 Max Health");
        add("tooltip.pasterdream.health_necklace.effect2", "§7▪ §9Grants Regeneration I");
        add(ModItems.FIRE_NECKLACE.get(), "Fire Necklace");
        add("tooltip.pasterdream.fire_necklace.effect1", "§7▪ §9Leaves a trail of fire while walking");
        add("tooltip.pasterdream.fire_necklace.effect2", "§7▪ §9Grants Haste II while on fire");
        add("tooltip.pasterdream.fire_necklace.effect3", "§7▪ §9Grants Fire Resistance");
        add(ModItems.CROSS_NECKLACE.get(), "Cross Necklace");
        add("tooltip.pasterdream.cross_necklace.effect", "§7▪ §9Extends Blink evasion duration to 0.5s");
        //Rings
        add(ModItems.EMBRYO_RING.get(), "Embryo Ring");
        add(ModItems.RED_DEW_RING.get(), "Red Dew Ring");
        add(ModItems.STRIKE_RING.get(), "Strike Ring");
        add("tooltip.pasterdream.red_dew_ring.effect", "§7▪ §9+%sMax Health");
        add("tooltip.pasterdream.strike_ring.effect", "§7▪ §9+%s Attack Damage");

        //Belts
        add(ModItems.EMBRYO_BELT.get(), "Embryo Belt");
        add(ModItems.NATURE_BELT.get(), "Nature Belt");
        add(ModItems.TRAVELER_BELT.get(), "Traveler Belt");
        add("tooltip.pasterdream.traveler_belt.effect", "§7▪ §9-0.5 Blink Consumption");
        add(ModItems.DREAM_TRAVELER_BELT.get(), "Dream Traveler Belt");
        add("tooltip.pasterdream.dream_traveler_belt.effect1", "§7▪ §9+3% Movement Speed");
        add("tooltip.pasterdream.dream_traveler_belt.effect2", "§7▪ §9-0.1 Blink Cooldown");
        //Embryo
        add("tooltip.pasterdream.introduction.tooltip", "§f▪ §7Introduction");
        add("tooltip.pasterdream.embryo.tooltip1", "§7A curios embryo made from titanium and soul essence");
        add("tooltip.pasterdream.embryo.tooltip2", "§7Using titanium's life affinity and the spiritual connection of soul essence");
        add("tooltip.pasterdream.embryo.tooltip3", "§7Allow you to strengthen yourself through accessories made from physical materials");
        //curios tooltips
        add("tooltip.pasterdream.lv", "§7Level: §bLv.%s");
        add("tooltip.pasterdream.only_one.tooltip", "§7Only one of this curios can be equipped in the curios slot.");
        add("tooltip.pasterdream.hold_shift", "§7Hold [§rShift§7] to view the description");

        // Blink Skill
        add("key.pasterdream.blink", "Blink");
        add("key.categories.pasterdream", "PasterDream");
        add("effect.pasterdream.cheer_up_buff", "Cheer Up");
        add("effect.pasterdream.lethargy_buff", "Lethargy");
        add("effect.pasterdream.trance_buff", "Trance");
        add("effect.pasterdream.insand_buff", "Insanity");
        add("effect.pasterdream.evasion_buff", "Evasion");
        add("effect.pasterdream.blink_cooldown", "Blink Cooldown");
        add("effect.pasterdream.dyedream_perfume_buff", "Dyedream Perfume");
        add("effect.pasterdream.goldenrod_tea_buff", "Goldenrod Tea");
        add("effect.pasterdream.cook_buff", "Cook");
        add("effect.pasterdream.dream_wish_buff", "Dream Wish");
        add("effect.pasterdream.cecilia_blessing_buff", "Blessing of Cecilia");
        add("effect.pasterdream.rest_buff", "Rest");
        add("effect.pasterdream.dream_harp_of_wanderer_buff", "DreamHarp of Wanderer");
        add("attribute.pasterdream.blink_cd", "Blink Cooldown");
        add("attribute.pasterdream.blink_consume", "Blink Hunger Cost");
        add("attribute.pasterdream.blink_range", "Blink Distance");
        add("attribute.pasterdream.san_variability", "Sanity Variability");
        add("attribute.pasterdream.skill_cooldown_rate", "Skill Cooldown Rate");
        add("attribute.pasterdream.skill_damage_rate", "Skill Damage Rate");
        add("subtitles.pasterdream.evasion", "Evasion");

        // Low San effect commands
        add("command.pasterdream.lowsan.overlay", "Low San screen overlay");
        add("command.pasterdream.lowsan.overlay.set", "Low San screen overlay set to %s");
        add("command.pasterdream.lowsan.overlay.get", "%s: %s");
        add("command.pasterdream.lowsan.jitter", "Low San screen jitter");
        add("command.pasterdream.lowsan.jitter.set", "Low San screen jitter set to %s");
        add("command.pasterdream.lowsan.jitter.get", "%s: %s");
        add("command.pasterdream.lowsan.sound", "Low San insanity sound");
        add("command.pasterdream.lowsan.sound.set", "Low San insanity sound set to %s");
        add("command.pasterdream.lowsan.sound.get", "%s: %s");
        add("item.pasterdream.star_wish_rod", "Star's Wish Rod");
        add("tooltip.pasterdream.star_wish_rod.desc", "§7▪ §9Grants an extra Super deep-sea treasure when fishing");
        add("tooltip.pasterdream.star_wish_rod", "§7§O --Fish as numerous as stars");
        add("command.pasterdream.lowsan.enabled", "enabled");
        add("command.pasterdream.lowsan.disabled", "disabled");

        // Terra Blade
        add(ModItems.TERRA_BLADE.get(), "Terra Blade");
        add("tooltip.pasterdream.terra_blade.skill_name", "Combat Art: §aTerra Sword Skill");
        add("tooltip.pasterdream.terra_blade.desc1", "§7▪ §9Right-click to empower your next 3 attacks as sword waves");
        add("tooltip.pasterdream.terra_blade.desc2", "§7▪ §9Unleash up to 3 sword waves in the direction you face");
        add("tooltip.pasterdream.terra_blade.desc3", "§7▪ §9Each wave deals 2+ATK damage");
        add("tooltip.pasterdream.terra_blade.desc4", "§7▪ §9Third wave gets doubled attribute bonus");
        add("tooltip.pasterdream.terra_blade.desc5", "§7▪ §9Cooldown: 5s | Melt Dream Energy Cost: §c0.3");
        add("tooltip.pasterdream.terra_blade.no_energy", "Not enough Melt Dream Energy");

        // Terra Floating Island
        add(ModItems.TERRA_FLOATING_ISLAND.get(), "Terra Floating Island");
        add("tooltip.pasterdream.terra_floating_island.desc1", "§7▪ §9Terra Sword Skill cooldown -60%");
        add("tooltip.pasterdream.terra_floating_island.desc2", "§7▪ §9Sword wave damage attribute bonus +30%");
        add("tooltip.pasterdream.terra_floating_island.desc3", "§7▪ §9Terra Sword Skill energy cost -0.2");
        add("tooltip.pasterdream.terra_floating_island.desc4", "§7▪ §9Sword wave ignores invulnerability frames");

        // Strawberry Heart
        add(ModItems.STRAWBERRY_HEART.get(), "Strawberry Heart");
        add("tooltip.pasterdream.strawberry_heart.desc1", "§7▪ §9Right-click to perform §4Costs 1 Melt Dream Energy");
        add("tooltip.pasterdream.strawberry_heart.desc2", "§7▪ §9Heals nearby players by 4 HP and grants brief Regen, Strength & Speed");
        add("tooltip.pasterdream.strawberry_heart.desc3", "§7▪ §7Cooldown: 12s §7| §6Radius: 8 blocks");
        add("tooltip.pasterdream.strawberry_heart.desc4", "§7▪ §dInstantly restores 2 SAN §dand grants 8s 3.6 SAN aura, immune to lethargy during this period");
        add("tooltip.pasterdream.strawberry_heart.no_energy", "Not enough Melt Dream Energy");

        // Memory Gem
        add(ModItems.MEMORY_GEM.get(), "Memory Gem");

        // Memento Item
        add(ModItems.EMPTY_VESSEL.get(), "Empty Vessel");
        add("tooltip.pasterdream.empty_vessel.1", "§7▪ §9Grants Luck +10 for 3 minutes");
        add("tooltip.pasterdream.empty_vessel.2", "§7Exclusive memento of GQ2529");
        add("tooltip.pasterdream.empty_vessel.3", "§6PasterDream Developer");

        // Qym Gear
        add(ModItems.QYM_CAT_EARS.get(), "Qym's Cat Ears");
        add("tooltip.pasterdream.qym_cat_ears.san", "§7▪ §9SAN always at maximum");
        add("tooltip.pasterdream.qym_cat_ears.set_bonus", "§7Set Bonus: §dSlumbering Rainbow Maiden");
        add("tooltip.pasterdream.qym_cat_ears.damage_reduce", "§f▪ §9Gain 80% Damage Reduction");
        add("tooltip.pasterdream.qym_cat_ears.dream_evasion", "§f▪ §9Immune to all damage in dream dimensions");
        add(ModItems.QYM_WIND_SHIRT.get(), "Qym's Wind Shirt");
        add("tooltip.pasterdream.qym_wind_shirt.flight", "§7▪ §9Gain flight ability");
        add("tooltip.pasterdream.qym_wind_shirt.fall_immune", "§7▪ §9Immune to fall damage");
        add(ModItems.QYM_SWAYING_SKIRT.get(), "Qym's Swaying Skirt");
        add("tooltip.pasterdream.qym_swaying_skirt.energy", "§7▪ §9Melt Dream Energy always at maximum");
        add(ModItems.QYM_CLOUD_BOOTS.get(), "Qym's Cloud Boots");
        add("tooltip.pasterdream.qym_cloud_boots.blink", "§7▪ §9Blink has no cooldown");
        add("tooltip.pasterdream.qym_cloud_boots.skill_cd", "§7▪ §9Skills have no cooldown");

        add("message.pasterdream.lost_sword_tomb.lack_strength", "Your strength is not enough to pull out this sword");
        add("tooltip.pasterdream.lost_sword_tomb", "§7If you can't pull it out, just use it with the stone!");

        // Enchantments
        add("enchantment.pasterdream.swift_strike", "Swift Strike");
        add("enchantment.pasterdream.shelter", "Shelter");

        // Counter Ring
        add(ModItems.COUNTER_RING.get(), "Counter Ring");
        add("tooltip.pasterdream.counter_ring.effect1", "§7▪ §9On successful dodge: gain Counterattack I (0:10)");
        add("tooltip.pasterdream.counter_ring.effect2", "§7▪ §9Next attack: Attack +3, Skill DMG ×+50%, then buff ends");
        add("effect.pasterdream.counter_attack_buff", "Counterattack");
        add("effect.pasterdream.memento_buff", "Dream Seeker's Prayer");

        // Advancements - Story
        add("advancements.pasterdream.story.root.title", "PasterDream");
        add("advancements.pasterdream.story.root.description", "Begins with a wish from the heart");
        add("advancements.pasterdream.story.pure_and_flawless.title", "Pure and Flawless");
        add("advancements.pasterdream.story.pure_and_flawless.description", "Obtain a Pale Snow Lotus");
        add("advancements.pasterdream.story.use_pale_boneneedle.title", "Ouch, it's hurt!");
        add("advancements.pasterdream.story.use_pale_boneneedle.description", "Use the Pale Boneneedle to Wake You up from the Dream");
        add("advancements.pasterdream.story.human_falls_out_of_dream.title", "Human Falls out of Dream");
        add("advancements.pasterdream.story.human_falls_out_of_dream.description", "You can’t tell for a moment whether your pain comes from falling or from being pierced by a bone needle.");

        // Advancements - Adventure Expansion
        add("advancements.pasterdream.adventure.forgotten_sword_tomb.title", "The Forgotten Sword Tomb");
        add("advancements.pasterdream.adventure.forgotten_sword_tomb.description", "Discovered the lost sword tomb, a sword tomb hidden deep in the jungle.");
        add("advancements.pasterdream.story.get_the_lost_sword.title", "Sword of Oblivion");
        add("advancements.pasterdream.story.get_the_lost_sword.description", "Got the Sword Embryo, it seems to resonate with the power of the jungle…");
        add("advancements.pasterdream.story.dig_up_a_tomb.title", "The girl next door has just grown up, strong and full of extraordinary strength");
        add("advancements.pasterdream.story.dig_up_a_tomb.description", "You... what exactly did you dig up???");
        add("advancements.pasterdream.new_standard_sword_drawing.title", "New Standard Sword Drawing");
        add("advancements.pasterdream.new_standard_sword_drawing.description", "Because you couldn't pull out the sword, you ended up using your enemy as a tool to break stones!");
        add("advancements.pasterdream.craft_kusanagi.title", "Power of the Jungle");
        add("advancements.pasterdream.craft_kusanagi.description", "Craft Kusanagi, a sword imbued with the power of the jungle. It is hungrily seeking lost souls...");
        add("advancements.pasterdream.get_murakumo_kusanagi.title", "Lost Souls Under the Sword");
        add("advancements.pasterdream.get_murakumo_kusanagi.description", "Let Kusanagi further evolve by killing enemies.");

        // Advancements - Nether Expansion
        add("advancements.pasterdream.get_molten_gold_ingot.title", "Real Gold Does Not Fear Fire");
        add("advancements.pasterdream.get_molten_gold_ingot.description", "Obtain Molten Gold Ingot, smelted from a kind of hot gold ore from the Nether.");
        add("advancements.pasterdream.craft_hellfire_sword.title", "Blazing Sword");
        add("advancements.pasterdream.craft_hellfire_sword.description", "Craft a Hellfire Sword, it resonates with the drop of a terrifying undead creature.");
        add("advancements.pasterdream.craft_inferno_sword.title", "Inferno Fire");
        add("advancements.pasterdream.craft_inferno_sword.description", "Inlay a Nether Star into the Hellfire Sword to let it reach its final evolution.");

        // Advancements - Husbandry Expansion
        add("advancements.pasterdream.get_deep_sea_treasure.title", "Gift from the Sea");
        add("advancements.pasterdream.get_deep_sea_treasure.description", "Get a treasure of the ocean, coming from the deep sea and the dyedream frozen ocean.");
        add("advancements.pasterdream.get_super_deep_sea_treasure.title", "To wish upon a satellite...");
        add("advancements.pasterdream.get_super_deep_sea_treasure.description", "To get higher-level marine treasures, you should find a more powerful fishing rod for it...");
        add("advancements.pasterdream.get_blue_dew.title", "Tears of the Ocean");
        add("advancements.pasterdream.get_blue_dew.description", "Get the blue dew, it comes from that enchanting ocean treasure glowing with magic light.");
        add("advancements.pasterdream.get_blue_heart_of_the_sea.title", "Heart of Deep Blue");
        add("advancements.pasterdream.get_blue_heart_of_the_sea.description", "Once you get the Blue Heart of the Sea, you'll know which weapon needs it...");
        add("advancements.pasterdream.get_beihairuo_tide_sword.title", "God of Tides");
        add("advancements.pasterdream.get_beihairuo_tide_sword.description", "Get the BeiHairuo Tide Sword; it holds the power of surging waves and riptide...");


        // Patchouli book
        add("pasterdream.seniors_dream", "Seniors Dream");
        add("pasterdream.seniors_dream.landing_text", "...");
        add("pasterdream.seniors_dream.category.test", "Test");
        add("pasterdream.seniors_dream.category.test.desc", "A test category.");
        add("pasterdream.seniors_dream.entry.welcome", "Welcome");
        add("pasterdream.seniors_dream.entry.welcome.page0", "If you can see this, the book works!");

        // Entities
        add(ModEntities.PINK_CHICKEN.get(), "Pink Chicken");
        add(ModItems.PINK_CHICKEN_SPAWN_EGG.get(), "Pink Chicken Spawn Egg");
        add(ModEntities.PINK_SLIME.get(), "Pink Slime");
        add(ModItems.PINK_SLIME_SPAWN_EGG.get(), "Pink Slime Spawn Egg");

    }
}
