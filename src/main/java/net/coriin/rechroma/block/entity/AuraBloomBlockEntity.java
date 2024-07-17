package net.coriin.rechroma.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AuraBloomBlockEntity extends BlockEntity {

    public AuraBloomBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.AURA_BLOOM_BE.get(), pPos, pBlockState);
    }
}
