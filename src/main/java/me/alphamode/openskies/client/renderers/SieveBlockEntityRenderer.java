package me.alphamode.openskies.client.renderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import me.alphamode.openskies.blocks.entity.SieveBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.Vec3;

public class SieveBlockEntityRenderer implements BlockEntityRenderer<SieveBlockEntity> {

    public SieveBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SieveBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder builder = tesselator.getBuilder();
        poseStack.pushPose();
        poseStack.translate(blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ());
        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_NORMAL);

        TextureAtlasSprite icon = Minecraft.getInstance().getModelManager().getBlockModelShaper().getParticleIcon(blockEntity.getBlockState());
        float minU = icon.getU0();
        float maxU = icon.getV1();
        float minV = icon.getU1();
        float maxV = icon.getU0();

        builder.vertex(0.0625f,/*fillAmount*/0.9,0.0625f).uv(minU, minV).color(255, 0, 0, 255).normal(0, 1, 0).endVertex();
        builder.vertex(0.0625f,/*fillAmount*/0.9,0.9375f).uv(minU,maxV).color(255, 0, 0, 255).normal(0, 1, 0).endVertex();
        builder.vertex(0.9375f,/*fillAmount*/0.9,0.9375f).uv(maxU,maxV).color(255, 0, 0, 255).normal(0, 1, 0).endVertex();
        builder.vertex(0.9375f,/*fillAmount*/0.9,0.0625f).uv(maxU,minV).color(255, 0, 0, 255).normal(0, 1, 0).endVertex();
        tesselator.end();
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        poseStack.popPose();
    }

    @Override
    public boolean shouldRender(SieveBlockEntity blockEntity, Vec3 vec3) {
        return true;
    }
}
