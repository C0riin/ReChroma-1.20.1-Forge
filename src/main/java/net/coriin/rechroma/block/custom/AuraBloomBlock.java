package net.coriin.rechroma.block.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.coriin.rechroma.auxiliary.ReChromaHelper;
import net.coriin.rechroma.block.entity.AuraBloomBlockEntity;
import net.coriin.rechroma.util.IProgressViewable;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class AuraBloomBlock extends Block  implements EntityBlock, IProgressViewable { //

    public AuraBloomBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return ReChromaHelper.canSee(player) || player.isShiftKeyDown();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!canSurvive(pState,pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }


        Tesselator tes = Tesselator.getInstance();
        BufferBuilder bfb = tes.getBuilder();

        bfb.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.BLOCK);
        //int lightValue = pLevel.getBrightness(LightLayer.BLOCK, pPos);
        RenderSystem.setShaderColor(1,1,1,1);

        bfb.vertex(pPos.getX(),pPos.getY(),pPos.getZ()).endVertex();
        bfb.vertex(pPos.getX()+1,pPos.getY()+1,pPos.getZ()+1).endVertex();
        bfb.vertex(pPos.getX()+2,pPos.getY()+2,pPos.getZ()+2).endVertex();

        bfb.end();
        tes.end();


        super.tick(pState, pLevel, pPos, pRandom);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AuraBloomBlockEntity(pPos, pState);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState state = pLevel.getBlockState(pPos.below());
        return state.is(BlockTags.DIRT);

    }


}
