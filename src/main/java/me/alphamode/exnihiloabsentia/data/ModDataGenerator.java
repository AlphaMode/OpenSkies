package me.alphamode.exnihiloabsentia.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(AbsentiaModelGenerator::new);
        generator.addProvider(ModBlockTagsGenerator::new);
        generator.addProvider(ModLootGenerator::new);
    }
}
