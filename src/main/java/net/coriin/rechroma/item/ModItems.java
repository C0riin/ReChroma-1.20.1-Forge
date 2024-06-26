package net.coriin.rechroma.item;

import net.coriin.rechroma.ReChroma;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReChroma.MOD_ID);

    public static final RegistryObject<Item> BLACK_CRYSTAL_SHARD = ITEMS.register("black_crystal_shard", ()-> new Item((new Item.Properties())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
