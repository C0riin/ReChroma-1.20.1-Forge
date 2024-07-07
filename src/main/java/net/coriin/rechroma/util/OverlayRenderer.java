package net.coriin.rechroma.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ReChroma.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OverlayRenderer{

    private static final ResourceLocation POWER_MANIPULATOR_OVERLAY_CURSOR =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/power_manipulator_cursor_overlay.png");
    private static int POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE = 16; // width and height

    private static final ResourceLocation POWER_MANIPULATOR_OVERLAY_WHEELBACK =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/power_manipulator_energy_wheelback_overlay.png");
    private static int POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE = 145;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void renderOverlay(RenderGuiEvent event){
        GuiGraphics guiGraphics =  event.getGuiGraphics();
        if(Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.POWER_MANIPULATOR.get())){

            renderPowerManipulatorOverlay(guiGraphics, event);
        }
    }
    public static void renderPowerManipulatorOverlay(GuiGraphics guiGraphics, RenderGuiEvent event){
        int x,y;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);
        RenderSystem.setShaderTexture(0, POWER_MANIPULATOR_OVERLAY_CURSOR);
        x = (event.getWindow().getWidth() ) / 4 - POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE/2;
        y = (event.getWindow().getHeight() ) / 4 - POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE/2;

        guiGraphics.blit(POWER_MANIPULATOR_OVERLAY_CURSOR, x, y, 0, 0,
                POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE, POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE,
                POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE,POWER_MANIPULATOR_OVERLAY_CURSOR_SIZE);


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);
        RenderSystem.setShaderTexture(0, POWER_MANIPULATOR_OVERLAY_CURSOR);
        x = 0;
        y = 0;

        if(Minecraft.getInstance().player.isShiftKeyDown()){
            guiGraphics.blit(POWER_MANIPULATOR_OVERLAY_WHEELBACK, x, y, 0, 0,
                    POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE,POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE,
                    POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE,POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE);
        }
        else {
            guiGraphics.blit(POWER_MANIPULATOR_OVERLAY_WHEELBACK, x, y, 0, 0,
                    POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE/2,POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE/2,
                    POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE/2,POWER_MANIPULATOR_OVERLAY_WHEELBACK_SIZE/2);
        }


    }

}
