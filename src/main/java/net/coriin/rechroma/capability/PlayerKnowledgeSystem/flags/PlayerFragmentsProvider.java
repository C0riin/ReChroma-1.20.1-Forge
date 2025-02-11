package net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags;

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

public class PlayerFragmentsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerFragmentsCapability> PLAYER_FRAGMENTS = CapabilityManager.get(new CapabilityToken<PlayerFragmentsCapability>(){ });

    private PlayerFragmentsCapability playerFragmentsCapability = null;
    private final LazyOptional<PlayerFragmentsCapability> optional = LazyOptional.of(this::createPlayerFragments);

    private PlayerFragmentsCapability createPlayerFragments() {
        if(this.playerFragmentsCapability == null){
            this.playerFragmentsCapability = new PlayerFragmentsCapability();
        }
        return this.playerFragmentsCapability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_FRAGMENTS){
            return this.optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerFragments().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerFragments().loadNBTData(nbt);
    }
}
