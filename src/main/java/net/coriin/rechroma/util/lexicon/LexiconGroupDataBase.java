package net.coriin.rechroma.util.lexicon;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexiconGroupDataBase {

    public Component name;
    public List<LexiconPageDataBase> pages = new ArrayList<>();
    public int pagesPerLine;

    public LexiconGroupDataBase(final Component name, LexiconPageDataBase[] pages) {
        this.name = name;
        this.pages.addAll(Arrays.stream(pages).toList());
        this.pagesPerLine = 4;
    }

    public LexiconGroupDataBase(final Component name, List<LexiconPageDataBase> pages) {
        this.name = name;
        this.pages = pages;
        this.pagesPerLine = 4;
    }

    public LexiconGroupDataBase(final Component name, LexiconPageDataBase[] pages, int pagesPerLine) {
        this.name = name;
        this.pages.addAll(Arrays.stream(pages).toList());
        this.pagesPerLine = pagesPerLine;
    }

}
