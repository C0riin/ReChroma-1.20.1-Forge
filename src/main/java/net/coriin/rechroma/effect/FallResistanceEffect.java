package net.coriin.rechroma.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FallResistanceEffect extends MobEffect {

    int ticksCount = 0;
    int ticksOnGround = 0;

    protected FallResistanceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        //pLivingEntity.fallDistance = 0;
        pLivingEntity.resetFallDistance();



        if(pLivingEntity.onGround() && ticksCount>=60){
            ticksOnGround++;
        }
        if(ticksOnGround>=60){
            pLivingEntity.removeEffect(ModEffects.FALL_RESISTANCE.get());
            ticksCount=0;
            ticksOnGround=0;
        }
        ticksCount++;


        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
