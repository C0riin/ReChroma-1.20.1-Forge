package net.coriin.rechroma.network.packet.toServer;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlagsSyncC2SPacket {

    CompoundTag nbt;


    public FlagsSyncC2SPacket(){
    }

    public FlagsSyncC2SPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public FlagsSyncC2SPacket(CompoundTag nbt){
        //ReChroma.LOGGER.error("right constructor");
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ServerPlayer pPlayer = ctx.getSender();

        if(pPlayer == null) {ReChroma.LOGGER.info("Fuck this shit player null again flags");}

        ctx.enqueueWork(() -> {
            ReChromaCapabilityHelper.syncFlags(pPlayer);
        });
        ctx.setPacketHandled(true);
    }
}
