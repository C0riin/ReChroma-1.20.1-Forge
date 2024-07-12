package net.coriin.rechroma.util.lexicon;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LexiconPageData {

    public Component name;
    public Component tooltip;
    public ResourceLocation icon;
    boolean multiPage;

    public LexiconPageData(ResourceLocation image, Component name, Component tooltip, boolean multiPage) {
        this.name = name;
        this.tooltip = tooltip;
        this.multiPage = multiPage;
        this.icon = image;
    }
}
