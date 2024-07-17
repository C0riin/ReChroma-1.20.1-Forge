package net.coriin.rechroma.network.packet;

import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledgeProvider;
import net.coriin.rechroma.ReChroma;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KnowledgeC2SPacket {

    CompoundTag nbt;


    public KnowledgeC2SPacket(){
    }

    public KnowledgeC2SPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public KnowledgeC2SPacket(CompoundTag nbt){
        ReChroma.LOGGER.error("right constructor");
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {

            Minecraft.getInstance().player.getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(clientKnowledge -> {
                clientKnowledge.loadNBTData(nbt);
            });
        });
        ctx.setPacketHandled(true);
    }
}
