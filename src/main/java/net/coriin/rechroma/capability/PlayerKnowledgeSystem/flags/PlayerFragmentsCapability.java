package net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags;

import net.coriin.rechroma.capability.BaseCapability;
import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.coriin.rechroma.util.lexicon.KnowledgeCore;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFragmentsCapability extends BaseCapability {

    private Map<String, Boolean> fragments = new HashMap<>();

    public Map<String, Boolean> getFragments() {
        return fragments;
    }

    public List<String> getAllProgressFlags() {
        return KnowledgeCore.ALL_FRAGMENTS;
    }

    public void addProgressFlag(String flag, Boolean value) {
        fragments.put(flag, value);
    }


    public void copyFrom(PlayerFragmentsCapability other){
        this.fragments = other.fragments;
    }

    @Override public void saveNBTData(CompoundTag nbt){
        for (Map.Entry<String,Boolean> entry : fragments.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
    }

    @Override public CompoundTag getAsNBT(){
        CompoundTag nbt = new CompoundTag();
        for (Map.Entry<String,Boolean> entry : fragments.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
        return nbt;
    }

    @Override public void loadNBTData(CompoundTag nbt){
        for (String key : KnowledgeCore.ALL_FRAGMENTS){
            if(nbt.contains(key)){
                fragments.put(key,nbt.getBoolean(key));
            }
        }
    }
}
