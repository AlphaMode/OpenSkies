package me.alphamode.exnihiloabsentia.data;

import com.google.gson.JsonElement;
import me.alphamode.exnihiloabsentia.ExNihiloAbsentia;
import me.alphamode.exnihiloabsentia.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class AbsentiaModelGenerator extends FabricModelProvider {
    public AbsentiaModelGenerator(FabricDataGenerator generator) {
        super(generator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        itemGen.generateFlatItem(ModItems.BONE_CROOK, ModelTemplates.FLAT_ITEM);

        pebbleItem(ModItems.STONE_PEBBLE, itemGen.output);
        pebbleItem(ModItems.GRANITE_PEBBLE, itemGen.output);
        pebbleItem(ModItems.DIORITE_PEBBLE, itemGen.output);
        pebbleItem(ModItems.ANDESITE_PEBBLE, itemGen.output);

        itemGen.generateFlatItem(ModItems.OAK_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.SPRUCE_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.BIRCH_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.JUNGLE_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.ACACIA_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.DARK_OAK_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.CACTUS_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.SUGAR_CANE_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.CARROT_SEEDS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.POTATO_SEEDS, ModelTemplates.FLAT_ITEM);
    }

    private static void pebbleItem(Item pebble, BiConsumer<ResourceLocation, Supplier<JsonElement>> output) {
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(pebble), new TextureMapping().put(TextureSlot.LAYER0, new ResourceLocation(ExNihiloAbsentia.MOD_ID, "item/pebble")), output);
    }
}
