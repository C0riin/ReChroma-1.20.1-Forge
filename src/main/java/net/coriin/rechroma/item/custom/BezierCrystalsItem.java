package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.auxiliary.RechromaMathHelper;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.toClient.RenderBezierCurveS2ACPacket;
import net.coriin.rechroma.sounds.ModSounds;
import net.minecraft.sounds.SoundSource;
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

import java.util.ArrayList;
import java.util.List;

public class BezierCrystalsItem extends Item {

    int range = 20;
    int max_bounds = 5;
    int currentBounds = 0;
    int ticksBetweenAttacks = 6;
    int tickElapsed = 0;
    public static int qualityParamOfParticleBezierCurve = 60;
    public int damage = 4;

    List<LivingEntity> toNotBounce = new ArrayList<>();
    private static final double REFERENCE_COS = Math.cos(Math.toRadians(60));

    public BezierCrystalsItem(Properties pProperties) {
        super(pProperties);
    }

    @Override public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
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
                tickElapsed++;
            }
        }
        //Minecraft.getInstance().level.addParticle(ModParticles.BEZIER_CRYSTALS_ATTACK_PARTICLE.get(), 0,0,0,0,0,0);
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);

    }

    @Override public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {

        if(entity instanceof Player player && tickElapsed > 40){
            player.getCooldowns().addCooldown(this,100);
        }
        tickElapsed = 0;
        currentBounds = 0;
        super.onStopUsing(stack, entity, count);
    }

    @Override public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public void doAttack(LivingEntity pLivingEntity1, LivingEntity pLivingEntity2, Level pLevel){
        if(!toNotBounce.contains(pLivingEntity2)){
            pLivingEntity2.hurt(pLivingEntity2.damageSources().magic(), damage);
            toNotBounce.add(pLivingEntity2);
            pLevel.playSeededSound(null, pLivingEntity1.getX(), pLivingEntity1.getY(), pLivingEntity1.getZ(),
                    ModSounds.BEZIER_CRYSTALS_ATTACK.get(), SoundSource.PLAYERS,
                    1f,1f,0);


            Vec3 dist = pLivingEntity2.position().subtract(pLivingEntity1.position());
            ModMessages.sendToClients(new RenderBezierCurveS2ACPacket(pLivingEntity1.position().add(RechromaMathHelper.getRandomPlusMinus1Vector()),
                    RechromaMathHelper.getRandomPlusMinus1Vector().scale(dist.length()/3).add(dist.scale(0.5).add(pLivingEntity1.position())),
                    pLivingEntity2.position().add(new Vec3(0, pLivingEntity2.getBbHeight()/2,0))));
        }

        LivingEntity newNearest = getNearestLivingEntity(pLevel, pLivingEntity1,
                RechromaMathHelper.getRandomPlusMinus1Vector());

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
        double cos;
        double dist = Integer.MAX_VALUE;

        List<LivingEntity> li = getEntitiesInRadius(pLevel, range, (int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());

        for (LivingEntity livingEntity : li) {
            if(livingEntity == pLivingEntity || toNotBounce.contains(livingEntity)) continue;

            posVector = livingEntity.position().subtract(pLivingEntity.position());
            cos = RechromaMathHelper.cosFromScalarProduct((livingEntity.position().subtract(pLivingEntity.position())).normalize(), playerVec.normalize());


            if(dist > posVector.length() && cos >= REFERENCE_COS) {
                nearest = livingEntity;
                dist = posVector.length();
            }
        }

        return nearest;
    }

    private LivingEntity getNearestLivingEntity(Level pLevel, LivingEntity pLivingEntity, Vec3 dir) {

        LivingEntity nearest = null;
        Vec3 posVector;
        double cos;
        double dist = Integer.MAX_VALUE;

        List<LivingEntity> li = getEntitiesInRadius(pLevel, range, (int)pLivingEntity.getX(), (int)pLivingEntity.getY(), (int)pLivingEntity.getZ());

        for (LivingEntity livingEntity : li) {
            if(livingEntity == pLivingEntity || toNotBounce.contains(livingEntity)) continue;

            posVector = livingEntity.position().subtract(pLivingEntity.position());

            cos = RechromaMathHelper.cosFromScalarProduct((livingEntity.position().subtract(pLivingEntity.position())).normalize(), dir.normalize());


            if(dist > posVector.length() && cos >= REFERENCE_COS) {
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
