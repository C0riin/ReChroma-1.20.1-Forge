package net.coriin.rechroma.network.packet;

import jdk.jfr.Enabled;
import net.coriin.rechroma.capability.BaseCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Deprecated
public class SyncCapS2CPacket {

    CompoundTag nbt;


    public SyncCapS2CPacket(){
    }

    public SyncCapS2CPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public SyncCapS2CPacket(CompoundTag nbt){
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {

            Minecraft.getInstance().player.getCapability(CapSyncing.stringToCap.get(nbt.getString(CapSyncing.CAP_NAME))).ifPresent(capability -> {
                ((BaseCapability)capability).loadNBTData(nbt);
            });
        });
        ctx.setPacketHandled(true);
    }
}
