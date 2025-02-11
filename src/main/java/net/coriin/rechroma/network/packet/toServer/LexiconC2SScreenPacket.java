package net.coriin.rechroma.network.packet.toServer;

import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.toClient.LexiconS2PScreenPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LexiconC2SScreenPacket {

    public LexiconC2SScreenPacket(){

    }

    public LexiconC2SScreenPacket(FriendlyByteBuf buf){
    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ServerPlayer pPlayer = ctx.getSender();

        ctx.enqueueWork(() -> {

            ReChromaCapabilityHelper.syncFragments(pPlayer);
            ModMessages.sendToPlayer(new LexiconS2PScreenPacket(), pPlayer);

        });
        ctx.setPacketHandled(true);
    }


}
