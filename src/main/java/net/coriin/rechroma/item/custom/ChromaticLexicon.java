package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.screen.lexicon.LexiconMainPageScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChromaticLexicon extends Item {



    Component LexiconTitle = Component.translatable("rechroma.gui.lexicon.main_page.title");

    public ChromaticLexicon(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(Minecraft.getInstance().player.isShiftKeyDown()){

        }
        else {
            Minecraft.getInstance().setScreen(new LexiconMainPageScreen(LexiconTitle));
        }


        return super.use(pLevel, pPlayer, pUsedHand);
    }


}
