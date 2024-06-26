package net.coriin.rechroma.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.ReChroma;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.client.renderer.GameRenderer;

public class CastingTableScreen extends AbstractContainerScreen<CastingTableMenu>{

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/casting_table_tier1_gui.png");

    public CastingTableScreen(CastingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 218, 208);

        //renderProgressArrow(guiGraphics, x, y);
    }
}
