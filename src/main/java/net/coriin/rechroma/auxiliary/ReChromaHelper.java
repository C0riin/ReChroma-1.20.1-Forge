package net.coriin.rechroma.auxiliary;

import net.coriin.rechroma.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

public class ReChromaHelper {

    public static boolean canSee(Player pPlayer){
        return pPlayer.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.POWER_MANIPULATOR.get());
    }

}
