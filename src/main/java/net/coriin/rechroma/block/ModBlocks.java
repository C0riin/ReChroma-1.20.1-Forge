package net.coriin.rechroma.block;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.custom.*;
import net.coriin.rechroma.fluid.ModFluids;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
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
    public static final RegistryObject<Block> LIQUID_CHROMA_COLLECTOR = registerBlock("liquid_chroma_collector",
            ()-> new LiquidChromaCollectorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CRYSTAL_BREW_STAND = registerBlock("crystal_brew_stand",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));


    public static final RegistryObject<Block> CRYSTALLINE_STONE = registerBlock("crystalline_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> CRYSTALLINE_STONE_CORNER = registerBlock("crystalline_stone_corner",
            ()-> new CrystallineStoneCornerBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> CRYSTALLINE_STONE_GROOVE = registerBlock("crystalline_stone_groove",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> CRYSTALLINE_STONE_BEAM = registerBlock("crystalline_stone_beam",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> CRYSTALLINE_STONE_BRICK = registerBlock("crystalline_stone_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> CRYSTALLINE_STONE_COLUMN = registerBlock("crystalline_stone_column",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> ENGRAVED_CRYSTALLINE_STONE = registerBlock("engraved_crystalline_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> EMBOSSED_CRYSTALLINE_STONE = registerBlock("embossed_crystalline_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.DEEPSLATE)));



    public static final RegistryObject<Block> TRAVEL_PATH = registerBlock("travel_path",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).speedFactor(1.5f).jumpFactor(1.5f)));

    public static final RegistryObject<Block> CASTING_STAND = registerBlock("casting_stand",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> SPECTRUM_FLOWER = registerBlock("spectrum_flower",
            ()-> new FlowerBlock(()-> MobEffects.NIGHT_VISION , 100, BlockBehaviour.Properties.copy(Blocks.DANDELION).offsetType(BlockBehaviour.OffsetType.NONE)));

    // Progress viewable plants
    public static final RegistryObject<Block> AURA_BLOOM_FLOWER = registerBlock("aura_bloom",
            ()-> new AuraBloomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.NONE)));
    public static final RegistryObject<Block> VOID_REEDS = registerBlock("void_reeds",
            ()-> new VoidReedsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.NONE)));


    public static final RegistryObject<LiquidBlock> LIQUID_CHROMA_BLOCK = BLOCKS.register("liquid_chroma_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_LIQUID_CHROMA, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));


    public static final RegistryObject<Block> BLACK_CRYSTAL_RUNE = registerBlock("black_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),0));
    public static final RegistryObject<Block> BLUE_CRYSTAL_RUNE = registerBlock("blue_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),1));
    public static final RegistryObject<Block> BROWN_CRYSTAL_RUNE = registerBlock("brown_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),2));
    public static final RegistryObject<Block> CYAN_CRYSTAL_RUNE = registerBlock("cyan_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),3));
    public static final RegistryObject<Block> GRAY_CRYSTAL_RUNE = registerBlock("gray_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),4));
    public static final RegistryObject<Block> GREEN_CRYSTAL_RUNE = registerBlock("green_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),5));
    public static final RegistryObject<Block> LIGHTBLUE_CRYSTAL_RUNE = registerBlock("lightblue_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),6));
    public static final RegistryObject<Block> LIGHTGRAY_CRYSTAL_RUNE = registerBlock("lightgray_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),7));
    public static final RegistryObject<Block> LIME_CRYSTAL_RUNE = registerBlock("lime_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),8));
    public static final RegistryObject<Block> MAGENTA_CRYSTAL_RUNE = registerBlock("magenta_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),9));
    public static final RegistryObject<Block> ORANGE_CRYSTAL_RUNE = registerBlock("orange_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),10));
    public static final RegistryObject<Block> PINK_CRYSTAL_RUNE = registerBlock("pink_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),11));
    public static final RegistryObject<Block> PURPLE_CRYSTAL_RUNE = registerBlock("purple_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),12));
    public static final RegistryObject<Block> RED_CRYSTAL_RUNE = registerBlock("red_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),13));
    public static final RegistryObject<Block> WHITE_CRYSTAL_RUNE = registerBlock("white_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),14));
    public static final RegistryObject<Block> YELLOW_CRYSTAL_RUNE = registerBlock("yellow_crystal_rune",
            ()-> new RuneBlock(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 8),15));

    public static final RegistryObject<Block> PURPLE_CRYSTAL_BLOCK = registerBlock("purple_crystal_block",
            ()-> new Block(BlockBehaviour.Properties.copy(ModBlocks.CRYSTALLINE_STONE.get()).lightLevel(value -> 12)));


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
