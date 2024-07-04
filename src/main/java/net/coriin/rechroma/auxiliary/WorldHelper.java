package net.coriin.rechroma.auxiliary;

import net.minecraft.core.BlockPos;

public class WorldHelper {

    public static BlockPos blockPosSum(BlockPos pos1, BlockPos pos2){
        return new BlockPos(pos1.getX() + pos2.getX(),
                pos1.getY() + pos2.getY(),
                pos1.getZ() + pos2.getZ());
    }
}
