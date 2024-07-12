package net.coriin.rechroma.PlayerKnowledgeSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class KnowledgeData {

    public Component title;
    public Component tooltip;

    public ResourceLocation icon;

    public Component[] description;

    boolean isMultiPage = false;

    public KnowledgeData(Component title, Component tooltip, boolean isMultiPage){
        this.title = title;
        this.tooltip = tooltip;
        this.isMultiPage = isMultiPage;

    }
}
