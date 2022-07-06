package me.alphamode.openskies.client.models;

import me.alphamode.openskies.blocks.SieveBlock;
import me.alphamode.openskies.meshes.MeshType;
import me.alphamode.openskies.meshes.OpenMeshes;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class SieveModel extends ForwardingBakedModel {

    private final Map<String, BakedModel> meshes;

    public SieveModel(BakedModel wrapped, Map<String, BakedModel> meshes) {
        this.wrapped = wrapped;
        this.meshes = meshes;
    }

    @Override
    public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        MeshType type = state.getValue(SieveBlock.MESH_TYPE);
        if (type != OpenMeshes.NONE)
            ((FabricBakedModel)meshes.get(OpenMeshes.MESH.getKey(type).getPath())).emitBlockQuads(blockView, state, pos, randomSupplier, context);
        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
    }
}
