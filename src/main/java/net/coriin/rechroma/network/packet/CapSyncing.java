package net.coriin.rechroma.network.packet;

import net.coriin.rechroma.capability.PlayerKnowledgeSystem.flags.PlayerFragmentsProvider;
import net.coriin.rechroma.capability.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.minecraftforge.common.capabilities.Capability;

import java.util.Map;

@Deprecated
public class CapSyncing {

    public static final String CAP_NAME = "cap_name";

    public static final Map<String, Capability<?>> stringToCap = Map.ofEntries(
            Map.entry("flags", PlayerFlagsProvider.PLAYER_KNOWLEDGE),
            Map.entry("fragments", PlayerFragmentsProvider.PLAYER_FRAGMENTS)
    );



}
