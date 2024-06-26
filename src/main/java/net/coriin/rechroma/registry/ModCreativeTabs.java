package net.coriin.rechroma.registry;

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
                        output.accept(ModItems.BLACK_CRYSTAL_SHARD.get());
                        output.accept(ModBlocks.CASTING_TABLE.get());
                        output.accept(ModBlocks.CRYSTALLINE_STONE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
