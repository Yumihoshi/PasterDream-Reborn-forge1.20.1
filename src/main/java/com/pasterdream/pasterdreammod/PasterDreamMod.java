package com.pasterdream.pasterdreammod;

import com.pasterdream.pasterdreammod.client.*;
import com.pasterdream.pasterdreammod.helper.fluidhandler.FluidHandlerResolvers;
import com.pasterdream.pasterdreammod.helper.sanbiomeratemanager.SanBiomeRateManager;
import com.pasterdream.pasterdreammod.helper.tooltipadder.AddToolTip;
import com.pasterdream.pasterdreammod.event.ModMobDrops;
import com.pasterdream.pasterdreammod.event.PlayerEvents;
import com.pasterdream.pasterdreammod.init.*;
import com.pasterdream.pasterdreammod.world.item.curio.RedDewRingItem;
import com.pasterdream.pasterdreammod.world.item.curio.StrikeRingItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import com.pasterdream.pasterdreammod.world.item.ModToolTiers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.UUID;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(PasterDreamMod.MOD_ID)
public class PasterDreamMod
{
    public static final String MOD_ID = "pasterdream";

    private static final UUID SWIFT_STRIKE_ATTACK_SPEED_UUID = UUID.fromString("bdf05f70-b53d-4828-8e37-9a502bde0ec1");

    public PasterDreamMod(FMLJavaModLoadingContext context)
    {
        GeckoLib.initialize();

        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);             //注册物品
        ModCreativeModeTabs.register(modEventBus);  //注册创造模式物品栏
        ModParticleTypes.register(modEventBus);     //注册粒子
        ModBlocks.register(modEventBus);            //注册方块
        ModBlockEntities.register(modEventBus);     //注册方块实体
        ModFluids.register(modEventBus);            //注册流体
        ModMenus.register(modEventBus);             //注册菜单
        ModRecipes.register(modEventBus);           //注册配方
        ModSounds.register(modEventBus);            //注册音效
        ModEffects.register(modEventBus);           //注册药水效果
        ModAttributes.register(modEventBus);        //注册属性
        ModTreeDecoratorTypes.register(modEventBus); //注册树木装饰器类型
        ModFeatures.register(modEventBus);          //注册自定义特征
        ModEntities.register(modEventBus);          //注册实体
        ModLootTables.register(modEventBus);        //注册自定义战利品函数类型
        ModNetwork.register();                      //注册网络包
        ModEnchantment.register(modEventBus);       //注册附魔
        ModCriteriaTriggers.init();                 //注册自定义进度触发器

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.addListener(this::AddItemTooltip);
        MinecraftForge.EVENT_BUS.addListener(this::AddCommand);
        MinecraftForge.EVENT_BUS.addListener(PasterDreamMod::onHoeTill);
        MinecraftForge.EVENT_BUS.addListener(ModMobDrops::onLivingDrops);
        MinecraftForge.EVENT_BUS.addListener(PasterDreamMod::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(PasterDreamMod::onFoxFireVulnerableHurt);
        MinecraftForge.EVENT_BUS.addListener(PlayerEvents::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(PlayerEvents::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(PlayerEvents::onPlayerSleepInBed);
        MinecraftForge.EVENT_BUS.addListener(PlayerEvents::onAttackEntity);
        MinecraftForge.EVENT_BUS.addListener(PasterDreamMod::onItemAttributeModifier);
        MinecraftForge.EVENT_BUS.addListener(PasterDreamMod::onShelterLivingHurt);
        modEventBus.addListener(this::AddOverlays);
        modEventBus.addListener(this::AddEntityRenderersEvent);
        modEventBus.addListener(this::AddRegisterLayerDefinitions);
        MinecraftForge.EVENT_BUS.addListener(this::onAddReloadListeners);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);     //加载配置文件
    }

    //在这里输入通用端注册内容
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        FluidHandlerResolvers.FluidHandlerResolverRegister();
        ModFluidContainerRelation.registerFluidContainerRelation();
        ModDreamNotesContentRelation.registerDreamNotesContentRelation();
        ModDreamNotesBookContentRelation.registerDreamNotesBookContentRelation();
        ModCropRelation.registerCropRelation();
    }

    //在这里输入客户端注册内容
    private void clientSetup(final FMLClientSetupEvent event)
    {
        ClientSetRenderLayer.register();
        ModScreens.register(event);
        ModBlockEntityRenderer.FMLClientSetupEventRegister(event);
        event.enqueueWork(this::registerItemProperties);
    }

