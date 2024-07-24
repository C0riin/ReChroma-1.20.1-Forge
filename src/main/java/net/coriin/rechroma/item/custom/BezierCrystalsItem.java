package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.auxiliary.RechromaMathHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Random;

import java.util.ArrayList;
import java.util.List;

public class BezierCrystalsItem extends Item {

    int range = 20;
    int max_bounds = 5;
    int currentBounds = 0;
    int ticksBetweenAttacks = 6;
    int tickElapsed = 0;
    List<LivingEntity> toNotBounce = new ArrayList<>();

    public BezierCrystalsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if(pLivingEntity instanceof Player pPlayer){
            if(!pLevel.isClientSide){
                if(tickElapsed >= ticksBetweenAttacks){
                    currentBounds = 0;
                    toNotBounce.clear();

                    LivingEntity nearest = getNearestLivingEntity(pLevel, pPlayer);
                    if(nearest != null) {
                        toNotBounce.add(pLivingEntity);
                        doAttack(pPlayer,nearest,pLevel);
                    }
                }
                //currentBounds = 0;
                //List<LivingEntity> toNotBounce = new ArrayList<>();
                tickElapsed++;
            }
        }
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);

    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        tickElapsed = 0;
        currentBounds = 0;
        super.onStopUsing(stack, entity, count);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public void doAttack(LivingEntity pLivingEntity1, LivingEntity pLivingEntity2, Level pLevel){
        if(!toNotBounce.contains(pLivingEntity2)){
            pLivingEntity2.hurt(pLivingEntity2.damageSources().magic(), 2);
            toNotBounce.add(pLivingEntity2);
        }

        LivingEntity newNearest = getNearestLivingEntity(pLevel, pLivingEntity1,
                new Vec3((Math.random()-0.5)*2, (Math.random()-0.5)*2, (Math.random()-0.5)*2));

        if(newNearest != null && max_bounds > currentBounds){
            currentBounds++;
            doAttack(pLivingEntity2, newNearest, pLevel);
        }


        tickElapsed = 0;
    }

    private LivingEntity getNearestLivingEntity(Level pLevel, LivingEntity pLivingEntity) {
        Vec3 playerVec = pLivingEntity.getLookAngle();

        LivingEntity nearest = null;
        Vec3 posVector;
        Vec3 posVectorNormalized;
        double cos;
        double dist = Integer.MAX_VALUE;

        List<LivingEntity> li = getEntitiesInRadius(pLevel, range, (int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());

        for (LivingEntity livingEntity : li) {
            if(livingEntity == pLivingEntity || toNotBounce.contains(livingEntity)) continue;

            posVector = livingEntity.position().subtract(pLivingEntity.position());
            posVectorNormalized = (livingEntity.position().subtract(pLivingEntity.position())).normalize();
            cos = RechromaMathHelper.cosFromScalarProduct(posVectorNormalized, playerVec.normalize());


            if(dist > posVector.length() && cos >= 0.5) {
                nearest = livingEntity;
                dist = posVector.length();
            }
        }

        return nearest;
    }

    private LivingEntity getNearestLivingEntity(Level pLevel, LivingEntity pLivingEntity, Vec3 dir) {

        LivingEntity nearest = null;
        Vec3 posVector;
        Vec3 posVectorNormalized;
        double cos;
        double dist = Integer.MAX_VALUE;

        List<LivingEntity> li = getEntitiesInRadius(pLevel, range, (int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());

        for (LivingEntity livingEntity : li) {
            if(livingEntity == pLivingEntity || toNotBounce.contains(livingEntity)) continue;

            posVector = livingEntity.position().subtract(pLivingEntity.position());
            posVectorNormalized = (livingEntity.position().subtract(pLivingEntity.position())).normalize();
            cos = RechromaMathHelper.cosFromScalarProduct(posVectorNormalized, dir.normalize());


            if(dist > posVector.length() && cos >= 0.5) {
                nearest = livingEntity;
                dist = posVector.length();
            }
        }

        return nearest;
    }

    private List<LivingEntity> getEntitiesInRadius(Level pLevel, int r, int x, int y, int z) {
        return pLevel.getEntitiesOfClass(LivingEntity.class, new AABB(x-r,y-r,z-r,x+r,y+r,z+r));
    }


}
