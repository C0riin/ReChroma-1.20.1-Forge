package net.coriin.rechroma.PlayerKnowledgeSystem.flags;

import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsCapability;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFragmentsCapability {

    private Map<String, Boolean> fragments = new HashMap<>();

    public Map<String, Boolean> getFragments() {
        return fragments;
    }

    public List<String> getAllProgressFlags() {
        return ReChromaKnowledgeHelper.KnowledgeCore.ALL_FRAGMENTS;
    }

    public void addProgressFlag(String flag, Boolean value) {
        fragments.put(flag, value);
    }


    public void copyFrom(PlayerFragmentsCapability other){
        this.fragments = other.fragments;
    }

    public void saveNBTData(CompoundTag nbt){
        for (Map.Entry<String,Boolean> entry : fragments.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
    }

    public CompoundTag getAsNBT(){
        CompoundTag nbt = new CompoundTag();
        for (Map.Entry<String,Boolean> entry : fragments.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){
        for (String key : ReChromaKnowledgeHelper.KnowledgeCore.ALL_FRAGMENTS){
            if(nbt.contains(key)){
                fragments.put(key,nbt.getBoolean(key));
            }
        }
    }
}
