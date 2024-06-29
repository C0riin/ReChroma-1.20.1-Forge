package net.coriin.rechroma.fluid;

import net.coriin.rechroma.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class LiquidChromaBlock extends LiquidBlock {

    public LiquidChromaBlock(FlowingFluid pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }



    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof LivingEntity) {
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.SATURATION, 60));
            ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60));
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
