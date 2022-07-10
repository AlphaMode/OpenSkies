package me.alphamode.openskies.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import me.alphamode.openskies.barrel.BarrelItemStorage;
import me.alphamode.openskies.blocks.entity.BarrelBlockEntity;
import me.alphamode.openskies.util.Color;
import me.alphamode.star.transfer.FluidTank;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("UnstableApiUsage")
public class BarrelRenderer implements BlockEntityRenderer<BarrelBlockEntity> {
    public BarrelRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(BarrelBlockEntity barrel, float f, PoseStack pose, MultiBufferSource buffer, int i, int j) {
        BarrelItemStorage barrelItemStorage = barrel.getItemStorage(null);
        FluidTank barrelFluidTank = barrel.getFluidStorage(null);

        if (barrelFluidTank.getAmount() > 0)
            renderFluid(barrel, pose, buffer);
        if (barrelItemStorage.getAmount() > 0)
            renderBlockFace(barrel, barrelItemStorage.getStack(), pose, buffer);
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(RenderType.translucent());
    }

    protected void renderFluid(BarrelBlockEntity barrel, PoseStack pose, MultiBufferSource buffer) {
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

    protected void renderBlockFace(BarrelBlockEntity barrel, ItemStack content, PoseStack pose, MultiBufferSource buffer) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        ResourceLocation id = Registry.BLOCK.getKey(((BlockItem)content.getItem()).getBlock());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation(id.getNamespace(), "block/" + id.getPath()));
        if (sprite == null)
            return;
        QuadEmitter emitter = renderer.meshBuilder().getEmitter();

        emitter.square(Direction.UP, 0.1f, 0.1f, 1 - 0.1f, 1 - 0.1f, .1f);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);
        Color color = barrel.getCurrentColor();
        buffer.getBuffer(RenderType.solid()).putBulkData(pose.last(), emitter.toBakedQuad(0, sprite, false), color.r, color.g, color.b, 0x00F0_00F0, OverlayTexture.NO_OVERLAY);
    }
}
