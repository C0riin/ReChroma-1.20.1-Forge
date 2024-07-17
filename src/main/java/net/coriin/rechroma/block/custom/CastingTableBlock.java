package net.coriin.rechroma.block.custom;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledgeProvider;
import net.coriin.rechroma.auxiliary.ReChromaHelper;
import net.coriin.rechroma.block.entity.CastingTableBlockEntity;
import net.coriin.rechroma.block.entity.ModBlockEntities;
import net.coriin.rechroma.item.ModItems;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.KnowledgeC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import org.slf4j.Logger;


public class CastingTableBlock extends BaseEntityBlock {

    private static final Logger LOGGER = LogUtils.getLogger();

    public CastingTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
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
                    pPlayer.sendSystemMessage(Component.literal(String.valueOf(((CastingTableBlockEntity) blockEntity).calculateTier(pLevel,pPos))));
                }
            else { NetworkHooks.openScreen(((ServerPlayer) pPlayer), ((CastingTableBlockEntity) blockEntity), pPos);}
            } else {throw new IllegalStateException("Casting Table Container provider is missing");}

            // for knowledge
            if(ReChromaHelper.setFlagValue((ServerPlayer) pPlayer,"test_flag", true)){
                pPlayer.sendSystemMessage(Component.literal("здесь должна выскочить ачивка"));
            }
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
