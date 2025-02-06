package net.coriin.rechroma.util.lexicon;

import net.coriin.rechroma.screen.lexicon.LexiconArticleScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LexiconPageDataBase {

    public String id;
    public Component name;
    public ResourceLocation icon;

    public ResourceLocation[] pictures;
    public Component[] articleText;


    public LexiconPageDataBase(ResourceLocation image, Component name, String id) {
        this.name = name;
        this.icon = image;
        this.id = id;
    }

    public LexiconPageDataBase(ResourceLocation image, Component name, String id, ResourceLocation[] pics, Component[] text) {
        this.name = name;
        this.id = id;
        this.icon = image;
        this.pictures = pics;
        this.articleText = text;

    }



    public LexiconArticleScreenBase GetScreen(){
        return new LexiconArticleScreenBase(this);
    }
}
