package net.coriin.rechroma.auxiliary;

import net.minecraft.world.phys.Vec3;

public class RechromaMathHelper {

    public static double cosFromScalarProduct(Vec3 a, Vec3 b) {
        return (a.x*b.x + a.y*b.y + a.z*b.z)/(Math.sqrt((a.x*a.x + a.y*a.y + a.z*a.z)*(b.x*b.x + b.y*b.y + b.z*b.z)));
    }

}
