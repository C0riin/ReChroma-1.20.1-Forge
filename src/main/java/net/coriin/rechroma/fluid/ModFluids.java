package net.coriin.rechroma.fluid;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ReChroma.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_LIQUID_CHROMA = FLUIDS.register("liquid_chroma_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.LIAUID_CHROMA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_LIQUID_CHROMA = FLUIDS.register("flowing_liquid_chroma_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.LIAUID_CHROMA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIAUID_CHROMA_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LIQUID_CHROMA_FLUID_TYPE, SOURCE_LIQUID_CHROMA, FLOWING_LIQUID_CHROMA)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.LIQUID_CHROMA_BLOCK).bucket(ModItems.LIQUID_CHROMA_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
