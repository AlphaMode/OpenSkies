package me.alphamode.openskies.client.renderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.alphamode.openskies.blocks.entity.InfestedBlockEntity;
import me.alphamode.openskies.client.models.InfestedLeavesModel;
import me.alphamode.openskies.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;

public class InfestedLeavesBlockEntityRenderer implements BlockEntityRenderer<InfestedBlockEntity> {

    public InfestedLeavesBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(InfestedBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        BlockState leafState = blockEntity.getLeavesState();
        BakedModel model = Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(blockEntity.getLeavesState());
        Color green = new Color(BiomeColors.getAverageFoliageColor(blockEntity.getLevel(), blockEntity.getBlockPos()));
        Color newColor = Color.average(green, new Color(1f, 1f, 1f, 1f), (float) Math.pow(blockEntity.getProgress(), 2));
        Minecraft.getInstance().getBlockRenderer().getModelRenderer()
                .renderModel(poseStack.last(), multiBufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(leafState, false)), leafState, model, newColor.r, newColor.g, newColor.b, i, j);
    }
}
