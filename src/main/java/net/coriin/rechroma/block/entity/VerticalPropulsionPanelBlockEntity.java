package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class VerticalPropulsionPanelBlockEntity extends BlockEntity {

    public VerticalPropulsionPanelBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VERTICAL_PROPULSION_PANEL_BE.get(), pPos, pBlockState);
    }

    int DelayTicks = 20;
    int Ticks = 0;
    List<LivingEntity> entities;


    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if(checkStructure(pLevel, pPos)) {
            AABB aabb = AABB.of(new BoundingBox(pPos.getX()-1, pPos.getY()+1, pPos.getZ()-1,
                    pPos.getX()+1, pPos.getY()+1, pPos.getZ()+1));
            entities = pLevel.getEntitiesOfClass(LivingEntity.class, aabb);

            if(!entities.isEmpty()){
                Ticks++;
            }
            else {
                Ticks = 0;
            }
            if(Ticks >= DelayTicks){
                for(LivingEntity e : entities) {
                    e.addDeltaMovement(new Vec3(0, 5, 0));
                    e.addEffect(new MobEffectInstance(ModEffects.FALL_RESISTANCE.get(), 1200, 0, false, false));
                }
            }
        }
    }

    public boolean checkStructure(Level pLevel,BlockPos pPos) {
        return pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() + 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_GROOVE.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX(), pPos.getY(), pPos.getZ() - 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_GROOVE.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ())).getBlock() == ModBlocks.CRYSTALLINE_STONE_GROOVE.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ())).getBlock() == ModBlocks.CRYSTALLINE_STONE_GROOVE.get() &&

                pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() + 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_CORNER.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() + 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_CORNER.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX() + 1, pPos.getY(), pPos.getZ() - 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_CORNER.get() &&
                pLevel.getBlockState(new BlockPos(pPos.getX() - 1, pPos.getY(), pPos.getZ() - 1)).getBlock() == ModBlocks.CRYSTALLINE_STONE_CORNER.get();
    }
}
