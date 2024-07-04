package net.coriin.rechroma.block.custom;

import net.coriin.rechroma.block.entity.CastingTableBlockEntity;
import net.coriin.rechroma.block.entity.LiquidChromaCollectorBlockEntity;
import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class CastingTableBlock extends BaseEntityBlock {

    public CastingTableBlock(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CastingTableBlockEntity) {
                ((CastingTableBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof CastingTableBlockEntity) {
                if (pPlayer.getItemInHand(pHand).is(ModItems.POWER_MANIPULATOR.get())) {
                    ((CastingTableBlockEntity) blockEntity).doCrafting = true;
                }
                else if(pPlayer.getItemInHand(pHand).is(Items.DEBUG_STICK)){
                    pPlayer.sendSystemMessage(Component.literal(String.valueOf(((CastingTableBlockEntity) blockEntity).calculateTier(pLevel,pPos, pPlayer))));
                }
            else { NetworkHooks.openScreen(((ServerPlayer) pPlayer), ((CastingTableBlockEntity) blockEntity), pPos);}
            } else {throw new IllegalStateException("Casting Table Container provider is missing");}
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.CASTING_TABLE_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CastingTableBlockEntity(blockPos, blockState);
    }

}
