package net.coriin.rechroma.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


public class ElementalManipulator extends Item {

    public ElementalManipulator(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        pPlayer.startUsingItem(pHand);
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    int ticksCounter = 0;
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {

        if(ticksCounter >= 30){ //
            // тут делаешь магию, которая тебе меняет модель
        }
        else if(ticksCounter >= 25){ //
            // тут делаешь магию, которая тебе меняет модель
        }
        else if(ticksCounter >= 20){ //
            // тут делаешь магию, которая тебе меняет модель
        }
        else if(ticksCounter >= 15){ //
            // тут делаешь магию, которая тебе меняет модель
        }
        else if(ticksCounter >= 10){ //
            // тут делаешь магию, которая тебе меняет модель
        }
        else if(ticksCounter >= 5){ //
            // тут делаешь магию, которая тебе меняет модель
        }

        ticksCounter++;
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }
}
