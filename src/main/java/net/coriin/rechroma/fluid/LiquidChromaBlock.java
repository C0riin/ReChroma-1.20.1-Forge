package net.coriin.rechroma.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class LiquidChromaBlock extends LiquidBlock {

    public LiquidChromaBlock(FlowingFluid pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        List<LivingEntity> livingEntity = pLevel.getEntitiesOfClass(LivingEntity.class, new AABB(0,0,0,1,0,1));

        for(LivingEntity le : livingEntity) {
            le.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60));
            le.addEffect(new MobEffectInstance(MobEffects.SATURATION, 60));
        }
        super.tick(pState, pLevel, pPos, pRandom);
    }
}
