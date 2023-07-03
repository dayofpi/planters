package com.dayofpi.planters.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class PlanterBlockEntityRenderer implements BlockEntityRenderer<PlanterBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public PlanterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PlanterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        BlockState blockState1 = Blocks.POPPY.defaultBlockState();
        BlockState blockState2 = Blocks.DANDELION.defaultBlockState();
        BlockState blockState3 = Blocks.CORNFLOWER.defaultBlockState();
        float f = pBlockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise().toYRot();

        BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState1);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.25F, 0.5F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-f));
        pPoseStack.scale(0.8F, 0.8F, 0.8F);
        pPoseStack.translate(-0.5F, 0.0F, -0.1F);
        this.blockRenderer.renderSingleBlock(blockState1, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
        pPoseStack.translate(0.0F, 0.0F, -0.4F);
        this.blockRenderer.renderSingleBlock(blockState2, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
        pPoseStack.translate(0.0F, 0.0F, -0.35F);
        this.blockRenderer.renderSingleBlock(blockState3, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();
    }
}
