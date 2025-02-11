package net.coriin.rechroma.util.lexicon;

import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Map;

public class KnowledgeCore {

    //region FLAGS
    // tier 1
    public static final String DEFAULT_PROGRESS_FLAG = "default"; // shouldn't be used anywhere

    public static final String CASTING_PROGRESS_FLAG = "casting";

    public static final List<String> ALL_PROGRESS_FLAGS = List.of(
            CASTING_PROGRESS_FLAG
    );
    public static final Map<String, List<String>> FLAG2FLAG = Map.ofEntries(
            Map.entry(DEFAULT_PROGRESS_FLAG, List.of(CASTING_PROGRESS_FLAG))
    );

    //endregion

    //region FRAGMENTS
    public static final String BLANK_FRAG = "blank";

    public static final String START_FRAG = "start";
    public static final String LEXICON_FRAG = "lexicon";
    public static final String CRYSTALS_FRAG = "crystals";
    public static final String PYLONS_FRAG = "pylons";

    public static final List<String> ALL_FRAGMENTS = List.of(
            START_FRAG,
            LEXICON_FRAG,
            CRYSTALS_FRAG,
            PYLONS_FRAG
    );
    public static final Map<String, List<String>> FLAG2FRAG = Map.ofEntries(
            Map.entry(DEFAULT_PROGRESS_FLAG, List.of(START_FRAG, LEXICON_FRAG, CRYSTALS_FRAG, PYLONS_FRAG))
    );

    public static final Map<String, Component> FRAG2COMPONENT = Map.ofEntries(
            Map.entry(BLANK_FRAG, Component.translatable("rechroma.fragment.name.blank")),
            Map.entry(START_FRAG, Component.translatable("rechroma.fragment.name.start")),
            Map.entry(LEXICON_FRAG, Component.translatable("rechroma.fragment.name.lexicon"))
    );
    //endregion
}
