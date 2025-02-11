package net.coriin.rechroma.auxiliary;

import net.coriin.rechroma.ReChroma;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.joml.Math;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Optional;

public class WorldHelper {

    public static BlockPos blockPosSum(BlockPos pos1, BlockPos pos2){
        return new BlockPos(pos1.getX() + pos2.getX(),
                pos1.getY() + pos2.getY(),
                pos1.getZ() + pos2.getZ());
    }

    public static BlockPos Vec3ToBlockPos(Vec3 vec){
        return new BlockPos((int)(vec.x),(int)vec.y,(int)(vec.z));
    }



    public static Optional<BlockPos> RayCastForConcreteBlock(Level pLevel, Block pBlock, Vec3 pPos, Vec3 dir, int dist, int pQuality){
        //ReChroma.LOGGER.info(dir.x  + " " + dir.y + " " + dir.z);
        float step = 1f/pQuality;
        for(float i = 0; i <= dist; i += step){
            BlockPos posToCheck = Vec3ToBlockPos(pPos.add(dir.scale(i)));
            if(pLevel.getBlockState(posToCheck).is(pBlock)){ return Optional.of(posToCheck);}
            else if(!pLevel.getBlockState(posToCheck).isAir()){
                ReChroma.LOGGER.info("No block found");
                return Optional.empty();
            }

        }
        ReChroma.LOGGER.info("No block found");
        return Optional.empty();
    }
    public static boolean RayCastForAnyBlock(){
        return false;
    }

}
