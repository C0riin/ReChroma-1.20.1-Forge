package net.coriin.rechroma.PlayerKnowledgeSystem.fragments;

import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
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


public class PlayerFlagsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerFlagsCapability> PLAYER_KNOWLEDGE = CapabilityManager.get(new CapabilityToken<PlayerFlagsCapability>(){ });

    private PlayerFlagsCapability playerFragmentsCapability = null;
    private final LazyOptional<PlayerFlagsCapability> optional = LazyOptional.of(this::createPlayerKnowledge);

    private PlayerFlagsCapability createPlayerKnowledge() {
        if(this.playerFragmentsCapability == null){
            this.playerFragmentsCapability = new PlayerFlagsCapability();
        }
        ReChromaKnowledgeHelper.InitKnowledge(this.playerFragmentsCapability);
        return this.playerFragmentsCapability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_KNOWLEDGE){
            return this.optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerKnowledge().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerKnowledge().loadNBTData(nbt);
    }
}
