package net.coriin.rechroma.block.custom;

import net.coriin.rechroma.block.entity.LiquidChromaCollectorBlockEntity;
import net.coriin.rechroma.block.entity.VerticalPropulsionPanelBlockEntity;
import net.coriin.rechroma.fluid.ModFluids;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

public class LiquidChromaCollectorBlock extends BaseEntityBlock {



    public LiquidChromaCollectorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LiquidChromaCollectorBlockEntity(pPos,pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if(blockEntity instanceof LiquidChromaCollectorBlockEntity) {
            if(pPlayer.getItemInHand(pHand).is(Items.BUCKET)){
                if(((LiquidChromaCollectorBlockEntity) blockEntity).getFluidTank().getFluidAmount() >= 1000){
                    pPlayer.getItemInHand(pHand).shrink(1);
                    pPlayer.addItem(new ItemStack(ModItems.LIQUID_CHROMA_BUCKET.get()));
                    ((LiquidChromaCollectorBlockEntity) blockEntity).drainBuckets(1);//.drain(new FluidStack(ModFluids.SOURCE_LIQUID_CHROMA.get(), 1000), IFluidHandler.FluidAction.EXECUTE);
                }
            }
            pPlayer.sendSystemMessage(Component.literal(String.valueOf((((LiquidChromaCollectorBlockEntity) blockEntity).getFluidTank().getFluidAmount()))));
        }


        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(pEntity instanceof Player) {
            Player player = (Player)pEntity;

            if(!pLevel.isClientSide()) {
                BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                if(blockEntity instanceof LiquidChromaCollectorBlockEntity) {
                    if(((LiquidChromaCollectorBlockEntity) blockEntity).getFluidFreeSpace() > 0){
                        if(player.totalExperience >= 10) {
                            ((LiquidChromaCollectorBlockEntity) blockEntity).collectChroma(5);
                            player.giveExperiencePoints(-10);
                            EntityDataAccessor<Boolean> RECHROMA_PROGRESS_FLAG_TEST = SynchedEntityData.defineId(Player.class, EntityDataSerializers.BOOLEAN);

                        }
                    }
                }
            }
        }


        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
