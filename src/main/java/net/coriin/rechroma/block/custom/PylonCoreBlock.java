package net.coriin.rechroma.block.custom;

import net.coriin.rechroma.block.ModBlockEntities;
import net.coriin.rechroma.block.entity.PylonCoreBlockEntity;
import net.coriin.rechroma.capability.colors.ColorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class PylonCoreBlock extends BaseEntityBlock {

    public static final IntegerProperty ColorOrdinal = IntegerProperty.create("color",0,15);


    public PylonCoreBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PylonCoreBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        pState.getValue(ColorOrdinal);
        return createTickerHelper(pBlockEntityType, ModBlockEntities.PYLON_CORE_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        //super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ColorOrdinal);
    }

    public ColorHelper.Color GetColor(BlockState pState){
        return ColorHelper.Color.values()[pState.getValue(ColorOrdinal)];
    }

}
