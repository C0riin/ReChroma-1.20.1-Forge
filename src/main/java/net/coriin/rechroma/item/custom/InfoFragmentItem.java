package net.coriin.rechroma.item.custom;

import net.coriin.rechroma.PlayerKnowledgeSystem.fragments.PlayerFlagsProvider;
import net.coriin.rechroma.PlayerKnowledgeSystem.ReChromaKnowledgeHelper;
import net.coriin.rechroma.ReChroma;
import net.coriin.rechroma.auxiliary.RechromaMathHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InfoFragmentItem extends Item {

    public InfoFragmentItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!pLevel.isClientSide){
            String fragmentName;
            ItemStack pStack = pPlayer.getItemInHand(pUsedHand);

            if(pStack.getOrCreateTag().contains("rechroma.fragment.name")){
                fragmentName = pStack.getTag().getString("rechroma.fragment.name");
            } else {
                fragmentName = ReChromaKnowledgeHelper.KnowledgeCore.BLANK_FRAG;
            }


            if(Objects.equals(fragmentName, "blank")) {
                List<String> a = ReChromaKnowledgeHelper.GetPotentialFragments((ServerPlayer) pPlayer);
                if(!a.isEmpty()) {
                    pStack.getTag().putString("rechroma.fragment.name", a.get(RechromaMathHelper.RandInt(0, a.size())));
                }
            }
            else {
                if(!ReChromaKnowledgeHelper.GetFragmentValue((ServerPlayer) pPlayer,fragmentName)){
                    ReChromaKnowledgeHelper.UnlockFragment((ServerPlayer) pPlayer, pStack.getTag().getString("rechroma.fragment.name"));
                    pStack.shrink(1);
                }

            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        if(pStack.getOrCreateTag().contains("rechroma.fragment.name")){
            String name = pStack.getTag().getString("rechroma.fragment.name");
            pTooltipComponents.add(ReChromaKnowledgeHelper.KnowledgeCore.FRAG2COMPONENT.get(name));
        } else {
            pTooltipComponents.add(ReChromaKnowledgeHelper.KnowledgeCore.FRAG2COMPONENT.get(ReChromaKnowledgeHelper.KnowledgeCore.BLANK_FRAG));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
