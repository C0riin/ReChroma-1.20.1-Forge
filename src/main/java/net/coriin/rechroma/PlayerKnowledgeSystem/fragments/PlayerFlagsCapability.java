package net.coriin.rechroma.PlayerKnowledgeSystem.fragments;

import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class PlayerFlagsCapability {

    private Map<String,Boolean> progressFlags = new HashMap<>();

    public Map<String,Boolean> getProgressFlags() {
        return progressFlags;
    }

    public String[] getAllProgressFlags() {
        return (String[])ReChromaKnowledgeHelper.KnowledgeCore.ALL_PROGRESS_FLAGS.toArray();
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

    public void saveNBTData(CompoundTag nbt){
        for (Map.Entry<String,Boolean> entry : progressFlags.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
    }

    public CompoundTag getAsNBT(){
        CompoundTag nbt = new CompoundTag();
        for (Map.Entry<String,Boolean> entry : progressFlags.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){
        for (String key : ReChromaKnowledgeHelper.KnowledgeCore.ALL_PROGRESS_FLAGS){
            if(nbt.contains(key)){
                progressFlags.put(key,nbt.getBoolean(key));
            }
        }
    }
}
