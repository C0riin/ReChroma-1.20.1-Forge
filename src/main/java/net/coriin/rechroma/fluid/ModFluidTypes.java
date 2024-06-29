package net.coriin.rechroma.fluid;

import net.coriin.rechroma.ReChroma;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation(ReChroma.MOD_ID,"block/fluids/chroma/chroma_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation(ReChroma.MOD_ID,"block/fluids/chroma/chroma_flowing");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(ReChroma.MOD_ID, "misc/in_liquid_chroma");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ReChroma.MOD_ID);

    public static final RegistryObject<FluidType> LIQUID_CHROMA_FLUID_TYPE = register("liquid_chroma_fluid",FluidType.Properties.create()
            .canConvertToSource(false).canSwim(true).lightLevel(6).density(15).viscosity(5));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL,WATER_FLOWING_RL,SOAP_OVERLAY_RL, 0xEEDDDDDD,
                new Vector3f(240f/255f,240f/255f,240f/255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
