package me.alphamode.openskies.client;

import me.alphamode.openskies.OpenBlockEntities;
import me.alphamode.openskies.OpenBlocks;
import me.alphamode.openskies.blocks.entity.InfestedLeavesBlockEntity;
import me.alphamode.openskies.client.models.InfestedLeavesModel;
import me.alphamode.openskies.client.models.SieveModel;
import me.alphamode.openskies.client.renderers.BarrelRenderer;
import me.alphamode.openskies.client.renderers.SieveBlockEntityRenderer;
import me.alphamode.openskies.meshes.OpenMeshes;
import me.alphamode.openskies.util.Color;
import me.alphamode.star.client.models.ModelSwapper;
import me.alphamode.star.events.client.ModelBakeEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class OpenSkiesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(OpenBlockEntities.SIEVE, SieveBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(OpenBlockEntities.BARREL, BarrelRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(), OpenBlocks.INFESTED_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), OpenBlocks.SIEVE);
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            Optional<InfestedLeavesBlockEntity> blockEntity = blockAndTintGetter.getBlockEntity(blockPos, OpenBlockEntities.INFESTED_LEAVES);

            if(blockEntity.isEmpty())
                return Color.WHITE.toInt();

            Color originalColor = new Color(Minecraft.getInstance().getBlockColors().getColor(blockEntity.get().getLeavesState(), blockEntity.get().getLevel(), blockPos, 0));
            return Color.average(originalColor, Color.WHITE, (float) Math.pow(blockEntity.get().getProgress(), 2)).toInt();
        }, OpenBlocks.INFESTED_LEAVES);
        ModelBakeEvent.ON_MODEL_BAKE.register((modelManager, existingModels, loader) -> {
            Map<String, BakedModel> meshes = new HashMap<>();
            for (ResourceLocation mesh : OpenMeshes.MESH.keySet()) {
                if (!mesh.getPath().equals("none"))
                    meshes.put(mesh.getPath(), loader.bake(new ResourceLocation(mesh.getNamespace(), "block/" + mesh.getPath() + "_mesh"), BlockModelRotation.X0_Y0));
            }
            ModelSwapper.getAllBlockStateModelLocations(OpenBlocks.SIEVE).forEach(m -> {
                existingModels.put(m, new SieveModel(existingModels.get(m), meshes));
            });

            ModelSwapper.getAllBlockStateModelLocations(OpenBlocks.INFESTED_LEAVES).forEach(m -> {
                existingModels.put(m, new InfestedLeavesModel(existingModels.get(m)));
            });
        });
    }
}