    private void registerItemProperties()
    {
        // 星者祈愿钓竿出杆切换模型（原版 cast predicate 仅注册在 Items.FISHING_ROD 上）
        ItemProperties.register(
                ModItems.STAR_WISH_ROD.get(),
                ResourceLocation.parse("cast"),
                (stack, level, entity, seed) -> {
                    if (entity == null) return 0.0F;
                    boolean held = entity.getMainHandItem() == stack || entity.getOffhandItem() == stack;
                    if (entity instanceof net.minecraft.world.entity.player.Player player) {
                        return held && player.fishing != null ? 1.0F : 0.0F;
                    }
                    return 0.0F;
                }
        );

        ItemProperties.register(
                ModItems.RED_DEW_RING.get(),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "lv"),
                (stack, level, entity, seed) -> RedDewRingItem.getPredicateValue(RedDewRingItem.getLv(stack))
        );
        ItemProperties.register(
                ModItems.STRIKE_RING.get(),
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "lv"),
                (stack, level, entity, seed) -> StrikeRingItem.getPredicateValue(StrikeRingItem.getLv(stack))
        );
    }

    private void AddItemTooltip(ItemTooltipEvent event)
    {
        AddToolTip.addTooltip(event);
    }

    private void AddCommand(RegisterCommandsEvent event)
    {
        ModCommands.register(event.getDispatcher());
    }

    private void AddOverlays(RegisterGuiOverlaysEvent event)
    {
        event.registerAboveAll("melt_dream_energy", MeltDreamEnergyTank.MELT_DREAM_ENERGY_TANK);
        event.registerAboveAll("san", SanTank.SAN_TANK);
        event.registerBelowAll("lose_mind", LoseMind.GUI_OVERLAY);
    }

    private void AddEntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event)
    {
        ModBlockEntityRenderer.EntityRenderersEventRegister(event);
        ModEntityRenderer.registerRenderers(event);
    }

    private void AddRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        ModEntityRenderer.registerLayerDefinitions(event);
    }

    private void onAddReloadListeners(AddReloadListenerEvent event)
    {
        event.addListener(SanBiomeRateManager.INSTANCE);
    }

    // 染梦耕地相关
    public static void onHoeTill(BlockEvent.BlockToolModificationEvent event) {
        if (event.getToolAction() != ToolActions.HOE_TILL) return;
        Block block = event.getState().getBlock();
        if (block == ModBlocks.DYEDREAM_GRASS_BLOCK.get() || block == ModBlocks.DYEDREAM_DIRT.get()) {
            event.setFinalState(ModBlocks.DYEDREAM_FARMLAND.get().defaultBlockState());
        }
    }

    // 染梦工具增强：持有染梦(染梦合金与融梦水晶)工具时伤害 +50%
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player
                && player.hasEffect(ModEffects.DYEDREAM_UP_BUFF.get())) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.getItem() instanceof TieredItem tiered
                    && (tiered.getTier() == ModToolTiers.DYEDREAM
                            || tiered.getTier() == ModToolTiers.MELT_DREAM)) {
                event.setAmount(event.getAmount() * 1.5f);
            }
        }
    }

    // 狐火易伤：被狐火立场标记的生物受到 +20% 伤害
    public static void onFoxFireVulnerableHurt(LivingHurtEvent event) {
        if (event.getEntity().getPersistentData().getBoolean("pasterdream:fox_fire_vulnerable")) {
            event.getEntity().getPersistentData().remove("pasterdream:fox_fire_vulnerable");
            event.setAmount(event.getAmount() * 1.2f);
        }
    }

    // 疾风连击：根据附魔等级增加攻击速度（剑每级+6%，斧每级+4%）
    public static void onItemAttributeModifier(ItemAttributeModifierEvent event) {
        if (event.getSlotType() != EquipmentSlot.MAINHAND) return;

        var swiftStrike = ModEnchantment.SWIFT_STRIKE_ENCHANTMENT.get();

        ItemStack stack = event.getItemStack();
        int level = stack.getEnchantmentLevel(swiftStrike);
        if (level > 0) {
            double multiplier = stack.getItem() instanceof AxeItem ? 0.04 : 0.06;
            event.addModifier(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                            SWIFT_STRIKE_ATTACK_SPEED_UUID,
                            "Swift Strike attack speed bonus",
                            level * multiplier,
                            AttributeModifier.Operation.MULTIPLY_BASE
                    )
            );
        }
    }

    // 庇护：每级-2%受到的伤害（全身护甲叠加）
    public static void onShelterLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = event.getEntity();

        var shelter = ModEnchantment.SHELTER_ENCHANTMENT.get();

        int totalLevel = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) continue;
            ItemStack armor = entity.getItemBySlot(slot);
            if (!armor.isEmpty()) {
                totalLevel += armor.getEnchantmentLevel(shelter);
            }
        }

        if (totalLevel > 0) {
            event.setAmount(event.getAmount() * (1.0f - totalLevel * 0.02f));
        }
    }
}
