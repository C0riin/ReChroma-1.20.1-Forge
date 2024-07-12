package net.coriin.rechroma.datagen.loot;

import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.BLACK_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.BLUE_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.BROWN_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.CYAN_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.GRAY_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.GREEN_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.LIGHTBLUE_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.LIGHTGRAY_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.LIME_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.MAGENTA_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.ORANGE_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.PINK_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.PURPLE_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.RED_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.WHITE_CRYSTAL_RUNE.get());
        this.dropSelf(ModBlocks.YELLOW_CRYSTAL_RUNE.get());

        this.dropSelf(ModBlocks.CRYSTALLINE_STONE.get());
        this.dropSelf(ModBlocks.CRYSTALLINE_STONE_CORNER.get());
        this.dropSelf(ModBlocks.CRYSTALLINE_STONE_GROOVE.get());
        this.dropSelf(ModBlocks.CRYSTALLINE_STONE_BEAM.get());
        this.dropSelf(ModBlocks.CRYSTALLINE_STONE_BRICK.get());
        this.dropSelf(ModBlocks.CRYSTALLINE_STONE_COLUMN.get());
        this.dropSelf(ModBlocks.ENGRAVED_CRYSTALLINE_STONE.get());
        this.dropSelf(ModBlocks.EMBOSSED_CRYSTALLINE_STONE.get());

        this.dropSelf(ModBlocks.SPECTRUM_FLOWER.get());

        this.dropSelf(ModBlocks.CASTING_TABLE.get());
        this.dropSelf(ModBlocks.LIQUID_CHROMA_COLLECTOR.get());
        this.dropSelf(ModBlocks.VERTICAL_PROPULSION_PANEL.get());

    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
