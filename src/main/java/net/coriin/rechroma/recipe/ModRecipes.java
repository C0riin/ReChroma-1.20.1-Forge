package net.coriin.rechroma.recipe;

import net.coriin.rechroma.ReChroma;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ReChroma.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CastingRecipe>> CASTING_TIER_1 = SERIALIZERS.register("casting",
            ()-> CastingRecipe.Serializer.INSTANCE);



    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
