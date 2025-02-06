package net.coriin.rechroma.auxiliary;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class RechromaMathHelper {

    public static double cosFromScalarProduct(Vec3 a, Vec3 b) {
        return (a.x*b.x + a.y*b.y + a.z*b.z)/(Math.sqrt((a.x*a.x + a.y*a.y + a.z*a.z)*(b.x*b.x + b.y*b.y + b.z*b.z)));
    }

    public static Vec3 getBezierCurvePoint(Vec3 p0, Vec3 p1, Vec3 p2, float t) {
        double x = (1-t)*((1-t)*p0.x + t*p1.x) + t*((1-t)*p1.x + t*p2.x);
        double y = (1-t)*((1-t)*p0.y + t*p1.y) + t*((1-t)*p1.y + t*p2.y);
        double z = (1-t)*((1-t)*p0.z + t*p1.z) + t*((1-t)*p1.z + t*p2.z);

        return new Vec3(x,y,z);
    }

    public static List<Vec3> getBezierCurve(Vec3 p0, Vec3 p1, Vec3 p2, int numOfPoints) {
        List<Vec3> points = new ArrayList<>();

        for(int i = 0; i <= numOfPoints; i++) {
            points.add(getBezierCurvePoint(p0, p1, p2, 1f/numOfPoints*i));
        }

        return points;
    }

    public static Vec3 getRandomPlusMinus1Vector() {
        return new Vec3((Math.random()-0.5)*2,(Math.random()-0.5)*2,(Math.random()-0.5)*2);
    }

    public static List<?> SubstractList(List<?> l1, List<?> l2){
        for(Object le : l2){
            l1.remove(le);
        }
        return l1;
    }

    public static int RandInt(int min_inclusive, int max_exclusive){
        return (int) (Math.random() * (max_exclusive - min_inclusive)) + min_inclusive;

    }

}
