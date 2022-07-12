package me.alphamode.exnihiloabsentia.client;

import me.alphamode.exnihiloabsentia.ModBlockEntities;
import me.alphamode.exnihiloabsentia.ModBlocks;
import me.alphamode.exnihiloabsentia.blocks.entity.InfestedLeavesBlockEntity;
import me.alphamode.exnihiloabsentia.client.models.SieveModel;
import me.alphamode.exnihiloabsentia.client.renderers.BarrelRenderer;
import me.alphamode.exnihiloabsentia.client.renderers.InfestedLeavesBlockEntityRenderer;
import me.alphamode.exnihiloabsentia.client.renderers.SieveBlockEntityRenderer;
import me.alphamode.exnihiloabsentia.meshes.ModMeshes;
import me.alphamode.exnihiloabsentia.util.Color;
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
public class ExNihiloAbsentiaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.SIEVE, SieveBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.BARREL, BarrelRenderer::new);

        BlockEntityRendererRegistry.register(ModBlockEntities.INFESTED_LEAVES, InfestedLeavesBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(), ModBlocks.INFESTED_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ModBlocks.SIEVE);
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            Optional<InfestedLeavesBlockEntity> blockEntity = blockAndTintGetter.getBlockEntity(blockPos, ModBlockEntities.INFESTED_LEAVES);

            if(blockEntity.isEmpty())
                return Color.WHITE.toInt();

            Color originalColor = new Color(Minecraft.getInstance().getBlockColors().getColor(blockEntity.get().getLeavesType(), blockEntity.get().getLevel(), blockPos, 0));
            return Color.average(originalColor, Color.WHITE, (float) Math.pow(blockEntity.get().getProgress(), 2)).toInt();
        }, ModBlocks.INFESTED_LEAVES);
        ModelBakeEvent.ON_MODEL_BAKE.register((modelManager, existingModels, loader) -> {
            Map<String, BakedModel> meshes = new HashMap<>();
            for (ResourceLocation mesh : ModMeshes.MESH.keySet()) {
                if (!mesh.getPath().equals("none"))
                    meshes.put(mesh.getPath(), loader.bake(new ResourceLocation(mesh.getNamespace(), "block/" + mesh.getPath() + "_mesh"), BlockModelRotation.X0_Y0));
            }
            ModelSwapper.getAllBlockStateModelLocations(ModBlocks.SIEVE).forEach(m -> {
                existingModels.put(m, new SieveModel(existingModels.get(m), meshes));
            });
        });
    }
}
