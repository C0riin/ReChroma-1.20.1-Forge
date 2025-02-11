package net.coriin.rechroma.capability.colors;

import net.minecraft.nbt.CompoundTag;

public class PlayerEnergyCapability {

    private int[] energy = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public int[] getEnergy() {
        return energy;
    }

    public int AddEnergy(ColorHelper.Color color, int amount) {
        return AddEnergy(color.ordinal(), amount);
    }

    public int AddEnergy(int index, int amount) {
        int toReturn = 0;
        if(energy.length == 0){ energy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};}
        if(energy[index] + amount > getMaxEnergy()){
            toReturn = getMaxEnergy() - amount - energy[index];
        }
        energy[index] = Math.min(energy[index] + amount, getMaxEnergy());

        return toReturn;
    }

    public int WithDrawEnergy(ColorHelper.Color color, int amount){
        return WithDrawEnergy(color.ordinal(),amount);
    }
    public int WithDrawEnergy(int index, int amount){
        if(energy[index] >= amount){
            energy[index] -= amount;
            return amount;
        }
        int tmp = energy[index];
        energy[index] = 0;
        return tmp;

    }

    public void copyFrom(PlayerEnergyCapability other){
        this.energy = other.energy;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putIntArray("energy", energy);
    }

    public CompoundTag getAsNBT(){
        CompoundTag nbt = new CompoundTag();
        nbt.putIntArray("energy", energy);
        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){
        energy = nbt.getIntArray("energy");
    }

    public int getNonNullEnergyColorsCount(){
        int nonNull = 0;
        for(int el : energy){
            if(el > 0) { nonNull++;}
        }
        return nonNull;
    }

    public int getMaxEnergy() {
        return 6000*getNonNullEnergyColorsCount() + 4000;
    }
}
