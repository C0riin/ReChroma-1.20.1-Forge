package net.coriin.rechroma.auxiliary;

import com.mojang.logging.LogUtils;
import jdk.jfr.Description;
import net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.capability.PlayerKnowledgeSystem.fragments.PlayerFlagsCapability;
import net.coriin.rechroma.capability.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.coriin.rechroma.capability.colors.PlayerEnergyProvider;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.toClient.EnergyS2CPacket;
import net.coriin.rechroma.network.packet.toClient.FlagsS2CPacket;
import net.coriin.rechroma.network.packet.toClient.FragmentsS2CPacket;
import net.coriin.rechroma.util.lexicon.KnowledgeCore;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReChromaCapabilityHelper {

    private static final Logger LOGGER = LogUtils.getLogger();




    //Knowledge
    public static void InitKnowledge(PlayerFlagsCapability playerKnowledge){
        playerKnowledge.addProgressFlag(KnowledgeCore.DEFAULT_PROGRESS_FLAG, true);
        for(String flag : KnowledgeCore.ALL_PROGRESS_FLAGS){
            playerKnowledge.addProgressFlag(flag, false);
        }
    }
    @Description("Should be replaced with getFlagValue when special flags be added")
    @Deprecated()
    public static int getKnowledgePower(Player pPlayer){
        final int[] power = {0};

        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            for(Boolean flag : knowledge.getProgressFlags().values()){
                if(flag){
                    power[0]++;
                }
            }
        });
        return power[0];
    }
    public static boolean getFlagValue(ServerPlayer pPlayer, String flagName){
        boolean[] flag = {false};

        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            if(knowledge.getProgressFlags().containsKey(flagName)){
                if(knowledge.getProgressFlags().get(flagName)){
                    flag[0] = true;
                }
            }
            else {
                LOGGER.error("No knowledge flag found for " + flagName);
            }
        });
        return flag[0];
    }
    @Deprecated()
    @Description("Replaced with UnlockFlag and ForgetFlag")
    public static boolean setFlagValue(ServerPlayer pPlayer, String flagName, boolean value){
        boolean[] t = {false};
        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            if(Arrays.stream(knowledge.getAllProgressFlags()).toList().contains(flagName)){
                if(knowledge.getProgressFlags().containsKey(flagName)){
                    knowledge.getProgressFlags().replace(flagName, value);
                }
                else { knowledge.getProgressFlags().put(flagName, value); /*here we need do some visual effects and shaders magic*/ t[0] = true; }
            }
            else {
                LOGGER.error("No knowledge flag found for " + flagName);
            }
        });
        syncFlags(pPlayer);

        return t[0];
    }
    @Description("Should be redacted align comments inside")
    public static void UnlockFlag(ServerPlayer pPlayer, String flagName){
        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(playerFlags -> {
            if(playerFlags.getProgressFlags().containsKey(flagName)){
                if(!playerFlags.getProgressFlags().get(flagName)){
                    playerFlags.getProgressFlags().replace(flagName, true);

                    // DO THE VISUAL MAGIC HERE
                }
            } else { playerFlags.getProgressFlags().put(flagName, true);}

        });
        syncFlags(pPlayer);
    }
    public static void ForgetFlag(ServerPlayer pPlayer, String flagName){
        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(playerKnowledge -> {
            if(playerKnowledge.getProgressFlags().containsKey(flagName)){
                playerKnowledge.getProgressFlags().replace(flagName, false);
            } else { playerKnowledge.getProgressFlags().put(flagName, false);}
        });
        syncFlags(pPlayer);
    }
    //@OnlyIn(Dist.DEDICATED_SERVER)
    public static void syncFlags(ServerPlayer pPlayer){
        CompoundTag[] tag = new CompoundTag[]{new CompoundTag()};
        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            tag[0] = knowledge.getAsNBT();
        });
        ModMessages.sendToPlayer(new FlagsS2CPacket(tag[0]), pPlayer);

        //tag[0].putString(CapSyncing.CAP_NAME, "flags");
        //ModMessages.sendToPlayer(new SyncCapS2CPacket(tag[0]), pPlayer);
    }
    public List<String> GetPotentialFlags(ServerPlayer pPlayer){
        List<String> result = List.of();



        return result;
    }

    //Fragment
    public static boolean GetFragmentValue(ServerPlayer pPlayer, String fragmentName){
        boolean[] t = {true};

        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(playerFragments -> {
            if(!(playerFragments.getFragments().containsKey(fragmentName) && playerFragments.getFragments().get(fragmentName))){
                t[0] = false;
            }
        });

        return t[0];
    }
    public static void UnlockFragment(ServerPlayer pPlayer, String fragmentName){
        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(playerFragments -> {

            if(playerFragments.getFragments().containsKey(fragmentName)){
                if(!playerFragments.getFragments().get(fragmentName)){
                    playerFragments.getFragments().replace(fragmentName, true);
                    // DO THE VISUAL MAGIC HERE
                }
            } else {
                playerFragments.getFragments().put(fragmentName, true);
                //DO THE VISUAL MAGIC HERE
            }
        });
        syncFragments(pPlayer);
    }
    public static void ForgetFragment(ServerPlayer pPlayer, String fragmentName){
        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(playerFragments -> {
            if(playerFragments.getFragments().containsKey(fragmentName)){
                playerFragments.getFragments().replace(fragmentName, false);
            } else { playerFragments.getFragments().put(fragmentName, false);}

        });
        syncFragments(pPlayer);
    }
    public static void syncFragments(ServerPlayer pPlayer){
        //ReChroma.LOGGER.info("syncing");
        CompoundTag[] tag = new CompoundTag[]{new CompoundTag()};
        pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(fragments -> {
            tag[0] = fragments.getAsNBT();
        });
        //ReChroma.LOGGER.info("sending packet");

        ModMessages.sendToPlayer(new FragmentsS2CPacket(tag[0]), pPlayer);

        // test for common syncing
        //tag[0].putString(CapSyncing.CAP_NAME, "fragments");
        //ModMessages.sendToPlayer(new SyncCapS2CPacket(tag[0]),pPlayer);
    }
    public static List<String> GetPotentialFragments(ServerPlayer pPlayer){
        List<String> result = new ArrayList<>();

        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(flags -> {
            for (Map.Entry<String,Boolean> entry : flags.getProgressFlags().entrySet()){
                if(entry.getValue()) { result.addAll(KnowledgeCore.FLAG2FRAG.get(entry.getKey())); }
            }

            pPlayer.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(fragments -> {
                for(Map.Entry<String,Boolean> entry : fragments.getFragments().entrySet()){
                    if(entry.getValue()) {result.remove(entry.getKey());}
                }
            });

        });



        return result;
    }


    //Energy
    public static void SyncEnergy(ServerPlayer pPlayer){
        CompoundTag[] tag = new CompoundTag[]{new CompoundTag()};
        pPlayer.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(energy -> {
            tag[0] = energy.getAsNBT();
        });

        ModMessages.sendToPlayer(new EnergyS2CPacket(tag[0]), pPlayer);
    }

}
