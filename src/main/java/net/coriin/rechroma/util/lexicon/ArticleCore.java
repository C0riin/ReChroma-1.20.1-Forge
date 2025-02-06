package net.coriin.rechroma.util.lexicon;

import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.coriin.rechroma.ReChroma;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ArticleCore {

    public static final LexiconPageDataBase test1 = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
            Component.literal("blank"), ReChromaKnowledgeHelper.KnowledgeCore.BLANK_FRAG,
            null,
            new Component[]{
                    Component.literal("test article Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam volutpat tristique lobortis. Sed cursus elit nunc, et tempus arcu gravida at. Sed nunc dui, gravida a vehicula eget, posuere at massa. Donec ex nulla, maximus in lacus ac, luctus euismod magna. Morbi sem metus, pellentesque sed ex fermentum, venenatis commodo ipsum. Pellentesque nec lacus dolor. Integer erat sem, volutpat at finibus eget, commodo a neque. Nullam molestie augue quis sem finibus, non tincidunt libero pretium. Nulla lacinia nibh nisl, non efficitur nibh dapibus nec. Nulla id justo id lorem dictum sagittis vitae at felis. Etiam quis diam gravida lacus eleifend tempus nec non ante. Nunc lacus sem, imperdiet nec ex sit amet, commodo finibus ipsum. Pellentesque bibendum lacus id vulputate commodo. Praesent turpis elit, rhoncus vel mauris ut, porta interdum augue. Pellentesque eros turpis, bibendum nec massa in, maximus faucibus felis. In vitae efficitur massa. Suspendisse porta malesuada neque eget posuere. Vivamus vehicula, metus eu facilisis facilisis, nisi metus suscipit mi, non dictum elit diam ac mauris. Mauris nec ante imperdiet, elementum nulla sit amet, efficitur lectus. Sed gravida felis quis sagittis ultricies. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed in turpis tincidunt, aliquet purus ut, aliquet sem. Pellentesque tincidunt metus in purus sodales mattis. Donec luctus ac felis eget mattis. Quisque fringilla magna vel mi aliquet consequat. Sed non luctus nulla, et pellentesque nisl. Nulla ut porta eros. Duis venenatis nisl in libero vulputate, at ultricies tortor congue. Nullam vel ipsum sit amet ante feugiat consequat sit amet sit amet arcu. Ut tincidunt felis mollis finibus ultricies. Suspendisse ligula arcu, accumsan ut diam sed, sodales mattis magna. Aenean gravida, ipsum vitae posuere malesuada, est nulla sollicitudin libero, ut tempus sapien lorem quis purus."),
                    Component.literal("addition: test smth")
    });

    public static final LexiconPageDataBase start = new LexiconPageDataBase(
            new ResourceLocation(ReChroma.MOD_ID, "textures/gui/bg.png"),
            Component.literal("getting started"), ReChromaKnowledgeHelper.KnowledgeCore.START_FRAG,
            null,
            new Component[]{
                    Component.literal("This world is strange, confusing, and unknown. You have no idea what drives or forms it, something you will need to learn before you can possibly begin to exploit it and bend the forces to your needs. Fortunately for you, you have a notebook of sorts, though you will need to populate it with information you collect as you explore and experiment.")
            });

}
