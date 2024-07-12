package net.coriin.rechroma.PlayerKnowledgeSystem;

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


public class PlayerKnowledgeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerKnowledge> PLAYER_KNOWLEDGE = CapabilityManager.get(new CapabilityToken<PlayerKnowledge>(){ });

    private PlayerKnowledge playerKnowledge = null;
    private final LazyOptional<PlayerKnowledge> optional = LazyOptional.of(this::createPlayerKnowledge);

    private PlayerKnowledge createPlayerKnowledge() {
        if(this.playerKnowledge == null){
            this.playerKnowledge = new PlayerKnowledge();
        }
        return this.playerKnowledge;
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
