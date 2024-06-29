package net.coriin.rechroma.item;

import net.coriin.rechroma.ReChroma;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ReChroma.MOD_ID);

    public static final RegistryObject<Item> BLACK_CRYSTAL_SHARD = ITEMS.register("black_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> BLUE_CRYSTAL_SHARD = ITEMS.register("blue_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> BROWN_CRYSTAL_SHARD = ITEMS.register("brown_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> CYAN_CRYSTAL_SHARD = ITEMS.register("cyan_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> GRAY_CRYSTAL_SHARD = ITEMS.register("gray_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> GREEN_CRYSTAL_SHARD = ITEMS.register("green_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> LIGHTBLUE_CRYSTAL_SHARD = ITEMS.register("lightblue_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> LIGHTGRAY_CRYSTAL_SHARD = ITEMS.register("lightgray_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> LIME_CRYSTAL_SHARD = ITEMS.register("lime_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> MAGENTA_CRYSTAL_SHARD = ITEMS.register("magenta_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> ORANGE_CRYSTAL_SHARD = ITEMS.register("orange_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> PINK_CRYSTAL_SHARD = ITEMS.register("pink_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> PURPLE_CRYSTAL_SHARD = ITEMS.register("purple_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> RED_CRYSTAL_SHARD = ITEMS.register("red_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> WHITE_CRYSTAL_SHARD = ITEMS.register("white_crystal_shard", ()-> new Item((new Item.Properties())));
    public static final RegistryObject<Item> YELLOW_CRYSTAL_SHARD = ITEMS.register("yellow_crystal_shard", ()-> new Item((new Item.Properties())));


    public static final RegistryObject<Item> POWER_MANIPULATOR = ITEMS.register("power_manipulator", ()-> new Item((new Item.Properties().stacksTo(1))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
