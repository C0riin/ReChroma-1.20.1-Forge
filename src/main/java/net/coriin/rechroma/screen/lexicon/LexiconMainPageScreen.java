package net.coriin.rechroma.screen.lexicon;

import com.mojang.blaze3d.systems.RenderSystem;
import net.coriin.rechroma.auxiliary.ScreenHelper;
import net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
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
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LexiconMainPageScreen extends Screen {

    public LexiconMainPageScreen(Component pTitle) {
        super(pTitle);
        pPlayer = Minecraft.getInstance().player;

        //init();
    }

    private final int basicPadding = 20;
    int bgXOffset = 0;
    int bgYOffset = 0;
    Player pPlayer;
    List<String> playerFlags = new ArrayList<>();
    boolean isCreative;


    private static final ResourceLocation TEXTURE_BLACK_ARTICLE =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/blank.png");

    private static final ResourceLocation TEXTURE_EMPTY =
            new ResourceLocation(ReChroma.MOD_ID,"textures/gui/empty.png");

    //List<List<LexiconGroupDataBase>> availableArticles = List.copyOf(ArticleCore.allArticles);
    List<List<LexiconGroupDataBase>> availableArticles;

    @Override public void renderBackground(GuiGraphics pGuiGraphics) {
        ScreenHelper.renderImage(pGuiGraphics,  ScreenHelper.TEXTURE_BG, 0, 0, bgXOffset, bgYOffset,
                width, height, 256, 256, 0.7f);
    }
    @Override public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if(this.minecraft == null) return;

        bgXOffset = Math.min(Math.max(0, bgXOffset),getRowWidth(ArticleCore.GetLongestRow())); // fix to math.min with the tallest row
        bgYOffset = Math.max(0, bgYOffset);

        renderBackground(pGuiGraphics);

        pGuiGraphics.drawString(this.font, Component.translatable("rechroma.gui.lexicon.main_page_title"),
                20 - bgXOffset, 15 - bgYOffset, 0xEEEEEE);


        if(isCreative){
            renderAllRows(pGuiGraphics, ArticleCore.GetAllArticles(), pMouseX, pMouseY, pPartialTick);
        }
        else {
            if(!availableArticles.isEmpty()){
                renderAllRows(pGuiGraphics, availableArticles, pMouseX, pMouseY, pPartialTick);
            } else { pGuiGraphics.drawString(minecraft.font, Component.literal("Whoops, there is nothing to read, collect some fragments"), 200, 200,0xffffff);}
        }


        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
    @Override protected void init() {
        this.minecraft = Minecraft.getInstance();

        //availableArticles = new ArrayList<>();
        availableArticles = ArticleCore.GetAllArticles();

        CompoundTag lexiconTag = Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag();
        isCreative = lexiconTag.contains("is_creative_spawned") && lexiconTag.getBoolean("is_creative_spawned");

        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(fragments ->{
            for(Map.Entry<String, Boolean> entry : fragments.getFragments().entrySet()){
                if(entry.getValue()){
                    playerFlags.add(entry.getKey());
                }
            }
        });

        for(String flag : playerFlags){
            ReChroma.LOGGER.info(flag);
        }

        for (List<LexiconGroupDataBase> row : availableArticles) {
            for (LexiconGroupDataBase group : row) {
                for (int k = 0; k < group.pages.size(); k++) {
                    //ReChroma.LOGGER.info(group.pages.get(k).id);
                    if (!playerFlags.contains(group.pages.get(k).id)) {
                        group.pages.remove(group.pages.get(k));
                    }
                }
            }
        }
        super.init();
    }
    @Override public boolean isPauseScreen() {
        return false;
    }
    @Override public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {

        if(hasShiftDown()){
            bgXOffset -= pDragX * 2;
            bgYOffset -= pDragY * 2;
        } else {
            bgXOffset -= pDragX;
            bgYOffset -= pDragY;
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    public void renderGroup(GuiGraphics pGuiGraphics, LexiconGroupDataBase group, int pX, int pY, int pMouseX, int pMouseY, float pPartialTick){

        int groupPosX = basicPadding - bgXOffset + pX;
        int groupPosY = basicPadding + 25 - bgYOffset + pY;


        int rows = (int)Math.ceil((double)group.pages.size()/(double)group.pagesPerLine);
        int countInLines = Math.min(group.pagesPerLine, group.pages.size());

        int groupWidth = 64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1);
        int groupHeight = 64 * rows+ 2*basicPadding + basicPadding * (rows-1);

        if(!group.pages.isEmpty()){
            pGuiGraphics.drawString(this.font, group.name,
                    basicPadding - bgXOffset +pX, ((int) (basicPadding * 1.5f)) - bgYOffset  + pY, 0xDDDDDD);


            int color = 0x0f0f0f;
            /*
            if(
                    (pMouseX > groupPosX && pMouseX < groupPosX + width) &&
                    (pMouseY > groupPosY && pMouseY < groupPosY + height)
            ) { color = 0xffffff;}
            */
            ScreenHelper.DrawBox(pGuiGraphics,
                    groupPosX, groupPosY,
                    groupWidth,
                    groupHeight,
                    3, color);

            /*
            ScreenHelper.renderImage(pGuiGraphics, TEXTURE_BLACK_ARTICLE,
                    groupPosX, groupPosY,
                    0,0,
                    64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                    64 * rows+ 2*basicPadding + basicPadding * (rows-1),
                    64*countInLines + 2*basicPadding + basicPadding * (countInLines - 1),
                    64 * rows + 2*basicPadding + basicPadding * (rows-1));
            */
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






}
