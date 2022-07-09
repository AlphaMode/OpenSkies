package me.alphamode.openskies.client.models;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachedBlockView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

public class InfestedLeavesModel extends ForwardingBakedModel {

    public InfestedLeavesModel(BakedModel wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        RenderAttachedBlockView renderAttachedBlockView = (RenderAttachedBlockView)blockView;

        @Nullable
        BlockState leavesState = (BlockState)renderAttachedBlockView.getBlockEntityRenderAttachment(pos);

        context.pushTransform(quad -> {
            Direction cullFace = quad.cullFace();
            return Block.shouldRenderFace(state, blockView, pos, cullFace, pos.relative(cullFace));
        });

        ((FabricBakedModel)Minecraft.getInstance().getModelManager().getBlockModelShaper().getBlockModel(leavesState)).emitBlockQuads(blockView, state, pos, randomSupplier, context);
        context.popTransform();
    }
}
