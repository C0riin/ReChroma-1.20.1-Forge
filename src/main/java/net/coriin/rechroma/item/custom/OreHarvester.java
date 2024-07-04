package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.auxiliary.WorldHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OreHarvester extends PickaxeItem {

    public OreHarvester(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    int blockCounter = 0;

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {

        if(super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving)){

            //var tagManager = ForgeRegistries.BLOCKS.tags();
            //var tagKey = tagManager.createTagKey(new ResourceLocation("forge:ores"));
            //var oresTag = tagManager.getTag(tagKey);

            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(0,1,0))).is(pState.getBlock()) && blockCounter <=20){ //&& pState.is(oresTag.getKey())
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(0,1,0)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(0,-1,0))).is(pState.getBlock())&& blockCounter <=20){
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(0,-1,0)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(1, 0,0))).is(pState.getBlock())&& blockCounter <=20){
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(1,0,0)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(-1,0,0))).is(pState.getBlock())&& blockCounter <=20){
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(-1,0,0)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(0, 0,1))).is(pState.getBlock())&& blockCounter <=20){
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(0,0,1)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            if(pLevel.getBlockState(WorldHelper.blockPosSum(pPos, new BlockPos(0,0,-1))).is(pState.getBlock())&& blockCounter <=20){
                try { mineBlock(pStack, pLevel, pState, WorldHelper.blockPosSum(pPos, new BlockPos(0,0,-1)), pEntityLiving); blockCounter++;} catch (Exception e) { }
            }
            blockCounter = 0;
            return true;
        }
        return false;
    }
}
