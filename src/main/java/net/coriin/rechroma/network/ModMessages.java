package net.coriin.rechroma.network;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.network.packet.toClient.*;
import net.coriin.rechroma.network.packet.toServer.FlagsSyncC2SPacket;
import net.coriin.rechroma.network.packet.toServer.FragmentsSyncC2SPacket;
import net.coriin.rechroma.network.packet.toServer.LexiconC2SScreenPacket;
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

        // To Client
        net.messageBuilder(FlagsS2CPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FlagsS2CPacket::new)
                .encoder(FlagsS2CPacket::toBytes)
                .consumerMainThread(FlagsS2CPacket::handlePacket)
                .add();
        net.messageBuilder(FragmentsS2CPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FragmentsS2CPacket::new)
                .encoder(FragmentsS2CPacket::toBytes)
                .consumerMainThread(FragmentsS2CPacket::handlePacket)
                .add();
        net.messageBuilder(EnergyS2CPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergyS2CPacket::new)
                .encoder(EnergyS2CPacket::toBytes)
                .consumerMainThread(EnergyS2CPacket::handlePacket)
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
        /*net.messageBuilder(SyncCapS2CPacket.class, getId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncCapS2CPacket::new)
                .encoder(SyncCapS2CPacket::toBytes)
                .consumerMainThread(SyncCapS2CPacket::handlePacket)
                .add();*/

        // To Server
        net.messageBuilder(FlagsSyncC2SPacket.class, getId(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FlagsSyncC2SPacket::new)
                .encoder(FlagsSyncC2SPacket::toBytes)
                .consumerMainThread(FlagsSyncC2SPacket::handlePacket)
                .add();
        net.messageBuilder(FragmentsSyncC2SPacket.class, getId(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FragmentsSyncC2SPacket::new)
                .encoder(FragmentsSyncC2SPacket::toBytes)
                .consumerMainThread(FragmentsSyncC2SPacket::handlePacket)
                .add();
        net.messageBuilder(LexiconC2SScreenPacket.class, getId(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(LexiconC2SScreenPacket::new)
                .encoder(LexiconC2SScreenPacket::toBytes)
                .consumerMainThread(LexiconC2SScreenPacket::handlePacket)
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
