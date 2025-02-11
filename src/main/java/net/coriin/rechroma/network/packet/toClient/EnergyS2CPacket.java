package net.coriin.rechroma.network.packet.toClient;

import net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.capability.colors.PlayerEnergyProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergyS2CPacket {

    CompoundTag nbt;


    public EnergyS2CPacket(){
    }

    public EnergyS2CPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public EnergyS2CPacket(CompoundTag nbt){
        //ReChroma.LOGGER.error("right constructor");
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {
            Minecraft.getInstance().player.getCapability(PlayerEnergyProvider.PLAYER_ENERGY).ifPresent(clientEnergy -> {
                clientEnergy.loadNBTData(nbt);
            });
        });
        ctx.setPacketHandled(true);
    }
}
