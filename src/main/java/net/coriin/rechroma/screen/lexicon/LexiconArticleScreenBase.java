package net.coriin.rechroma.screen.lexicon;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.util.lexicon.LexiconPageDataBase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.swing.*;

public class LexiconArticleScreenBase extends Screen {

    public LexiconPageDataBase base;
    public static ResourceLocation exitImg = new ResourceLocation("rechroma","lexicon_button_exit.png");

    public int YOffset = 0;

    public final int basicIndent = 20;

    private Button exitBtn;

    public LexiconArticleScreenBase(LexiconPageDataBase base) {
        super(Component.empty());
        this.base = base;

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if(this.minecraft == null) return;

        YOffset = Math.max(0, YOffset);

        renderBackground(pGuiGraphics);

        LexiconMainPageScreen.ScreenHelper.renderImage(pGuiGraphics, base.icon, basicIndent,basicIndent - YOffset,0,0, 128,128,128,128);

        //exitBtn = addWidget(Button.builder(Component.empty(), button -> {minecraft.popGuiLayer() /*вместо уничтожения меню стоит обратно пререкидывать в main через пакет*/;}).bounds(0,0,64,64).build());
        //LexiconMainPageScreen.ScreenHelper.renderImage(pGuiGraphics, exitImg, width-basicIndent-64,basicIndent + YOffset,0,0, 64,64,64,64);


        //pGuiGraphics.drawString(minecraft.font, base.articleText[0], basicIndent, 2*basicIndent + 128 + YOffset, 0xffffff);
        int textBlockHeight = -40;
        for(Component textBlock: base.articleText){
            textBlockHeight += 40;
            textBlockHeight += LexiconMainPageScreen.ScreenHelper.renderMultiLineTextOnScreenWidth(
                    pGuiGraphics,
                    textBlock,
                    basicIndent,
                    (int)(width*0.6f) - basicIndent,
                    basicIndent, 2*basicIndent + 128 - YOffset + textBlockHeight
            );

        }


        //multiLineTextWidget.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {


        if(hasShiftDown()){
            YOffset -= pDragY * 2;
        } else {
            YOffset -= pDragY;
        }

        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        LexiconMainPageScreen.ScreenHelper.renderImage(pGuiGraphics,  LexiconMainPageScreen.ScreenHelper.TEXTURE_BG, 0, 0, 0, YOffset,
                width, height, 256, 256, 0.7f);
    }

}
