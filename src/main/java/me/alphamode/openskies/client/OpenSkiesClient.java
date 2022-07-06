package me.alphamode.openskies.client;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.client.models.InfestedLeavesModel;
import me.alphamode.openskies.client.models.SieveModel;
import me.alphamode.openskies.client.renderers.InfestedLeavesBlockEntityRenderer;
import me.alphamode.openskies.client.renderers.SieveBlockEntityRenderer;
import me.alphamode.openskies.meshes.MeshType;
import me.alphamode.openskies.meshes.OpenMeshes;
import me.alphamode.star.client.models.ModelSwapper;
import me.alphamode.star.events.client.ModelBakeEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class OpenSkiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(OpenBlockEntities.INFESTED_LEAVES, InfestedLeavesBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(OpenBlockEntities.SIEVE, SieveBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(), OpenBlocks.INFESTED_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), OpenBlocks.SIEVE);
        ModelBakeEvent.ON_MODEL_BAKE.register((modelManager, existingModels, loader) -> {
            Map<String, BakedModel> meshes = new HashMap<>();
            for (ResourceLocation mesh : OpenMeshes.MESH.keySet()) {
                if (!mesh.getPath().equals("none"))
                    meshes.put(mesh.getPath(), loader.bake(new ResourceLocation(mesh.getNamespace(), "block/" + mesh.getPath() + "_mesh"), BlockModelRotation.X0_Y0));
            }
            ModelSwapper.getAllBlockStateModelLocations(OpenBlocks.SIEVE).forEach(m -> {
                existingModels.put(m, new SieveModel(existingModels.get(m), meshes));
            });
        });
    }
}
