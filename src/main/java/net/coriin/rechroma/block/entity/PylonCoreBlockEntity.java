package net.coriin.rechroma.block.entity;

import net.coriin.rechroma.block.ModBlockEntities;
import net.coriin.rechroma.block.custom.PylonCoreBlock;
import net.coriin.rechroma.capability.colors.ColorHelper;
import net.coriin.rechroma.capability.colors.PlayerEnergyProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PylonCoreBlockEntity extends BlockEntity {
    public PylonCoreBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PYLON_CORE_BE.get(), pPos, pBlockState);
        //EnergyType =
    }

    public int energy = 0;
    int maxEnergy = 1000000;
    int perTick = 8;

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        energy = Math.min(energy + perTick, maxEnergy);
    }

    @Override protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("energy", energy);

        super.saveAdditional(pTag);
    }
    @Override public void load(CompoundTag pTag) {
        super.load(pTag);
        energy = pTag.getInt("energy");
    }


    public int WithDrawEnergy(Player pPlayer, BlockState pState, int amount){
        boolean[] t = {false};
        pPlayer.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(playerEnergy -> {
            int redo = playerEnergy.AddEnergy(getEnergyType(pState), Math.min(amount, maxEnergy));
            addEnergy(redo);
            if(redo == amount){
                t[0] = true;
            }
        });
        if(t[0]) {return 0;}
        return Math.min(amount, maxEnergy);
    }
    /*
    public int WithDrawEnergy(BlockState pState, int amount){
        pPlayer.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(playerEnergy -> {
            int redo = playerEnergy.AddEnergy(getEnergyType(pState), Math.min(amount, maxEnergy));
            addEnergy(redo);
        });
        return Math.min(amount, maxEnergy);
    }*/

    public int addEnergy(int amount){
        int toReturn = 0;
        if(energy + amount > maxEnergy){
            toReturn = maxEnergy - amount - energy;
        }
        energy = Math.min(energy + amount, maxEnergy);

        return toReturn;
    }

    public ColorHelper.Color getEnergyType(BlockState pState){
        return ColorHelper.Color.values()[pState.getValue(PylonCoreBlock.ColorOrdinal)];
    }
}
