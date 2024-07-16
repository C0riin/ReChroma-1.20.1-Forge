package net.coriin.rechroma.screen.lexicon;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.util.lexicon.LexiconGroupData;
import net.coriin.rechroma.util.lexicon.LexiconPageData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LexiconMainPageScreen extends Screen {

    public LexiconMainPageScreen(Component pTitle) {
        super(pTitle);
        //ReChroma.LOGGER.error("before exception or late");
        //this.minecraft = Minecraft.getInstance();
        //this.font = Minecraft.getInstance().font;
    }


    private static final ResourceLocation TEXTURE_BG =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/bg.png");

    private static final ResourceLocation TEXTURE_BLACK_ARTICLE =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/blank.png");

    private static final ResourceLocation TEXTURE_EMPTY =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/empty.png");

    private final int basicPadding = 20;

    int bgXOffset = 0;
    int bgYOffset = 0;

    /*
    * идея правильной отрисовки только нужных статей:
    * 0. все действия происходят разово при открытии книги в методе init, либо в конструкторе
    * 1. есть LexiconGroupData[][] с полной отрисовкой лексикона
    * 2. из фрагментов получаются статьи которые должны быть отрисованны, фрагмент хранит String, который String -> pageData через заготовленный Map<String, pageData>
    * 3. перебираются все LexiconGroupData.pages в копии LexiconGroupData[][] с полной отрисовкой лексикона
    * 4. нужные(из пункта 2) статьи остаются, остальные удаляются, пустые группы и строчки тоже удаляются удаляются
    * 5. полученый список отправляется на отрисовку
    */

    LexiconGroupData testGroup = new LexiconGroupData(Component.literal("test group"),
            new LexiconPageData[]{
                    new LexiconPageData(new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
                            Component.literal("test article1"), Component.literal("test article1 tooltip"), false),
                    new LexiconPageData(new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
                            Component.literal("test article2"), Component.literal("test article2 tooltip"), false),
                    new LexiconPageData(new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
                            Component.literal("test article2"), Component.literal("test article2 tooltip"), false),
                    new LexiconPageData(new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
                            Component.literal("test article2"), Component.literal("test article2 tooltip"), false),
                    new LexiconPageData(new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
                            Component.literal("test article2"), Component.literal("test article2 tooltip"), false)});

    LexiconGroupData[] testRow1 = {testGroup,testGroup,testGroup};
    LexiconGroupData[][] testRows1 = {testRow1,testRow1,testRow1};



    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        renderImage(pGuiGraphics,  TEXTURE_BG, 0, 0, bgXOffset, bgYOffset,
                width, height, 256, 256, 0.7f);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if(this.minecraft == null) return;
        bgXOffset = Math.min(Math.max(0, bgXOffset),getRowWidth(testRow1)); // fix to math.min with the tallest row
        bgYOffset = Math.max(0, bgYOffset);

        renderBackground(pGuiGraphics);

        pGuiGraphics.drawString(this.font, Component.translatable("rechroma.gui.lexicon.main_page_title"),
                20 - bgXOffset, 15 - bgYOffset, 0xEEEEEE);
        //renderRow(pGuiGraphics, testRow1, 0);
        renderAllRows(pGuiGraphics, testRows1);



        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                            int pWidth, int pHeight, int pTextureWidth, int pTextureHeight){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }

    public void renderGroup(GuiGraphics pGuiGraphics, LexiconGroupData group, int pX, int pY){

        int groupPosX = basicPadding - bgXOffset + pX;
        int groupPosY = basicPadding + 25 - bgYOffset + pY;
        int rows = (int)Math.ceil((double)group.pages.length/(double)group.pagesPerLine);
        int countInLines = Math.min(group.pagesPerLine, group.pages.length);

        pGuiGraphics.drawString(this.font, group.name,
                basicPadding - bgXOffset +pX, ((int) (basicPadding * 1.5f)) - bgYOffset  + pY, 0xDDDDDD);

        renderImage(pGuiGraphics, TEXTURE_BLACK_ARTICLE,
                groupPosX, groupPosY,
                0,0,
                64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                64 * rows+ 2*basicPadding + basicPadding * (rows-1),
                64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                64 * rows + 2*basicPadding + basicPadding * (rows-1));
        int row = 0;
        int index = 0;
        for(LexiconPageData page : group.pages){
            renderImage(pGuiGraphics,group.pages[row*group.pagesPerLine + index].icon,
                    groupPosX + basicPadding*(index+1) + 64*index, groupPosY + basicPadding*(row+1) + 64*row, 0,0,
                    64,64,64,64);

            index++;
            if(index >= group.pagesPerLine){
                row++;
                index = 0;
            }
        }
    }

    public void renderRow(GuiGraphics pGuiGraphics, LexiconGroupData[] row, int pY){
        for(LexiconGroupData group : row){
            renderGroup(pGuiGraphics,group,0,0);
        }
        for(int i = 0;i<row.length;i++){
            renderGroup(pGuiGraphics,row[i],i*200 + getGroupsWidth(row,i),pY);
        }
    }

    public void renderAllRows(GuiGraphics pGuiGraphics, LexiconGroupData[][] rows){
        for(int i = 0;i< rows.length;i++){
            renderRow(pGuiGraphics, rows[i], i*350); // fix to indent
        }
    }

    private int getGroupsWidth(LexiconGroupData[] groups,int i){
        int sum = 0;
        for(int j = 0;j<i;j++){
            int countInLines = Math.min(groups[j].pagesPerLine, groups[j].pages.length);
            sum += 64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1);
        }
        return sum;
    }
    private int getRowWidth(LexiconGroupData[] row){
        return getGroupsWidth(row,row.length); //+ (row.length-2)*200
    }


    public void renderImage(GuiGraphics pGuiGraphics, ResourceLocation TEXTURE, int pX, int pY, int pOx, int pOy,
                            int pWidth, int pHeight, int pTextureWidth, int pTextureHeight, float alpha){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, TEXTURE_BG);

        pGuiGraphics.blit(TEXTURE, pX, pY, pOx, pOy, pWidth, pHeight, pTextureWidth,pTextureHeight);
    }



    @Override
    protected void init() {
        this.minecraft = Minecraft.getInstance();
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {

        if(hasShiftDown()){
            bgXOffset -= pDragX * 2;
            bgYOffset -= pDragY * 2;
        } else {
            bgXOffset -= pDragX;
            bgYOffset -= pDragY;
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
}
