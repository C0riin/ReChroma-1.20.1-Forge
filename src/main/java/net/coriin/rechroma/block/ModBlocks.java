package net.coriin.rechroma.block;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.custom.CastingTableBlock;
import net.coriin.rechroma.block.custom.VerticalPropulsionPanel;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ReChroma.MOD_ID);


    public static final RegistryObject<Block> CASTING_TABLE = registerBlock("casting_table",
            ()-> new CastingTableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> VERTICAL_PROPULSION_PANEL = registerBlock("vertical_propulsion_panel",
            ()-> new VerticalPropulsionPanel(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    public static final RegistryObject<Block> CRYSTALLINE_STONE = registerBlock("crystalline_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));


    public static final RegistryObject<Block> BLACK_CRYSTAL_RUNE = registerBlock("black_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> BLUE_CRYSTAL_RUNE = registerBlock("blue_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> BROWN_CRYSTAL_RUNE = registerBlock("brown_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> CYAN_CRYSTAL_RUNE = registerBlock("cyan_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> GRAY_CRYSTAL_RUNE = registerBlock("gray_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> GREEN_CRYSTAL_RUNE = registerBlock("green_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> LIGHTBLUE_CRYSTAL_RUNE = registerBlock("lightblue_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> LIGHTGRAY_CRYSTAL_RUNE = registerBlock("lightgray_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> LIME_CRYSTAL_RUNE = registerBlock("lime_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> MAGENTA_CRYSTAL_RUNE = registerBlock("magenta_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> ORANGE_CRYSTAL_RUNE = registerBlock("orange_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> PINK_CRYSTAL_RUNE = registerBlock("pink_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> PURPLE_CRYSTAL_RUNE = registerBlock("purple_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> RED_CRYSTAL_RUNE = registerBlock("red_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> WHITE_CRYSTAL_RUNE = registerBlock("white_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));
    public static final RegistryObject<Block> YELLOW_CRYSTAL_RUNE = registerBlock("yellow_crystal_rune",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem( String name,  RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
