package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.network.ModMessages;
import net.coriin.rechroma.network.packet.LexiconS2PScreenPacket;
import net.coriin.rechroma.screen.lexicon.LexiconFragmentMenu;
import net.coriin.rechroma.screen.lexicon.LexiconMainPageScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ChromaticLexicon extends Item implements MenuProvider {

    public static Component LexiconTitle = Component.translatable("rechroma.gui.lexicon.main_page.title");
    public int fragmentInvSize = 36;

    public ChromaticLexicon(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide){
            if(pPlayer.isShiftKeyDown()){
                NetworkHooks.openScreen((ServerPlayer) pPlayer, ((ChromaticLexicon)pPlayer.getItemInHand(pUsedHand).getItem()));
            }
            else {
                ModMessages.sendToPlayer(new LexiconS2PScreenPacket(), (ServerPlayer) pPlayer);
            }
        }

        pPlayer.getCooldowns().addCooldown(this, 20);

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("rechroma.gui.lexicon.main_page.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new LexiconFragmentMenu(pContainerId, pPlayerInventory,
                pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ChromaticLexicon ?
                        pPlayer.getItemInHand(InteractionHand.MAIN_HAND) : pPlayer.getItemInHand(InteractionHand.OFF_HAND));
    }
}
