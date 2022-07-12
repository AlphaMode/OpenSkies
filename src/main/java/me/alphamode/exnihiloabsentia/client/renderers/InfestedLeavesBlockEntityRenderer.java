package me.alphamode.exnihiloabsentia.client.renderers;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import me.alphamode.exnihiloabsentia.blocks.entity.InfestedLeavesBlockEntity;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedLeavesBlockEntityRenderer implements BlockEntityRenderer<InfestedLeavesBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public InfestedLeavesBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(InfestedLeavesBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource bufferSource, int i, int j) {
        if (!blockEntity.hasLevel() || blockEntity.getBlockState().isAir()) return;

        Level level = blockEntity.getLevel();

        BlockPos blockPos = blockEntity.getBlockPos();
        BlockState blockState = blockEntity.getLeavesType();

        VertexConsumer buffer = bufferSource.getBuffer(ItemBlockRenderTypes.getChunkRenderType(blockState));
        blockRenderer.getModelRenderer().tesselateBlock(level, blockRenderer.getBlockModel(blockState), blockEntity.getBlockState(), blockPos, poseStack, buffer, true, new Random(), blockState.getSeed(blockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY);
    }

    @Override
    public int getViewDistance() {
        return 128;
    }
}
