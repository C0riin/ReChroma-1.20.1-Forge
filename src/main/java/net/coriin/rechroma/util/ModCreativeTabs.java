package net.coriin.rechroma.util;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReChroma.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_RECHROMA_TAB = CREATIVE_TABS.register("rechroma_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.BLACK_CRYSTAL_SHARD.get())).title(Component.translatable("creativetab.rechroma.main"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.POWER_MANIPULATOR.get());
                        output.accept(ModItems.CHROMATIC_LEXICON.get());

                        output.accept(ModItems.LIQUID_CHROMA_BUCKET.get());

                        output.accept(ModItems.AURA_DUST.get());
                        output.accept(ModItems.VOID_ESSENCE.get());
                        output.accept(ModItems.CHROMIC_DUST.get());

                        output.accept(ModItems.MULTI_TOOL.get());
                        output.accept(ModItems.ORE_HARVESTER.get());

                        output.accept(ModItems.BLACK_CRYSTAL_SHARD.get());
                        output.accept(ModItems.BLUE_CRYSTAL_SHARD.get());
                        output.accept(ModItems.BROWN_CRYSTAL_SHARD.get());
                        output.accept(ModItems.CYAN_CRYSTAL_SHARD.get());
                        output.accept(ModItems.GRAY_CRYSTAL_SHARD.get());
                        output.accept(ModItems.GREEN_CRYSTAL_SHARD.get());
                        output.accept(ModItems.LIGHTBLUE_CRYSTAL_SHARD.get());
                        output.accept(ModItems.LIGHTGRAY_CRYSTAL_SHARD.get());
                        output.accept(ModItems.LIME_CRYSTAL_SHARD.get());
                        output.accept(ModItems.MAGENTA_CRYSTAL_SHARD.get());
                        output.accept(ModItems.ORANGE_CRYSTAL_SHARD.get());
                        output.accept(ModItems.PINK_CRYSTAL_SHARD.get());
                        output.accept(ModItems.PURPLE_CRYSTAL_SHARD.get());
                        output.accept(ModItems.RED_CRYSTAL_SHARD.get());
                        output.accept(ModItems.WHITE_CRYSTAL_SHARD.get());
                        output.accept(ModItems.YELLOW_CRYSTAL_SHARD.get());


                        output.accept(ModItems.INFO_FRAGMENT.get());


                    }).build());


    public static final RegistryObject<CreativeModeTab> BLOCK_RECHROMA_TAB = CREATIVE_TABS.register("rechroma_tab_blocks",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.CASTING_TABLE.get())).title(Component.translatable("creativetab.rechroma.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BLACK_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.BLUE_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.BROWN_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.CYAN_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.GRAY_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.GREEN_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.LIGHTBLUE_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.LIGHTGRAY_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.LIME_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.MAGENTA_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.ORANGE_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.PINK_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.PURPLE_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.RED_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.WHITE_CRYSTAL_RUNE.get());
                        output.accept(ModBlocks.YELLOW_CRYSTAL_RUNE.get());

                        output.accept(ModBlocks.CASTING_TABLE.get());
                        output.accept(ModBlocks.CASTING_STAND.get());
                        output.accept(ModBlocks.VERTICAL_PROPULSION_PANEL.get());
                        output.accept(ModBlocks.LIQUID_CHROMA_COLLECTOR.get());
                        output.accept(ModBlocks.CRYSTAL_BREW_STAND.get());

                        output.accept(ModBlocks.CRYSTALLINE_STONE.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE_CORNER.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE_GROOVE.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE_BEAM.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE_BRICK.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE_COLUMN.get());
                        output.accept(ModBlocks.ENGRAVED_CRYSTALLINE_STONE.get());
                        output.accept(ModBlocks.EMBOSSED_CRYSTALLINE_STONE.get());

                        output.accept(ModBlocks.TRAVEL_PATH.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> WORLD_GEN_RECHROMA_TAB = CREATIVE_TABS.register("rechroma_tab_world_gen",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModBlocks.CASTING_TABLE.get())).title(Component.translatable("creativetab.rechroma.world_gen"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.AURA_BLOOM_FLOWER.get());
                        output.accept(ModBlocks.VOID_REEDS.get());

                        output.accept(ModBlocks.PURPLE_CRYSTAL_BLOCK.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
