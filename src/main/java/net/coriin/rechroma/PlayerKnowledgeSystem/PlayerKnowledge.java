package net.coriin.rechroma.PlayerKnowledgeSystem;

import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class PlayerKnowledge {

    private Map<String,Boolean> progressFlags = new HashMap<String,Boolean>();
    public static String[] allFlags = {"test_flag","casting"};

    public Map<String,Boolean> getProgressFlags() {
        return progressFlags;
    }

    public String[] getAllProgressFlags() {
        return allFlags;
    }


    public void addProgressFlag(String flag, boolean value) {
        progressFlags.put(flag,value);
    }

    public void changeProgressFlag(String flag, boolean value) {
        progressFlags.replace(flag,value);
    }

    public void copyFrom(PlayerKnowledge other){
        this.progressFlags = other.progressFlags;
    }

    public void saveNBTData(CompoundTag nbt){
        for (Map.Entry<String,Boolean> entry : progressFlags.entrySet()){
            nbt.putBoolean(entry.getKey(),entry.getValue());
        }
    }
    public void loadNBTData(CompoundTag nbt){
        for (String key : allFlags){
            if(nbt.contains(key)){
                progressFlags.put(key,nbt.getBoolean(key));
            }
        }
    }
}
