package net.coriin.rechroma.network.packet;

import net.coriin.rechroma.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.item.custom.ChromaticLexicon;
import net.coriin.rechroma.screen.lexicon.LexiconMainPageScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LexiconS2PScreenPacket {

    public LexiconS2PScreenPacket(){

    }

    public LexiconS2PScreenPacket(FriendlyByteBuf buf){
    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public void handlePacket(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {

            openScreen();
        });
        ctx.setPacketHandled(true);
    }
    @OnlyIn(Dist.CLIENT)
    public static void openScreen() {
        Minecraft.getInstance().setScreen(new LexiconMainPageScreen(ChromaticLexicon.LexiconTitle));
    }

}
