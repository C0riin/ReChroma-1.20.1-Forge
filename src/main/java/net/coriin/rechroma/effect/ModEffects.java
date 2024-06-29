package net.coriin.rechroma.effect;

import net.coriin.rechroma.ReChroma;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ReChroma.MOD_ID);

    public static final RegistryObject<MobEffect> FALL_RESISTANCE = MOB_EFFECTS.register("fall_resistance",()->
            new FallResistanceEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static final RegistryObject<MobEffect> MULTI_JUMP = MOB_EFFECTS.register("multi_jump",()->
            new FallResistanceEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }


}
