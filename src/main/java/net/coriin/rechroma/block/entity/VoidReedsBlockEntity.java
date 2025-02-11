package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VoidReedsBlockEntity extends BlockEntity {
    public VoidReedsBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.VOID_REEDS_BE.get(), pPos, pBlockState);
    }
}
