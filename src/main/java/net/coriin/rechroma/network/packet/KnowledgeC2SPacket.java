package net.coriin.rechroma.network.packet;

import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledgeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KnowledgeC2SPacket {

    public KnowledgeC2SPacket(){

    }

    public KnowledgeC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            //player.sendSystemMessage(Component.literal("packet received"));
            //player.sendSystemMessage(Minecraft.getInstance().player.getName());
            player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(serverKnowledge -> {
                Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(clientKnowledge -> {
                    clientKnowledge.copyFrom(serverKnowledge);
                });
            });

        });
        return true;
    }
}
