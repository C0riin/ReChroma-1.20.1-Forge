package net.coriin.rechroma.auxiliary;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledgeProvider;
import net.coriin.rechroma.item.ModItems;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.KnowledgeC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Map;


public class ReChromaHelper {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean canSee(Player pPlayer){

        final boolean[] t = {false};

        pPlayer.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            if(knowledge.getProgressFlags().containsKey("test_flag")){
                if(knowledge.getProgressFlags().get("test_flag")){
                    t[0] = true;
                }
            }
        });

        return pPlayer.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.POWER_MANIPULATOR.get()) ||
                pPlayer.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.POWER_MANIPULATOR.get()) || t[0];
    }

    // for  knowledge system

    public static final String CASTING_PROGRESS_FLAG = "casting";

    public static int getKnowledgePower(Player pPlayer){
        final int[] power = {0};

        pPlayer.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            for(Boolean flag : knowledge.getProgressFlags().values()){
                if(flag){
                    power[0]++;
                }
            }
        });
        return power[0];
    }

    public static boolean getFlagValue(String flagName){
        boolean[] flag = {false};

        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
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

    public static boolean setFlagValue(ServerPlayer pPlayer, String flagName, boolean value){
        boolean[] t = {false};
        Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            if(Arrays.stream(knowledge.getAllProgressFlags()).toList().contains(flagName)){
                if(knowledge.getProgressFlags().containsKey(flagName)){
                    knowledge.getProgressFlags().replace(flagName, value);
                }
                else { knowledge.getProgressFlags().put(flagName, value); t[0] = true; }
            }
            else {
                LOGGER.error("No knowledge flag found for " + flagName);
            }
        });
        syncKnowledge(pPlayer);

        return t[0];
    }

    public static void syncKnowledge(ServerPlayer pPlayer){
        ModMessages.sendToPlayer(new KnowledgeC2SPacket(), pPlayer);
    }

}
