package me.alphamode.openskies.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;

@SuppressWarnings("UnstableApiUsage")
public class BarrelRenderer implements BlockEntityRenderer<BarrelBlockEntity> {
    public BarrelRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(BarrelBlockEntity barrel, float f, PoseStack pose, MultiBufferSource buffer, int i, int j) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        ResourceAmount<FluidVariant> currentFluid = barrel.getCurrentFluid();

        TextureAtlasSprite fluidSprite = FluidVariantRendering.getSprite(currentFluid.resource());
        if (fluidSprite == null)
            return;
        QuadEmitter emitter = renderer.meshBuilder().getEmitter();

        emitter.square(Direction.UP, 0.1f, 0.1f, 1 - 0.1f, 1 - 0.1f, 1.01f - ((float) currentFluid.amount() / barrel.getCapacity()));
        emitter.spriteBake(0, fluidSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);

        int color = FluidVariantRendering.getColor(currentFluid.resource(), barrel.getLevel(), barrel.getBlockPos());
        float r = ((color >> 16) & 255) / 256f;
        float g = ((color >> 8) & 255) / 256f;
        float b = (color & 255) / 256f;

        buffer.getBuffer(RenderType.solid()).putBulkData(pose.last(), emitter.toBakedQuad(0, fluidSprite, false), r, g, b, 0x00F0_00F0, OverlayTexture.NO_OVERLAY);
    }
}
