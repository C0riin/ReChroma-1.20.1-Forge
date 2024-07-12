package net.coriin.rechroma.util.lexicon;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LexiconArticlePageData extends LexiconPageData {

    public ResourceLocation Image;

    LexiconArticlePageData(ResourceLocation image, Component name, Component tooltip, boolean multiPage) {
        super(image, name, tooltip, multiPage);
        this.Image = image;

    }
}
