package net.coriin.rechroma.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CastingRecipeCategory implements IRecipeCategory<net.coriin.rechroma.recipe.CastingRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(ReChroma.MOD_ID, "casting_tier_1");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ReChroma.MOD_ID, "textures/gui/casting_tier_1_gui.png");

    public static final RecipeType<net.coriin.rechroma.recipe.CastingRecipe> CASTING_TIER_1_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, net.coriin.rechroma.recipe.CastingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CastingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CASTING_TABLE.get()));
    }

    @Override
    public RecipeType<net.coriin.rechroma.recipe.CastingRecipe> getRecipeType() {
        return CASTING_TIER_1_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Casting");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, net.coriin.rechroma.recipe.CastingRecipe recipe, IFocusGroup iFocusGroup) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 62 + 18 * i, 37 + j * 18).addIngredients(recipe.getIngredients().get(j * 3 + i));
            }
            iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 189, 12).addItemStack(recipe.getResultItem(null));
        }
    }
}
