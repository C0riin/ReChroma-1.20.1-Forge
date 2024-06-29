package net.coriin.rechroma.util;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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
                        output.accept(ModBlocks.VERTICAL_PROPULSION_PANEL.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
