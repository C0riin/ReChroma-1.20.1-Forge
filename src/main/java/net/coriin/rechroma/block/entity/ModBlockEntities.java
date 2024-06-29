package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ReChroma.MOD_ID);

    public static final RegistryObject<BlockEntityType<CastingTableBlockEntity>> CASTING_TABLE_BE =
            BLOCK_ENTITIES.register("casting_table_be", ()->
                    BlockEntityType.Builder.of(CastingTableBlockEntity::new, ModBlocks.CASTING_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<VerticalPropulsionPanelBlockEntity>> VERTICAL_PROPULSION_PANEL_BE =
            BLOCK_ENTITIES.register("vertical_propulsion_panel", ()->
                    BlockEntityType.Builder.of(VerticalPropulsionPanelBlockEntity::new, ModBlocks.VERTICAL_PROPULSION_PANEL.get()).build(null));

    public static final RegistryObject<BlockEntityType<LiquidChromaCollectorBlockEntity>> LIQUID_CHROMA_COLLECTOR_BE =
            BLOCK_ENTITIES.register("liquid_chroma_collector_be", ()->
                    BlockEntityType.Builder.of(LiquidChromaCollectorBlockEntity::new, ModBlocks.LIQUID_CHROMA_COLLECTOR.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
