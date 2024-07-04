package net.coriin.rechroma;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.renderer.blockentity.CastingTableEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ReChroma.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {

        LOGGER.error("registerRenderers called");
        e.registerBlockEntityRenderer(ModBlockEntities.CASTING_TABLE_BE.get(),
                CastingTableEntityRenderer::new);
    }

}
