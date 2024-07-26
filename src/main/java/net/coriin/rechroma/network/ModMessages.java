package net.coriin.rechroma.network;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.network.packet.KnowledgeC2SPacket;
import net.coriin.rechroma.network.packet.LexiconS2PScreenPacket;
import net.coriin.rechroma.network.packet.RenderBezierCurveS2ACPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int getId(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ReChroma.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(KnowledgeC2SPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(KnowledgeC2SPacket::new)
                .encoder(KnowledgeC2SPacket::toBytes)
                .consumerMainThread(KnowledgeC2SPacket::handlePacket)
                .add();

        net.messageBuilder(LexiconS2PScreenPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(LexiconS2PScreenPacket::new)
                .encoder(LexiconS2PScreenPacket::toBytes)
                .consumerMainThread(LexiconS2PScreenPacket::handlePacket)
                .add();

        net.messageBuilder(RenderBezierCurveS2ACPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(RenderBezierCurveS2ACPacket::new)
                .encoder(RenderBezierCurveS2ACPacket::toBytes)
                .consumerMainThread(RenderBezierCurveS2ACPacket::handlePacket)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void  sendToClients(MSG message){
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
