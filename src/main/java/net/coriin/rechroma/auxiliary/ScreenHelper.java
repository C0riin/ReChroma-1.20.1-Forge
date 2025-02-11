package net.coriin.rechroma.auxiliary;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.ReChroma;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ScreenHelper {
    public static final ResourceLocation TEXTURE_BG =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/bg.png");

    public static void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                                   int pWidth, int pHeight, int pTextureWidth, int pTextureHeight, float alpha){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, TEXTURE_BG);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }
    public static void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                                   int pWidth, int pHeight, int pTextureWidth, int pTextureHeight){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }
    public static void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                                   int pWidth, int pHeight, int pTextureWidth, int pTextureHeight, int pColor){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor((pColor&0x110000)>>4, (pColor&0x001100)>>2, pColor&0x000011, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }
    public static int renderMultiLineTextOnStringLength(GuiGraphics pGuiGraphics, Component text, int innerTextIndent, int maxCharInLine, int pX, int pY){
        List<Component> toDraw = new ArrayList<>();

        String[] txt = text.getString().split(" ");

        StringBuilder toBeComponent = new StringBuilder();
        for(String word : txt){
            //component = Component.literal(toBeComponent.toString());
            if(toBeComponent.toString().length() + word.length() < maxCharInLine){
                toBeComponent.append(word);
            }
            else {
                toDraw.add(Component.literal(toBeComponent.toString()));
                toBeComponent = new StringBuilder().append(word);
            }
        }

        for(int i = 0; i < toDraw.size();i++){
            //ReChroma.LOGGER.info("rendered multiline text, line: " + toDraw.get(i).getString());
            pGuiGraphics.drawString(
                    Minecraft.getInstance().font,
                    toDraw.get(i),
                    pX,
                    pY + innerTextIndent*i+ Minecraft.getInstance().font.lineHeight*i,
                    0xffffff
            );
        }

        return innerTextIndent * toDraw.size() + Minecraft.getInstance().font.lineHeight*toDraw.size();
    }
    public static int renderMultiLineTextOnScreenWidth(GuiGraphics pGuiGraphics, Component text, int innerTextIndent, int screenWidth, int pX, int pY){
        List<Component> toDraw = new ArrayList<>();

        String[] txt = text.getString().split(" ");

        StringBuilder toBeComponent = new StringBuilder();
        for(String word : txt){
            Font font = Minecraft.getInstance().font;
            //component = Component.literal(toBeComponent.toString());
            if(font.width(toBeComponent.toString()) + font.width(word + " ") < screenWidth){
                toBeComponent.append(word).append(" ");
                if(word.equals(txt[txt.length-1])){
                    toDraw.add(Component.literal(toBeComponent.toString()));
                }
            }
            else {
                toDraw.add(Component.literal(toBeComponent.toString()));
                toBeComponent = new StringBuilder().append(word).append(" ");
            }
        }

        for(int i = 0; i < toDraw.size();i++){
            //ReChroma.LOGGER.info("rendered multiline text, line: " + toDraw.get(i).getString());
            pGuiGraphics.drawString(
                    Minecraft.getInstance().font,
                    toDraw.get(i),
                    pX,
                    pY + innerTextIndent*i+ Minecraft.getInstance().font.lineHeight*i,
                    0xffffff
            );
        }
        //ReChroma.LOGGER.info(String.valueOf(innerTextIndent * (toDraw.size()-1) + Minecraft.getInstance().font.lineHeight*toDraw.size()));
        return innerTextIndent * (toDraw.size()-1) + Minecraft.getInstance().font.lineHeight*toDraw.size();
    }

    private static final ResourceLocation lineMaterial = new ResourceLocation("recroma","textures/gui/line_material.png");
    public static void DrawBox(GuiGraphics pGuiGraphics, int pX, int pY, int width, int height, int border, int pColor){
        //Horizontal lines
        renderImage(pGuiGraphics, lineMaterial, pX, pY,0,0, width, border, 1,1, pColor);
        renderImage(pGuiGraphics, lineMaterial, pX, pY + height,0,0, width, border, 1,1, pColor);
        //Vertical Lines
        renderImage(pGuiGraphics, lineMaterial, pX, pY,0, 0, border, height,1,1, pColor);
        renderImage(pGuiGraphics, lineMaterial, pX + width, pY,0, 0, border, height,1,1, pColor);
    }
}