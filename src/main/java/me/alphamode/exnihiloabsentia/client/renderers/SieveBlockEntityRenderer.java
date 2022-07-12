package me.alphamode.exnihiloabsentia.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;

import me.alphamode.exnihiloabsentia.blocks.entity.SieveBlockEntity;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class SieveBlockEntityRenderer implements BlockEntityRenderer<SieveBlockEntity> {

    public SieveBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SieveBlockEntity sieve, float f, PoseStack pose, MultiBufferSource buffer, int i, int j) {
        QuadEmitter emitter = RendererAccess.INSTANCE.getRenderer().meshBuilder().getEmitter();

        TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getBlockModelShaper().getParticleIcon(sieve.getContent().defaultBlockState());

        emitter.square(Direction.UP, 0.0625f, 0.0625f, 0.0625f, 0.0625f, 0);
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);

        buffer.getBuffer(RenderType.solid()).putBulkData(pose.last(), emitter.toBakedQuad(0, sprite, false), 0 , 0, 0, 0x00F0_00F0, OverlayTexture.NO_OVERLAY);
    }

    @Override
    public boolean shouldRender(SieveBlockEntity blockEntity, Vec3 vec3) {
        return true;
    }
}
