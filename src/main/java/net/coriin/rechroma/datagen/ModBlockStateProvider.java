package net.coriin.rechroma.datagen;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ReChroma.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(ModBlocks.VERTICAL_PROPULSION_PANEL);
        //blockWithItem(ModBlocks.ENGRAVED_CRYSTALLINE_STONE);
        //blockWithItem(ModBlocks.EMBOSSED_CRYSTALLINE_STONE);
        //blockWithItem(ModBlocks.CRYSTALLINE_STONE_GROOVE);
        //blockWithItem(ModBlocks.CRYSTALLINE_STONE_BRICK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
