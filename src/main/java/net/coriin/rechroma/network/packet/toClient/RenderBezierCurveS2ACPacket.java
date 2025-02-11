package net.coriin.rechroma.network.packet.toClient;

import net.coriin.rechroma.auxiliary.RechromaMathHelper;
import net.coriin.rechroma.item.custom.BezierCrystalsItem;
import net.coriin.rechroma.particles.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class RenderBezierCurveS2ACPacket {

    Vec3 a,b,c;

    public RenderBezierCurveS2ACPacket(Vec3 a, Vec3 b, Vec3 c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public RenderBezierCurveS2ACPacket(FriendlyByteBuf buf){
        a = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
        b = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
        c = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeDouble(a.x);
        buf.writeDouble(a.y);
        buf.writeDouble(a.z);

        buf.writeDouble(b.x);
        buf.writeDouble(b.y);
        buf.writeDouble(b.z);

        buf.writeDouble(c.x);
        buf.writeDouble(c.y);
        buf.writeDouble(c.z);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            renderCurve(a,b,c);
        });
        ctx.setPacketHandled(true);
    }
    @OnlyIn(Dist.CLIENT)
    public static void renderCurve(Vec3 a, Vec3 b, Vec3 c) {
        List<Vec3> bezierCurve = RechromaMathHelper.getBezierCurve(a,b,c, BezierCrystalsItem.qualityParamOfParticleBezierCurve);
        for(Vec3 point : bezierCurve){
            Minecraft.getInstance().level.addParticle(
                    ModParticles.BEZIER_CRYSTALS_ATTACK_PARTICLE.get(), point.x, point.y,point.z,0,0,0);
        }

    }
}
