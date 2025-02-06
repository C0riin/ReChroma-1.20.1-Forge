package net.coriin.rechroma.events;

import net.coriin.rechroma.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsCapability;
import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.coriin.rechroma.ReChroma;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReChroma.MOD_ID)
public class ModEventHandler {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).isPresent()) {
                event.addCapability(new ResourceLocation(ReChroma.MOD_ID, "flags_cap"), new PlayerFlagsProvider());
            }
            if (!event.getObject().getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).isPresent()) {
                event.addCapability(new ResourceLocation(ReChroma.MOD_ID, "fragments_cap"), new PlayerFragmentsProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFlagsProvider.PLAYER_KNOWLEDGE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFragmentsProvider.PLAYER_FRAGMENTS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerFlagsCapability.class);
        event.register(PlayerFragmentsProvider.class);
    }

    @SubscribeEvent
    public static void syncCap(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer) { // && Minecraft.getInstance().getConnection() != null
            ReChromaKnowledgeHelper.syncFlags((ServerPlayer) event.getEntity());
            ReChromaKnowledgeHelper.syncFragments((ServerPlayer) event.getEntity());
        }
    }



}
