package net.coriin.rechroma.datagen;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ReChroma.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.LIQUID_CHROMA_BUCKET, "tools");

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ReChroma.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String additionalPath) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ReChroma.MOD_ID,"item/" +additionalPath+"/"+ item.getId().getPath()));
    }
}
