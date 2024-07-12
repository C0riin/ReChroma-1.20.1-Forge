package net.coriin.rechroma.util.lexicon;

import net.minecraft.network.chat.Component;

public class LexiconGroupData {

    public Component name;
    public LexiconPageData[] pages;
    public int pagesPerLine;

    public LexiconGroupData(final Component name, LexiconPageData[] pages) {
        this.name = name;
        this.pages = pages;
        this.pagesPerLine = 4;
    }

    public LexiconGroupData(final Component name, LexiconPageData[] pages, int pagesPerLine) {
        this.name = name;
        this.pages = pages;
        this.pagesPerLine = pagesPerLine;
    }

}
