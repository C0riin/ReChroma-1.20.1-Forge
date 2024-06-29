package net.coriin.rechroma;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.effect.ModEffects;
import net.coriin.rechroma.recipe.Modrecipes;
import net.coriin.rechroma.util.ModCreativeTabs;
import net.coriin.rechroma.item.ModItems;
import net.coriin.rechroma.screen.CastingTableScreen;
import net.coriin.rechroma.screen.ModMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ReChroma.MOD_ID)
public class ReChroma
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rechroma";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public ReChroma()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("registering items for rechroma");

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        Modrecipes.register(modEventBus);
        ModEffects.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            MenuScreens.register(ModMenuTypes.CASTING_TABLE_MENU.get(), CastingTableScreen::new);
        }
    }
}
