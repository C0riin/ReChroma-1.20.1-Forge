package net.coriin.rechroma.util.lexicon;

import net.coriin.rechroma.util.RuneData;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LexiconCraftingPageData extends LexiconPageDataBase{

    public RuneData[] runes;
    public boolean isHaveRunes;
    //public EnergyData[] energy;
    public boolean isHaveEnergy;

    public ResourceLocation recipeLocation;

    LexiconCraftingPageData(ResourceLocation image, Component name, Component tooltip, ResourceLocation recipeLocation) {
        super(image, name, "");
        this.isHaveRunes = false;
        this.isHaveEnergy = false;

        this.recipeLocation = recipeLocation;
    }
    /*
    LexiconCraftingPageData(ResourceLocation image, Component name, Component tooltip, RuneData[] runes, ResourceLocation recipeLocation) {
        super(image, name, tooltip);
        this.isHaveRunes = true;
        this.runes = runes;
        this.isHaveEnergy = false;

        this.recipeLocation = recipeLocation;
    }

    LexiconCraftingPageData(ResourceLocation image, Component name, Component tooltip, boolean multiPage, EnergyData[] energy) {
        super(image, name, tooltip, multiPage);
        this.isHaveRunes = false;
        this.isHaveEnergy = true;
        this.energy = energy;
    }

    LexiconCraftingPageData(ResourceLocation image, Component name, Component tooltip, boolean multiPage, RuneData[] runes, EnergyData[] energy) {
        super(image, name, tooltip, multiPage);
        this.isHaveRunes = true;
        this.runes = runes;
        this.isHaveEnergy = true;
        this.energy = energy;
    }*/
}
