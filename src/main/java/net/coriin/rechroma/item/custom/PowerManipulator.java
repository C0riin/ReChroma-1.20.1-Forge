package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.coriin.rechroma.auxiliary.WorldHelper;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.block.custom.PylonCoreBlock;
import net.coriin.rechroma.block.entity.PylonCoreBlockEntity;
import net.coriin.rechroma.capability.colors.ColorHelper;
import net.coriin.rechroma.capability.colors.PlayerEnergyCapability;
import net.coriin.rechroma.capability.colors.PlayerEnergyProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;


public class PowerManipulator extends Item {

    public PowerManipulator(Properties pProperties) {
        super(pProperties);
    }

    public int scanDistance = 24;

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if(!pLevel.isClientSide){
            if(pLivingEntity instanceof Player pPlayer){

                Optional<BlockPos> corePos = WorldHelper.RayCastForConcreteBlock(
                        pLevel,
                        ModBlocks.PYLON_CORE.get(),
                        pLivingEntity.getEyePosition(),
                        pLivingEntity.getLookAngle(),
                        scanDistance,
                        4
                );

                corePos.ifPresent(pos -> {

                    BlockState state = pLevel.getBlockState(pos);
                    PylonCoreBlockEntity blockEntity = (PylonCoreBlockEntity)pLevel.getBlockEntity(pos);

                    ReChroma.LOGGER.info("present");
                    pPlayer.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy ->{
                        int obtained = blockEntity.WithDrawEnergy(pPlayer, state, energy.getMaxEnergy()/200);
                        ReChromaCapabilityHelper.SyncEnergy((ServerPlayer) pPlayer);
                        ReChroma.LOGGER.info("energy obtained:" + obtained + " in type of:" + ColorHelper.Color.values()[state.getValue(PylonCoreBlock.ColorOrdinal)] +
                                " current energy: " + energy.getEnergy()[state.getValue(PylonCoreBlock.ColorOrdinal)]);
                    });

                });

            }
}

        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }
}
