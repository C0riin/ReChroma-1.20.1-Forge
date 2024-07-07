package net.coriin.rechroma.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.screen.CastingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIReChromaPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ReChroma.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CastingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<net.coriin.rechroma.recipe.CastingRecipe> castingTier1Recipes = recipeManager.getAllRecipesFor(net.coriin.rechroma.recipe.CastingRecipe.Type.INSTANCE);
        registration.addRecipes(CastingRecipeCategory.CASTING_TIER_1_RECIPE_RECIPE_TYPE, castingTier1Recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CastingTableScreen.class,68,38,10,36,
                CastingRecipeCategory.CASTING_TIER_1_RECIPE_RECIPE_TYPE);
    }
}
