package com.dayofpi.planters.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class PlanterRenderer implements BlockEntityRenderer<PlanterBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public PlanterRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    private BlockState getState(Block block) {
        if (block instanceof DoublePlantBlock)
            return block.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
        return block.defaultBlockState();
    }

    @Override
    public void render(PlanterBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();

        BlockState blockState1;
        BlockState blockState2;
        BlockState blockState3;

        if (pBlockEntity.getPlant(PlanterBlock.Slot.RIGHT).getItem() instanceof BlockItem blockItem) {
            blockState1 = getState(blockItem.getBlock());
        } else blockState1 = Blocks.AIR.defaultBlockState();
        if (pBlockEntity.getPlant(PlanterBlock.Slot.MIDDLE).getItem() instanceof BlockItem blockItem) {
            blockState2 = getState(blockItem.getBlock());
        } else blockState2 = Blocks.AIR.defaultBlockState();
        if (pBlockEntity.getPlant(PlanterBlock.Slot.LEFT).getItem() instanceof BlockItem blockItem) {
            blockState3 = getState(blockItem.getBlock());
        } else blockState3 = Blocks.AIR.defaultBlockState();

        float f = pBlockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise().toYRot();

        BakedModel bakedModel = this.blockRenderer.getBlockModel(blockState1);
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
