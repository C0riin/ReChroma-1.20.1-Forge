package net.coriin.rechroma.capability;

import net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags.PlayerFragmentsCapability;
import net.minecraft.nbt.CompoundTag;

import java.util.Map;

public abstract class BaseCapability {

    public abstract void saveNBTData(CompoundTag nbt);

    public abstract CompoundTag getAsNBT();

    public abstract void loadNBTData(CompoundTag nbt);
}
