package net.coriin.rechroma.recipe;

import net.coriin.rechroma.ReChroma;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Modrecipes {

    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ReChroma.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CastingTier1Recipe>> CASTING_TIER_1 = SERIALIZERS.register("casting_tier_1",
            ()-> CastingTier1Recipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
