package net.coriin.rechroma.screen;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.screen.lexicon.LexiconFragmentMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ReChroma.MOD_ID);


    public static final RegistryObject<MenuType<CastingTableMenu>> CASTING_TABLE_MENU = registerMenuType("casting_table_menu", CastingTableMenu::new);

    public static final RegistryObject<MenuType<LexiconFragmentMenu>> LEXICON_FRAGMENTS_MENU = registerMenuType("lexicon_fragments_menu", LexiconFragmentMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
