package net.coriin.rechroma.item;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier MULTI_TOOL_TIER = TierSortingRegistry.registerTier(
      new ForgeTier(3,2000,5f,4f,20,
              ModTags.Blocks.MULTI_TOOL_MINEABLE, ()-> Ingredient.of(ItemStack.EMPTY)),
              new ResourceLocation(ReChroma.MOD_ID, "multi_tool"), List.of(Tiers.IRON), List.of());
}
