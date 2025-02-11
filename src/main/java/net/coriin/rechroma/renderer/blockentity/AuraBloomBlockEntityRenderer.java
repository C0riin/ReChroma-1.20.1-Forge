package net.coriin.rechroma.renderer.blockentity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.coriin.rechroma.auxiliary.ReChromaHelper;
import net.coriin.rechroma.block.ModBlocks;
import net.coriin.rechroma.block.entity.AuraBloomBlockEntity;
import net.coriin.rechroma.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.joml.Matrix4f;

public class AuraBloomBlockEntityRenderer  implements BlockEntityRenderer<AuraBloomBlockEntity>{
    public AuraBloomBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {

    }

    @Override
    public void render(AuraBloomBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if(ReChromaHelper.canSee(Minecraft.getInstance().player)) {
            renderSingleBlock(ModBlocks.AURA_BLOOM_FLOWER.get().defaultBlockState(),
                    pPoseStack, pBuffer, pPackedLight, pPackedOverlay, ModelData.EMPTY, null);
        }


        /*

        BlockPos pos = pBlockEntity.getBlockPos();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        pPoseStack.pushPose();
        Matrix4f mm = pPoseStack.last().pose();

        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(mm,(float)pos.getX()-1, (float)pos.getY() + 1, (float)pos.getZ()-1).color(1,1,1,1).endVertex();
        bufferbuilder.vertex(mm,(float)pos.getX() -1, (float)pos.getY() + 1, (float)pos.getZ() + 1).color(1,1,1,1).endVertex();
        bufferbuilder.vertex(mm, (float)pos.getX() + 1, (float)pos.getY() + 1, (float)pos.getZ() + 1).color(1,1,1,1).endVertex();
        bufferbuilder.vertex(mm, (float)pos.getX() + 1, (float)pos.getY() + 1, (float)pos.getZ() - 1).color(1,1,1,1).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());

        pPoseStack.popPose();

        */
    }
    public void renderSingleBlock(BlockState state, PoseStack p_110914_, MultiBufferSource p_110915_,
                                  int pPackedLight, int p_110917_, net.minecraftforge.client.model.data.ModelData modelData, net.minecraft.client.renderer.RenderType renderType) {
        BakedModel bakedmodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
        int i = Minecraft.getInstance().getBlockColors().getColor(state, null, null, 0);
        for (RenderType rt : bakedmodel.getRenderTypes(state, RandomSource.create(42), modelData))
            Minecraft.getInstance().getBlockRenderer().getModelRenderer().
                    renderModel(p_110914_.last(), p_110915_.getBuffer(renderType != null ? renderType :
                                    net.minecraftforge.client.RenderTypeHelper.getEntityRenderType(rt, false)),
                            state, bakedmodel, 255f, 255f, 255f, pPackedLight, p_110917_, modelData, rt);
        //LOGGER.error(String.valueOf(pPackedLight));
    }

    @Override
    public boolean shouldRenderOffScreen(AuraBloomBlockEntity blockEntity) {
        return true;
    }


}
