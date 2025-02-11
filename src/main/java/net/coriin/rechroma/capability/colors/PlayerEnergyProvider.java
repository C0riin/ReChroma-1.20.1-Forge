package net.coriin.rechroma.capability.colors;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEnergyProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerEnergyCapability> PLAYER_ENERGY = CapabilityManager.get(new CapabilityToken<PlayerEnergyCapability>(){ });

    private PlayerEnergyCapability playerEnergyCapability = null;
    private final LazyOptional<PlayerEnergyCapability> optional = LazyOptional.of(this::createPlayerEnergy);

    private PlayerEnergyCapability createPlayerEnergy() {
        if(this.playerEnergyCapability == null){
            this.playerEnergyCapability = new PlayerEnergyCapability();
        }
        return this.playerEnergyCapability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_ENERGY){
            return this.optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerEnergy().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerEnergy().loadNBTData(nbt);
    }
}
