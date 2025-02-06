package net.coriin.rechroma.auxiliary;

import com.mojang.logging.LogUtils;
import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;


public class ReChromaHelper {



    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean canSee(Player pPlayer){

        final boolean[] t = {false};

        pPlayer.getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(knowledge -> {
            if(knowledge.getProgressFlags().containsKey("test_flag")){
                if(knowledge.getProgressFlags().get("test_flag")){
                    t[0] = true;
                }
            }
        });

        return pPlayer.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.POWER_MANIPULATOR.get()) ||
                pPlayer.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.POWER_MANIPULATOR.get()) || t[0];
    }

    public static List<?> SubstractLists(List<?> l1, List<?> l2){
        List<?> result = List.copyOf(l1);
        for(Object el : l2){
            result.remove(el);
        }
        return result;
    }




}
