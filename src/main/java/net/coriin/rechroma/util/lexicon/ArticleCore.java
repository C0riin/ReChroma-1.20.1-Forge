package net.coriin.rechroma.util.lexicon;

import net.coriin.rechroma.auxiliary.ReChromaCapabilityHelper;
import net.coriin.rechroma.ReChroma;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ArticleCore {

    public static final LexiconPageDataBase test1 = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
            Component.literal("blank"), KnowledgeCore.BLANK_FRAG,
            null,
            new Component[]{
                    Component.literal("test article Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam volutpat tristique lobortis. Sed cursus elit nunc, et tempus arcu gravida at. Sed nunc dui, gravida a vehicula eget, posuere at massa. Donec ex nulla, maximus in lacus ac, luctus euismod magna. Morbi sem metus, pellentesque sed ex fermentum, venenatis commodo ipsum. Pellentesque nec lacus dolor. Integer erat sem, volutpat at finibus eget, commodo a neque. Nullam molestie augue quis sem finibus, non tincidunt libero pretium. Nulla lacinia nibh nisl, non efficitur nibh dapibus nec. Nulla id justo id lorem dictum sagittis vitae at felis. Etiam quis diam gravida lacus eleifend tempus nec non ante. Nunc lacus sem, imperdiet nec ex sit amet, commodo finibus ipsum. Pellentesque bibendum lacus id vulputate commodo. Praesent turpis elit, rhoncus vel mauris ut, porta interdum augue. Pellentesque eros turpis, bibendum nec massa in, maximus faucibus felis. In vitae efficitur massa. Suspendisse porta malesuada neque eget posuere. Vivamus vehicula, metus eu facilisis facilisis, nisi metus suscipit mi, non dictum elit diam ac mauris. Mauris nec ante imperdiet, elementum nulla sit amet, efficitur lectus. Sed gravida felis quis sagittis ultricies. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed in turpis tincidunt, aliquet purus ut, aliquet sem. Pellentesque tincidunt metus in purus sodales mattis. Donec luctus ac felis eget mattis. Quisque fringilla magna vel mi aliquet consequat. Sed non luctus nulla, et pellentesque nisl. Nulla ut porta eros. Duis venenatis nisl in libero vulputate, at ultricies tortor congue. Nullam vel ipsum sit amet ante feugiat consequat sit amet sit amet arcu. Ut tincidunt felis mollis finibus ultricies. Suspendisse ligula arcu, accumsan ut diam sed, sodales mattis magna. Aenean gravida, ipsum vitae posuere malesuada, est nulla sollicitudin libero, ut tempus sapien lorem quis purus."),
                    Component.literal("addition: test smth")
    });
    public static final LexiconPageDataBase start = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/lexicon/article_icons/start.png"),
            Component.literal("Getting started"), KnowledgeCore.START_FRAG,
            null,
            new Component[]{
                    Component.literal("This world is strange, confusing, and unknown. You have no idea what drives or forms it, something you will need to learn before you can possibly begin to exploit it and bend the forces to your needs. Fortunately for you, you have a notebook of sorts, though you will need to populate it with information you collect as you explore and experiment.")
            });
    public static final LexiconPageDataBase theLexicon = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/lexicon/article_icons/the_lexicon.png"),
            Component.literal("The lexicon"), KnowledgeCore.LEXICON_FRAG,
            null,
            new Component[]{
                    Component.literal("Seeing as you are learning this as you go along, you will be required to piece together your understanding from various fragments of information, supplemented by your own reasoning and experimentation; rarely will one item or event hold all of the information you need. In particular, various notes, though incomplete on their own, might become more complete when thought of together. Also, for some reason, sneaking when navigating the book accelerates the process.")
            });
    public static final LexiconPageDataBase crystals = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/lexicon/article_icons/crystals.png"),
            Component.literal("Crystals"), KnowledgeCore.CRYSTALS_FRAG,
            null,
            new Component[]{
                    Component.literal("Crystal energy, if it reaches a critical density, seems to coalesce and crystallize, forming interesting structures that radiate an aura related to the crystal. Aside from their obvious utility as a source of mild effect boosts, their stored crystal energy is clearly going to be invaluable. One wonders how they handle the corrupting influence of the Nether...")
            });
    public static final LexiconPageDataBase pylons = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/lexicon/article_icons/pylons.png"),
            Component.literal("Pylons"), KnowledgeCore.PYLONS_FRAG,
            null,
            new Component[]{
                    Component.literal("At points where the energy field seems to be the strongest, you tend to find dark stone structures adorned with sparkling aura and topped with a beacon that visibly and audibly radiates raw power. Unfiltered and uncontained, they seem too powerful and unstable - a lesson you quickly learned - to be easily used, but you suspect that later on they will be a key component of your research.")
            });




    public static List<List<LexiconGroupDataBase>> GetAllArticles(){

        final LexiconGroupDataBase entryLevel = new LexiconGroupDataBase(Component.literal("Entry level"),
                new LexiconPageDataBase[]{ start, theLexicon, crystals, pylons});
        final LexiconGroupDataBase exploration = new LexiconGroupDataBase(Component.literal("Exploration"),
                new LexiconPageDataBase[]{ start, theLexicon, crystals, pylons});

        final LexiconGroupDataBase articleGroup2 = new LexiconGroupDataBase(Component.literal("test group"),
                new LexiconPageDataBase[]{test1,test1,test1,test1,test1});
        final List<LexiconGroupDataBase> articleRows = List.of(entryLevel,articleGroup2,articleGroup2);
        final List<List<LexiconGroupDataBase>> allArticles = List.of(articleRows,articleRows,articleRows);

        return allArticles;
    }

    public static List<LexiconGroupDataBase> GetLongestRow(){
        List<LexiconGroupDataBase> longest = new ArrayList<>();
        for(List<LexiconGroupDataBase> row : GetAllArticles()) {
            if(row.size() > longest.size()) { longest = List.copyOf(row);}
        }
        return longest;
    }



}
