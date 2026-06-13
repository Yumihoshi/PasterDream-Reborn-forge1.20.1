package com.pasterdream.pasterdreammod;

import com.mojang.logging.LogUtils;
import com.pasterdream.pasterdreammod.world.level.block.ModBlocks;
import com.pasterdream.pasterdreammod.world.item.ModCreativeModeTabs;
import com.pasterdream.pasterdreammod.world.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// 此处的值应与 META-INF/mods.toml 文件中的条目匹配
@Mod(PasterDreamMod.MOD_ID)
public class PasterDreamMod
{
    // 在一个公共位置定义 mod id 以便各处引用
    public static final String MOD_ID = "pasterdream";
    // 直接引用 slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public PasterDreamMod(FMLJavaModLoadingContext context)
    {
        GeckoLib.initialize();

        IEventBus modEventBus = context.getModEventBus();

        // 注册 DeferredRegister
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeModeTabs.TABS.register(modEventBus);

        // 注册 commonSetup 方法用于 mod 加载
        modEventBus.addListener(this::commonSetup);

        // 注册我们感兴趣的服务器及其他游戏事件
        MinecraftForge.EVENT_BUS.register(this);

        // 将物品注册到创造模式物品栏
        modEventBus.addListener(this::addCreative);

        // 注册 mod 的 ForgeConfigSpec，以便 Forge 为我们创建和加载配置文件
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // 一些通用初始化代码
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // 将示例方块物品添加到建筑方块标签页
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // 你可以使用 @SubscribeEvent 让 Event Bus 自动发现要调用的方法
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // 服务器启动时执行的操作
        LOGGER.info("HELLO from server starting");
    }

    // 你可以使用 @EventBusSubscriber 自动注册类中所有带有 @SubscribeEvent 注解的静态方法
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // 一些客户端初始化代码
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
