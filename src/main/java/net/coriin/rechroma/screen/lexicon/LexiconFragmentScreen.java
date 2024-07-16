package net.coriin.rechroma.screen.lexicon;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.ReChroma;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Math;

public class LexiconFragmentScreen extends AbstractContainerScreen<LexiconFragmentMenu> {

    private static final ResourceLocation TEXTURE_BG =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/lexicon/lexicon_fragment_storage.png");

    private static final ResourceLocation TEXTURE_BTN =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/lexicon/lexicon_buttons.png");


    private Button btnUp;
    private Button btnDown;

    private Inventory inv;

    public LexiconFragmentScreen(LexiconFragmentMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.inv = pPlayerInventory;
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;


        this.btnDown = addWidget(
                Button.builder(
                        Component.empty(), btn -> {
                            menu.setOffset(Math.clamp(0,(int)Math.ceil(menu.invSize / 9f),menu.rowIndexOffset - 1));
                            inv.player.sendSystemMessage(Component.literal("Down"));
                            //menu.rowIndexOffset = Math.clamp(0,(int)Math.ceil(menu.invSize / 9f), menu.rowIndexOffset);
                        }
                ).bounds((width - imageWidth) / 2 + 144, (height - imageHeight) / 2 + 4,12,10).build());

        this.btnUp = addWidget(
                Button.builder(
                   Component.empty(), btn -> {
                       menu.setOffset(Math.clamp(0,(int)Math.ceil(menu.invSize / 9f),menu.rowIndexOffset + 1));
                       inv.player.sendSystemMessage(Component.literal("Up"));
                       //menu.rowIndexOffset = Math.clamp(0,(int)Math.ceil(menu.invSize / 9f), menu.rowIndexOffset);
                   }
                ).bounds((width - imageWidth) / 2 + 156, (height - imageHeight) / 2 + 4,12,10).build());
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE_BG);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE_BG, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        //renderImage(pGuiGraphics, TEXTURE_BTN, (width - imageWidth) / 2 + 144, (height - imageHeight) / 2 + 4, 100,6, 24,10,256,256);
        renderImage(pGuiGraphics, TEXTURE_BTN, (width - imageWidth) / 2 + 156, (height - imageHeight) / 2 + 4, 100,6, 12,10,256,256); // down
        renderImage(pGuiGraphics, TEXTURE_BTN, (width - imageWidth) / 2 + 144, (height - imageHeight) / 2 + 4,112,6, 12,10,256,256); //up
    }

    public void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                            int pWidth, int pHeight, int pTextureWidth, int pTextureHeight){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }

    @Override
    public void afterKeyboardAction() {
        this.minecraft = Minecraft.getInstance();
        super.afterKeyboardAction();
    }

    @Override
    public void afterMouseAction() {
        this.minecraft = Minecraft.getInstance();
        super.afterMouseAction();
    }
}
