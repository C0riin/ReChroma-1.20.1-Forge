package net.coriin.rechroma.auxiliary;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class WorldHelper {

    public static BlockPos blockPosSum(BlockPos pos1, BlockPos pos2){
        return new BlockPos(pos1.getX() + pos2.getX(),
                pos1.getY() + pos2.getY(),
                pos1.getZ() + pos2.getZ());
    }

    public static BlockPos Vec3fToBlockPos(Vector3f vec){
        return new BlockPos((int)(vec.x-0.5),(int)vec.y,(int)(vec.z-0.5));
    }
}
