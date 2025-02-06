package net.coriin.rechroma.screen.lexicon;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.coriin.rechroma.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.util.lexicon.ArticleCore;
import net.coriin.rechroma.util.lexicon.LexiconGroupDataBase;
import net.coriin.rechroma.util.lexicon.LexiconPageDataBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LexiconMainPageScreen extends Screen {

    public LexiconMainPageScreen(Component pTitle) {
        super(pTitle);
        pPlayer = Minecraft.getInstance().player;
        /*
        ReChroma.LOGGER.info("entered constructor");
        //this.minecraft = Minecraft.getInstance();
        //this.font = Minecraft.getInstance().font;
        */

        init();
    }

    Player pPlayer;
    List<String> playerFlags = new ArrayList<>();

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
    * 1. есть LexiconGroupDataBase[][] с полной отрисовкой лексикона
    * 2. из фрагментов получаются статьи которые должны быть отрисованны, фрагмент хранит String, который String -> pageData через заготовленный Map<String, pageData>
    * 3. перебираются все LexiconGroupDataBase.pages в копии LexiconGroupDataBase[][] с полной отрисовкой лексикона
    * 4. нужные(из пункта 2) статьи остаются, остальные удаляются, пустые группы и строчки тоже удаляются удаляются
    * 5. полученый список отправляется на отрисовку
    */

    LexiconPageDataBase testArticle = ArticleCore.test1;
    LexiconPageDataBase start = ArticleCore.start;

    LexiconGroupDataBase articleGroup = new LexiconGroupDataBase(Component.literal("test group"),
            new LexiconPageDataBase[]{testArticle,start,testArticle,testArticle,testArticle});

    LexiconGroupDataBase articleGroup2 = new LexiconGroupDataBase(Component.literal("test group"),
            new LexiconPageDataBase[]{testArticle,testArticle,testArticle,testArticle,testArticle});

    List<LexiconGroupDataBase> articleRows = List.of(articleGroup,articleGroup2,articleGroup);
    List<List<LexiconGroupDataBase>> allArticles = List.of(articleRows,articleRows,articleRows);

    List<List<LexiconGroupDataBase>> availableArticles = List.copyOf(allArticles);

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        ScreenHelper.renderImage(pGuiGraphics,  ScreenHelper.TEXTURE_BG, 0, 0, bgXOffset, bgYOffset,
                width, height, 256, 256, 0.7f);
    }
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if(this.minecraft == null) return;

        bgXOffset = Math.min(Math.max(0, bgXOffset),getRowWidth(articleRows)); // fix to math.min with the tallest row
        bgYOffset = Math.max(0, bgYOffset);

        renderBackground(pGuiGraphics);

        pGuiGraphics.drawString(this.font, Component.translatable("rechroma.gui.lexicon.main_page_title"),
                20 - bgXOffset, 15 - bgYOffset, 0xEEEEEE);
        //renderRow(pGuiGraphics, testRow1, 0);

        //renderAllRows(pGuiGraphics, allArticles, pMouseX, pMouseY, pPartialTick);


        if(!availableArticles.isEmpty()){
            renderAllRows(pGuiGraphics, availableArticles, pMouseX, pMouseY, pPartialTick);
        } else { pGuiGraphics.drawString(minecraft.font, Component.literal("Whoops, there is nothing to read, collect come fragments"), 200, 200,0xffffff);}




        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void renderGroup(GuiGraphics pGuiGraphics, LexiconGroupDataBase group, int pX, int pY, int pMouseX, int pMouseY, float pPartialTick){

        int groupPosX = basicPadding - bgXOffset + pX;
        int groupPosY = basicPadding + 25 - bgYOffset + pY;
        int rows = (int)Math.ceil((double)group.pages.size()/(double)group.pagesPerLine);
        int countInLines = Math.min(group.pagesPerLine, group.pages.size());

        if(!group.pages.isEmpty()){
            pGuiGraphics.drawString(this.font, group.name,
                    basicPadding - bgXOffset +pX, ((int) (basicPadding * 1.5f)) - bgYOffset  + pY, 0xDDDDDD);

            ScreenHelper.renderImage(pGuiGraphics, TEXTURE_BLACK_ARTICLE,
                    groupPosX, groupPosY,
                    0,0,
                    64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                    64 * rows+ 2*basicPadding + basicPadding * (rows-1),
                    64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                    64 * rows + 2*basicPadding + basicPadding * (rows-1));
        }


        int row = 0;
        int index = 0;
        for(LexiconPageDataBase page : group.pages){
            Button articleButton = addWidget(Button.builder(page.name, btn -> {minecraft.setScreen(new LexiconArticleScreenBase(page));})
                    .bounds(groupPosX + basicPadding*(index+1) + 64*index, groupPosY + basicPadding*(row+1) + 64*row, 64, 64)
                    .tooltip(Tooltip.create(page.name)).build());

            ScreenHelper.renderImage(pGuiGraphics,group.pages.get(row*group.pagesPerLine + index).icon,
                    groupPosX + basicPadding*(index+1) + 64*index, groupPosY + basicPadding*(row+1) + 64*row, 0,0,
                    64,64,64,64);

            if(articleButton.isMouseOver(pMouseX, pMouseY)){
                pGuiGraphics.drawString(
                        Minecraft.getInstance().font,
                        articleButton.getMessage(),
                        pMouseX + 10,
                        pMouseY,
                        0xffffff);
            }

            index++;
            if(index >= group.pagesPerLine){
                row++;
                index = 0;
            }
        }
    }
    public void renderRow(GuiGraphics pGuiGraphics, List<LexiconGroupDataBase> row, int pY, int pMouseX, int pMouseY, float pPartialTick){
        for(LexiconGroupDataBase group : row){
            renderGroup(pGuiGraphics,group,0,0, pMouseX, pMouseY, pPartialTick);
        }
        for(int i = 0;i<row.size();i++){
            renderGroup(pGuiGraphics,row.get(i),i*200 + getGroupsWidth(row,i),pY, pMouseX, pMouseY, pPartialTick);
        }
    }
    public void renderAllRows(GuiGraphics pGuiGraphics, List<List<LexiconGroupDataBase>> rows, int pMouseX, int pMouseY, float pPartialTick){
        for(int i = 0;i < rows.size();i++){
            renderRow(pGuiGraphics, rows.get(i), i*350, pMouseX, pMouseY, pPartialTick); // fix to indent
        }
    }
    private int getGroupsWidth(List<LexiconGroupDataBase> groups,int i){
        int sum = 0;
        for(int j = 0;j<i;j++){
            int countInLines = Math.min(groups.get(j).pagesPerLine, groups.get(j).pages.size());
            sum += 64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1);
        }
        return sum;
    }
    private int getRowWidth(List<LexiconGroupDataBase> row){
        return getGroupsWidth(row,row.size()); //+ (row.length-2)*200
    }
    @Override
    protected void init() {
        //ReChroma.LOGGER.info("init");
        this.minecraft = Minecraft.getInstance();



        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(fragments ->{
                for(Map.Entry<String, Boolean> entry : fragments.getFragments().entrySet()){
                    if(entry.getValue()){
                        playerFlags.add(entry.getKey());
                        //ReChroma.LOGGER.info("entry.getKey()");
                    }
                }
            });


        ReChroma.LOGGER.info("checking for available articles");

        for(int i = 0; i < availableArticles.size();i++){
            for(int j = 0; j < availableArticles.get(i).size();j++){
                for(int k = 0; k < availableArticles.get(i).get(j).pages.size();k++){
                    if(!playerFlags.contains(availableArticles.get(i).get(j).pages.get(k).id)){
                        availableArticles.get(i).get(j).pages.remove(availableArticles.get(i).get(j).pages.get(k));
                        //group.pages.remove(page);
                    }
                }
            }
        }

        /*
        for(List<LexiconGroupDataBase> row : availableArticles){
            for(LexiconGroupDataBase group : row){
                for(LexiconPageDataBase page : group.pages){
                    if(!playerFlags.contains(page.id)){
                        //group.pages.remove(page);
                    }
                }
                //group.pages.removeIf(article -> !playerFlags.contains(article.id));
            }
        }*/

        ReChroma.LOGGER.info("ended checking for available articles");


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

    public static class ScreenHelper {
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
    }


}
