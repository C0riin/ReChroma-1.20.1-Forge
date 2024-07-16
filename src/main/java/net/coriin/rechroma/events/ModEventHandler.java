package net.coriin.rechroma.events;

import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledge;
import net.coriin.rechroma.PlayerKnowledgeSystem.PlayerKnowledgeProvider;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.auxiliary.ReChromaHelper;
import net.coriin.rechroma.item.custom.ChromaticLexicon;
import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.KnowledgeC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReChroma.MOD_ID)
public class ModEventHandler {



    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).isPresent()) {
                event.addCapability(new ResourceLocation(ReChroma.MOD_ID, "properties"), new PlayerKnowledgeProvider());
            }
        }
    }

    /*
    @SubscribeEvent
    public static void onAttachCapabilitiesItem(AttachCapabilitiesEvent<ItemStack> event) {
        if(event.getObject().getItem() instanceof ChromaticLexicon) {
            event
        }
    }*/

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerKnowledgeProvider.PLAYER_KNOWLEDGE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerKnowledge.class);
    }

    @SubscribeEvent
    public static void syncCap(EntityJoinLevelEvent event) {
        if(event.getEntity() instanceof Player && Minecraft.getInstance().getConnection() != null){
            ReChromaHelper.syncKnowledge();
        }
    }
}
