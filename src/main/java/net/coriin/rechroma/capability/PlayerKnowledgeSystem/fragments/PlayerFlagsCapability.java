package net.coriin.rechroma.capability.PlayerKnowledgeSystem.fragments;

import net.coriin.rechroma.capability.BaseCapability;
import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.coriin.rechroma.util.lexicon.KnowledgeCore;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class PlayerFlagsCapability extends BaseCapability {

    private Map<String,Boolean> progressFlags = new HashMap<>();

    public Map<String,Boolean> getProgressFlags() {
        return progressFlags;
    }

    public String[] getAllProgressFlags() {
        return (String[]) KnowledgeCore.ALL_PROGRESS_FLAGS.toArray();
    }


    public void addProgressFlag(String flag, boolean value) {
        progressFlags.put(flag,value);
    }

    public void changeProgressFlag(String flag, boolean value) {
        progressFlags.replace(flag,value);
    }

    public void copyFrom(PlayerFlagsCapability other){
        this.progressFlags = other.progressFlags;
    }

    @Override public void saveNBTData(CompoundTag nbt){
        for (Map.Entry<String,Boolean> entry : progressFlags.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
    }

    @Override public CompoundTag getAsNBT(){
        CompoundTag nbt = new CompoundTag();
        for (Map.Entry<String,Boolean> entry : progressFlags.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
        return nbt;
    }

    @Override public void loadNBTData(CompoundTag nbt){
        for (String key : KnowledgeCore.ALL_PROGRESS_FLAGS){
            if(nbt.contains(key)){
                progressFlags.put(key,nbt.getBoolean(key));
            }
        }
    }
}
