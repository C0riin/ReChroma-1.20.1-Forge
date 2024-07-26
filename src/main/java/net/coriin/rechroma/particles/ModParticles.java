package net.coriin.rechroma.particles;

import net.coriin.rechroma.ReChroma;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ReChroma.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BEZIER_CRYSTALS_ATTACK_PARTICLE =
            PARTICLE_TYPES.register("bezier_crystals_attack_particle", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
