package me.alphamode.exnihiloabsentia.data;

import me.alphamode.exnihiloabsentia.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootGenerator extends FabricBlockLootTableProvider {
    protected ModLootGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        dropSelf(ModBlocks.STONE_BARREL);
        dropSelf(ModBlocks.WOOD_BARREL);
        dropSelf(ModBlocks.SIEVE);
    }
}
