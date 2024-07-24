package net.coriin.rechroma.block.custom;

import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.block.entity.VerticalPropulsionPanelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VerticalPropulsionPanel extends BaseEntityBlock {


    public VerticalPropulsionPanel(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }


    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        //BlockEntityType<VerticalPropulsionPanelBlockEntity> blockEntity = ModBlockEntities.VERTICAL_PROPULSION_PANEL_BE.get();

        /* //sth like that for chroma collector
        BlockEntity be = pLevel.getBlockEntity(pPos);

        if(be instanceof VerticalPropulsionPanelBlockEntity) {
            ((VerticalPropulsionPanelBlockEntity) be).checkStructure(pLevel, pPos);
        }*/

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level, pos, state, blockEntity) -> ((VerticalPropulsionPanelBlockEntity)blockEntity).tick(level, pos, state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VerticalPropulsionPanelBlockEntity(pPos, pState);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }
}
