package net.coriin.rechroma.datagen;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ReChroma.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.CRYSTALLINE_STONE.get(),

                        ModBlocks.BLACK_CRYSTAL_RUNE.get(),
                        ModBlocks.BLUE_CRYSTAL_RUNE.get(),
                        ModBlocks.BROWN_CRYSTAL_RUNE.get(),
                        ModBlocks.CYAN_CRYSTAL_RUNE.get(),
                        ModBlocks.GRAY_CRYSTAL_RUNE.get(),
                        ModBlocks.GREEN_CRYSTAL_RUNE.get(),
                        ModBlocks.LIGHTBLUE_CRYSTAL_RUNE.get(),
                        ModBlocks.LIGHTGRAY_CRYSTAL_RUNE.get(),
                        ModBlocks.LIME_CRYSTAL_RUNE.get(),
                        ModBlocks.MAGENTA_CRYSTAL_RUNE.get(),
                        ModBlocks.ORANGE_CRYSTAL_RUNE.get(),
                        ModBlocks.PINK_CRYSTAL_RUNE.get(),
                        ModBlocks.PURPLE_CRYSTAL_RUNE.get(),
                        ModBlocks.RED_CRYSTAL_RUNE.get(),
                        ModBlocks.WHITE_CRYSTAL_RUNE.get(),
                        ModBlocks.YELLOW_CRYSTAL_RUNE.get(),

                        ModBlocks.CASTING_TABLE.get(),
                        ModBlocks.LIQUID_CHROMA_COLLECTOR.get(),
                        ModBlocks.VERTICAL_PROPULSION_PANEL.get()
                );


        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        ModBlocks.CRYSTALLINE_STONE.get(),

                        ModBlocks.BLACK_CRYSTAL_RUNE.get(),
                        ModBlocks.BLUE_CRYSTAL_RUNE.get(),
                        ModBlocks.BROWN_CRYSTAL_RUNE.get(),
                        ModBlocks.CYAN_CRYSTAL_RUNE.get(),
                        ModBlocks.GRAY_CRYSTAL_RUNE.get(),
                        ModBlocks.GREEN_CRYSTAL_RUNE.get(),
                        ModBlocks.LIGHTBLUE_CRYSTAL_RUNE.get(),
                        ModBlocks.LIGHTGRAY_CRYSTAL_RUNE.get(),
                        ModBlocks.LIME_CRYSTAL_RUNE.get(),
                        ModBlocks.MAGENTA_CRYSTAL_RUNE.get(),
                        ModBlocks.ORANGE_CRYSTAL_RUNE.get(),
                        ModBlocks.PINK_CRYSTAL_RUNE.get(),
                        ModBlocks.PURPLE_CRYSTAL_RUNE.get(),
                        ModBlocks.RED_CRYSTAL_RUNE.get(),
                        ModBlocks.WHITE_CRYSTAL_RUNE.get(),
                        ModBlocks.YELLOW_CRYSTAL_RUNE.get(),

                        ModBlocks.CASTING_TABLE.get(),
                        ModBlocks.LIQUID_CHROMA_COLLECTOR.get(),
                        ModBlocks.VERTICAL_PROPULSION_PANEL.get()
                );


    }

}
