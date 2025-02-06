package net.coriin.rechroma.network.packet;

import net.coriin.rechroma.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.coriin.rechroma.ReChroma;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FragmentsC2SPacket {

    CompoundTag nbt;


    public FragmentsC2SPacket(){
    }

    public FragmentsC2SPacket(FriendlyByteBuf buf){
        this.nbt = buf.readNbt();
    }
    public FragmentsC2SPacket(CompoundTag nbt){
        //ReChroma.LOGGER.error("right constructor");
        this.nbt = nbt;
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(nbt);
    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {

            Minecraft.getInstance().player.getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(clientFragments -> {
                clientFragments.loadNBTData(nbt);
            });
        });
        ctx.setPacketHandled(true);
    }
}
