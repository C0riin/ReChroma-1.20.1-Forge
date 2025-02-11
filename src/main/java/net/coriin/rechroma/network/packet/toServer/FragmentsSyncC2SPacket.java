package net.coriin.rechroma.network.packet.toServer;

import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FragmentsSyncC2SPacket {

    CompoundTag nbt;


    public FragmentsSyncC2SPacket(){
    }

    public FragmentsSyncC2SPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public FragmentsSyncC2SPacket(CompoundTag nbt){
        //ReChroma.LOGGER.error("right constructor");
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ServerPlayer pPlayer = ctx.getSender();

        if(pPlayer == null) {
            ReChroma.LOGGER.info("Fuck this shit player null again fragments");}

        ctx.enqueueWork(() -> {
            ReChromaCapabilityHelper.syncFragments(pPlayer);
        });
        ctx.setPacketHandled(true);
    }
}
