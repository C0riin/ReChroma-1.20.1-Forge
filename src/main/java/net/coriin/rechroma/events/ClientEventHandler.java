package net.coriin.rechroma.events;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.fluid.ModFluids;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.KnowledgeC2SPacket;
import net.coriin.rechroma.renderer.blockentity.AuraBloomBlockEntityRenderer;
import net.coriin.rechroma.renderer.blockentity.VoidReedsBlockEntityRenderer;
import net.coriin.rechroma.screen.CastingTableScreen;
import net.coriin.rechroma.screen.ModMenuTypes;
import net.coriin.rechroma.screen.lexicon.LexiconFragmentScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ReChroma.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {

        LOGGER.error("registerRenderers called");
        //e.registerBlockEntityRenderer(ModBlockEntities.CASTING_TABLE_BE.get(), CastingTableEntityRenderer::new);
        e.registerBlockEntityRenderer(ModBlockEntities.AURA_BLOOM_BE.get(), AuraBloomBlockEntityRenderer::new);
        e.registerBlockEntityRenderer(ModBlockEntities.VOID_REEDS_BE.get(), VoidReedsBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        // Some client setup code
        LOGGER.info("HELLO FROM CLIENT SETUP");
        LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LIQUID_CHROMA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LIQUID_CHROMA.get(), RenderType.translucent());

        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.SPECTRUM_FLOWER.get(), RenderType.cutout());


        MenuScreens.register(ModMenuTypes.CASTING_TABLE_MENU.get(), CastingTableScreen::new);
        MenuScreens.register(ModMenuTypes.LEXICON_FRAGMENTS_MENU.get(), LexiconFragmentScreen::new);
    }

}
